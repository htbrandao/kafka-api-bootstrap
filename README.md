### Kafka API Bootstrap


1- Clone the repository: $ git clone _URL_

2- Add content to the app (e.g: A method to produce **your** relevant data) or import it as a lib

2.1- If some sort of authentication is needed, and isn't solved by a external repo (e.g.: Maven lib/dependency), add the lib to the applications's path (e.g: libs/my-auth-lib.jar). Also, uncomment one of the suggestion in  **build.gradle** so local libs/jars can be inherited into the app (e.g.: fileTree(include: ['*.jar'], dir: 'libs')

3- _docker build . -t $APP:$VERSION_

4- Create docker services and set enviroment variables in **docker-compose.yml**

5- _docker-compose up -d_

9- Example "docker-compose.yml":

    version: '3'
    services:

    # Producer:
        kfkApi-Producer:
        container_name: myBehaviorApiProd
        environment:
            - bhvList=producer
            - groupId=appgroupid
            - appId=myappid
            - bhv0_topic=topicname
            - bhv0_bootstrapServers=broker_kafka-1:9092;broker_kafka-2:9092;broker_kafka-3:9092
            - bhv0_sleep=2000
        image: $APP:$VERSION  
        network_mode: network_name
        ports:
          - "6601:6601"
    
    # Two consumers:
        kfkApi-Consumer:
        container_name: myBehaviorApiCons
            - bhvList=consumer;consumer;consumer
            - groupId=appgroupid
            - appId=myappid
            - bhv0_topic=topicname
            - bhv0_bootstrapServers=broker_kafka-1:9092;broker_kafka-2:9092;broker_kafka-3:9092
            - bhv1_topic=topicname
            - bhv1_bootstrapServers=broker_kafka-1:9092;broker_kafka-2:9092;broker_kafka-3:9092
        image: $APP:$VERSION
        network_mode: network_name
        ports:
            - "6621:6621"
    
    # Stream:
        kfkApi-Stream:
        container_name: myBehaviorApiStream
        environment:
            - bhvList=stream
            - groupId=appgroupid
            - appId=myappid
            - bhv0_topic_IN=topicname
            - bhv0_topic_OUT=anothertopicname
            - bhv0_bootstrapServers=broker_kafka-1:9092;broker_kafka-2:9092;broker_kafka-3:9092
        image: $APP:$VERSION
        network_mode: network_name
        ports:
        - "6621:6621"
    # EOF
    

10- Hope you enjoy!
