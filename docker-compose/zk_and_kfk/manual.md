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
