https://kubernetes.io/docs/reference/kubectl/cheatsheet/
https://kubectl.docs.kubernetes.io/references/kubectl/

kubectl
	
	run [name]
		--image [imageName]								image is downloaded from dockerhub repo
		--dry-run=client -o yaml						displays command in YAML to stdout (could be writte to a file)

	expose
		pod [podName]
			--port=[port]								creates a service to expose a port of a Pod

	get
		all												list all k8s objects
		pods											list Pods (name, status, etc)
			-o [wide]									more info
			-n/--namespace [namespace]					list Pods in a namespace
			-A/--all-namespaces							list Pods in all namespaces
			-l/--selector [k=v,k=v,..]					list Pods matching labels (selector)
		replicaset/rs 									list ReplicaSets
		deployments/deploy								list Deployments
		pod
			-o [yaml/json/wide/name]					displays Pod definition in a format to stdout
		configmaps/cm									list ConfigMaps
		serviceaccount/sa								list ServiceAccounts
		jobs											list Jobs
		cronjob											list CronJobs
		service/svc										list Services
		endpoints										list Endpoints
		ingress											list Ingress
		networkpolicy/netpol							list NetworkPolicies
		persistentvolume/pv								list PersistentVolumes
		persistentvolumeclaim/pvc						list PersistentVolumeClaims
		storageclass/sc 								list StorageClasses
		roles											list Roles
		rolebindings									list RoleBindings

	create
		-f [.yml file]									create an object specified in yml file
		-n/--namespace=[namespace]						create in a specific namespace
		--record										record deployment revision change-cause
		namespace [namespace]							create a namespace
		configmap [name] 
			--from-literal [k=v]						create a configmap of k=v pairs (add more --from-literal to add more k=v pairs)
			--from-file [file]							create a configmap of k=v pairs from a file
		secret [name]
			--from-literal [k=v]						create a secret of k=v pairs (add more --from-literal to add more k=v pairs)
			--from-file [file]							create a secret of k=v pairs from a file
		serviceaccount [name]							create a ServiceAccount
		token [serviceAccountName]						create a token for ServiceAccount

	edit
		pod [name]										edit Pod definition
		replicaset/res [name]							edit ReplicaSet definition

	replace
		-f [fileName] --force							deletes and recreates Pod with new config file

	label
		node [nodeName]
			k=v 										labels a node with k=v pair

	taint
		node [nodeName] k=v:taint-effect				taints a node, taint-effect specifies what would happen to the pod that doesn"t tolerate the taint (NoSchedule/PreferNoSchedule/NoExecute)

	describe
		pod [name]										info about the pod

	explain [resourceName]								describes the resource
		--recursive										describes the resource with all fields

	config
		view											show KubeConfig
			--kubeconfig [path]							specify custom KubeConfig
		use-context										change current-context (will change kubeconfig file)
		set-context --current --namespace=[namespace]	change kubectl namespace in k8s context

	logs [podName] [containerName]						show logs for pod (and specific container if necessary)
		-f 												stream logs

	rollout
		status
			deployment/[deploymentName]					see rollout of a Deployment
		history
			deployment/[deploymentName]					see history of Deployment revisions
				--revision								of a specific revision
		undo
			deployment/[deploymentName]					perform rolling rollback
				--to-revision							to a specific revision

	auth
		can-i [verb] [noun]								checks if your role can perform an action (e.g. create pods)
			--as [role]									test if you can perform an action as a specific role
			--namespace [name]							test that in a specific namespace

	api-resources
		--namespaced [true|false]						see namespaced or cluster scoped resources

	convert
		-f [old-definition.yml] --output-version [v]	(plugin) convert a definition to a new version (e.g. -f nginx.yml --output-version apps/v1)

	proxy [port]&										runs kube-apiserver proxy on localhost; curl it like so: curl localhost:port/apis/v1 (& runs proxy process in the background)