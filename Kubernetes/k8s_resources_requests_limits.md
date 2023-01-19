# Kubernetes resources: CPU/memory requests/limits

## Resources
https://highload.ru/moscow/2018/abstracts/3933
https://www.youtube.com/watch?v=4QyecOoPsGU
https://www.youtube.com/watch?v=nWGkvrIPqJ4
https://www.youtube.com/watch?v=8-apJyr2gi0
https://www.youtube.com/watch?v=xjpHggHKm78
https://www.youtube.com/watch?v=beWXWEimTxs
https://sysdig.com/blog/kubernetes-limits-requests/
https://nodejs.org/en/docs/guides/dont-block-the-event-loop/
https://engineering.fb.com/2018/07/19/production-engineering/oomd/
https://github.com/kubernetes/kubernetes/issues/51135


## Notes

CPU -> throttling
200m CPU limit => container can use 0.2s of CPU time per sec
making CPU limit 10 times lower (e.g. 1 -> 0.1) might make computation more 10x slower (it's not proportional)

Memory -> OOM kill

__Overcommit:__
limits > requests
pod will be scheduled but might not have available resources on that node
this is fine as long as demand of all pods in node < node capacity
otherwise OOMkills can mess it up
example:
memory.requests: 1G, memory.limits: 2G -> if node has > 1G pod will be scheduled but might be OOMkilled later if there is < 2G memory available on node


__CFS__ - Completely Fair Scheduler (process scheduler in Linux kernel)
`cfs_period_us` - period CFS uses (e.g. 100ms, hardcoded value in k8s)
`cfs_quota_us` - given quota for process (e.g. 20ms)
it means that a process can have 20ms out of every 100ms of a single-CPU time (for multi-cpu env, you just scale equivalent amounts)
it some req uses 20ms in the beginning of 100ms period and needs more than it will have to wait until next period, so it will take >100ms to process -- that's throttling

From k8s 1.12 CFS period can be changed to <100ms (e.g. 5ms) to improve scheduling
https://github.com/kubernetes/kubernetes/issues/51135

__Slack__ - difference between requested resources and actually used
__Stranded__ - e.g. available memory on the node which will never be used bc there is not enough CPU on this node anymore

[Kube-resource-report](https://codeberg.org/hjacobs/kube-resource-report)

[Kube-ops-view](https://codeberg.org/hjacobs/kube-ops-view)

__Scaling:__
- HPA
  - Adapter for custom events for HPA https://github.com/zalando-incubator/kube-metrics-adapter
- [Downscaler](https://codeberg.org/hjacobs/kube-downscaler)
All that exists in KEDA
