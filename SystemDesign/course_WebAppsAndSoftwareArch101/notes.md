# Web Application and Software Architecture 101

## Tiers
Single/Two/Three/N tier applications

#### Tiers vs Layers
The difference between layers and tiers is that layers represent the conceptual/logical organization of the code, whereas tiers represent the physical separation of components.

## Web Architecture

HTTP Push
HTTP Pull:
- polling
- long polling (connection open slightly longer than with polling, server only sends update when data is available)
- web sockets (bi-directional data flow)
  https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API
  https://web.dev/websockets-basics/
- server-sent events (flow from server to client only)
  https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events
- streaming (streaming of large data objects)
  https://developer.mozilla.org/en-US/docs/Web/API/Streams_API/Concepts

## Scaling
Vertical scaling
Horizontal scaling

## Performance
Ways to increase performance:
- Profile your code
- Use caching
- CDN
- Data compression
- Fewer requests
- Testing scaling
- Take into account:
- CPU usage
- Network bandwidth consumption
- Throughput
- Number of requests processed within a stipulated time
- Latency
- Memory usage of the program
- End-user experience when the system is under heavy load and so on.

## High Availability
Fault tolerance
High availability modes:
- Active-passive: Redundancy - several passive nodes ready to take the load in case of active node failure
- Active-active: Replication - all nodes are active, in case of node failure the load is distributed
- Geographic distribution (availability + proximity)

## Load Balancing
Load balancers need to have awareness of available node by performing health checks.

Three modes of load balancing:
- DNS Load Balancing
- Hardware-based Load Balancing
- Software-based Load Balancing

Algorithms used by LBs:
- Round robin
- Weighted round robin
- Least connections
- Random
- Hashing (hashing the source IP ensures that a client’s request with a certain IP will always be routed to the same server)

## Microservices
Pros:
- No SPOF
- Leverage the heterogeneous technologies
- Independent and continuous deployments
Cons:
- Management complexity
- Strong consistency
Trade-offs:
- Need to set up distributed logging, monitoring, inter-service communication, service discovery, alerts, tracing, dedicated build and release pipelines, health checks, etc. (Custom tooling from scratch, etc.)

## Micro Frontends
A concept of microservices for FE. Each team owns a full stack of services, FE, BE, DB, etc.
Pros:
- Easier coordination between the frontend and the backend devs
- Leveraging the right technology
Trade-offs:
- Architectural and maintenance complexities
- Additional code to combine all the components built with heterogeneous tech
- Compatibility and performance issues when using multiple technologies together

Micro FEs can be integrated on: client side or server side.

## Databases

### Relational
ACID
Atomicity
Consistency
Isolation
Durability

### NoSQL
Scalability (horizontal scaling on clusters)
Eventual consistency (not strong consistency)

Schemaless
Not normalized data
Inconsistency
Don't always support ACID

### Multi-model DBs
Multi-model databases support multiple data models like the graph, document-oriented, relational, etc.
Examples: ArangoDB, CosmosDB, OrientDB, Couchbase, etc.

### Eventual consistency
Higher availability
Easier scaling
Lower data accuracy

### Strong consistency
Maximum data accuracy (might mean the need to lock all nodes on each queued update)
Results in lower scaling and availability

### CAP theorem
- Consistency
- Availability
- Partition tolerance
-
Theorem states that it's possible to only have 2 of 3.

Example: when a few nodes go down you have a choice between consistency (locking nodes until all of them come back live and perform a write operation) or availability (writing to existing nodes, leaving dead nodes in inconsistent state for some time).

### Types of DBs

#### Document-oriented
Examples: MongoDB, CouchDB, OrientDB, Google Cloud Datastore, Amazon DocumentDB, etc.
When: need flexible schema, need to scale fast and stay highly available

#### Graph
Examples: Neo4J
When: need to store complex relationships (like social network, map, etc)

#### Key-value
Examples: Redis, Memcached, Hazelcast, Riak, Voldemort, etc.
When: need caching, need pub-sub system, etc

#### Time-series
Examples: Prometheus, Influx DB, Timescale DB, etc.
When: analytics

#### Wide-column
Aka column-oriented databases. Records with a dynamic number of columns (potentially billions of columns).
Examples:  Cassandra, HBase, Google BigTable, ScyllaDB, etc.
When: massive amounts of data (Big Data), high performance, scalable architecture


## Caching

### Strategies
https://hazelcast.com/blog/a-hitchhikers-guide-to-caching-patterns/
- Cache-aside
- Read-through
- Write-through
- Write-behind (write-back)

## Message Queues

Models:
- pub-sub (1:N)
- point-to-point (1:1)

Protocols:
- AMQP (Advanced Message Queue Protocol)
- STOMP (Simple or Streaming Text Oriented Message Protocol)









---
## Notes

DENTTAL:
- Documentation,
- Exception Handling,
- Null pointers,
- Time complexity,
- Test coverage,
- Analysis of code complexity,
- Logging
