# Kubernetes (Application Developer, CKAD)

Notes for Udemy course [Kubernetes Certified Application Developer (CKAD) with Tests](https://www.udemy.com/course/certified-kubernetes-application-developer)

NB! At the time of making these noted, the course is structured by the old CKAD curriculum. However the total body of materials cover the new curriculum as well.


## Core concepts

### Docker / containerd / CRI

__CRI__ - Container Runtime Interface
CRI was developed by k8s to support other container runtime environments (not only Docker that it supported in the beginning)

__ctr__ - native CLI for containerd
__nerdctl__ - is a Docker-like CLI for containerd
__crictl__ - CLI for CRI compatible container runtimes (developed and maintained by k8s community)), manually used only for debugging

### Docker

Docker container is running in a host in it's own namespace
Docker container processes will be visible from host namespace, but won't be visible from another docker container namespace
By default docker container processes are run as root user
This can be changed by specifying `--user` in `docker run` or in the docker image itself:
```dockerfile
FROM ubuntu
USER 1000 # will run container build with this image as user 1000
```
However root user within docker container is not exactly the same as the host root user, it has limited capabilites
Capabilities can be added or dropped by adding `--cap-add` or `--cap-drop` (e.g. `--cap-add MAC_ADMIN`) or `--priviledged` (to add all priviliges) to `docker run`

Same concepts can be configured in K8s (see below)

#### CMD vs ENTRYPOINT

`CMD` - default command, will be overridden if `docker run` specifies a different command (e.g. `docker run app echo 123` - instead of command in CMD will echo `123`)
`ENTRYPOINT` - command that will always be executed, additional `docker run` args will be treated like params to this function (e.g. `docker run app echo` - `echo` will become a param to ENTRYPOINT command)
`ENTRYPOINT` and `CMD` can be used together to specify a command and default argument:
```Dockerfile
ENTRYPOINT ["echo", "Hello"] # will always be executed
CMD ["World"]  							 # with "World" by default, but can be changed in docker run command
```

### Pods

__Pod__ - wrapper for a container
Smallest object in k8s
Pods contain only a single container of the same kind (1 pod = 1 container)), however it can contain several containers of different kinds (helper containers)
_Network:_ containers inside 1 pod can talk through localhost
_Storage:_ contaienrs inside 1 pod can share same storage space

### YAML

4 top level required fields:
```yml
apiVersion:
kind:
metadata:
spec:
```

### ReplicaSets

__ReplicaSet__ - a process that monitors the pods and that ensures that predefined number of pods are running across nodes at all times.

```yml
spec:
	template:
		[pod definition, without apiVersion and kind]
	replicas: [number]
	selector:
		matchLabels:
			[labels matching labels of a pod]
```

ReplicaSet manages pods specified in the template and also in selector
Template is used to create new pods in case some fail
Selector is used to match potentially existing pods (created before the ReplicaSet) and future pods

__Scaling__
- Change `replicas: #` and run `kubectl replace -f [.yml]`
- Run `kubectl scale --replicas=# -f [.yml]` or `kubectl scale --replicas=# replicaset [replicaSetName]` (but this will not permanently change .yml content!)

### Deployments

__Deployment__ - an object above ReplicaSet in k8s hierarchy that provides capabilities to update instances with rolling updates, undo, pause and resume changes, etc.

Deployment definition is the same as ReplicaSet but with `kind: Deployment`

### Namespaces

Namespaces separate resources
Resourceses in 1 namespace can refer to each other simply by their names
Services in other namespaces can be reached by appending namespace to their name
Resource can be limited for a namespace using ResourceQuota kind

------------------------------------------------------------------------------------

## Configuration

### Docker commands

Containers are made to run processes, container is alive as long as the process running in it is alive

### ConfigMaps

ConfigMaps are used to store pod definition data separately and centraly
ConfigMaps are created and then injected into a pod

__Create__
```yml
apiVersion: v1
kind: ConfigMap
metadata:
	name: app-config
data:
	KEY: value
	COLOR: blue # e.g.
```

