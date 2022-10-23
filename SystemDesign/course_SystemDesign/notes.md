# System Design (Udemy) - Notes

## Scalability

Horizontal vs vertical scaling

## Failover strategies

Cold standby
Warm standby
Hot standby

## Sharding

## Data Lakes

## ACID compliance

Atomicity: entire trans succeeds or fails
Consistency: all DB rules are enforced or trans is rolled back
Isolation: no trans is affected by another still in progress
Durability: once trans is committed it is saved reliably

## CAP theorem

Consistency: difference in time bw write and read
- https://en.wikipedia.org/wiki/Consistency_model
Availability: avoiding SPOF
Partition-tolerance: horizontal scaling

## Caching

Fleet of separate hor scaled cache servers
Better for apps w more reads than writes
Expiration policy - how long to cache for
Hotspots (celebrity problem) - caching also by load, not just keys
Cold-start problem - how to do initial warm up of cache layer

### Eviction policies

LRU: least recently used (keys in linked list)
LFU: least frequently used
FIFO: first in first out

### Tech

Memcached
Redis:
Ncache
Ehcache
Elasticache

## CDNs

Local hosting of HTML/CSS/JS/static
Geo distributed

### Tech

AWS CloudFront
Google Cloud CDN
MS Azure CDN
Akamai
CloudFlare
...

## Resiliency

### Levels

1 server
Entire rack
Entire DC
Entire region

## Distributed storage

### Tech

Amazon S3
Google Cloud Storage
MS Azure
Hadoop HDFS (self-hosted)


## Cloud computing


## SLAs

Durability
Availability
