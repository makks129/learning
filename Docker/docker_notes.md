## Intro
3 innovations of Docker:
- images (build)
- registry (ship)
- containers (run)

3 reasons containers exist:
- isolation (better isolation in a single OS)
- environment (reduces env variances)
- speed (as in the speed of business)
  Docker allows you to faster:
  - develop
  - build
  - test
  - deploy


## Containers

alpine - minimal dist of linux (only 4mb), much smaller than ubuntu, ideal for containers,

## Networking

All containers are connected to a private virtual network (VN) `bridge`
All containers on a VN can talk to each other without -p
Best practice is to create a new VN for each app

__Drivers__
- bridge - default docker network, connected to host
- host - directly connects to host
- none - disconnected from host
- overlay - network used for Swarm (?)

`docker network create my_app_net` - create you own network (default driver: bridge)

### DNS

Docker daemon has a built-in DNS server
Containers shouldn't rely on IP addresses to talk to each other (unreliable as IP might change)
Container names are used as network names for containers

### Persistence

Containers are meant to be immutable and ephemeral (to be more scalable, easier to maintain, etc)
When persistent data is necessary Docker has 2 solutions:
- Volumes - special location outside of container UFS
- Bind Mounts - link container path to host path

#### Volumes

How to create:
- with `VOLUME` in Dockerfile
- `docker container run -v` specify a volume when running a container
- `docker volume create` explicitly create ahead of time

#### Bind Mount

Maps host file/dir to a container file/dir
As it's host-dependent it cannot be created in Dockerfile, must be at `docker container run`
To create
- `docker container run -v` specify a bind mount when running a container, same as volume but will create a bind mount if first arg starts with `/`, e.g. `-v /path/on/host:/path/in/container`




## Images

Images don't have names, they are reffered to by: `user/repo:tag`

### Layers

`docker image history` shows history of image layers
In order to store changes of lower level in higher layers, docker uses COW (copy-on-write) technique to only store the diff in the higher layer, so files in lower layer are actually not changed

`docker image inspect`

### Dockerfile

`FROM` - start of any Dockerfile
`ENV` - setting env vars
`EXPOSE` - by default no ports are exposed, they need to be exposed explicitly
`WORKDIR` - change dir (basically cd command)
`CMD` - required command to be launched in the container. Note: CMD might already be specified in FROM image

Note: order of commands matters a lot because when one layer is rebuilt all following layers need to be rebuilt as well. So thing that change the least should go to the top, the most - to the bottom

Note: logging is handled by Docker itself, all we need to do is to make sure logs are spit into stdout and stderr (e.g. linking nginx logs to stdout `RUN ln -sf /dev/stdout /var/log/nginx/access.log`)

#### Best practices
Maturity model:
- make it start
- make it log all things to stdout/stderr
- make it documented in file
- make it works for others
- make it lean
- make it scale

Patterns:
- `FROM` - never use :latest, use concrete versions
- specify versions of other software in `ENV` right after `FROM`
- if you use package managers (npm, apt-get, etc), specify concrete versions for packages (for the ones particular to your app, so for example for php but not for curl)

Your nodes and containers need to be replaceable. Don't install anything but docker and swarm on them, don't make them unique or have any state ("don't turn cattle into pets")

## Permissions / Users / Access

__Troubleshooting users__

Use the command ps aux in each container to see a list of processes and usernames. The process needs a matching user ID or group ID to access the files in question.

Find the UID/GID in each containers `/etc/passwd` and `/etc/group` to translate names to numbers. You'll likely find there a miss-match, where one containers process originally wrote the files with its UID/GID and the other containers process is running as a different UID/GID.

Figure out a way to ensure both containers are running with either a matching user ID or group ID. This is often easier to manage in your own custom app (when using a language base image like python or node) rather than trying to change a 3rd party app's container (like nginx or postgres)... but it all depends. This may mean creating a new user in one Dockerfile and setting the startup user with USER. (see USER docs) The node default image has a good example of the commands for creating a user and group with hard-coded IDs:
```
RUN groupadd --gid 1000 node && useradd --uid 1000 --gid node --shell /bin/bash --create-home node
USER 1000:1000
```

