# =====================================================================
# Build inside container

# FROM gradle:4.10.2-jre8-alpine
#
# COPY build.gradle /home/gradle/kafka_client_bootstrap/
# COPY settings.gradle /home/gradle/kafka_client_bootstrap/
# COPY src/ /home/gradle/kafka_client_bootstrap/
#
# USER root
# RUN chown -R gradle:gradle /home/gradle/kafka_client_bootstrap
#
# USER gradle
# WORKDIR /home/gradle/kafka_client_bootstrap
# RUN gradle uberjar
# RUN chmod 777 build/kafka_client_bootstrap/libs/kafka_client_bootstrap.jar
#
# ENTRYPOINT java -jar build/kafka_client_bootstrap/libs/kafka_client_bootstrap.jar

# =====================================================================
# Build locally

FROM gradle:4.10.2-jre8-alpine

USER root

RUN mkdir /kafka_client_bootstrap

COPY build/kafka_client_bootstrap/libs/kafka_client_bootstrap.jar /kafka_client_bootstrap/

ENTRYPOINT java -jar /kafka_client_bootstrap/kafka_client_bootstrap.jar