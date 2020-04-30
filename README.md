## Kafka API Bootstrap

1 - If some sort of authentication is needed, and isn't solved by a external repo (e.g.: Maven lib/dependency), add the lib to the applications's path (e.g: libs/my-auth-lib.jar).

1.1 - Also, uncomment one of the suggestion in  **build.gradle** so local libs/jars can be inherited into the app (e.g.: fileTree(include: ['*.jar'], dir: 'libs')

2 - $ docker build . -t $APP:$VERSION

3 - Create docker services and set environment variables in **docker-compose.yml**

4 - $ docker-compose up -d

Hope you enjoy!

# start zookeeper and kafka

    docker-compose up -d

# connect to container

    docker exec -it zk_kfk_kafka1_1 bash

# list existing topics

    kafka-topics --zookeeper zoo1:2181 --list

# create topics: topic1, topic2, topic3

    kafka-topics --zookeeper zoo1:2181 --create --partitions 1 --replication-factor 1 --topic topic1

    kafka-topics --zookeeper zoo1:2181 --create --partitions 1 --replication-factor 1 --topic topic2

    kafka-topics --zookeeper zoo1:2181 --create --partitions 1 --replication-factor 1 --topic topic3

# create a console consumer for topic1 inside container

    kafka-console-consumer --bootstrap-server kafka1:9092 --topic topic1

# create a console producer for topic1 inside container

    kafka-console-producer --broker-list kafka1:9092 --topic topic1
