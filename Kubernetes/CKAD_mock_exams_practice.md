## Mock exam series

[Mock Exam Series](https://kodekloud.com/courses/ultimate-certified-kubernetes-application-developer-ckad-mock-exam-series/) 

### TEST 1

6 - helm
	helm lint to validate
	helm install
	helm uninstall
3 - while true; do echo $(date -u) Hi I am from Sidecar container >> /var/log/index.html; sleep 5;done
7 - long task
18- recheck
20 - create endpoint object manually

### TEST 2

1_8 done
2_4 done
3_4 done
4_4 done
5_8 done
6_4 done (probably correct?)
7_4 done
8_4 done
9_4  mb done?
10_2 done
11_9 done (why weight 9??)
12_1 done
13_4 done
14_8 done
15_2 done
16_10 prob done
17_8 done
18_1 done
19_4 done
20_7 maybe done
You are requested to create a network policy named deny-all-svcn that denies all incoming and outgoing traffic to ckad12-svcn namespace.
Note: The namespace ckad12-svcn doesn't exist. Create the namespace before creating the Policy.

### TEST 3

1_8 done - will de error
2_4 done
3_4 done i think
4_4 not done, don't know command for pi in perl..
5_2 done
6_4 done but deploys are not starting, bug
7_4 done mb?
8_10 prob done, mb version is wrong
9_4 done
10_4 done
11_7 done
12_8 done
13_4 done
14_4 done
15_4 done
16_5 done
17_4 done
18_4 done
19_8 kinda done but pods are not starting (bug!)
20_4 not done, no ingress provided (bug!)

### TEST 4

1_8 not done
2_4 done
3_4 done
4_4 done
5_4 done
6_8 done
7_4 done
8_4 done
9_4 md done? not sure text is saved in correct format
10_4 done
11_7 done
12_4 done
13_1 done
14_8 done
15_2 done
16_10 done
17_4 done
18_1 done
19_8 done
20_7 done 

### TEST 5

1_2 done
2_4 done
3_4 done
4_10 done hopefully (if date command is correct)
5_4 not done, bug, no helm deployment or repo
6_4 done
7_4 done
8_8 done
9_2 done probably, if output file format is correct
10_4 done
11_9 done
12_8 done
13_4 done
14_4 done
15_2 done
16_7 done
17_8 done
18_4 done
19_4 done
20_4 done

### TEST 6

1_2 done
2_4 not done, idk the perl command for pi (["perl", "-Mbignum=bpi", "-wle", "print bpi(1024)"])
3_8 done probably, if cj schedule is correct
4_6 done
5_4 done (check how they did it!!)
6_2 done
7_4 done
8_10 done
9_2 done
10_8 done
11_5 done
12_8 done
13_2 done
14_4 done
15_4 done
16_7 done
17_8 done
18_1 done
19_1 done
20_10 done

### TEST 7

1_8 done
2_4 done
3_2 done
4_6 done
5_4 done
6_4 done
7_8 done
8_4 done
9_4 done
10_2 done
11_9 done
12_8 done
13_2 done
14_2 done
15_4 done
16_9 done
17_1 done
18_8 done
19_8 done
20_3 bug, ingress not deployed for the task, cannot be finished

### TEST 8

1_4 done
2_2 done
3_4 done
4_10 done
5_4 done
6_4 not done, bug. helm chart is not deployed (see how they did it!)
7_2 done
8_10 done
9_8 done
10_2 done
11_5 done
12_1 done
13_4 done
14_8 mb done
15_4 done
16_8 done
17_4 done
18_8 done
19_1 done
20_7 done

### TEST 9

1_2 done
2_4 done
3_4 done
4_10 done
5_4 not done, bug. helm chart is not deployed
6_4 not done, bug. helm chart is not deployed
7_2 done
8_10 done - check!
9_4 done
10_4 done
11_7 done 
12_? done
13_2 done
14_8 done
15_2 done (check)
16_9 done
17_8 done (check)
18_1 done
19_8 done
20_3 done

### TEST 10

1_2 done
2_8 done
3_4 done
4_6 done
5_8 done
6_4 done
7_4 not done, bug. helm chart is not deployed
8_4 done
9_2 done
10_4 done
11_9 done
12_4 done
13_4 done
14_8 done
15_8 done
16_1 done
17_8 done (check)
18_1 done
19_8 done (check)
20_3 done

---

## Mock exam on killer shell

[Kubernetes Exam Simulator](https://killer.sh/dashboard)

1 done
2 done
3 done
4_5 almost done, except last subtask
5_3 done
6_7 done
7_4 done
8_4 done (how do i tell team neptune?) ngnix
9_5 done
10_4 done
11_7 didn't finish last subtask with podman logs
12_8 done
13_6 done if i wrote pvc logs to file correctly
14_4 done
15_5 done
16_6 done
17_4 done
18_4 done
19_3 done
20_9 done
21_4 done
22_3 

105/112 finished before time was up


---

### Cheatsheet


vi

set expandtab
set tabstop=2
set shiftwidth=2


Helm

- helm repo ls 
- helm repo update RELEASE_NAME -n NS
- helm search repo REPO/nginx -n NS -l
- helm show values REPO/nginx | yq e
- helm upgrade RELEASE_NAME REPO/nginx -n NS --version=2.0.0 --set replicaCount=2
- helm install RELEASE_NAME . (or REPO/nginx)
- helm lint


Docker/Podman

sudo
- docker build -t NAME:TAG . (or -f Dockerfile)
- docker run -d --name NAME iMAGE
- docker ps
- docker logs

Probes
- initialDelaySeconds
- periodSeconds
- timeoutSecond
- failureThreshold

Env

- envFrom.configMapRef.name (always name)
- env.name/valueFrom.configMapKeyRef.name/key (always name)

Volumes

- volumes.name/secret.secretName


Tolerations

- tolerations.key/operator/value/effect

Job

- activeDeadlineSeconds
- backoffLimit


Formatting

- -o custom-columns='NAME:.metadata.name'
- --sort-by=metadata.name