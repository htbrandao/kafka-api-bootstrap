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
    
      ######################################################################
      # KAFKA AND ZOOKEEPER________________________________________________#
      ######################################################################
    
      services:
        zookeeper:
          image: wurstmeister/zookeeper
          ports:
            - "2181:2181"
        kafka:
          build: .
          ports:
            - "9092:9092"
          environment:
            KAFKA_ADVERTISED_HOST_NAME: 192.168.99.100
            KAFKA_CREATE_TOPICS: "test:1:1"
            KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
          volumes:
            - /var/run/docker.sock:/var/run/docker.sock
    
      ######################################################################
      # PRODUCER___________________________________________________________#
      ######################################################################
    
      kfkApiProd01:
        container_name: kfkApiProd01
        environment:
          - bhvList=producer
          - groupId=gId_=group_kfkApiProd01
          - appId=app_kfkApiProd01
          - bhv0_topic=topic_name.t1
          - bhv0_bootstrapServers=kafka-1:19092
          - bhv0_sleep=200
        image: kafka_client_bootstrap:1.0.0
        networks:
          - net-backend
        ports:
          - "3601:3601"
    
      #######################################################################
      ## CONSUMER___________________________________________________________#
      #######################################################################
    
      kfkApiCons01:
       container_name: kfkApiCons01
       environment:
         - bhvList=consumer;consumer;consumer
         - groupId=group_kfkApiCons01
         - appId=app_kfkApiCons01
         # Thread 0
         - bhv0_topic=topic_name.t2
         - bhv0_bootstrapServers=kafka_broker-1:9092;kafka_broker-2:29092;kafka_broker-3:39092
         # Thread 1
         - bhv1_topic=topic_name.t2
         - bhv1_bootstrapServers=kafka_broker-1:9092;kafka_broker-2:29092;kafka_broker-3:39092
         # Thread 2
         - bhv2_topic=topic_name.t2
         - bhv2_bootstrapServers=kafka_broker-1:9092;kafka_broker-2:29092;kafka_broker-3:39092
       image: kafka_client_bootstrap:1.0.0
       networks:
         - data-science_net-backend
       ports:
         - "3621:3621"
    
     #####################################################################
     # STREAM____________________________________________________________#
     #####################################################################
    
      kfkApiStream01:
       container_name: kfkApiStream01
       environment:
         - bhvList=stream
         - groupId=gId_=group_kfkApiStream01
         - appId=app_kfkApiStream01
         - bhv0_topic_IN=topic_name.t1
         - bhv0_topic_OUT=topic_name.t2
         - bhv0_bootstrapServers=kafka_broker-1:9092;kafka_broker-2:29092;kafka_broker-3:39092
       image: kafka_client_bootstrap:1.0.0
       networks:
         - data-science_net-backend
       ports:
         - "3611:3611"

10- Hope you enjoy!
