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

__Resources with questions to practice__
- https://medium.com/bb-tutorials-and-thoughts/practice-enough-with-these-questions-for-the-ckad-exam-2f42d1228552
- https://killer.sh/course/preview/052229bd-1062-44a4-8aae-f50d0770165a

__Try real exams__
- [Kubernetes Exam Simulator](https://killer.sh/dashboard)


## Tips

[Official tips on CKA/CKAD](https://docs.linuxfoundation.org/tc-docs/certification/tips-cka-and-ckad)

- Do easy questions first (at least 10q in 45min)
- Mark hard questions or ones taking too long for later


`KUBE_EDITOR=nano k edit ...` - to use nano instead of vi

`k run x --restart=Never --rm -i --image=nginx:alpine -- curl -m 5 <pod/ip>:<port>`

Root privileges can be obtained by running `sudo −i`

`jq` for YAML/JSON processing

Nodes making up each cluster can be reached via ssh: `ssh <nodename>`


## Flow

### Before 1st question

`alias k=kubectl`
`alias c=clear`
`export do="--dry-run=client -o yaml"` Usage: `$do`

Check `~/.vimrc`:
```
set expandtab
set tabstop=2
set shiftwidth=2
```

### On each question

`k config set-context --current --namespace=[namespace]` - to change context