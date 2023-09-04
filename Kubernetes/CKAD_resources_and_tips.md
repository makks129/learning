## Resources

[Official CKAD page](https://training.linuxfoundation.org/certification/certified-kubernetes-application-developer-ckad/)
[Portal](https://openprofile.dev/)
[Schedule Exam](https://trainingportal.linuxfoundation.org/learn/course/certified-kubernetes-application-developer-ckad/exam/exam)
[Curriculum](https://github.com/cncf/curriculum/blob/master/CKAD_Curriculum_v1.26.pdf)

__Experiences__
- https://www.linkedin.com/pulse/my-ckad-exam-experience-atharva-chauthaiwale/
- https://medium.com/@harioverhere/ckad-certified-kubernetes-application-developer-my-journey-3afb0901014
- https://github.com/lucassha/CKAD-resources 

__Playgrounds__
- [Killer Shell CKAD](https://killercoda.com/killer-shell-ckad)
- [Play with k8s](https://labs.play-with-k8s.com/)

__Resources with questions to practice individual tasks__
- https://medium.com/bb-tutorials-and-thoughts/practice-enough-with-these-questions-for-the-ckad-exam-2f42d1228552
- https://github.com/dgkanatsios/CKAD-exercises
- https://killer.sh/course/preview/052229bd-1062-44a4-8aae-f50d0770165a

__Try real exams__
- [Mock Exam Series](https://kodekloud.com/courses/ultimate-certified-kubernetes-application-developer-ckad-mock-exam-series/) -- this is the best practice before the exam!
- [Kubernetes Exam Simulator](https://killer.sh/dashboard)

__Try exam remote environment__
- [CKS CKA CKAD Remote Desktop](https://killercoda.com/kimwuestkamp/scenario/cks-cka-ckad-remote-desktop)


## Tips

[Official tips on CKA/CKAD](https://docs.linuxfoundation.org/tc-docs/certification/tips-cka-and-ckad)

- Do easy questions first (at least 10q in 45min)
- Mark hard questions or ones taking too long for later

Curl in temp pod: `k run tmp --restart=Never --rm -it --image=nginx:alpine -- curl -m 5 <pod/ip>:<port>`

Show pods and services: `k get po,svc,ep -owide --show-labels`

Root privileges can be obtained by running `sudo −i`

`yq e` / `jq` for YAML/JSON processing

Nodes making up each cluster can be reached via ssh: `ssh <nodename>`


## Exam

### Prepare env

`alias k=kubectl`
`alias c=clear`
`export do="--dry-run=client -o yaml"` Usage: `$do`

Check `~/.vimrc`:
```
set nu
set expandtab
set tabstop=2
set shiftwidth=2
```

### On each question

- Copy-paste values from the task
- Check containers in pods are running
- Check files contain data you saved there
