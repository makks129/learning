## Deployment
```yml
spec:
	strategy:
		rollingUpdate:
			maxSurge: 50%
			maxUnavailable: 0%
```

## Pod
```yml
spec:

	# CONTAINERS
	initContainers:
	containers:
	- name:
		image:

		# PROBES
		readinessProbe:
			exec:
				command: []
			httpGet:
				path: /
				port: 80
			initialDelaySeconds: 10
			periodSeconds: 5
		livenessProbe: ...

		# RESOURCES
		resources:
			requests:
				cpu:
				memory:
			limits:
				cpu:
				memory:

		# SECURITY CONTEXT
		securityContext:
			runAsUser: 1000
			runAsGroup: 2000
			capabilities: # only in containers
				add: []

		# ENV
		envFrom:
		- configMapRef:
				name:
		- secretRef:
				name:
		env:
		- name:
			value:
			valueFrom:
				configMapKeyRef:
					key: KEY1
					name: cm-name
				secretKeyRef:
					key: KEY1
					name: secret-name

		# VOLUME MOUNTS
		volumeMounts:
		- name: vol
			mountPath: /path

	# VOLUMES
	volumes:
	- name: vol
		emptyDir: {}
	- name: vol-cm
		configMap:
			name: cm-name
	- name: vol-secret
		secret:
			secretName: secret-name
	- name: vol-pvc
		persistentVolumeClaim:
			claimName: pvc-name

	# SERVICE ACCOUNT
	serviceAccountName: admin

	# SECURITY CONTEXT
	securityContext:
		runAsUser: 1000
		runAsGroup: 2000

	# NODE SELECTORS
	nodeSelector:
		key1: val1 # node label
	nodeName: node1

	# TAINS AND TOLERATIONS
 	# to taint: k taint node node1 key1=val1:NoSchedule
	tolerations:
	- key: key1
		operator: Equals
		value: val1
		effect: NoSchedule / PreferNoSchedule / NoExecute

	# AFFINITY
	affinity:
		nodeAffinity / podAffinity / podAntiAffinity:
			requiredDuringSchedulingIgnoredDuringExecution / preferredDuringSchedulingIgnoredDuringExecution: ... # see docs


```

## Job
```yml
spec:
	completions: 10
	parallelism: 10
	activeDeadlineSeconds: 30
```

## CronJob
```yml
spec:
	schedule: "* * * * *" # min, hour, day of month, month, day of week
```


## PersistentVolume
```yml
spec:
	storageClassName: ...
	accessModes:
	- ReadWriteOnce / ReadWriteMany / ReadOnlyMany
	capacity:
		storage: 10Gi
	hostPath:
		path: /path
```

## PersistentVolumeClaim
```yml
spec:
  storageClassName: ...
  accessModes:
  - ReadWriteOnce / ReadWriteMany / ReadOnlyMany
  resources:
    requests:
      storage: 4Gi
```



## Service
```yml
spec:
	selector:
		key: val # label
```

## NetworkPolicy
__See docs__
```yml
spec:
	podSelector:
		matchLabels:
			app: some-label
	policyTypes:
		- Ingress / Egress
	ingress:
		- from: ...
		- ports: ...
	egress:
		- to: ...
		- ports: ...
```

## Ingress
__See docs__
```yml
metadata:
  name: minimal-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  ingressClassName: nginx # k get ingressclass
  rules:
  - http:
      paths:
      - path: /path
        pathType: Prefix
        backend:
          service:
            name: test
            port:
              number: 80
```

## Secret
__See docs__
```yml
metadata:
  name: admin-secret
  annotations:
    kubernetes.io/service-account.name: admin
type: kubernetes.io/service-account-token
```

## LimitRange
__See docs__
```yml
spec:
  limits:
  - type: Container # type of a resource
    max:
      memory: 500Mi
    min:
      memory: 100Mi
```

## CustomResourceDefinition
__See docs__
```yml
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
```