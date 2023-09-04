# Triggers, Stored Procedures, FKs

From time to time developers ask if they can use MySQL features like triggers, stored procedures and foreign keys in their database schemas. We almost do not use them inside our schemas. Although these internal features have a lot of pros and definitely can make life easier from the first sight but with great power comes great responsibility. 
Lets focus and put together a summary why triggers/stored procedures and foreign keys are dangerous and should be avoided:

1. **Complexity and Maintenance**: Triggers, stored procedures and foreign keys can add complexity to a database schema and can be challenging to manage and maintain. They are executed implicitly based on certain events, which can make it harder to understand the flow of actions within the database.

2. **Side Effects**: Triggers, stored procedures and foreign keys can cause unexpected side effects, especially when multiple triggers are involved. It might be difficult to predict the order in which they will execute, leading to unintended consequences. 

3. **Performance Impact**: Poorly designed triggers can negatively impact database performance. Triggers add overhead, and if they involve complex operations or interact with multiple tables, they might lead to performance bottlenecks. Foreign keys can add overhead to database operations, especially during data modifications (inserts, updates, and deletes). When a foreign key is defined, the database needs to check for referential integrity, which can lead to additional queries and slow down certain operations.

4. **Debugging and Testing**: Triggers, stored procedures and foreign keys can make it harder to debug and test database operations. Since they are executed implicitly, it can be challenging to identify the root cause of issues when something goes wrong. Testing them can also be more complex, as they often depend on specific data and database states.

5. **Security Concerns**: Triggers, stored procedures and foreign keys can be used for malicious purposes if not implemented carefully. For instance, a poorly designed trigger might lead to data breaches or unauthorized access.

6. **Code Visibility and version control**: Triggers, stored procedures and foreign keys are often not as visible or apparent as regular stored procedures or application code. This lack of visibility might make it harder for developers to understand the complete logic flow. Application logic should be stored within the code, not database. 

7. **Learning Curve**: For developers who are not familiar with triggers, stored procedures and foreign keys or who have limited experience with database design, understanding and implementing foreign keys correctly might be challenging

8. **Bad experience**: We have had a couple of cases previously that lead to big incidents because of errors in triggers code. Also we had a bad experience with adding foreign keys that lead to big performance troubles.

Specifically about foreign keys:

1. **Replication and Migration Challenges**: When working with replicated databases or migrating data between environments, foreign keys can sometimes cause synchronization problems or migration difficulties.

2. **Altering**: Foreign keys can make it more challenging to perform certain data manipulations or schema changes. If not managed properly, foreign keys might lead to issues when altering the structure of the database.

3. **Data Integrity Constraints**: While foreign keys help enforce referential integrity, they can also introduce constraints that may be too strict for certain scenarios. For example, cascading delete operations might lead to unintentional data loss if not carefully managed. Foregin keys cause implicit locking being done on the foreign key table and sometimes it’s not as optimistic as it could be in an ideal scenario leading to locking scenarios that are very hard to find and solve. Even our best engineers struggle with relatively simple GAP lock issues and we have seen them repeat year after year so foreign key locks being a level of complexity higher are not a good idea without dedicated database experts within teams.