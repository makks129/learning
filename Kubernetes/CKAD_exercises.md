## Exercises

➡️ Create a simple busybox dockerfile, build an image from it, run a container from this image, exec into the container
<details><summary>show</summary>
<p>

```sh
docker build -f Dockerfile .
docker run --name app -p 8080:80 busybox /bin/sh -c "sleep 10"
docker exec -it app /bin/sh
```

</p>
</details>

➡️ Set up rolling update with max 2 additional pods and 0 unavailable pods.
<details><summary>show</summary>
<p>

```yml
spec:
  strategy:
    rollingUpdate:
      maxSurge: 50%
      maxUnavailable: 0%
```

</p>
</details>

➡️ Perform a Blue/Green deployment
<details><summary>show</summary>
<p>

```yml
# deployment: app-v1
metadata:
  labels:
    version: v1

# deployment: app-v2
metadata:
  labels:
    version: v2

# create service
k expose deploy app-v1 --name app-svc --port 80 --target-port 80

# to switch edit svc
spec:
  selector:
    version: v2

# to check the switch
k get po -owide # see IPs
k get ep # before switch should match IPs of v1, after switch should match IPs of v2
```

</p>
</details>

➡️ Perform a canary deployment
<details><summary>show</summary>
<p>

```yml
# deployment: app-v1, replicas 10
metadata:
  labels:
    app: myapp # same label

# deployment: app-v2, replicas 2
metadata:
  labels:
    app: myapp # same label

# create service
k expose deploy app-v1 --name app-svc --port 80 --target-port 80 --selector app=myapp

# scale down v1
k scale deploy app-v1 --replicas 8
```

</p>
</details>

➡️ Update deployment image from `nginx:1.16` to `nginx:1.17` and then rollback
<details><summary>show</summary>
<p>

```yml
# deployment: app
spec:
  template:
    spec:
      containers:
      - image: nginx:1.16

# upgrade
k set image deploy app nginx=nginx:1.17
k rollout status deploy app

# rollback
k rollout undo deploy app
```

</p>
</details>

➡️ Add readiness probe with command `echo ready` with delay 20sec checking periodically every 5sec
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  containers:
  - name: app
    readinessProbe:
      exec:
        command: ["echo", " ready"]
      initialDelaySeconds: 20
      periodSeconds: 5
```

</p>
</details>

➡️ Add liveness probe with http call to path `/alive` at port 80 with delay 20sec checking periodically every 5sec
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  containers:
  - name: app
    livenessProbe:
      httpGet:
        path: /alive
        port: 80
```

</p>
</details>

➡️ Set resource requests (cpu 0.5, memory 128Mi) and limits (cpu 2, memory 256Mi)
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  containers:
  - name: app
    resources:
      requests:
        cpu: 0.5
        memory: 256Mi
      limits:
        cpu: 2
        memory: 256Mi
```

</p>
</details>

➡️ Run a container as user 1000 and add CAP_KILL capability
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  containers:
  - name: app
    securityContext:
      runAsUser: 1000
      capabilities:
        add: ["CAP_KILL"]
```

</p>
</details>

➡️ Create a config map with COLOR=BLUE and use the value as an env variable. Create a secret with SHAPE=SQUARE and use the value as an env variable
<details><summary>show</summary>
<p>

```yml
k create cm app-cm --from-literal COLOR=BLUE
k create secret generic app-sec --from-literal SHAPE=SQUARE
# pod
spec:
  containers:
  - name: app
    env:
      - name: COLOR
        valueFrom:
          configMapKeyRef:
            name: app-cm
            key: COLOR
      - name: SHAPE
        valueFrom:
          secretKeyRef:
            name: app-sec
            key: SHAPE
```

</p>
</details>

➡️ Create a config map with COLOR=BLUE and use it as env source. Create a secret with SHAPE=SQUARE and use it as env source
<details><summary>show</summary>
<p>

```yml
k create cm app-cm --from-literal COLOR=BLUE
k create secret generic app-sec --from-literal SHAPE=SQUARE
# pod
spec:
  containers:
  - name: app
    envFrom:
    - configMapRef:
        name: app-cm
    - secretRef:
        name: app-sec
```

</p>
</details>

➡️ Create a dir an mount it as a volume at path `/dir`. Create a config map with COLOR=BLUE and mount it as a volume at path `/path`. Create a secret with SHARE=SQUARE and mount it as a volume at path `/path`
<details><summary>show</summary>
<p>

```yml
k create cm app-cm --from-literal COLOR=BLUE
k create secret generic app-sec --from-literal SHAPE=SQUARE
# pod
spec:
  containers:
  - name: app
    volumeMounts:
    - name: vol-dir
      mountPath: /dir
    - name: vol-cm
      mountPath: /path
    - name: vol-sec
      mountPath: /path
  volumes:
  - name: vor-dir
    emptyDir: {}
  - name: vol-cm
    configMap:
      name: app-cm
  - name: vol-sec
    secret:
      secretName: app-sec
```

</p>
</details>

➡️ Create a PV: storage class manual, RWX, capacity 10Gi, storing data on host path `/data`. Create a PVC: request 5Gi from that PV. Mount PVC as a volume at path `/storage`
<details><summary>show</summary>
<p>

