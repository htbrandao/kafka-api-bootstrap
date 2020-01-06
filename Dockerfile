FROM gradle:4.10.2-jre8-alpine

COPY build.gradle /home/gradle/bootstrap_kafka_api/
COPY settings.gradle /home/gradle/bootstrap_kafka_api/
COPY src/ /home/gradle/bootstrap_kafka_api/

USER root
RUN chown -R gradle:gradle /home/gradle/bootstrap_kafka_api

USER gradle
WORKDIR /home/gradle/bootstrap_kafka_api
RUN gradle uberjar
RUN chmod 777 out/bootstrap_kafka_api/libs/bootstrap_kafka_api.jar
ENTRYPOINT java -jar out/bootstrap_kafka_api/libs/bootstrap_kafka_api.jar