__Inject__
1. Env
```yml
# some pod definition...
spec:
	containers:
		envFrom:
			- configMapRef:
				name: app-config
```
2. Single env
```yml
# some pod definition...
spec:
	containers:
		env:
			- name: COLOR
			  valueFrom:
			  	configMapKeyRef:
					name: app-config
					key: COLOR
```
3. Volume
```yml
# some pod definition...
spec:
	containers:
	volumes:
		- name: app-config-volume
		  configMap:
		  		name: app-config
```

### Secrets

Secrets are similar to ConfigMaps but data is stored in an encoded format
Notes:
- Secrets are encoded but NOT encrypted by default
- Secrets are not encrypted in etcd
- Never commit secret definition files
- Enable encryption at Rest (see below)
- Consider 3rd-party secret store providers (AWS Providers, etc)
- There are better ways of handling sensitive data like tools like Helm Secrets, HashiCorp Vault, etc.

Technical notes:
- Anyone with access to create pods/deployments in the same namespace can access secrets
- A secret is only sent to a node if a pod on that node requires it
- Kubelet stores the secret into a tmpfs so that the secret is not written to disk storage
- Once the Pod that depends on the secret is deleted, kubelet will delete its local copy of the secret data as well

Secrets are created and then injected into a pod

__Create__
```yml
apiVersion: v1
kind: Secret
metadata:
	name: app-secret
data:
	KEY: value # must be specified in encoded format
	PASSWORD: cSf9k2==
```
To encode a value:
`echo -n 'value' | base64` > dmFsdWU=
To decode a value:
`echo -n 'dmFsdWU=' | base64 --decode` > value

__Inject__
1. Env
```yml
# some pod definition...
spec:
	containers:
		envFrom:
			- secretRef:
				name: app-secret
```
2. Single env
```yml
# some pod definition...
spec:
	containers:
		env:
			- name: PASSWORD
			  valueFrom:
			  	secretKeyRef:
					name: app-secret
					key: PASSWORD
```
3. Volume
```yml
# some pod definition...
spec:
	containers:
		volumes:
			- name: app-secret-volume
			  secret:
			  		secretName: app-secret
```
When mounted as volumes each secret will be a file in the system
`ls /opt/app-secret-volume` > PASSWORD
`cat /opt/app-secret-volume/PASSWORD` > cSf9k2==

#### Enabled encryption at Rest

https://kubernetes.io/docs/tasks/administer-cluster/encrypt-data/

To enable encryption we need to create an `EncryptionConfiguration`
Example:
```yml
apiVersion: apiserver.config.k8s.io/v1
kind: EncryptionConfiguration
resources:
  - resources:
      - secrets
      - configmaps
      - pandas.awesome.bears.example
    providers: # top provider in the list will be used for encryption (so order matters!)
      - aescbc:
          keys:
            - name: key1
              secret: <BASE 64 ENCODED SECRET>
      - identity: {}
```
Follow the guide for complete setup

### K8s security

Security settings can be configured on container and/or pod level
Pod settings will be applied to all containers in the pod
In case of both - container settings will override pod settings

```yml
# some pod definition...
spec:
	# security settings on pod level
	securityContext:
		runAsUser: 1000
	containers:
		- name: ubuntu
		  image: ubuntu
		  # security settings on container level
		  securityContext:
				runAsUser: 1000
				capabilities: # capabilities can only be added to a container, not pod
					add: ["MAC_ADMIN"]
```

### ServiceAccounts

2 types of accounts in k8s:
- User accounts (used by humans)
- Service accounts (used by machines)

Creating a ServiceAccount creates a token (stored as secret object) which should be used for auth
The token is a JWT token

Every namespace has a `default` service account
`default` service account is automatically mounted to pods

```yml
# some pod definition...
spec:
	# specifying a custom service account to be mounted
	serviceAccountName: my-service-account
	# specifying to NOT mount default service account automatically (if needed)
	automountServiceAccountToken: false
```

__Changes since 1.22:__
Instead of default service account token a new token is generated through TokenRequest API and mounted as projected volume into the pod