## NodeJS
[Node.js + Docker for Showing Good Defaults in Using Node.js with Docker](https://github.com/BretFisher/node-docker-good-defaults)


## Swarm

Node - physical machine
Container - instance of an application running on 1 node
Service - 1 application that can have several replicas of the same container running on different nodes
stack - collection of services + volumes + networks + secrets

`docker swarm init`

__Swarm example: cat/dog app__
Run within the manager node
```
docker network create -d overlay backend
docker network create -d overlay frontend
docker service create --name vote -p 80:80 --network frontend --replicas 2 bretfisher/examplevotingapp_vote
docker service create --name redis --network frontend redis:3.2
docker service create --name worker --network frontend --network backend bretfisher/examplevotingapp_worker
docker service create --name db --mount type=volume,source=db-data,target=/var/lib/postgresql/data --network backend --replicas 1 -e POSTGRES_HOST_AUTH_METHOD=trust postgres:9.4
docker service create --name result -p 5001:80 --network backend bretfisher/examplevotingapp_result
```

Swarm has in-built LB which will find an appropriate node even when accessing a node which does not serve the particular app.

### How nodes work
[How nodes work](https://docs.docker.com/engine/swarm/how-swarm-mode-works/nodes/)
[Raft Consensus Algorithm](https://raft.github.io/)
[Everything You Thought You Already Knew About Orchestration](https://www.youtube.com/watch?v=Qsv-q8WbIZY&ab_channel=Docker)

NB! Manager nodes should not be used for real work as Raft requires more resources.

#### Fault tolerance
The idea is not to have even number of managers because there is no fault tolerance benefit at the same level of quorum.
[Maintain the quorum of managers](https://docs.docker.com/engine/swarm/admin_guide/#maintain-the-quorum-of-managers)
[Add manager nodes for fault tolerance](https://docs.docker.com/engine/swarm/admin_guide/#add-manager-nodes-for-fault-tolerance)
[Pros and Cons of running all Docker Swarm nodes as Managers?](https://stackoverflow.com/questions/48853473/pros-and-cons-of-running-all-docker-swarm-nodes-as-managers)

### Stacks

Compose for Swarm

### Secrets

To create an external secret:
```
services:
  some-service:
    secrets:
      - secret-name
    environment:
      - SECRET_ENV_VARIABLE_FILE=/run/secrets/secret-name
secrets:
  secret-name:
    external: true
```
and then do
`echo "secretInfo" | docker secret create secret-name -`
NB! Don't forget to clean bash history!

Outside of Swarm (in docker compose for dev) secrets are not secure but they can also be used.
Instead of echoing a secret externally just add a file with the same name next to docker-compose file
```
services:
  some-service:
    secrets:
      - secret-name
    environment:
      - SECRET_ENV_VARIABLE_FILE=/run/secrets/secret-name
secrets:
  secret-name:
    file: ./some-secret-info.txt
```

### Service updates

Rolling replacement of containers in a service
Limits downtime

Update either dynamically with docker commands or by updating and deploying changed docker-compose file
Examples:
- `docker service update --env-add NODE_ENV=production --publish-rm 8080`, can update (add/rm) several things in 1 command
- `stack deploy`, if stack is already running, will issue an update

### Rebalancing

Sometimes doing updates on services can result in some nodes having much fewer containers running then other nodes.
Docker won't do balancing by itself but it's possible to rebalance it manually by issuing an empty update on a few services, then Docker will pick least used nodes for those thus rebalancing the swarm.
`docker service update --force service-name`

### Healthcheck

Docker will `exec` the command in a container, it expects `exit 0` (OK) or `exit 1` (Error)
3 container states:
- starting - will run every 30s
- healthy - if command returns 0
- unhealthy - if command returns 1
Service updates depend on this healthcheck to start updating next containers
Params:
- `--interval=DURATION` (default: 30s) -- how often it will run healthcheck
- `--timeout=DURATION` (default: 30s) -- how long it will wait for healthcheck request to finish before erroring
- `--start-period=DURATION` (default: 0s) -- grace first interval period (will still do healthchecks but won't use unhealthy state for this amount of time)
- `--retries=N` (default: 3) -- will try healthcheck N number of times before considering unhealthy

Healthcheck command example:
`curl -f http://localhost/health || exit 1` (or `|| false`)

### Setting up Docker Compose / Swarm for dev, test and prod
https://www.udemy.com/course/docker-mastery/learn/lecture/6797114#overview


## Container Registries

Choices:
- Docker Hub
- Docker Enterprise Edition DTR (Docker Trusted Registry)
- Docker Registry (own privately hosted registry)
- Quay https://quay.io/
- GCD https://cloud.google.com/container-registry/
- AWS https://aws.amazon.com/ecr/
- Azure https://azure.microsoft.com/en-us/products/container-registry/
- GitLab https://about.gitlab.com/blog/2016/05/23/gitlab-container-registry/


## Security
[Study materials](https://www.udemy.com/course/docker-mastery/learn/lecture/16846490#overview)
[Read](https://github.com/BretFisher/ama/discussions/150)




## Kubernetes

Container orchestrator
Run on top of Docker as a set of APIs in containers
Provides API/CLI to manage containers across servers
Cloud providers provide it for you (some have their own distributions of K8s)

[Certified K8s distributions](https://kubernetes.io/partners/#conformance)

### Basics

- Node - single server in k8s cluster
- Kubelet - k8s agent on a node
- Control plane (all of the masters nodes) - set of containers that manage the cluster: API server, scheduler, controller manager, etcd, ...
- Pod - one or more containers running together on one node (basic unit of deployment, containers are always in pods)
- Controller - object for creating/updating pods. Types:
  - Deployment
  - ReplicaSet
  - StatefulSet
  - DeamonSet
  - Job
  - CronJob
  - etc. (can be 3rd party type of controllers)
- Service - network endpoint to connect to a pod (not the same as in Swarm/Docker), needs to be added on top of a pod
- Namespace - filtered group of objects in a cluster
- Secrets, ConfigMaps, etc.

Inside a master:
- etcd - distributed kv storage system (uses Raft)
- API - way to talk to the cluster
- scheduler (how containers are places on the nodes in objects called pods)
- controller manager
- CoreDNS
- etc.

Inside a worker node:
- kubelet
- kube-proxy - controls networking

### 3 approaches to manage K8s

- Imperative commands: run, expose, scale, edit, create deployment, etc.
  - best for dev
- Imperative objects: create -f file.yml, replace, delete, etc.
  - file per command
  - good for prod small envs
- Declarative: apply -f file.yml
  - best for prod
  - easier to automate
  - harder to understand what will change

Note: Don't mix these approaches

### Building YAML

kind + apiVersions is used to determine which resource and version to use

__`kind`__
Check `kubectl api-resources`, `kind` column

__`apiVersion`__
Check `kubectl api-versions`, `kind` column

__`metadata`__
`name` - required
labels - for selecting, grouping, filtering resources

__`spec`__
Description of this resource
`kubectl explain <kind> --recursive` - show all the keys supported
`kubectl explain <kind>.spec` - show doc for spec
`kubectl explain deployment.spec.template.spec.volumes` (e.g.) - show doc for deeper levels of spec

label selectors - for telling services and deployments with pods are theirs

### Services

__ClusterIP__ (default)
Only within the cluster (one set of pods talking to another set of pods)
- Own DNS address in CoreDNS Control Plane
- Single, internal virtual IP allocated
Allows other pods to talk to this pod through this service

__NodePort__
For something outside the cluster to talk to this service through IP addresses on the nodes themselves
- High port allocated on each node
- Port is open on every node's IP
- Anyone can connect (if they can reach node)
- Other pods need to be updated to this port

__LoadBalancer__
Mostly used in the cloud
Only for traffic coming into your cluster from external source
- Controls a LB endpoint external to the cluster
- Only available when infra provider gives you a LB (like AWS LB, etc)
- Creates NodePort+ClusterIP services, tells LP to send to NodePort

These 3 types are additive, each creating the one above. E.g. if you create NodePort, ClusterIP will be created automatically

__ExternalName__
For talking from inside your cluster to external services
- Adds CNAME DNS record to CoreDNS only
- Not used to Pods, but for giving pods a DNS name to use for something outside k8s

### DNS
Default: CoreDNS
Like in Swarm k8s has DNS-based service discovery

### Ingress
...

### Storage
Volumes
- tied to lifecycle of a pod
- all containers in a pod share volumes
PersistentVolumes
- on a cluster level (outside a pod)
- multiple pods can share them

### Namespaces and Context
Namespaces limit scope (aka "virtual clusters")
Namespaces are not related to Docker/Linux namespaces
Context changes kubectl cluster and namespace (e.g. for managing several clusters from 1 machine)

















































---

## Testing
[ChaosMonkey](https://netflix.github.io/chaosmonkey/)
