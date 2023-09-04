## TOPICS

[Curriculum](https://github.com/cncf/curriculum/blob/master/CKAD_Curriculum_v1.26.pdf)

__Core__
- ✅ KubeConfig
- ✅ API versions, groups, API server

__App design and build__
- ✅ Docker: images, containers
- ✅ Labels, selectors, annotations
- ✅ Taints and Tolerations
- ✅ Node Selectors
- ✅ Node Affinity
- ✅ Jobs, CronJobs
- ✅ Multi-container pod design
	- ✅ Init
	- ✅ Sidecar
	- ✅ Adapter
	- ✅ Ambassador
- ✅ Persistence
	- ✅ Volumes
	- ✅ PV, PVC
	- ✅ Storage classes
- ✅ Stateful sets

__App deployment__
- ✅ Pods
- ✅ Deployments, ReplicaSets
- ✅ Rollouts, rollbacks
- ✅ Deployment strategies
	- ✅ Rolling update
	- ✅ Blue/green
	- ✅ Canary
- ✅ Helm

__App o11y and maintenance__
- ✅ API versions, deprecations
- ✅ Readiness, Liveness Probes
- ✅ Debugging, logs, events

__App env, config and security__
- ✅ CRDs
- ✅ Security Contexts
- ✅ Resources
- ✅ ConfigMaps
- ✅ Secrets
- ✅ Service Accounts
- ✅ RBAC
- ✅ Admission control

__Service and networking__
- ✅ Services
- ✅ Network policies
- ✅ Ingress

---

## Core

### kubectl basics

```sh
# Docs
k explain pod.spec --recursive | grep -z pattern

# See version of k8s
k get nodes

# Global cli options
k options

# kube-apiserver.yaml location
/etc/kubernetes/manifests/kube-apiserver.yaml
# kube-apiserver config also here
ps aux | grep kube-apiserver
# kube-apiserver status
crictl ps

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

---

## App design and build

### Labels, selectors, annotations

```yml
metadata:
	labels: ...
	annotations: ...

spec:
	selector:
		matchLabel: ...
```
```sh
k run nginx --image nginx -l k=v
k get pod nginx -l k=v # select pod with label k=v
```
Annotaions are for arbitrary non-identifying metadata

### Taints and Tolerations

```sh
k taint node node1 key1=value1:NoSchedule
```
```yml
# pod
spec:
	tolerations:
		- key: key1
			operator: Equals / Exists / ...
			value: value1
			effect: NoSchedule / PreferNoSchedule / NoExecute
```

### Node Selectors

```yml
# node
metadata:
	labels:
		key: value
# pod
spec:
	containers: ...
	nodeSelector: # use node seletor
		key: value
	nodeName: node1 # OR just specify node
```

### Node Affinity

More sophisticated than nodeSelector
```yml
# pod
spec:
	affinity:
		nodeAffinity / podAffinity / podAntiAffinity:
			requiredDuringSchedulingIgnoredDuringExecution / preferredDuringSchedulingIgnoredDuringExecution: ...
```
See some examples [here](https://kubernetes-tutorial.schoolofdevops.com/advanced_pod_scheduling/)

### Jobs, CronJobs

```sh
# Create a job
k create job print-node-version --image node -- node -v
# Print logs for job pod
k logs print-node-version-... (pod name)
# Create job from cronjob
k create job --from cronjob/cj-name
```
```yml
spec:
	completions: 10
	parallelism: 10
	activeDeadlineSeconds: 30 # terminate if not finished in 30 sec
```
```sh
# Create a cronjob (print every minute)
k create cj print-date --image busybox --schedule "*/1 * * * *" -- date
```

### Multi-container pod design

```sh
# Run in a specific pod's container
k exec -it -c cont1 -- sh
```

#### Init

```yml
# pod
spec:
	initContainers: ...
```

#### Sidecar

2 pod.spec.containers running side by side

#### Adapter

Container in a pod that is used to perfom additional processing on the data from main container

#### Ambassador

Container used as a proxy to access other resources by the main container


### Persistence

#### Volumes

```yml
# mount empty dir volume
# pod
spec:
	containers:
		- name: cont
			image: ...
			volumeMounts:
				- name: vol
					mountPath: /some/path
	volumes:
		- name: vol
			emptyDir: {}
```
```yml
# mount config map
# pod
spec:
	containers:
		- name: cont
			image: ...
			volumeMounts:
				- name: cm-vol
					mountPath: /some/path
	volumes:
		- name: cm-vol
			configMap:
				name: my-cm
```

#### PV

```yml
# Create PV
# pv
spec:
	storageClassName: ...
	hostPath:
		path: ...
	capacity:
		storage: 10Gi
	accessModes:
	- ReadWriteOnce
	- ReadWriteMany
```

#### PVC

```yml
# Create PVC
# pvc
spec:
  storageClassName: ...
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: 4Gi
```
```yml
# Add PVC to pod
# pod
spec:
	containers:
		- name: app
			image: ...
			volumeMounts:
				- name: pvc
					mountPath: /some/path
	volumes:
		- name: pvc
			persistentVolumeClaim:
				claimName: pvc
```

#### Storage Classes

```yml
# StorageClass
provisioner: kubernetes.io/gce-pd # e.g.

# PVC
spec:
	storageClassName: storage-class
```

### Stateful sets

Used for stateful pods
- fixed names (names pod-0, pod-1, etc)
- individual DNS names per pod (required a Headless Service)
- ordered rollout/deletion
- `spec.volumeClaimTemplates` is used to define a separate PVC for each pod

---

## App deployment


### Pods

```sh
# Write new pod definition to file
k run my-pod --image httpd:alpine -n my-ns $do > my-pod.yml

# Run pod with custom command
k run my-pod --image busybox --command -- sleep 3600 # --command will make sure sleep is not args but a command

# Check spec
k explain pod.spec --recursive

# Create pod from yml
k create -f my-pod.yml -n my-ns

# Do something (add label) for several pods
k label pod pod{1..3} label=something
```

### Deployments, ReplicaSets

```sh
# Create a deployment
k create deploy webapp --image nginx --replicas 5

# Update image
k set image deploy webapp nginx=nginx:1.9.0
```

### Rollouts, rollbacks

```sh
k rollout history deploy webapp
k rollout status deploy webapp
k rollout undo deploy webapp --to-revision 2
k rollout pause deploy webapp
k rollout resume deploy webapp
```

### Deployment strategies

#### Rolling update

```yml
# Define rollout strategy in deployment
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

#### Blue/Green

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

#### Canary

```sh
# Create second deployment with same label to be selected by the service, but with smaller amount of pods
# Scale down first deployment by that number of pods
k scale deploy my-dep-v1 --replicas 8 # if v2 has 2 replicas, then it would be a 20% canary deployment
```

### Helm

```sh
helm repo list
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update

helm search repo
helm pull bitnami/nginx --untar
helm install my-nginx -f nginx/values.yaml nginx/
helm install my-nginx bitnami/nginx

helm show values bitnami/nginx | grep replica
helm install my-nginx bitnami/nginx --set replicaCount=5
```

---

## App o11y and maintenance

### API versions, deprecations

```sh
# kube-apiserver version
k version
# api version of resource
k explain ...
# all resources with versions
k api-resources
```

### Readiness, Liveness Probes

```yml
# pod
containers:
	- name: nginx
		readinessProbe:
			exec:
				command: ["sh", "..."]
			httpGet:
				path: /
				port: 80
			initialDelaySeconds: 10
			periodSeconds: 5
		livenessProbe:
			...
```

### Debugging, logs, events

```sh
k logs nginx
k describe po nginx # see Events
k events | grep -i error

k top nodes # if metrics-server is running
k top pod
```

---

## App env, config and security

### CRDs

```yml
apiVersion: apiextensions.k8s.io/v1 # get from explain or k api-resources
kind: CustomResourceDefinition
metadata:
  name: operators.stable.example.com # must be <names.plural><group>
spec:
  group: stable.example.com
  scope: Namespaced
  names:
    kind: Operator
    plural: operators
    singular: operator
    shortNames:
    - op
  versions:
  - name: v1
    served: true
    storage: true
    schema:
      openAPIV3Schema:
          type: object # !!! start with type and properties !!!
          properties:
            spec:
              type: object
              properties:
                email:
                  type: string
                name:
                  type: string
                age:
                  type: integer
```
Note: when creating object by a CRD its `apiVersion` will be `<group><versions.name>`

### SecurityContext

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

### Resources

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
```yml
apiVersion: v1
kind: LimitRange
metadata:
  name: lr
  namespace: test # limit is always set on a namespace
spec:
  limits:
  - type: Container # type of a resource
    max:
      memory: 500Mi
    min:
      memory: 100Mi
```

### ConfigMaps

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

### Secrets

Similar to ConfigMaps

### Secure access to API server

- Authentication (Service Accounts)
- Authorization (RBAC)
- Admission Control (Admission Controllers)

### Service Accounts

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

### RBAC

Not sure RBAC is needed for CKAD
In any case code examples are easy to get from official docs

### Admission control

```sh
# Get kube-apiserver configs to see enabled admission controllers 
cat /etc/kubernetes/manifests/kube-apiserver.yaml
# or
ps aux | grep kube-apiserver

# Enable/disable plugins by adding them to kube-apiserver.yaml
--enable-admission-plugins=...
--disable-admission-plugins=...
```

---

## Service and networking

### Basics on networking

```sh
k get pod -owide # see pods IP addresses

```

### Services

__ClusterIP__
```sh
k create service clusterip my-svc --tcp 80:8080

k expose pod nginx --name=my-svc --port 80 --target-port 3080 # pro: will auto apply selector labels for this pod

k run nginx --image nginx --port 80 --expose # create a pod AND a service

k get ep # get endpoints
```
__NodePort__
```sh
k create service nodeport my-np-svc --tcp: 80:8080 --node-port 31000 # if node-port omitted it will be generated
```

```sh
# Access deployment through a service
curl <service-name>.<namespace-name>.svc.cluster.local:<port>
```

### Network policies

```yml
# netpol
spec:
	podSelector:
		matchLabels:
			app: some-label
	policyTypes:
		- Ingress
		- Egress
	ingress:
		- from: ...
		- ports: ...
	egress:
		- to: ...
		- ports: ...

```

### Ingress

```yml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: world
  namespace: world
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  ingressClassName: nginx # k get ingressclass -- Ingress controller implementations use this field to know whether they should be serving this Ingress resource
  rules:
  - host: world.universe.mine
    http:
      paths:
      - path: /europe
        pathType: Prefix
        backend:
          service:
            name: europe
            port:
              number: 80
      - path: /asia
        pathType: Prefix
        backend:
          service:
            name: asia
            port:
              number: 80
```




--- 

## Extra

### Tmux

`tmux new`
`ctrl+b d` detach from session
`ctrl+b %` split vert
`ctrl+b "` split horiz
`ctrl+b <arrow-keys>` switch between panes
`ctrl+b [` to activate scroll move, scroll with arrow keys, `q` to quit
```