# SRE Book

https://sre.google/sre-book/table-of-contents/

- `Hope is not a strategy.`
- `Our job is to keep agility and stability in balance in the system.`
- `May the queries flow, and the pager stay silent.`


---


# Part I - Introduction

## The conflict

Conflict between product team and ops (sys admin) team: dev team wants to release new features, ops team wants stability.
Ops team scales linearly with service size.
SRE as an approach to automate sysadmin's work (to make the system automatic, not just automated).
SRE team is responsible for the availability, latency, performance, efficiency, change management, monitoring, emergency response, and capacity planning of their services.
SRE resolves this conflict with introduction of error budget.
100% reliability is a wrong target (e.g. enormous effort/cost to add last 0.001%). Right reliability target is defined by product. Difference (e.g. 0.01%) is the error budget.
Error budget is spent on quicker feature launch risks.
SREs and product developers aim to spend the error budget getting maximum feature velocity.

## Monitoring

3 types of monitoring output: alerts, tickets, logging.

## Emergency response

Reliability is a function of mean time to failure, MTTF, and mean time to repair, MTTR (how fast the system can be brought back up to health).
Best practices and "playbook" improves MTTR 3x.

## Demand, capacity, provisioning, efficiency

Demand forecasting and capacity planning can be viewed as ensuring that there is sufficient capacity and redundancy to serve projected future demand with the required availability.
SRE must be in charge or capacity planning and provisioning.
Demand/load (predict), capacity (provision), efficiency (measure).
As load increases up to capacity, speed decreases. SREs provision capacity at specified response speed.
Improving performance adds capacity, increasing efficiency.


---


# Part II - Principles


## Ch3 - Embracing Risk

SRE seeks to balance risk on unavailability w the goals of rapid innovation and efficient ops, to optimize user's happiness with features and performance.

### Managing Risk

Cost of reliability does not inc linearly.
Costs: res redundancy, engineering res reallocation.
Reliability target (e.g. 99.99%) is both a min and max: reliable system + thoughtful risktaking.

### Measuring Service Risk

