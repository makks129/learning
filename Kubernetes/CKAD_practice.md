## TOPICS

[Curriculum](https://github.com/cncf/curriculum/blob/master/CKAD_Curriculum_v1.26.pdf)

__Core__
- ✅ KubeConfig
- ✅ API versions, groups, API server

__App design and build__
- ✅ Docker: images, containers
- ➡️ Labels, selectors, annotations
- ➡️ Taints and Tolerations
- ➡️ Node Selectors
- ➡️ Node Affinity
- ➡️ Jobs, CronJobs
- ➡️ Multi-container pod design:
	- ➡️ Init
	- ➡️ Sidecar
	- ➡️ Adapter
	- ➡️ Ambassador
- ➡️ Persistence:
	- ➡️ Volumes
	- ➡️ PV, PVC
	- ➡️ Storage classes
	- ➡️ Stateful sets

__App deployment__
- ➡️ Deployments, ReplicaSets
- ✅ Deployment strategies: rolling, blue/green, canary
- ➡️ Rollbacks
- ➡️ Helm

__App o11y and maintenance__
- ➡️ API versions, deprecations
- ➡️ Readiness Probe
- ➡️ Liveness Probe
- ➡️ Health checks
- ➡️ Logging
- ➡️ Monitoring
- ➡️ User provided tools to monitor K8s applications (?)

__App env, config and security__
- ➡️ CRDs
- ✅ Security Contexts
- ✅ Resources
- ✅ ConfigMaps
- ✅ Secrets
- ✅ Service Accounts
- ➡️ RBAC
- ➡️ Cluster roles
- ➡️ Admission control

__Service and networking__
- ➡️ Network policies
- ➡️ Services
- ➡️ Ingress


## Core

### kubectl basics

```sh
# See version of k8s
k get nodes

# Execute command in a container
k exec nginx -- curl google.com

# Enter into a container with specified shell
k exec -it nginx -- sh # type `exit` to exit

# Run some command in a pod once
k run x --image=nginx --restart=Never -it --rm -- echo "hello"
```

### KubeConfig

```sh
# View config
k config view

# Check current context
k config current-context

# Switch context
k config use-context my-ctx
```

### Namespaces

```sh
# Create ns
k create ns my-ns
```

### API versions

```sh
# List all api versions
k api-versions

# List all api resources (aliases, api versions, kind)
k api-resources
```
_Docs: Reference > API Overview_

### API groups

```sh
# Core start with /api
/api/v1/namespaces
# Named cluster level
/apis/GROUP/VERSION/*
# Named namespace level
/apis/GROUP/VERSION/namespace/NAMESPACE/*
```

### API server

```sh
# Access through kubectl proxy on localhost:8001
k proxy

curl localhost:8001 # all paths
curl localhost:8001/apis/apps/v1 # example
```


## App design and build

## Pods

```sh
# Write new pod definition to file
k run my-pod --image httpd:alpine -n my-ns $do > my-pod.yml

# Run pod with custom command
k run my-pod --image busybox --command -- sleep 3600 # --command will make sure sleep is not args but a command

# Check spec
k explain pod.spec --recursive

# Create pod from yml
k create -f my-pod.yml -n my-ns
```

## ConfigMaps

```sh
# Create a configmap
k create cm my-cm --from-literal key=value
```
```yml
# Reference env variable from cm
spec:
  containers:
  - image: nginx:alpine
    name: pod1
    env:
    - name: TREE1           # env var name
      valueFrom:            # reference value
        configMapKeyRef:    # from cm
          name: trauerweide # cm name
          key: tree         # cm key

# Mount cm to pod as volume
spec:
  containers:
  - image: nginx:alpine
    name: pod1
    volumeMounts:
    - name: whatever     		# mount volume with the same name
      mountPath: "/etc/path"    # at path

  volumes:                  	# define volume
    - name: whatever        	# name it whatever
      configMap:
        name: birke  	        # volume references cm
```
```sh
# Confirm env var is set up from cm
k exec pod1 -- env | grep TREE1

# Confirm cm volume is mounted to pod
k exec pod1 -- ls -la /etc/path
```

## Secrets

Similar to ConfigMaps

## Secure access to API server

- Authentication
- Authorization
- Admission Control

## Service Accounts

```sh
# Create SA
k create sa admin

# Make a TokenRequest to create a token (short-lived or with duration)
k create token admin --duration=1000h # admin is SA name
```
```yml
# Add SA to pod
spec:
	serviceAccountName: admin

# To create a token with no expiry manually create a Secret
apiVersion: v1
kind: Secret
metadata:
  name: admin-secret
  annotations:
    kubernetes.io/service-account.name: admin # controlplane will auto-create token for this secret
type: kubernetes.io/service-account-token
```

Binding SA to a Role with RoleBinding will authorize pod to make specific actions


## SecurityContext

```yml
# Define security context of pod
spec:
	securityContext:
		runAsUser: 1000
		runAsGroup: 2000
	containers:
		securityContext: # will override pod securityContext
			runAsUser: 1001
			runAsGroup: 2001
			capabilites:
				add: ... # add capabilities to this container
```

## Resources: requests and limits

```yml
# Specify resources for a container
spec:
	containers:
		resources:
			requests:
				cpu: 1
				memory: 100M
			limits:
				cpu: 0.5
				memory: 200M
``` 

## ReadinessProbe

```yml
# Add readiness probe to deployment definition
spec:
	template:
		spec:
			containers:
				- image: httpd:alpine
				  readinessProbe:
				  	exec:
				  		command:
				  			- sh
				  			- ...
				  	initialDelaySeconds: 10
				  	periodSeconds: 5
```

## Deployment strategies

### Rolling update

```yml
# Define rollout strategy in deploument
spec:
	strategy:
		rollingUpdate:
			maxSurge: 50%
			maxUnavailable: 0%
```
```sh
# Updating deployment will perform a rolling update
k edit deploy my-deployment
```

### Blue/Green

```yml
# Create 2 deployments with labels version:v1 and version:v2
# Switch service from v1 to v2
spec:
	selector:
		version: v1 # change to v2
```
```sh
# Scale down v1
k scale deploy my-dep-v1 --replicas 0
```

### Canary

```sh
# Create second deployment with same label to be selected by the servive, but with smaller amount of pods
# Scale down first deployment by that number of pods
k scale deploy my-dep-v1 --replicas 8 # if v2 has 2 replicas, then it would be a 20% canary deployment
```