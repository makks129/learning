## Anatomy

- requires own disk space
- holds copy of indexed data
- is pure redundancy

### Leaf nodes

Doubly-linked list (DLL) connects index leaf nodes.
Each leaf node is stored in a block (page).
All index blocks are of the same size (a few kb).
Each block contains as many index entries as possible.

Index order on 2 levels:
- index entries within each leaf node
- leaf nodes among each other using DLL

### B-Tree

Leaf nodes are connected via DLL but are not stored in order on disk.
To quickly find a specific entry in the index we need another data structure in addition to DLL - balanced search tree (B-Tree).

![B-Tree](https://use-the-index-luke.com/static/fig01_02_tree_structure.en.SIIhx7If.png)

- Root node
- Branch node
  - each entry corresponds to the biggest values in leaf node
  - this process repeats for N levels until Root node
- Leaf node

**Balanced** - tree depth is equal at every position (distance from root to all leaves is the same)

![Index traversal](https://use-the-index-luke.com/static/fig01_03_tree_traversal.en.bhRJyIWe.png)

Tree traversal is very fast because the tree is balanced and because of log growth of tree depth (mlns of records will have depth of just 4-5).

Index lookup:
- tree traversal
- following lead node chain (entry with the same value might be in the following block)
- fetching table data

## WHERE

Equality ensures only 1 entry, so only tree traversal (const) and at most 1 table access.

**Concatenated index** (aka composite, multi-column, combined) - index on all PK columns. Column order matters greatly.

![Concatenated index](https://use-the-index-luke.com/static/fig02_01_concatenated_index.en.iruQ3LZ5.png)

If where clause uses complete PR (all columns of PK index), DB will perform a `INDEX UNIQUE SCAN`. Concat index is useless if query is performed on columns in a wrong order (e.g. only on col2 or col2-col1, instead of col1-col2).

When defining a concat index think about all ways the index can be used to define correct columns order.

### Functions and FBI

If there is an index on `name` and the query has `WHERE UPPER(name) = 'Alice'` - index won't be used. It is seen be the optimizer as `BLACKBOX(...)` as `name` is just a param to `UPPER` and it's not related to index on `name`. In this case we need an index on `UPPER(name)` (FBI).

**FBI** - function based index

FBI must be fully deterministic (result fully determined by params), e.g. it cannot contain calculation based on current date, etc, because this will change every time you call it.

### Parameterized queries

Param queries can be beneficial because they prevent SQL injections and also improve performance as they can be cached by DB to reuse execution plan.

Exception: if DB query strategy depends on values. In this case the same strategy will be used for every query, which might not be optimal.
E.g. when one value of `WHERE` clause will result in index scan (because index returns only a few rows) or on full table scan (because index returns too many rows and the total cost of accessing table by row id many times will be higher).

**Bind parameters** should always be used, except for values that _will_ influence the execution plan.

Partitioning and `LIKE` queries can also influence bind parameters performance trade-off.

### >, <, BETWEEN

Leaf node traversal in the biggest performance risk for indexes. Index range should be as small as possible.

For a query with range (`>/</BETWEEN`) `AND` equality (`=`), index for equality first. Otherwise the bigger the range the slower the query will be.

### LIKE



---

## CBO / Statistics

**Cost-based optimizer** uses statistics on the contents of the DB to understand which strategy to take (with index, without, etc).

Most statistics are on column level: number of distinct values, smallest, largest, number of nulls, etc.

Most important index statistics: tree depth, num of leaf nodes, num of distinct keys, clustering factor.