First need to identify a metric to optimize.
Google consistently uses: `unplanned downtime` (usually window is a year).
Unplanned downtime - service availability (`nines`).
```
availability = uptime / (uptime + downtime)
```
[Availability table](https://sre.google/sre-book/availability-table/#appendix_table-of-nines)

As Google's systems are distributed instead of uptime they use `request success rate` (for a window, like a day).
```
availability = successful req / total req
```

### Risk Tolerance of Services

Determining availability target and how much risk can be tolerated.

#### Consumer services

Factors when assessing risk tolerance:
- What level of availability is required?
- Do different types of failures have different effects on the service?
- How can we use the service cost to help locate a service on the risk continuum?
- What other service metrics are important to take into account?

Defining target level of availability:
- What level of service will the users expect?
- Does this service tie directly to revenue (either our revenue, or our customers’ revenue)?
- Is this a paid service, or is it free?
- If there are competitors in the marketplace, what level of service do those competitors provide?
- Is this service targeted at consumers, or at enterprises?

Types of failures:
- Which is worse for the service: a constant low rate of failures, or an occasional full-site outage?

Cost:
- Is the cost of reaching 1 more 9 of availability > than additional revenue? (not always an easy question)

Other metrics to consider:
- Which other metrics are important for risk assessment? E.g. latency (not every service's latency needs to be the same), etc.

#### Infra services

Defining availability can be trickier as same infra can be used in different use cases.
Can be solved by partitioning infra into several clusters by level of service.

### Motivation for Error Budgets (EB)

Tensions (dev/ops conflict):
- Fault tolerance: how much fault to tolerate, so that service is stable but also has room to innovate
- Testing: how much testing is enough
- Push frequency: how often to allow pushing changes
- Canary duration and size: how big should canary be, how long to wait

Both sides need to agree on the metric.
They define a quarterly Error Budget (EB) based on SLOs.

Process:
- PMs defines SLO
- Actual uptime is measured by monitoring system
- Diff is the Error Budget
- As long as uptime > SLO (i.e. there is EB remaining) changes can be pushed.
- If EB is spent more resources are used to improve reliability (testing, dev, impr perf)
  - Can also be more subtle: releases are slowed down, rolled back when EB is close to being used up

Dev team can take more risks if EB is high, SRE team can stop launches if EB is depleted.
Innovation can also be increased by loosening SLOs.


## Ch4 - SLOs

### Terminology

**SLI** (indicator)
Quantitative measure of some aspect of the level of service.
Often aggregated - raw data is collected over a window and turned into rate/avg/percentile.
Examples:
- request latency
- error rate
- system throughput
- availability (as fraction of successful requests, called yield)
- durability (for storage systems)

Nines: 99% (2 nines), 99.95% (3 and a half nines)

**SLO** (objective)
A target value or range of values for a service level that is measured by an SLI.
SLOs set expectations about how a service will perform.
SLOs = `SLI ≤ target`, or `lower bound ≤ SLI ≤ upper bound`
Examples:
- avg req latency < 100ms

**SLA** (agreement)
An explicit or implicit (!) contract with your users that includes consequences (usually financial but can take other forms) of meeting/missing the SLOs they contain.
SRE doesn't usually get involved in setting SLAs but does get involved in avoiding triggering consequences.
Example:
Some services (like Google Search) don't have SLAs as they are used by wide public, however others (like Google for Work) do have explicit SLAs. Despite that both have SLIs and SLOs used to manage the service.

### Practice - SLIs

Broad categories for SLIs:
- User-facing serving systems (FEs, etc): availability, latency, throughput.
- Storage systems: latency, availability, durability.
- Big data systems: throughput, e2e latency.
- All systems: correctness (was right answer returned, right analysis done).

SLIs are often gathered on the BE (e.g. req latency) but client-side collection can sometimes be more relevant as a better metric of what user actually experiences.

#### Aggregation

Should RPS be measured every sec or 1 min avg? Averaging can hide spikes and can also hide long tail of slow reqs.
Most metrics are better thought of as _distributions_ (percentiles) rather than _averages_ (mean).
Example:
Measuring 50, 85, 95 and 99 percentile latencies rather than averages. [img](https://lh3.googleusercontent.com/G-Ljl-lx35hRTILL9pwj-ty2S5KE8piLPmx4wZSoaLpnfvw4WgdseYm-X5ZPCMNZS01eJmyZFwjHL4yK3ptj6WglYlX20Oi3dxA=s900)

Percentiles allows to see the shape of distribution: p99 - worst case, p50 (median) - typical case.
High variance in resp times affects typical user by effect of queuing. Usually slightly slower system is preferred to high variance. So some SREs only focus on high percentile values (p99 or p99.9) bc if they are good than median is good too.

#### Standardize Indicators

Standardize SLIs for each common metric. E.g. interval - aggregated over 1min, frequency - every 10sec, requests - all GET reqs, etc.

---
## On percentiles and averages
[How Percentile Approximation Works (and Why It’s More Useful Than Averages)](https://www.timescale.com/blog/how-percentile-approximation-works-and-why-its-more-useful-than-averages/)

**Median** - middle value, p50, half of data is above, half below
**Mean** - average, (sum / count)
**Mode** - most common value
In the normal distribution (ND), bell curve, there 3 are the same.

**Percentile** - a value where X % of data falls below the value (e.g. p10 is a value where 10% of data is < that value and 90% is >= that value)

Percentiles take into account the number of data points below/above certain value.
Average takes into account values themselves (divided by the number of data points).
So in ND (in symmetric distribution) p50 and avg is the same.

P50 - 50% of values are below/above
Avg - 50% of _weighted_ values are below/above

In long-tail distribution (LTD), which is asymmetric, median and avg will start to diverge. [img](https://www.timescale.com/blog/content/images/2021/09/Graph-7.jpg)
Long tail has enough large values to make _weighted_ 50% (avg) larger than the median, which only represents the amount of values. So the outliers (very rare but huge values) change the avg by a lot, whereas they don't affect the median (meaning most cases) that much. So the avg is not a good way to distinguish between outliers and real effects and can give odd results in LTD or other asymmetric distributions.

Change in the median means that a large _number of users_ is affected and it might need immediate attention.
Change in the avg might only mean there are just a few new outlier cases, which need investigation but might not be immediate.
---

### Practice - SLOs

Work from desired objectives ("what users care about", approximating that to what we can actually measure) backwards to indicators.

#### Defining

SLOs should specify how they are measured and conditions under which they are valid.
Example:
- 99% (averaged over 1 minute) of Get RPC calls will complete in less than 100 ms (measured across all the backend servers)
Or, if shape of distribution is important:
- 90% in < 1ms
- 99% in < 10ms
- 99.9% in < 100ms
If 2 factors (for different workloads/users) are important:
- 95% of _throughput_ clients' calls will complete in < 1s
- 99% of _latency_ clients' calls with payload < 1kb will complete in < 10ms

It's unrealistic to meet SLOs 100% of time, instead EB should allow to miss SLOs sometimes. EB should be tracked daily/weekly.
**EB is an SLO for meeting other SLOs.**

Defining SLOs:
- Define together with product/business (SLOs reflect what users care about)
- Keep it simple
- Avoid absolutes (ideals)
- Have as few SLOs as possible
- Start with loose SLOs and tighten them (not the opposite)
- SLO should be a force to drive innovation while keeping good quality

#### Control loop

- Monitor and measure SLIs
- Compare the SLIs to the SLOs, and decide whether or not action is needed
- If action is needed, figure out what needs to happen in order to meet the target
- Take that action

Example:
If req latency is increasing (1) and SLO will soon be missed (2), decide on action (3), fix the issue (4).
Without SLOs it wouldn't know if you need to take action.

#### SLO expectations

Publishing SLOs will give users expectations they need
- Make internal SLOs tighter
- Don't overachieve


## Ch5 - Eliminating Toil

**Toil** - repetitive, manual, tactical, linearly-scaling, automatable work ("work we don't like to do").
Reducing toil allows SRE to scale up sublinearly to system scale.
**Engineering work** - novel, innovative, permanent improvement, guided by strategy.


## Ch6 - Monitoring Distributed Systems

Monitoring reasons:
- analyzing long-term trends
- comparing over time
- comparing with different tech
- alerting
- building dashboards
- debugging

### Black-box / White-box

**Black-box** is symptom-oriented and is only about active problems (good for alerting, bad for predicting future issues).
**White-box** allows to investigate causes and detect underlying or potential problems.

### Four main metrics

**Latency**
Track both success and error latency, separately.

**Traffic**
How much demand is on the system (service-specific).
For a web service - RPS, for streaming service - network I/O rate, for kv storage - transactions/sec, etc.

**Errors**
Failed req rate: either just 500, or + 200 with wrong content, or + req that violate resp time constraints (e.g. > 1sec).

**Saturation**
How "full" is your service (memory, I/O, CPU, etc).
High-level load measurement: can service handle 10% more traffic, or 100%?
Saturation can be predicted (trend) and alerting can be configured to prevent potential failure.

### Tail

Both avg and percentile will not give enough data about tail reqs.
Monitor reqs _bucketed_ by latencies: 0-10ms, 10-100ms, 100-1000ms, etc.

### Simplicity

Monitoring systems that are too complex, have too many dependencies and too many metrics to keep track of, become fragile.

Guidelines:
- rules that catch incidents most often must be simple, predictable and reliable
- monitoring that is rarely used (< once a quarter) should be up for removal
- signals that are collected but are not used in dashboards/alerting should be up for removal

### Alerting

- Every alert should be actionable
- Every alerts should require intelligence (if it can be automated, it shouldn't be an alert)
- Alerts should be about a new problem that hasn't been seen before
- On-call should be able to react urgently (but not more than a few times a day, otherwise it's tiring)

Keeping data on alerts, their frequency per shift and dynamic in the long term is important for analyzing if it works well and where alerting should be improved.

### Conclusion

Healthy monitoring/alerting is easy to reason about.
Alerting focus on symptoms. Underlying systems should be monitored separately.
Monitoring should be paired with diagnostics (logs for debugging, etc).


## Ch7 - The Evolution of Automation at Google

### Value of automation

**Consistency**
Manual will always lead to mistakes, oversights, issues w quality, reliability, etc.
Automated procedures bring consistency.

**Platform**
- Automatic system can become a platform that can be extended, applied to more systems.
- Centralizes mistakes: a bug will be fixed once and forever (also consistency).
- Makes running tasks more effective (more frequently, at inconvenient times, etc)
- Can collects metrics on its performance and give new insights

**Faster repairs**
Reduces MTTR for common problems that are automated.

**Time saving**
Saves time also because it decouples operator from operation (anyone can run an automation later).

### Automation at Google

From manual work to maintain/turnup clusters to automations, to autonomous system - Borg, the predecessor to Kubernetes.

## Ch8 - Release Engineering

__SKIPPED__

## Ch9 - Simplicity

- Boring is good. Surprising and "interesting" is not good
- Essential complexity is inevitable, we want to reduce accidental complexity
- Strive to eliminate complexity
- Remove dead code. Keep future / just in case code code in VCS
- Minimal APIs
- Modularity
- Release simplicity


---


# Part III - Practices

Service reliability hierarchy pyramid (read from the base at the bottom):
- Product
  - Reliable product launches
- Development
  - Large-scale system design
- Capacity planning
  - Making sure we're properly using capacity we've built
- Testing + Release procedures
  - Once we know what tends to go wrong we can prevent it, e.g. by adding tests
- Postmortem / Root cause analysis
  - Analysing what went wrong and what can be done about it
- Incident response
  - Before the fix there needs to be a response: stopping the bleeding, redirecting traffic, etc
  - Effective response comes before solution to remediate the issue is implemented
- Monitoring
  - Monitoring is the base, without it you are blind


## Ch10 - Practical Alerting

__NO NOTES__

## Ch11 - Being On-Call

The ideal methodology in incident management strikes the perfect balance of taking steps at the desired pace when enough data is available to make a reasonable decision while simultaneously critically examining your assumptions.

The most important on-call resources are:
- Clear escalation paths
- Well-defined incident-management procedures
- A blameless postmortem culture























































.