__Changes since 1.24:__
Creating a service account no longer automatically creates a secret with token! Token will have to be explicitly created
Creating a non-expiring token (the old way) can still be done this way. But you should do it ONLY if you cannot use TokenRequest API to obtain a token
```yml
# some Secret definition...
metadata:
	name: my-secret
	annotations:
		kubernetes.io/services-account.name: my-service-account
```

### Resources

K8s scheduler schedules pods on nodes according to resource requirements and available resources
Resources are specified on containers

Resources:
- CPU
- MEM
- Disk

Defaults can be set via LimitRange 

Note on MEM:
- 1M = 1,000,000 bytes (same goes for K and G)
- 1Mi = 1,048,567 bytes (same goes for Ki and Gi)

Resources can be limited to avoid over-usage and suffocation

To change resource for a container:
```yml
# pod definition
spec:
	containers:
		- name: my-app
			resources:
					requests:
							cpu: 1
							memory: '1G'
					limits:
							cpu: 2
							memory: '2G'
```

In case of exceeding limit:
- CPU - throttling
- MEM - OOM

More:
- [My research](https://github.com/makks129/learning/blob/master/Kubernetes/k8s_resources_requests_limits.md)
- [Assign Memory Resources to Containers and Pods](https://kubernetes.io/docs/tasks/configure-pod-container/assign-memory-resource/)
- [Configure Default Memory Requests and Limits for a Namespace](https://kubernetes.io/docs/tasks/administer-cluster/manage-resources/memory-default-namespace/)
- [Configure Default CPU Requests and Limits for a Namespace](https://kubernetes.io/docs/tasks/administer-cluster/manage-resources/cpu-default-namespace/)


### Taints and Tolerations

Taints and tolerations are used to set restrictions on which pods can be scheduled on a node
- Taints are set on nodes
- Tolerations are set on pods
Taints and tolerations don't tell pods which nodes they should be scheduled on, they only tell which nodes can accept which pods

To taint a node: `kubectl taint node [nodeName] k=v:taint-effect`. Example: `... app=blue:NoSchedule`
To remove the taint: `kubectl taint node [nodeName] k:taint-effect-`. Example `... app:NoSchedule-` (see help for more)
To add a toleration:
```yml
# pod definition
spec:
	tolerations:
		- key: "app"
			operator: "Equal"
			value: "blue"
			effect: "NoSchedule"
```

Note on Master nodes: 
There is also always a Master node in the cluster, pods don't get scheduled on Master nodes because of a taint that is placed on Master nodes. This behavior can be chandged but best practice is not to deploy application workloads on a Master server.
See `kubectl describe node kubemaster | grep Taint`

Related topic: node affinity

### Node Selectors

Node Selectors are used to schedule pods on particular nodes
It uses labels to specify which pods should go on which nodes
Node Selectors can't have complex logic like use lable A OR B - for that we can use Node Affinity

Example: schedule a pod only on nodes labeled size=Large
```yml
# pod definition
spec:
	nodeSelector:
		size: Large # size=Large is a label on a node
```

### Node Affinity

Same purpose as Node Selectors

Example: schedule a pod on nodes labeled size=Large or size=Medium
```yml
# pod definition
spec:
	affinity:
		nodeAffinity:
			requiredDuringSchedulingIgnoredDuringExecution:
				nodeSelectorTerms:
					- matchExpressions:
						- key: size
							operator: In # there are other operators as well
							values:
								- Large
								- Medium
```

`requiredDuringSchedulingIgnoredDuringExecution` / `preferredDuringSchedulingIgnoredDuringExecution`
- if a pod is being created (`DuringScheduling` part)
	- if `required` and node with specified labels is not found, it won't be scheduled
	- if `preferred` and node with specified labels is not found, it will be scheduled on any available node
- if a pod is already running (`DuringExecution` part)
	- the only option is `ignore`, so if a node labels are changed and it doesn't match pod's Node Affinity anymore, the pod will continue to run

A combination of Taints and Tolerations + Node Affinity can be used to:
- restrict some nodes to receive unwanted pods (apply Taints to nodes)
- allow some pods to be scheduled on certain nodes (add Node Affinity)



## Multi-container pods

3 patterns of multi-container architecture:
- Sidecar
- Adapter
- Ambassador 
They are defined in the same way in pod definition: in the array of `spec.containers`

### InitContainers

initContainers is a configuration of a container that is run before the actual pod containers are run
It runs a process to completion, e.g. cloning some repo, setup some config, etc, and only then real containers run
Several initContainers are run sequentially in order
If any of initContainers fail the pod restarts until all of them succeed

More on initContainers: https://kubernetes.io/docs/concepts/workloads/pods/init-containers/



## Observability

Pod status (single value):
- Pending, ContainerCreating, Running, ...

Pod conditions (boolean array):
- PodScheduled
- Initialized
- ContainersReady
- Ready

### Readiness probe

- Until readiness probe check succeeds k8s will not consider the pod ready and won't sent traffic to it
- By default if pod is not ready after 3 probe attempts, it will stop


```yml
# pod definition
spec:
	containers:
		- name: app
			readinessProbe:
				# check http
				httpGet:
					path: /api/ready
					port: 8080
				# check tcp
				tcpSocket:
					port: 3306
				# execute a command
				exec:
					command:
						- cat
						- /app/is_ready
				# if container needs more time to warm up it can specified like so
				initialDelaySeconds: 10
				# specify how often to probe
				periodSeconds: 5
				# specify number of attempts to probe
				failureThreshold: 8
```

### Liveness probe

Liveness probe is configured to periodically test if the application within the container is actually healthy

```yml
# pod definition
spec:
	containers:
		- name: app
			livenessProbe:
				# same options as in readinessProbe
```

### Logging

`kubectl logs -f [podName]`

### Monitoring

Several open-source and proprietary solutions:
- prometheus
- elastic search
- datadog
- dynatrace

Metrics Server - basic in-memory monitoring solution
Kubelet - an agent running on each node
cAdvisor - subcomponent of kubelet, responsible for retrieveing performance metrics from pods and exposing them to the Metrics Server through kubelet API

Deploy Metrics Server:
- clone: `git clone https://githib.com/kubernetes-incubator/metrics-server.git`
- deploy: `kubectl create -f deploy/1.8+/`

View performance metrics with 
- nodes: `kubectl top node`
- pods: `kubectl top pod`



## Pod design

### Labels, selectors, annotations

__Labels__ are used to mark object so they could be uniquely identified
```yml
# pod definition
metadata:
	labels:
		key: value
		app: web-app
		function: frontend
```

__Selector__ are used to find objects that match labels
`kubectl get pods --selector app=web-app`
`kubectl get pods -l app=web-app,env=prod`

```yml
# replicaset definition
spec:
	selector:
		matchLabels:
			app: web-app # labels to find on pods
	template:
		metadata:
			labels:
				app: web-app # labels of pods
```

__Annotations__ are used to record other details for information purposes
```yml
# replicaset definition
metadata:
	annotations:
		buildVersion: 1.34
```

### Rolling updates and Rollbacks in Deployments

Creating a deployment triggers a rollout
New rollout creates new deployment revision (e.g. Revision 1)
Subsequent rollouts create new revisions (e.g. Revision 2)

__Deployment strategies__
- Recreate: destroy all pods and then create new ones
- Rolling update (default): destroy pods 1by1 and create new pods replacing destroyed ones 1by1

To perform a rolling update a deployment will create a new replicaset and will start scaling down the old replicaset 1by1 pod simultaneously scaling up new replicaset as new pods come up
Rollback (`kubecl rollout undo`) does the same thing but in reverse

See [here](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#rolling-update-deployment) about:
- `.spec.strategy.rollingUpdate.maxUnavailable`
- `.spec.strategy.rollingUpdate.maxSurge`

[Revision history limit](https://kubernetes.io/docs/concepts/workloads/controllers/deployment/#revision-history-limit)

Cheatsheet:
- see real-time rollout status: `kubectl rollout status deploy [name]`
- see rollout logs: `kubectl describe deploy [name]`
- see status of deployment pods: `kubectl get deploy [name]` (READY, UP-TO-DATE, AVAILABLE, etc)
- see pods with their statuses: `kubectl get pods`

### Jobs

Pods are created by k8s to be running indefinitely (`restartPolicy` is `Always`)
A ReplicaSet is used to run a set of pods at all times
A Job is used to run a set of pods to perform a given task to completion

Example
```yml
apiVersion: batch/v1
kind: Job
metadata:
	name: math-add-job
spec:
	completions: 3
	parallelism: 3 # by default pods are created sequentially; add parallelism to run them in parallel
	template:
		# pod definition
		spec:
			containers:
				- name: math-add
					image: ubuntu
					command: ['expr', '2', '+', '3']
			restartPolicy: Never
```

`completions` means successful completions, so if a pod fails it will not count towards this number

### CronJob

Example
```yml
apiVersion: batch/v1beta1
kind: CronJob
metadata:
	name: reporting-cron-job
spec:
	schedule: "*/1 * * * *"
	jobTemplate:
		# job definition
		spec:
			completions: 3
			parallelism: 3
			template:
				# pod definition
				spec:
					containers:
						- name: reporting-tool
							image: reporting-tool
					restartPolicy: Never
```
K8s will create a job and a pod for each cron job run


## Networking

### Services

__NodePort__ is used to listen to a port on a node and forward traffic to a port on a pod on that node
__ClusterIP__ creates a virtual IP inside a cluster to enable comm between different services
__LoadBalancer__ provisions a cloud provider's native load balancer (AWS, GCP, etc)

Services are not separate running processes but rather just a networking abstraction

When Service is created it will also create Endpoint objects that are used to keep track of IP address bindings between service and pods 

#### NodePort

Terms from the view point of a Service:
- TargetPort: port of a pod
- Port: port of a service
- NodePort: port of a node (range: 30000-32767)
- Cluster IP of the service: IP address of a Service inside a cluster

```yml
apiVersion: v1
kind: Service
metadata:
	name: my-service
spec:
	type: NodePort
	ports:
		- targetPort: 80 # if not specified, will be the same as port
			port: 80 # required
			nodePort: 30008 # if not specified, will be auto allocated from the range
	selector:
		# lables from pod definition to match specific pod in a node
		app: my-app
		type: frontend
```

Labels and selectors are used to tell which pod within the node should get connected via the Service.

Multiple pods on the same node:
If a node has several pods with the same labels (so if a Service's configuration matches several pods on this node) then the Service will automatically act as a built-in load balancer on that node, splitting traffic between the pods.
Service will use algorithm: Random for load balancing.

Multiple pods on different nodes:
The Service will automatically span across all the nodes in the cluster and map the TargetPort to the NodePort on all of them.
If pods are removed or added the Service (Endpoints object) is automatically updated to map the ports. 

NodePorts are not really used for production as they directly expose node's port externally which is not very secure. Insted either cloud provider's LoadBalacer is used or Ingress 

#### ClusterIp

ClusterIP Service is used to map different groups of pods to each other (e.g. FE pods to be able to connect to BE pods, BE pods to DB/cache pods, etc)

```yml
apiVersion: v1
kind: Service
metadata:
	name: backend
spec:
	type: ClusterIP # ClusterIP is the default type
	ports:
		- targetPort: 80
			port: 80
	selector:
		app: my-app
		type: backend
```

### Ingress

__Ingress controller__ is an L7 LB built into the cluster
- LB
- auth
- SSL
- url-based routing configration
Ingress resources - set of rules for an ingress controller

Ingress controllers:
- GCP HTTP(s) Load Balancer (GCE)
- Nginx
- Contour
- Haproxy
- Traefik
- Istio

An ingress controller with Nginx requires:
- Deployment of a special nginx ingress controller image
- Service (NodePort) to expose nginx to the outside world
- ConfigMap to feed configuration into nginx
- ServiceAccount to give the deployment correct permissions in the cluster

__Ingress__ object defines ingress resources

#### Ingress resources

1. Simple
```yml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
	name: ingress-feature1
spec:
	backend:
		serviceName: feature1-service
		servicePort: 80
```

2. By paths
It is also possible to define sets of __rules__ as ingress resources
Each rule will have its own configuration for routing etc
```yml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
	name: ingress-watch-listen
spec:
	rules:
		- http:
				paths:
					- path: /watch
						pathType: Prefix
						backend:
							service:
								name: watch-service
								port: 
									number: 80
					- path: /listen
						pathType: Prefix
						backend:
							service:
								name: listen-service
								port: 
									number: 80
```
Default backend service needs to be configured to handle all the other paths (e.g. 404)

3. By host
```yml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
	name: ingress-watch-listen
spec:
	rules:
	- host: watch.my-website.com
	  http:
			paths: # each host config may also have several paths
				-	backend:
					service:
							name: watch-service
							port: 
								number: 80
	- host: listen.my-website.com
	  http:
			paths:
				-	backend:
						service:
							name: listen-service
							port: 
								number: 80
```

If the cluster has deploys in several namespaces the best practice is to create an Ingress resource for each namespace

[__rewrite-target__](https://kubernetes.github.io/ingress-nginx/examples/rewrite/)
Example: when we are configuring Ingress resources routing we want `http://<ingress-service>:<ingress-port>/watch` to forward to `http://<watch-service>:<watch-port>/` but without `rewrite-target` by default it will forward to `http://<watch-service>:<watch-port>/watch`
```yml
metadata:
  annotations:
  	# Add rewrite-target to forward to `/` instead of the original `/...`
    nginx.ingress.kubernetes.io/rewrite-target: /
```

### NetworkPolicy

__NetworkPolicy__ allows control over ingress/egress traffic between the pods
By default k8s cluster has an "Allow All" policy, so any pod can connect to any other pod

Example:
```yml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
	name: db-policy
spec:
	podSelector:
			# Same as with services/replicasets and other k8s objects we match them to pods with labels
			matchLabels:
				role: db
	policyType:
		- Ingress # incoming traffic
		- Egress # outgoing traffic
	ingress:
		- from:
				# Rules are ||, but selectors within one rule are &&
				# Rule1: pod and namespace selectors
				- podSelector:
						matchLabels:
							name: backend-pod
					# To only allow specific namespace (by default matching pods from any namespace will be allowed)
				  namepaceSelector:
						matchLabels:
							name: prod
				# Rule2: ipBlock selector
				- ipBlock: # To ALLOW specific IP addresses (not block)
						cidr: 192.168.5.10/32
			ports:
				- protocol: TCP
					port: 3306
	egress:
		- to:
				# rules (same as in ingress)
		  ports:
				# port (same as in ingress)
```

Note: 

Some network solutions implemented on k8s clusters support NetworkPolicy, some don't:
- Support:
	- Kube-router
	- Calico
	- Romana
	- Weave-net
- Do not support:
	- Flannel

Some netpol examples:
https://github.com/ahmetb/kubernetes-network-policy-recipes/blob/master/01-deny-all-traffic-to-an-application.md


## State Persistence

### Volumes

Simple example
```yml
# pod definition
spec:
	containers:
		- name: ...
			image: ...
			# this node directory will be mounted as /opt directore inside the container
			volumeMounts:
				- mountPath: /opt
					name: data-volume
	# this pod also defined a directory on the node to store the data
	volumes:
		- name: data-volume
			hostPath:
				path: /data
				type: Directory
```
Obviously in case there are several nodes the data in `/data` dir will be different!
K8s supports different storage solutions (e.g. NFS, AWS, GCP, Azure, etc, etc)
Simple example of AWS as a storage solution
```yml
spec:
	volumes:
		- name: data-volume
			awsElasticBlockStore:
				volumeId: <volume-id>
				fsType: ext4
```

### Persistent Volumes

__PersistentVolume__ - centrally configured cluster-wide pool of storage that pods can claim (PVC) and access

```yml
apiVersion: v1
kind: PersistentVolume
metadata:
	name: pv-vol1
spec:
	accessModes:
		- ReadWriteOnce
	capacity:
		storage: 1G
	hostPath:
		path: /tmp/data # path on the node to store data (in real prod env a storage solution should be used instead)
```

__accessModes__ - how a volume should be mounted on the hosts
- ReadOnlyMany
- ReadWriteOnce
- ReadWriteMany

### Persistent Volume Claim

__PersistentVolumeClaim__ - a claim of a pod to use a PV
PV and PVC have 1-to-1 relationship: if PV has higher capacity than PVC needs, no other pods will be able to utilize the remaining capacity
If no PV is available, the PVC will remain in a pending state, until a PV becomes available to be bound to

Declare a PVC
```yml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
	name: my-claim
spec:
	accessModes:
		- ReadWriteOnce
	resources:
		requests:
			storage: 500M
```
Register PVC in the pod
```yml
# pod definition
spec:
	volumes:
		- name: my-data
			persistentVolumeClaim:
				claimName: my-claim
```

It's possible to specify (`persistentVolumeReclaimPolicy`) what happens to PV when PVC is deleted:
- `Retain` (default): it will remain until manually deleted and won't be available for reuse by other PVCs
- `Delete`: it will be deleted along with PVC
- `Recycle`: data will be removed and PV will be made available for other PVCs

### Storage Classes

If PV is created to use a cloud provider storage, we would still have to manually provision required capacity from the cloud provider. Storage Classes automate that
__StorageClass__ is used to automatically provision an physical storage on the cloud provider
When the PVC is created a StorageClass will automatically provision storage of required size, create a PV and bind PVC to it

Define a StorageClass
```yml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
	name: google-storage
provisioner: kubernetes.io/gce-pd # using GCE as storage provisioner
```
Register it in PVC
```yml
# PVC definition
spec:
	storageClassName: google-storage
```


## Stateful Sets

Pods in Deployments are transient, they have dynamic IPs and are deployed/recreated/terminated without particular order

#### Order
There are cases when ordering matters, like when deploying and managing a DB (e.g. MySQL)
In a trypical DB arch there is a master pod and slave pods, 1 slave replicating data from the master and then every next slave replicating data from the previous slave
In such cases when order of deployment matters we can use StatefulSets instead of Deployments
__StatefulSet__ ensures pods are started/recreated/terminated always in the same order

#### Networking
In the same example of a DB arch, master pod needs to be exposed for both writing and reading, whereas slave nodes are only used for read operations
__Headless Service__ is used to assign correct (static) dns name for other pods to be able to connect to a specific pod in a StatefulSet

#### Storage
Sometimes we might want all of the pods in a StatefulSet to claim the same PVs, but sometimes each pod (like in this DB example) has it's own data
In this case we need a separate PVC for each pod (for each of which a StorageClass will provide a separate PV)
In order to define a separate PVC for each pod we can use `spec.volumeClaimTemplates`


## Security

First line of defence: controlling access to kube-apiserver
__Authentication__ - who can access
__Authorization__ - what can they do

### Authentication

2 types of access to a cluster:
- for admin purposes
- to applications of the cluster

2 types of access for admin purposes:
- users (admins, devs)
- service accounts (bots)

kube-apiserver authenticates reqs
auth mechanisms:
- static password file
- static token file
- certs
- identity service (LDAP etc)

### KubeConfig

File with settings to authenticate agains a kube-apiserver
Default path: `$HOME/.kube/config`

```yml
apiVersion: v1
kind: Config
current-context: admin@production # default context to use
clusters: # clusters you have access to
	# specify cluster: server, cert-auth, etc
contexts: # bind together users and clusters like - admin@production
	# bind user to cluster (and optionally to a specific namespace)
users: # user accounts with which you have access to different clusters
	# specify user: client cert, key, etc
```

### API Groups

k8s APIs:
- core
- named `/apis`. API groups:
	- `/apps`
		- `/v1`
			- `deployments`. Verbs:
				- list
				- get
				- create
				- delete
				- etc
			- `replicasets`
			- etc
	- `/extensions`
	- `/neteworking.k8s.io`
	- etc


### Authorization

Authorization helps separate access to different functions of the cluster for different roles
Authorization modes:
- Node
	- Node authorizer manages auth for kubelet allowing it to make some reads and writes to kube api (internal access)
- ABAC (attribute-based auth)
	- Associating a user with a set of permissions (external access)
- RBAC (role-based auth)
	- Instead of directly associating a user with permissions with define a role and then associate users to that role
- Webhook
- AlwaysAllow (default)
	- Allow all access
- AlwaysDeny
	- Deny all access

`ExecStart=/usr/local/bin/kube-apiserver --authorization-mode=Node,RBAC,Webhook`
If several modes are specified the auth will happen in that order

kube-apiserver pod definition: `/etc/kubernetes/manifests/kube-apiserver.yaml`

#### RBAC

Roles are defined as objects of `kind: Role`
Each object in `rules` has 3 props: `apiGroups`, `resources`, `verbs`

Roles are associated with users with `RoleBinding` object

#### Cluster Roles

Resources are divided into:
- namespaced (pods, services, etc)
- cluster scoped (nodes, PVs, etc)

`Role` is namespaced, `ClusterRole` is cluster scoped

ClusterRole can also be used to authorize access to namespaced resources across __all__ namespaces


### Admission controllers

Next step of auth after authentication and authorization
Allows to enforce more rules on how the cluster is used
Example: only using specific image tags, disallowing certain capabilities, etc

View enabled admission controllers:
`kubectl exec kube-apiserver-controlplane -n kube-system -- kube-apiserver -h | grep enable-admission-plugins`

To enable/disable admission controllers add flags to kube-apiserver command:
`--enable-admission-plugins / --disable-admission-plugins`

2 types of admission controllers:
- Mutating (ran first) 
- Validating

#### Webhooks

In addition there are also mutating and validating admission webhooks that are ran against a custom webhook server
The request to the kube-apiserver is sent to those webhooks and if the response contains `allowed: true` then the req goes further

See `ValidatingWebhookConfiguration` and `MutatingWebhookConfiguration`



## API versions

- `/v1alpha1`
	- can be enabled with a flag for kube-apiserver `--runtime-config=batch/v2alpha1`
	- lacking tests
	- might be dropped
- `/v1beta1`
	- enabled for use
- `/v1`
	- enabled for use
	- stable

Preferred vs Storage versions:
- preferred is the version specified in yaml definition file
- storage version is the one to which an object will be converted and stored in etcd



## Custom Resource Definitions (CRD)

When a deployment (e.g.) is created it's state is stored in etcd
A deployment controller is a process that monitors etcd and makes changes to runtime deployment object (e.g. changes replicas, etc)

It's possible to create a custom resource and a custom controller for it
`CustomResourceDefinition` is a k8s object describing a definition of a custom resource

`k api-resources` to see all existing resources including CRDs
`k explain [resource]` to get more info on a resource
`k get crd` to get created CRDs

To manage CRD a custom controller needs to be created
See https://github.com/kubernetes/sample-controller

Operator Framework packages CRD and a custom controller together
See existing available operators: https://operatorhub.io/operator/percona-xtradb-cluster-operator



## Deployment Strategies

2 built-in strategies are:
- Rolling update
- Recreate

It's possible to create other strategies which are not built into deployments

### Blue/Green

Blue/Green strategy is when both deployments are fully deployed (old - Blue, new - Green) and at some point all traffic is switched from Blue to Green

Can be implemented in k8s using a Service which will match Blue deployment by some label (e.g. `v1`) and then switching the label to another (e.g. `v2`) to immediately start routing all traffic to Green deployment

### Canary

Canary deployment is when a small % of traffic is routed to a new deployment but most still goes to old one
At some point all traffic will be routed to new deployment using another strategy like Rolling update

Can be implemented in k8s using a Service which will match both deployments by a common label
There is no way to control % of traffic precicely except simply deploying fewer pods in a new deployment

Note that Sevice matches labels on pods, not deployments



## Helm

Helm is something like a package manager for k8s, a higher level abscraction over the whole applicaiton that k8s runs
It manages the whole cluster (yaml files and objects etc) as a whole

k8s object definitions can use placeholder variables to refer to specific values
Template:
```yml
spec:
	containers:
		- image: {{ .Values.image }}
```
These varibles are defined in `values.yaml` file
```yml
# values.yaml
image: nginx:1.2
```
Together the templates and values.yaml are called a __chart__

See charts created by other users in repositories (`helm repo list`):
- https://artifacthub.io/
- https://bitnami.com/

Install a chart on a cluster:
`helm install [releaseName] [chartName]`