```yml
# PV
apiVersion: v1
kind: PersistentVolume
metadata:
  name: pv1
spec:
  storageClassName: manual
  accessModes:
  - ReadWriteMany
  capacity:
    storage: 10Gi

# PVC
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: pvc1
spec:
  storageClassName: manual
  accessModes:
  - ReadWriteMany
  resources:
    requests:
      storage: 5Gi
  selector:
    matchLabels:
      app: app

# pod
metadata:
  labels:
    app: app
spec:
  containers:
  - name: app
    volumeMounts:
    - name: app-pvc
      mountPath: /storage
  volumes:
  - name: app-pvc
    persistentVolumeClaim:
      claimName: pvc1
```

</p>
</details>

➡️ Create service account admin. Add admin service account to pod
<details><summary>show</summary>
<p>

```yml
k create sa admin
# pod
spec:
  serviceAccountName: admin
```

</p>
</details>

➡️ Run pod on a node nodeX
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  nodeName: nodeX
```

</p>
</details>

➡️ Run pod on a node with label color=red
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  nodeSelector:
    color: red
```

</p>
</details>

➡️ Taint a node with color=red, no schedule. Add a toleration for that color to a pod
<details><summary>show</summary>
<p>

```yml
spec:
  tolerations:
  - key: color
    operator: Equals
    value: red
    effect: NoSchedule
```

</p>
</details>

➡️ Add affinity to a pod to be run on nodes that have zone=a or zone=b (required)
<details><summary>show</summary>
<p>

```yml
# pod
spec:
  affinity:
    nodeAffinity:
      requiredDuringSchedulingIgnoredDuringExecution:
        nodeSelectorTerms:
        - matchExpressions: # matched labels
          - key: zome
            operator: In
            values:
            - a
            - b
```

</p>
</details>

➡️ Create a job that runs command `date` 10 times by 2 in parallel
<details><summary>show</summary>
<p>

```yml
k create job myjob --image busybox -oyaml --dry-run=client -- date > myjob.yml
# job
spec:
  completions: 10
  parallelism: 2
  template:
    spec:
      containers:
      - name: myjob
        command: ["date"]
```

</p>
</details>

➡️ Create a cronjob that runs a command `date` every hour
<details><summary>show</summary>
<p>

```yml
k create cj mycj --image busybox -oyaml --dry-run=client --schedule "* */1 * * *" -- date > mycj.yml
```

</p>
</details>

➡️ Create a node port service to expose a deployment on port 30200
<details><summary>show</summary>
<p>

```yml
k create deploy app --image nginx --replicas 3
k expose deploy app --type NodePort --port 80 --target-port 80
# svc
spec:
  ports:
  - nodePort: 30200 # edit this
    port: 80
    targetPort: 80
    protocol: TCP

k get node -owide # take internal-ip of a node
curl <node-internal-ip>:30200
```

</p>
</details>

➡️ Expose a deployment on a port 80 and access it from another pod
<details><summary>show</summary>
<p>

```sh
k create deploy app --image nginx
k expose deploy app --port 80 --target-port 80
k run tmp --image busybox --rm -it --restart Never -- wget -O- app.default:80 # or just app:80 or just app
```

</p>
</details>

➡️ Disallows all and outgoing traffic to the pods in the cluster. Create a pod and allow incoming and outgoing requests between it and another pod
<details><summary>show</summary>
<p>

```yml
# netpol that blocks all traffic
spec:
  podSelector: {}
  policyTypes:
  - Ingress
  - Egress

k run app1 --image nginx
k run app2 --image nginx

# edit netpol
spec:
  podSelector:
    matchLabels:
      run: app1
  ingress:
  - from:
    - podSelector: # can also be namespaceSelector
        matchExpressions: # using matchExpressions just as an example of how to use it
        - key: run
          operator: In
          values:
          - app2
  policyTypes:
  - Ingress
```

</p>
</details>

➡️ Route traffic to from deployment `app-blue` to path `/blue` port `4444` and from deployment `app-red` to path `/red` port `5555` on host `test.app.com`
<details><summary>show</summary>
<p>

```yml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: minimal-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  ingressClassName: nginx
  rules:
  - host: test.app.com  
    http:
      paths:
      - path: /blue
        pathType: Prefix
        backend:
          service:
            name: app-blue
            port:
              number: 4444
      - path: /red
        pathType: Prefix
        backend:
          service:
            name: app-red
            port:
              number: 5555
```

</p>
</details>

➡️ Create a CRD with spec `spec.task` (string) and `spec.duration` (number)
<details><summary>show</summary>
<p>

```yml
apiVersion: apiextensions.k8s.io/v1
kind: CustomResourceDefinition
metadata:
  name: mytasks.stable.example.com
spec:
  group: stable.example.com
  versions:
    - name: v1
      served: true
      storage: true
      schema:
        openAPIV3Schema:
          type: object
          properties:
            spec:
              type: object
              properties:
                task:
                  type: string
                duration:
                  type: integer
  scope: Namespaced
  names:
    plural: mytasks
    singular: mytask
    kind: MyTask
    shortNames:
    - mt
```

</p>
</details>

➡️ Run bitnami/mysql with Helm
<details><summary>show</summary>
<p>

```sh
helm repo add bitnami https://charts.bitnami.com/bitnami
helm search repo bitnami
helm install bitnami/mysql
```

</p>
</details>

➡️ Download bitnami/apache and run chart overriding `resource.limits.cpu: 1`, print all its k8s objects and then delete it
<details><summary>show</summary>
<p>

```sh
helm pull bitnami/apache --untar
helm install ./apache -f apache/values.yaml--set resources.limits.cpu=1
helm get manifest myapache
helm uninstall myapache
```

</p>
</details>