# SRE Book

https://sre.google/sre-book/table-of-contents/

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


# Part II - Principles

## Embracing Risk

SRE seeks to balance risk on unavailability w the goals of rapid innovation and efficient ops, to optimize user's happiness with features and performance.

## Managing Risk

Cost of reliability does not inc linearly.
Costs: res redundancy, engineering res reallocation.
Reliability target (e.g. 99.99%) is both a min and max: reliable system + thoughtful risktaking.
