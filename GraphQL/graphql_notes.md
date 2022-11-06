# GraphQL

GraphQL is a query language
It was its runtime env to fulfil those queries
It uses Schema Definitions

Schema - set of possible data to query
Functions to resolve schema fields
Single endpoint
Receives a string to be validated, interpreted and fulfilled, returns JSON

[Cheat Sheet](https://raw.githubusercontent.com/sogko/graphql-shorthand-notation-cheat-sheet/master/graphql-shorthand-notation-cheat-sheet.png)

## Object types and fields
Represent an object (and its fields) that can be fetched from your service
```gql
type Character {
  name: String!
  appearsIn: [Episode!]!
}
```

## Operation types
Define entry points of every gql operation
- query
- mutation
- subscription
(They are also just object types with fields)

### Query
Similar to GET request - to fetch data

### Mutation
Similar to POST/PUT/DELETE - to manipulate data

### Subscription
Sets up real-time link w server (WS [only?])

## Arguments
A field can have 0 or more args
Args are names
Either req or opt
If opt, can have default value

## Scalar types
Data type to be resolved (e.g. Int, Float, String, Boolean, ID)
Can have custom scalars (e.g. Date, JSON, Email)

## Enum types
Set of values
```gql
enum STATE {
  ACTIVE
  INACTIVE
}
```

## Type modifiers
Lists and non-nulls
See cheat sheet

## Interfaces
Provides inheritance
```gql
interface Foo {
  isFoo: Boolean
}
type Bar implements Foo {
  isBar: Boolean
}
```

## Fragments
Provide query access to fields of a specific interface


## Unions
Similar to interfaces but they don't specify common fields between types
```gql
union SearchResults = Human | Droid | Starship
```

## Input types
Same as `type` but used for mutations for arguments [just?]
```gql
input MessageInput {
  content: String
  author: String
}
type Mutation {
  createMessage(input: MessageInput): Message
}
```

## Resolver functions
Each field on each type is backed by a function called `resolver`
If a field is an object type then the query continues to its fields
If a field is a scalar (Int/String, etc) then execution completes on it (so gql queries always end on scalar values)

`fieldName: (parent, args, context, info) => data`
- parent - result returned from resolver on parent type
- args - args passed to the field
- context - state shared for all resolvers per request
- info - info about schema, query and state of operation

Can be sync or async

## Schema Directive
Identifier preceded by @ (optionally args)
Like annotations in Java


## PD GraphQL architecture
`graphql-service` is the main handler of /graphql reqs
It is a federator of global gql schema (supergraph)
It uses `schema-registry` as a global storage for schemas (where other services register their schemas)
Other services (subgraphs) can either interact with `graphql-service` or use `generated-graphql` service

--------------------------------------------------------------------------------

## Sharding

### Consistent hashing
A way to get hash of input to determine which shard this data is in

__Pros__
- scaling (data, memory)
- security (access control, e.g. users accessing only certain shards)
- smaller index size
__Cons__
- problem with trx across shards
- how to rollback
- schema changes are hard
- joins as hard
- how to query by something that is not a value of sharing (e.g. share by user region, but you query user by name)


















## GraphQL in PD

[Docs](https://pipedrive.atlassian.net/wiki/spaces/ENG/pages/2670723808/GraphQL+HUB)
There are also 2 TEX talks + 1 TalentLMS course

Native GraphQL impl in [menu-waitress](https://github.com/pipedrive/menu-waitress/pull/473/files)

Generated GraphQL:
- [generated-graphql](https://github.com/pipedrive/generated-graphql)
- lib that generates code for `generated-graphql` [graphql-service-codegen](https://github.com/pipedrive/graphql-service-codegen)






















---
