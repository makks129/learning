# CCP Notes

-------------------------------------------------

## Cloud Computing and AWS intro

Advantages of the cloud:
- flexibility
- cost-effectiveness
- scalability
- elasticity
- high-availability and fault-tolerance
- agility

AWS regions and service:
- Some services are global
- Most services are regional
- Some service are available only in specific regions (if not found in current region, might need to switch region)

[Shared responsibility model](https://aws.amazon.com/compliance/shared-responsibility-model/)

-------------------------------------------------

## IAM

Identity and Access Management

Global service

### Users and Groups
- Root account shouldn't be used or shared
- Users are people within the org
- Groups contain users (not other groups)
- Users can belong to no groups or multiple groups

Principle of least privilege

### Policies
Group policies
Inline policies (for users directly)

Statement:
- Sid
- Effect
- Principal
- Action
- Resource
- Condition

### MFA
Password + security device

Virtual:
- Google Authenticator (phone only)
- Authy (multi-device)
- others
Physical:
- U2F Security Key
- Hardware Key Fob MFA device
- Hardware Key Fob MFA device for AWS GovCloud 

### How to access AWS
- Management console (password + MFA)
- CLI (access keys)
- SDK (access keys)

### Create access keys
`IAM > Users > user > Security Credentials`
Add access key to CLI:
```sh
aws configure
```

### IAM Roles for Services
`IAM > Roles`
Assign roles to service to perform actions
Roles contain permissions

### Security tools
- IAM Credentials report (`IAM > Credential report`)
- IAM Access Advisor (`IAM > Users > Access Advisor`)

### Shared responsibility
AWS:
- Infra
- Configuration and vulnerability analysis
- Compliance validation
You:
- Users/Groups/Roles/Policies
- MFA
- Rotating keys
- Applying permissions
- Analyze access patterns and review permissions

### CLI commands
```sh
aws iam list-users
```

-------------------------------------------------

## EC2

### Sizing and config options
- OS
- CPU
- RAM
- Storage space
	- network-attached (EBS, EFS)
	- hardware (EC2 Instance Store)
- Network card (speed, IP)
- Firewall rules (security group)
- Bootstrap script (EC2 User Data)

### EC2 User Data
Script that runs ones when machine starts (bootstrap)
Install updates, software, download files, etc
Runs as root user

### Launching a new instance
Public IP will change every time you start/stop
Private IP will remain the same

### Instance types
Naming convention: `m5.2xlarge`
- m - instance class
- 5 - generation
- 2xlarge - size

Instance types: 
- https://aws.amazon.com/ec2/instance-types/
- https://instances.vantage.sh/

- General purpose
	- balance between CPU/memory/networking
- Compute optimized
	- batch processing
	- media transcoding
	- high perf web servers
	- ML
	- gaming servers, etc
- Memory optimized
	- fast perf of large data sets in memory
	- high perf for databases
	- distr cache stores, etc
- Storage optimized
	- storage intensive tasks
	- relational / NoSQL dbs
	- in-memory cache dbs
	- DW apps
	- distr file systems

### Security groups
- Firewall
- Control traffic in/out of EC2 instance
- Regulate:
	- access to ports
	- authorized IP ranges
	- control inbound/outbound traffic

- Many-to-many relationship with instances
- Locked to region/VPC
- Good to maintain a separate security group for SSH

- Only contain `allow` rules
- By default all inbound is __blocked__, all outbound is __authorized__
- Security groups can reference other security groups

### Ports
`22` - SSH
`21` - FTP
`22` - SFTP (SSH FTP)
`80` - HTTP
`443` - HTTPS
`3389` - RDP (Remote Desktop Protocol, log into Windows instance)


### SSH
```sh
chmod 0400 <private-key>.pem
ssh -i <private-key>.pem ec2-user@<public-ip>
```
`ec2-user` - default user in AWS Linux AMI

### Purchasing options
- On demand
	- highest cost
	- no long-term commitment
	- for short-term un-interrupted workloads
- Reserved (standard / convertible / scheduled)
	- discounts
	- reservation period (1-3y)
	- upfront payment options
	- scope: regional or zonal
	- can be bought/sold in Reserved Instance Marketplace
	- convertible reserved instance: can change some attributes
	- for steady-state usage apps (like dbs)
- Savings
	- discounts
	- commit to certain type of usage
	- usage beyond is billed like on-deman
	- locked: instance family and region
	- flexible: OS, size, tenancy
- Spot
	- cheapest
	- for workloads that can fail any time
	- not for critical jobs / dbs
- Dedicated host
	- most expensive
	- physical server w EC2 instance capacity
	- on-demand or reserved
	- for companies that have strong reg/compliance needs
- Dedicated instance
	- instance runs on hardware dedicated to you
	- your other instances may share this hardware
	- no control over instance placement
- Capacity reservation
	- reserve on-demand instances in specific AZ
	- no time commitment, no discounts
	- charges at on-demand rate (if using or not)
	- for short-term un-interrupted workloads in specific AZ

### Shared responsibility
AWS:
- Infra
- Isolation on physical hosts
- Replacing hardware
- Compliance validation
You:
- Security group rules
- OS patches/updates
- Software on EC2
- IAM roles
- Data security on EC2

-------------------------------------------------

## EC2 Storage

### EBS
Elastic Block Store
- __Network__ drive that can be attached to EC2 instances
- Persists data
- Can be mounted to 1 instance (EBS Multi-attach feature is out of scope of CCP)
- Have provisioned capacity (billed by capacity)
- Bound to AZ

- Can be mounted/unmounted to EC2 instance
- Can `Delete on termination`

#### EBS Snapshots
Backup of EBS volume
- Don't need to detach before, but recommended
- Can copy snapshots across AZ/Region
- Snapshot archive
	- move to "archive tier" (75% cheaper)
	- restoring takes 24-72h
- Recycle bin
	- protect deletion
	- setup retention rules

### EC2 Instance Store
EC2 instances have physical hardware disks with high-performance
- __Ephemeral__ - EC2 instance looses storage if stopped
- Good for cache, etc
- Backups/replication - your responsibility

### EFS
Elasic File System
- Managed __shared__ network file system (NFS)
- Can be mounted to many EC2 instances
- Works across AZs
- Pay per use (no capacity planning)

#### EFS-IA
EFS Infrequent Access
- Storage class for infrequently accessed files (cheaper)
- Enabled EFS-IA with a Lifecycle Policy
- No difference for file usage, only cost-optimization

### Shared responsibility
AWS:
- Infra
- Replication for EBS/EFS
- Replace hardware
- Ensure secure physical data access
Your:
- Backup / snapshot
- Data encryption
- Responsibility on any data
- Risk of EC2 Instance store

### AMI
Amazon Machine Image
- Customization of EC2 instance
- Built for specific region (can be copied across regions)
AMIs:
- Public (from AWS)
- Your own
- AWS Marketplace AMI

### EC2 Image Builder
Service
Automates the creation, maintainance, validation, testing of EC2 AMIs

### Amazon FSx
Launch 3rd party high-perf file sustems on AWS
- Fully managed service
- 3 types:
	- FSx for Lustre
		- For HPC (high-perf computing): ML, analytics, etc.
		- 100s of GB/s, millions of IOPS, etc.
	- FSx for Windows File Service
	- FSx for NetApp ONTAP


-------------------------------------------------

## ELB & ASG
Elastic Load Balancing & Auto Scaling Groups

__Scalability__
- Vertical (scaling up/down)
	- has a hardware limit
- Horizontal (scaling out/in)
	- requires a distr system

__Elasticity__ - ability for the system to scale based on the load (pay per use, match demand, optimize cost)

__High Availability (HA)__
- Running your system in min 2 AZs

__Load balancer__
- Spreads the load between EC2s
- Single point of access (DNS)
- Regular health checks for instances
- HTTPS
- HA across AZs

### ELB
Elastic Load Balancer

__3 kinds of LB__
- Application LB - Layer 7
	- HTTP/HTTPS/gRPC only
	- HTTP routing
	- Static DNS (URL)
- Network LB - Layer 4
	- Allows TCP/UDP
	- Ultra high-perf (mlns req/sec)
	- Static IP (Elastic IP)
- Gateway LB - Layer 3
	- GENEVE protocol
	- Routes traffic to EC2 firewalls,  3rd party security virtual appliances
	- Intrusion detection / deep packed inspection

### ASG
Auto Scaling Group

- Scale in/out
- Ensure min/max num of machines running
- Auto register instances to LB
- Replace unhealthy instances
- Cost savings

Min size -- desired capacity (actual size) -- max size

__Scaling strategies__
- Manual scaling (update sime of ASG manually)
- Dynamic scaling (respond to changing demand)
	- Simple/step scaling
	- Target tracking scaling
	- Scheduled scaling
- Predictive scaling
	- Uses ML to predict traffic

-------------------------------------------------

## S3

__Use cases__
- Backup and storage
- DR
- Archive
- App/media hosting
- Big data
- Static websites, etc

__Buckets__
- S3 stores objects (files) in buckets (dirs)
- Defined at region level
- Buckets have globally unique names

__Objects__
- Object key is full path (prefix + object name)
- Example: `s3://my-bucket/my_folder/my_file.txt`
	- `my_folder/` - prefix
	- `my_file.txt` - object name
	- `my_folder/my_file.txt` - key
- Max object size is 5TB
- If object size >5GB use "multi-part upload"
- Metadata
- Tags
- Version ID

### Security
User-based
- IAM policies
Resource-based
- Bucket policies
- Object ACLs
- Bucket ACLs

Bucket settings for Block Public Access

To make bucket objects public:
- Bucket > Permissions > Block public access -- turn OFF
- Bucket policy -- add * allow public get object
```json
{
    "Version": "2012-10-17",
    "Id": "Policy1692860887431",
    "Statement": [
        {
            "Sid": "Stmt1692860880828",
            "Effect": "Allow",
            "Principal": "*",
            "Action": "s3:GetObject",
            "Resource": "arn:aws:s3:::makks-demo-s3/*"
        }
    ]
}
```

### Static Website Hosting
Bucket > Properties > Static website hosting -- Enable

### Versioning
- Enabled on bucket level
- Same key overwrite will change version: 1,2,3..
- Protect against unintended delete
- Easy rollback
- Notes:
	- files before versioning will have version `null`
	- suspending versioning doesn't delete prev versions

Deleting versions:
- Permanent delete of a version
- Deleting the only version of a file will not permanently delete the file but add "Delete marker" to it, which is just another version (it can also be deleted to restore the file)

### Replication
__CRR__ - Cross Region Replication
__SRR__ - Same Region Replication
- Buckets can be in different AWS accounts
- Need to have proper IAM permissions
- Versioning must be enabled
- Copying is async

Bucket > Management > Replication rules

### Storage classes
Storage class is a property of an object

__Durability__
- 99.999999999% (11 9s)
- Same for all storage classes

__Availability__
- 99.99% (unavailable 53min/year)
- Depends on storage class

- Standard - General Purpose
	- Availability: 99.99%
	- Freq accessed data
	- Low latency, high throughput
	- Use cases: big data, mobile/gaming, content distr..
- Standard - Infrequent Access (IA)
	- Availability: 99.9%
	- Infrequent but rapid access
	- DR, backups
	- Lower cost
- One Zone - Infrequent Access
	- Availability: 99.5%
	- High durability in 1 AZ, data lost if AZ is destroyed
	- Storing secondary backups
- Glacier Instant Retrieval
	- Glacier is for low-cost object storage (archives/backups)
	- Pricing: storage + retrieval
	- Retrieval in ms
	- Min storage duration 90 days
- Gracier Flexible Retrieval
	- Retrieval: expedited (1-5min), standard (3-5h), bulk (5-12h)
	- Min storage duration 90 days
- Glacier Deep Archive
	- Retrieval: standard (12h), bulk (48h)
	- Min storage duration 180 days
- Intelligent Tiering
	- Auto moves objects bw tiers based on usage
	- Small monitoring fee
	- Tiers:
		- Frequent Access
		- Infrequent Access
		- Archive Instant
		- Archive Access
		- Deep Archive

Automate tier transitions for objects in a bucket:
Bucket > Management > Lifecycle rules -- Create a rule

### Encryption
- Server-side (default, always ON)
- Client-side

### Snow family
Physical devices to collect and process data at the edge and migrate data to/out of AWS
If it takes >week to migrate data, use snow device

- Data migration
	- Snowcone
	- Snowball Edge
	- Snowmobile
- Edge computing (no internet access, e.g. a ship or a mine)
	- Snowcone
	- Snowball Edge

__Snowball Edge__
- Physical data transport (alternative to moving data via network)
- Pay per data transfer job
- Types: Storage Optimized or Compute Optimized
- Use cases: large migrations, DR, etc

__Snowcone__
- Smaller device (up to 8TB)
- Types: Snowcone, Snowcone SSD
- DataSync agent

__Snowmobile__
- Actual truck
- Transfer Exabytes of data (100 PB per truck)

__OpsHub__ - software to manage Snow Family devices

### Hybrid cloud
To use AWS with on-premise storage
__AWS Storage Gateway__ is used to bridge on-premises servers and AWS Cloud (EBS, S3, Glacier)

### Shared responsibility
AWS:
- Infra
- Compliance validation
You:
- Versioning
- Bucket policies
- Replication
- Logging/monitoring
- Storage class
- Data encryption

-------------------------------------------------

## Databases and Analytics

__AWS managed DBs__
- provisioning, HA, vert/hor scaling
- auto backup
- upgrades and OS patching
- monitoring, alerting

__Summary__
- Relational DBs (OLTP): RDS & Aurora (SQL)
	- Deployments: Read replicas, Multi-AZ, Multi-region
- In-memory DB/cache: ElasticCache
- K-V DB: DynamoDB (serverless) + DAX (cache for DynamoDB)
- Warehouse (OLAP): Redshift (SQL)
- Hadoop Cluster: EMR
- Athena: query data on S3 (serverless & SQL)
- QuickSight: data dashboards (serverless)
- DocumentDB: like MongoDB (JSON, NoSQL)
- Neptune: graph DB
- Amazon QLDB: financial transaction ledger (immutable, cryptographic)
- Amazon Managed Blockchain: managed blockchain
- Glue: managed ETL and data catalog
- DMS: DB migrations


### RDS
Relational Database Service
- SQL
- Postgres, MySQL, MariaDB, Oracle, Microsoft Server, Aurora (AWS DB)
- Multi AZ setup
- Storage backed by EBS
- Can't SSH into it

#### RDS deployments
__Read replicas__
- Scale reads
- Up to 15 read replicas
__Multi-AZ__
- Failover in case of AZ outage (HA)
- Failover DB in different AZ (replicated)
- Only 1 AZ as failover
__Multi-region__
- Read replicas in different region
- Writes still happen across to original region 
- Used in case of region outage
- Replication cost

#### Aurora
- MySQL and Postgres are supported as Aurora DB
- 3-5x performance improvement
- Auto-grows by 10GB up to 128TB
- Costs more

### ElastiCache
- Same as RDS for relational DBs but for Redis/Memcached
- In-memory, high-perf

### DynamoDB
- NoSQL
- K-V DB
- Serverless
- Millions of reqs/sec, trillions of rows, 100s of TBs of storage
- Low latency (in ms)
- Standard + Infrequent Access table class

__DynamoDB Accelerator (DAX)__
- In-memory cache for DynamoDB
- 10x perf improvement (to microsecond latency)

__Global tables__
- Low latency in multiple regions
- Active-active replication:
	- Reads and writes to any region
	- 2-way replication bw regions

### Redshift
- Based on Postgres
- Columnar storage (not row based)
- SQL interface
- Load data once/hour
- 10x perf comparing to other DW
- MPP (massively parallel query exec)
- Scales to PBs of data
- For OLAP (online analytical processing, for analytics and DW)
- Integration w AWS Quicksight / Tableau (BI tools)

### EMR
Elastic MapReduce
- For Hadoop cluster (100s of EC2s)
- Supports Apache Spark, HBase, Presto, Flink
- EMR take care of provisioning, config, auto-scaling, etc
- For Big Data, ML, etc

### Athena
- Query service to perform analytics against S3 objects
- Serverless
- SQL
- Reporting based on Athena - Amazon QuickSight
- Use cases: BI, analytics, reporting, analyze logs, etc

### Amazon QuickSight
- Serverless ML-powered BI service
- Visualize DB data on dashboards
- Use cases: BI, visualizations, analysis, etc
- Integrations: RDS, Aurora, Athena, Redshift, S3, etc

### DocumentDB
- AWS implementaton of MongoDB (NoSQL) -- same as Aurora is to Postgres/MySQL
- JSON data
- Similar "deployments" to Aurora
- Fully managed
- HA, replicated across 3 AZs
- Auto-grows by 10GB up to 64TB

## Neptune
- Graph DB
- HA, replicated across 3 AZs (up to 15 replicas)
- Use cases: knowledge graphs (wikis), fraud detection, recommendations, social net, etc

### QLDB
Quantum Ledger DB
- Records financial transactions (ledger)
- SQL
- Fully managed
- HA, replicaed across 3 AZs
- Review history of all changes made to your app data over time
- Immutable
- Cryptographically verifiable
- Centralized DB (not decentralized like Amazon Managed Blockchain)

### Amazon Managed Blockchain
- Managed blockchain network
- Join public blockchain networks / create own private network
- Compatible w Ethereum, Hyperledger Fabric

### AWS Glue
- Managed __extract, transform and load (ETL)__ service
- Serverless
- Prepare and transform data for analytics
- Also see Glue Data Catalog (catalog of datasets)

### DMS
Database Migration Service
- Supports: homogeneous (from/to same type of DB) and heterogeneous (different types of DB) migrations
- Source DB remains available

-------------------------------------------------

## Other Compute Services

### ECS
Elastic Container Service
- Launch docker on AWS
- Provisioning of EC2 is on you
- Running containers is on AWS

### Fargate
- Serverless docker on AWS
- No need to provision EC2

### ECR
Elastic Container Registry
- Private docker registry on AWS

-------------------------------------------------

## Serverless

Serverless means you don't need to provision/manage/see servers
Examples: S3, DynamoDB, Fargate, Lambda, etc

### Lambda
- Virtual functions
- Short executions
- Event-driven: they get invoked by AWS when needed (reactive type of service)
- Scaling is automated
- Function time limit is 15 min

__Benefits__
- Pay per request/compute time (based on calls and on duration)
- Many programming languages
- Easy monitoring
- Easy to get resources per func

__Lambda Container Image__
- Run docker containers in Lambda, but they need to implement Lambda Runtime API

### Amazon API Gateway
- Create APIs for Lamdba functions (for external clients to access Lambda code)
- Serverless, fully managed
- REST/WS
- Supports: security, auth, API throttling, API keys, monitoring, etc

### AWS Batch
- Batch processing
- Fully managed
- Batch jobs are defined as docker images and run on ECS
- Not serverless as ECS is run on EC2

### Amazon Lightsail
- Virtual servers, storage, DBs, networking (simpler alternative to EC2, RDS, ELB, etc)
- Low and predictable pricing
- Use case: for people with little cloud exp - simple web site, or dev/test envs

-------------------------------------------------

## Deployments & Managing Infra at Scal

### CloudFormation
- Declarative way to provision and config infra
- IaaC

### AWS CDK
Cloud Development Kit
- Define infra in a programming language, which will get compiled into CloudFormation template YAML

### Elastic Beanstalk
- Higher level abstraction over CloudFormation that provides complete archs to deploy
- PaaS
- Fully managed

__3 arch models__
- Single instance deployment (good for dev)
- ELB + ASG (good for prod)
- ASG only (good for non-webapp in prod, like workers, etc)

__Monitoring__
- Health agent pushes metrics to CloudWatch

### AWS CodeCommit
- Code repository (like GitHub)
- git based
- Private, secure, integrated w AWS services

### AWS CodeBuild
- Code building service
- Compiles code, runs tests, produces packages for deployment
- Fully managed, serverless

### AWS CodeDeploy
- To deploy/upgrade infra automatically
- Hybrid service (works with both AWS and on-premise infra)
- Uses CodeDeploy Agent that assists with upgrades

### AWS CodePipeline
- Orchestrate different steps of deployment pipeline
- Basis for CI/CD on AWS
- Example: get code from CodeCommit -> build it with CodeBuild -> deploy with CodeDeploy onto Elastic Beanstalk

### AWS CodeArtifact
- Artifact management system
- Place to store code dependencies
- Devs and CodeBuild can store and retrieve dependencies from CodeArtifact

### AWS CodeStar
- UI to manage software development activities (AWS Code*) in 1 place

### AWS Cloud9
- Cloud IDE
- Online (in-browser)
- Has code collab

### AWS Systems Manager (SSM)
- Helps manage EC2 and on-premise systems at scale
- Hybrid service
- Most important features:
	- Automated patching
	- Run commands across entire fleet
	- Store param config with SSM Parameter Store
- Works through SSM Agent on instances

#### SSM Session Manager
- Allows to start secure shell on EC2s (or on-premise)
- No SSH needed (so no port 22 needed, hence better security)
- Flow: user with correct IAM permissions can execute commands via Session Manager that will be run by SSM Agent

### AWS OpsWorks
- Managed Chef & Puppet
- Hybrid service
- Alternative to SSM
- Can only provision standard AWS resources (EC2, DBs, LBs, EBS volumes, etc)

-------------------------------------------------

## Global Infra

__Why go global?__
- Decreased latency
- DR
- Attack protection

__Global apps__
- Route 53 (DNS)
- CloudFront (CDN)
- S3 Transfer Acceleration
- Global Accelerator

__Global Architecture__
- Single region, single AZ
- Single region, multi AZ
- Multi region, active-passive (only 1 region for writes, many regions for reads)
- Multi region, active-active (reads/writes to many regions)

### Route 53
__Records__
- Domain to IPv4 (A record)
- Domain to IPv6 (AAAA record)
- Domain to domain (CNAME)
- Domain to AWS resource (Alias)

__Routing policies__
- Simple (no health checks)
- Weighted (LB)
- Latency
- Failover (DR)

### CloudFront
CDN at edge locations

__Origins__
- S3 bucket
	- Enhanced security w Origin Access Control (OAC)
	- CloudFront can be used as ingress to upload files to S3
- Custom Origin (HTTP)

### S3 Transfer Acceleration
- Use case: upload file from one region to a bucket in another region
- File is uploaded to an edge location and then via private network to another region S3 bucket
- [Speed comparison](https://s3-accelerate-speedtest.s3-accelerate.amazonaws.com/en/accelerate-speed-comparsion.html)

### AWS Global Accelerator
- Improve global avail and perf using AWS internal network
- Traffic goes to an edge location and from their via AWS network to requested region's LB
- [Speed comparison](https://speedtest.globalaccelerator.aws/)

__Global Accelerator vs CloudFront__
- CF: serves content at the edge
- GA: proxies requests to other regions

### AWS Outposts
- AWS-managed __on-premise__ servers ("Outpost Racks")
- Makes it possible to use AWS services with on-premise infra
- All benefits of on-premise: low-latency, local data processing, data residency, etc

### AWS WaveLength
- Infra deployed at telecom's datacenters at the adge on 5G
- Ultra-low latency  
- Use cases: smart cities, connected vehicles, AR/VR, etc

### AWS Local Zones
- Place AWS services closer to end users for low-latency
- Add Local Zones to your region and create a subnet access it

In `EC2 > Settings > Zones` you will see:
- Local Zones
- WaveLength Zonez
- AZ

-------------------------------------------------

## Cloud Integrations

### SQS
Simple Queue Service
- Used to decouple applicaitons
- Messages are deleted after been consumed
- Default retention 4 days (max 14)
- Serverless, fully managed

__Types of queue__
- Standard
- FIFO (produces messages in order)

### SNS
Simple Notification Service
- Pub-sub
- Each subscriber will get all the messages
- No retention 
- Limits: 12,5mln subs per topic, 100k topics
- Subs: SQS, Lambda, Kinesis, Email, SMS/Mobile notif, HTTP endpoints

### Kinesis
- Real-time data streaming
- Managed
- Subservices:
	- Kinesis Streams (receiving data from clicks/IoT/metrics/etc)
	- Kinesis Analytics (for analysis)
	- Kinesis Firehose (send output to S3/Redshift)

### MQ
Message Queue
- Managed broker service for RabbitMQ and ActiveMQ
- Doesn't scale as much as SQS/SNS
- Runs on servers

-------------------------------------------------

## Cloud Monitoring

### CloudWatch Metrics
CloudWatch provides metrics for every service in AWS

__Important Metrics__
- EC2: CPU, Status checks, Network, (not RAM) -- defaults to every 5min
- EBS: Disk read/write
- S3: BucketSizeBytes, NumOfObjects, AllRequests
- Billing: Total estimated charge (only available in us-east-1 region)
- Service limits
- Custom metrics

### CloudWatch Alarms
- Trigger notifications for metrics
- Use cases:
	- Auto-scaling
	- EC2 actions (stop, reboot, etc)
	- SNS notifications

### CloudWatch Logs
- Logs collected from:
	- Elastic Beanstalk
	- ECS
	- Lambda
	- CloudTrail
	- CloudWatch Log Agents (on EC2 or on-premise)
	- Route53
- Real-time monitoring of logs

### EventBridge
- Schedule cronjobs
- Events on service doing something
- Sources/Destinations
	- a lot of AWS services
	- sources can also be 3rd party partners (Datadog, etc)
	- your custom apps
- Schema registry

### CloudTrail
History of all events, API calls in AWS account
- Governance, compliance, audit
- Enabled by default
- Can be sent to CloudWatch Logs or S3

### X-Ray
Tracing and visual analysis of app
- Usage:
	- Troubleshooting
	- Understand dependencies
	- Pinpoint issues
	- Review requests
	- Meeting SLAs
	- Identify impacted users

### CodeGuru
ML-powered service for automated code reviews and app perf recommendations
- CodeGuru Reviewer: review code
- CodeGuru Profiler: detect and optimize code on build and in live (of apps in AWS or on-premise)

### AWS Health Dashboard
- Service History: all regions, all services
- Your Account: events that impact you directly (personalized)
- Global service
- Shows AWS outages that impact you, etc

-------------------------------------------------

## VPC & Networking

__Virtual Private Cloud__ - private network for your resources (regional)

### IP addresses in AWS
- IPv4
	- EC2 private IPv4 is constant, public change on start/stop
- Elastic IP
	- Attach a fixed public IPv4 to EC2 instance
- IPv6
	- Public (no private range)

### Subnets
Subnets partition network in VPC
- 1 VPC = 1 region
- 1 VPC can span several AZs in that region
- Each AZ in the subnet can have its own public/private subnets
	- Public subnet (accessible on the internet)
		- Internet Gateway
	- Private subnet
		- NAT Gateway (for private subnet to access the internet) 
	- Route tables - used to define access to outside and bw subnets
- VPC has IP range ([CIDR Range](https://cidr.xyz/))

### Network security
- NACL (Network ACL)
	- Firewall, controls traffic __from/to a subnet__
	- Rules only include IP addresses
	- Allow/Deny rules
	- Stateless
- Security Groups
	- Firewall, controls traffic __from/to an EC2 instance__
	- Rules include IP addresses or other security groups
	- Only Allow rules
	- Stateful

### VPC Flow Log
Info about IP traffic into interfaces:
- VPC
- Subnet
- Elastic Network Interface (ENI)
Troubleshooting

### VPC Peering
- Connect 2 VPCs (only bw 2 VPCs)
- IP addrs shouldn't overlap

### VPC Endpoints
Connect to AWS services using private network
- VPC Endpoint Gateway: S3 & DynamoDB
- VPC Endpoint Interface: the rest

### PrivateLink
Expose a service to 1000s of VPCs
Private link between 3rd party VPC (NLB on their side) and your VPC (ENI on your side)

### Connect on-premises to AWS VPC
- Site to Site VPN
	- Traffic goes via VPN on public internet
	- On-premises uses Customer Gateway (CGW)
	- On AWP VPC uses Virtual Private Gateway (VGW)
- Direct Connect (DX)
	- Physical connection to AWS partner
	- Private network
	- Slower to establish
	- Faster speed

### Client VPN
Privately connect to AWS VPC from your machine
- AWS Client VPC (OpenVPN) on your machine
- Over public internet
- Will also be connected to on-premises network if Site-to-Site VPN is on

### Transit Gateway
- Solve complicated networking arch
- Transitive peering bw 1000s of VPCs, on-premises, etc

-------------------------------------------------

## Security & Compliance

### Shared responsibility model
[Shared responsibility model](https://aws.amazon.com/compliance/shared-responsibility-model/)

__AWS__
Security __of__ the cloud
- Infra
- Managed services
__You__
Security __in__ the cloud
- EC2: OS updates, firewall, network, IAM
- Encryption of app data
__Shared__
- config management, awareness, training

### DDOS protection
- AWS Shield Standard
- AWS Shield Advanced
- AWS WAF (filter reqs by rules)
- CloudFront & Route53 (global edge network)

### AWS Shield
- Standard
	- Free, activated for all
- Advanced
	- Additional protection + response team
	- 3000$/month

### AWS WAF
Web Application Firewall
- Layer 7 (HTTP)
- Deploy on ALB, API Gateway, CloudFront
- Define Web ACL
	- Rules by IPs, HTTP headers, body, URI strings, etc
	- Size constraints
	- Geo-match (block countries)
	- Rate-based rules

### AWS Network Firewall
Protect a VPC
- Layer 3 to 7
- Any direction traffic

### Pentesting
[AWS Penetration testing](https://aws.amazon.com/security/penetration-testing/)

You can pentest against AWS infra __without prior approval__ for 8 service:
- EC2, NAT Gateways, ELBs, RDS, CloudFront, Aurora, API Gateways, Lambda, Lightsail, Beanstalk
__Prohibited:__
- DNS zone walking
- DOS attacks
- Port/protocol/request flooding

### Encryption
__2 types__
- Encryption of data at rest
- Encryption of data in transit

#### AWS KMS
Key Management Service
- AWS manages encryption keys
- Some services have opt-in encryption, some auto

#### CloudHSM
Hardware Security Module
- Self-managed keys, AWS provisions only hardware

#### Customer Master Key (CMK)
- Customer-managed CMK: create, manage rotation policy, etc
- AWS-managed CMK: created, managed and used by AWS services
- AWS-owned CMK: keys AWS services use for multiple accounts
- CloudHSM keys: keys generated by CloudHSM hardware device

### AWS Certificate Manager (ACM)
Provision, manage, deploy SSL/TLS certs
- Both public and private TLS certs
- Auto-renewal of TLS certs

### AWS Secrets Manager
Stores secrets
- Can force rotation
- Automate generation using Lambda
- Integration w RDS
- Encryption w KMS

### AWS Artifact
Portal w access to AWS compliance docs and AWS agreements
- To support internal audit or compliance

### AWS GuardDuty
Threat discovery to protect AWS account
- ML algos, anomaly detection, etc
- Need to be enabled
- Input data: service/DNS/VPC logs, events, and more can be enabled
- (Can protect against CryptoCurrency attacks)

### Amazon Inspector