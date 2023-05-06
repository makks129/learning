mysql.server start (stop/restart)
mysql -u root
show databases;
use databaseName;

# To enter local docker mysql container with set env var MYSQL_ROOT_PASSWORD
sudo mysql -h 127.0.0.1 -u root -p  

\G - rows as cols



__Count sizes of tables in DB__
```sql
SELECT table_schema, table_name,
  ROUND((data_length+index_length)/POWER(1024,2),2) AS tablesize_mb
FROM information_schema.tables
ORDER BY tablesize_mb DESC LIMIT 20;
```

__See previous run query, optimized__
`SHOW WARNINGS`

__See previously ran queries__
```sql
SET GLOBAL log_output = 'TABLE';
SET GLOBAL general_log = 'ON';
# change user_host if necessary
SELECT * FROM  mysql.general_log  WHERE command_type ='Query' AND user_host LIKE "root%" ORDER BY event_time DESC LIMIT 100;
```