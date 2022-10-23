docker

  info

  image
    history   show layers
    inspect   metadata
    tag   (image[:tag], new image[:tag]) default tag - latest
    build
      -t  tag the new image
      -f  specify Dockerfile (or `.` in the end to use file from cwd)
    prune   clearn dangling images

  container
    ls    list running containers
      -a    list all containers
    ps    process list
    top   (hash or name) process list in a container
    port  (hash or name) shows ports config
    inspect   (container) metadata
    stats   performance stats
    run
      -p --publish    ports
      -d --detach   run in bg
      --name    add name of container (otherwise be random "adjective-name")
      --network   specify docker network
      -e --env    pass env variable
      -i    interractive
      -t    allocate a pseudo-tty
      --rm  remove the container if the main process is exited
      --net-alias   alias name for the network
      -v  specify (named) volume (`-v mysql-db:/var/lib/mysql`) or bind mount (`-v /path/on/host:/path/in/container`)
    start
      -a    attached
      -i    interactive
    stop
    rm    (hash) remove containers
      -f    force, remove running containers also
    logs
    exec    start a separate process (e.g. in mysql the daemon is running by default but you can get another process)
      -i    interactive
      -t    allocate a pseudo-tty

  update    update config of container (usually resources)

  volume
    create    create a volume ahead of time (can be also done with `VOLUME` in Dockerfile or with `container run -v`)
    prune   cleanup

  network
    ls
    inspect   metadata
    create
      --driver
    connect
    disconnect

  system
    prune   clean up everything (unused/dangling images, containers)
    df  see space usage

# Swarm

  swarm
    init

  node
    ls

  service
    create
    update
      --replicas  scale up
    rm
    ps

  stack
    deploy
    services
    ps

# Kubernetes

  kubectl

    get
      pods
      all

    run [name]
      --image

    create
      --dry-run -o yaml   don't run the command, but show the output of what would've happened in yaml format


    scale
      --replicas [N]

    delete

    apply
      --server-dry-run    compares with the state on the server and returns what will happen for this operation (see also diff command)

    diff    shows diff between specs on server and the one we specify in -f


    logs
      --follow
      --tail [N]

    describe
