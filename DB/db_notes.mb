## ACID

[InnoDB and the ACID Model](https://dev.mysql.com/doc/refman/8.0/en/mysql-acid.html)

### Transactions
Multiple queries
Unit of work
Transactions can also be used for reading to get a snapshot of the system at the time of start of the transaction

BEGIN
COMMIT
ROLLBACK

### Atomicity
Either all queries in a transaction succeed together or rollback
It's one unit of work

### Isolation
__Read phenomena__
- Dirty reads:
  - 2 reads in 1 trx, another trx updates data between those reads but doesn't commit (mb even rollback), data is inconsistent
- Non-repeatable read
  - 2 reads in 1 trx, another trx updates data between those reads and commits, data is inconsistent
- Phantom reads
  - 2 reads in 1 trx, another trx inserts new data between those reads and commits, data is inconsistent
- Lost updates
  - 1 update and 1 read in 1 trx, another trx updates with new data after trx1 update and commits, trx1 update is lost, data is inconsistent

__Isolation levels__
- Read uncommitted = no isolation (you can read uncommitted trx)
- Read committed = you can only read committed changes
- Repeatable read = you can read the same row in 1 trx and it won't change
- Snapshot = you can only work with a snapshot of a DB at the start of trx
- Serializable = transactions are serialized (executed strictly one one after another). If 2 trx make changes on the same data second trx commit will fail.

[Isolation levels vs Read phenomena](https://miro.medium.com/max/1400/1*NppBgUymDiDLwBJjAvqbEQ.png)

__Isolation implementations__
- Pessimistic (row level locks, table locks, page locks)
- Optimistic (no locks, just tracking changes and failing trx in case of changes)

### Consistency
__In data__
- defined by user
- referential integrity
- corruption of data or abandoned data

__In reads__
- effects system as a whole
- both for relational and NoSQL
- eventual consistency

### Durability
- Committed changes must be persisted in a durable non-volatile storage
- Durability techniques:
  - WAL (write ahead log) - persisting changes (this is changed to that)
  - Async snapshot
  - AOF

--------------------------------------------------------------------------------
## Database Internals

### row_id
Internal and system maintained
In some DBs it's the same as PK, in others it's a system column (row_id / tuple_id)

### Page
Rows are stored in pages
DB reads pages, not single rows
Disk location of fixed size (8kb in Postgres, 16kb Mysql)

### IO
IO can fetch 1 or more pages
IO doesn't read a single row, but a page (or many)
IO might also use OS cache before using disk

### Heap
Data structure where a table is stored with all its pages one after another
Traversing the heap is expensive

### Index
Data structure separate from the Heap that has pointers to the Heap
Index is also stored as pages and costs IO to pull
The smaller the index, the more it fits in memory and the faster is the search
Data structure for indices is B-Tree

### Row-oriented DBs
Tables are stored as rows on disk
- Optimal for read/write
    - aggregation is not efficient tho
    - efficient queries w multiple cols
- OLTP (online transaction processing)
- Compression is not efficient

### Column-oriented DBs
Tables are stored as columns on disk
- Writes are much slower but some reads can be fast
  - aggregation is fast
  - inefficient queries w multiple cols
- OLAP (online analytical processing)
- Compression is great (same values for 1 columns can be compressed)

--------------------------------------------------------------------------------
## Scans

Full table scan (seq table scan)
Index scan
  - scanning by indexed col(s) but fetching data from the disk afterwards
Index only scan
  - scanning by indexed col(s) and getting all data necessary from index itself (might be though additional non-key cols)
Bitmap heap scan (postgres)
Bitmap index scan (postgres)

--------------------------------------------------------------------------------
## Indexes

__Key and Non-Key Column Indexing__
In some DBs it's possible to include additional cols in the index, so when index scan happens it fetches additional data from the index directly without hitting the disk.

[Multiple column indexes](https://dev.mysql.com/doc/refman/8.0/en/multiple-column-indexes.html)

You can create index concurrently to not block DB mutation queries

[SQL Server and Azure SQL index architecture and design guide](https://docs.microsoft.com/en-us/sql/relational-databases/sql-server-index-design-guide?view=sql-server-ver15)

__Clustered index__
Table where the data for the rows are stored
- Only 1 per table
- If there is PK it will be based on it
__Non-clustered Index__
The indexes other than PRIMARY indexes (clustered indexes). Aka secondary indexes.
The non-clustered index and table data are both stored in different places.
[Difference between MySQL Clustered and Non-Clustered Index](https://www.javatpoint.com/mysql-clustered-vs-non-clustered-index)

In Postgres secondary index key points to the tuple
In MySQL secondry index key points to the primary key (so if primary key is large, like UUID, or poorly chosen, this can be a problem)


## Bloom Filters
[Bloom Filters](https://www.youtube.com/watch?v=V3pzxngeLqw)

Bloom filters can prevent unnecessary hits to the DB

## B-Trees & B+Trees
B-Tree:
- depth level is constant
- stores keys and values in all nodes
- < values go to the left, > go to the right
B+Tree:
- depth level is constant
- stores only keys in internal nodes
- stores values in leaf nodes
- leaf nodes are linked
- < values go to the left, >= go to the right
- internal nodes should fit in memory (then searching will be fast)


--------------------------------------------------------------------------------
## EXPLAIN (MySQL)

### ref (Access types), fastest->slowest
__const / eq_ref__
- performs B-Tree traversal to find a single value (binary search)
- can only get used if uniqueness is guaranteed
__ref / range__
- index range scan
- performs B-Tree traversal to find a starting point and then scans on
__index__
- full index scan
- traverses though entire index
__all__
- full table scan
- does not use index at all

### Extra
__Using index__
- index only (no heap IO)

--------------------------------------------------------------------------------
## SHOW (MySQL)
Examples:
```
SHOW TABLE status
SHOW COLUMNS FROM my_custom_table
```
TBD


--------------------------------------------------------------------------------
## Partitioning

__Horizontal__
Splitting rows into separate tables in the same DB
See [Vitess](https://vitess.io/)
__Vertical__
Storing certain columns separately

Partition by:
- range (date / id, etc)
- list (states, zip codes, etc)
- hash ()

Pros:
- faster queries on 1 partition
- can separate old data into another cheaper partition
Cons:
- slower updates that move rows from 1 partition to another
- inefficient queries can scan all partitions (slow)
- schema changes can be challenging

--------------------------------------------------------------------------------
## Sharding
Splitting table into multiple across different DB servers


--------------------------------------------------------------------------------
## Locks

### Shared vs Exclusive locks
Exclusive locks lock a row from either reading or writing
Shared locks lock a row only for reading

### Deadlock
Deadlock can happen if 2 processes are waiting on each others
E.g.: 2 trxs: A, B
- A inserts row with id 10
- B inserts row with id 20
- A inserts row with id 20 (starts waiting for trx B to complete as it inserted 20 first)
- B inserts row with id 10 (starts waiting for trx A to complete - deadlock)
- last insert in B will fail with error bc of deadlock (DB detects it)

### Two-phase locking
Solves double booking problem (race condition)
E.g.: 2 trxs: A, B
- A updates row
- B updates same row
- A commits - success
- B commits - success
- Although both trxs succeded, row has data from B

Two-phase locking can be solved with exclusive lock (`for update`)
E.g.: `select * from x where id = x for update`

### Implicit locking
Also solves double booking problems (but in implicit way)
E.g.: 2 trxs: A, B
- A updates row _if is not updated_
- B updates row if is not updated, but waits bc this row _is being updated by A_
- A commits, row is updated
- B continues but cannot execute update bc row got updated in A
In this way double booking will not happen bc DB locked row implicitly for A



--------------------------------------------------------------------------------
## Offset
Offset fetches and throws out rows
E.g. `select * from items offset 1000 limit 10` will fetch 1010 items, throw away 1000 and return 10
Problems:
- Never do pagination with offset!
- Double read: if you select `offset 1000 limit 10` again, another row could've been inserted into the first 1000, so now you will read row 1001 a second time

[We need tool support for keyset pagination](https://use-the-index-luke.com/no-offset)



--------------------------------------------------------------------------------
## Connections
- Establishing a connection on every request
- Connections pool
- ...

[MySQL Connection Handling and Scaling](https://dev.mysql.com/blog-archive/mysql-connection-handling-and-scaling)
MySQL server executes in a single OS process
Each user connection (aka session) executes in a new thread in parallel
A connection request is a TCP-IP connect message sent to port 3306
Each connection stays connected until it disconnects
![Connect](https://dev.mysql.com/blog-archive/mysqlserverteam/wp-content/uploads/2019/03/Connect-1024x427.png)
Incoming connection requests are queued and then processed by the receiver thread one by one

__Short Lived Connections__
The client opens a connection, executes a simple query, and then closes the connection
MySQL is good at handling short connections (up to 80.000 connects/disconnects per second)

__Long Lived Connections__
A long lived connection is a connection that is open "indefinitely"
The maximum number of clients the server permits to connect simultaneously is determined by the `max_connections` system variable

__What is the maximum load?__
You have to test your workload, for example as follows: You can start with 2 busy clients and measure server TPS and Latency, and then continue step-wise by doubling the number of clients for each step. Initially, TPS will increase and latency will be constant for each step you take. At some point TPS will be the same as before and latency will start to increase, and this is the maximum load and the maximum number of (useful) clients.
> Based on accumulated experience we recommend at maximum 4 times number of user threads as the number of real CPU cores, e.g. 4×48(cores)=196(user threads).

__Connection pooling__
Connection pool is a client side approach (as opposed to Thread pool which is a server side approach to handle connections)
[FAQ: MySQL Enterprise Thread Pool](https://dev.mysql.com/doc/refman/8.0/en/faqs-thread-pool.html)


--------------------------------------------------------------------------------
## Replication

__Master/Standby(Backup) replication__
1 Master node accepts writes
1+ standby node receives writes from master

__Multi-Master replication__
Multiple master nodes accept writes
1+ standby node receives writes from masters

__Sync vs Async__
Sync: trx is blocked until it's written to master + standbys (one, two or any, etc)
Async: trx is blocked only until it's written to master


--------------------------------------------------------------------------------
## Cursors
Used to work with queries that return big amounts of rows

E.g. say `table` has million of rows:
`DECLARE c CURSOR FOR SELECT id GROM table WHERE id > 100;` -- this will prepare the table and declare a cursor
`FETCH c;` -- this will fetch 1 row from the prepared table
`FETCH c;` -- each next fetch will return 1 next row
`FETCH last c;` -- will return last 1 row

__Pros__
- saves RAM (only fetching small amount of data to work with)
- streaming (pulling rows and streaming them)
- can be cancelled
- paging (not so easy in web as paging is stateful)
__Cons__
- stateful (transaction points to that cursor, so cursors cannot be shared)

## Server-side vs Client-side
Server-side means that cursor is created on the DB level and executing initial query takes short time but fetching the cursor actually gets data from the DB and returns
Client-side means that cursor is created on the client side and initial query does full query which on client side you can move around the query with the cursor



--------------------------------------------------------------------------------
## Networking

### Connection pooling
It's possible to create pools of connections and give each pool a specific DB user
Each DB user can have a specific set of permissions

[Database Permissions and Best Practices for Building REST API](https://www.udemy.com/course/database-engines-crash-course/learn/lecture/22597784#overview)


--------------------------------------------------------------------------------
## Engines

Engine is a library that takes care of disk storage and CRUD
(Can be as simple as kv store, or as complex as full ACID DB w transactions, etc)

DBMS uses an engine and build features on top of it: server, replication, isolation, stored procedures, etc

__MyISAM__
- Indexed seq access method
- B-Tree
- Not transaction
- Fast inserts (slow updates/deletes)
- Table level locking

__InnoDB__
- PK is necessary (either custom or automatic)
- B+Tree
- ACID compliant transactions
- FKs
- Row level locking

__SQLite__
- Popular for local data
- B-Tree
- ACID
- Table level locking
- Concurrent read and write

__LevelDB__
- LSM
- High inserts
- No transactions

__RocksDB__
- Forked from LevelDB
- Transactions
- High performance
- Multi-threaded








--------------------------------------------------------------------------------
## Tools

Benchmarking
[sysbench](https://github.com/akopytov/sysbench)
















--------------------------------------------------------------------------------
## Resources

- [Fundamentals of Database Engineering](https://www.udemy.com/course/database-engines-crash-course/)
- [Things every developer absolutely, positively needs to know about database indexing](https://www.youtube.com/watch?v=HubezKbFL7E&t=744s&ab_channel=LaraconEU)
- [Developers Need to Index](https://use-the-index-luke.com/sql/preface)





---
