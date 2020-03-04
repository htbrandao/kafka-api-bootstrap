package kafka_client_bootstrap

import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.slf4j.LoggerFactory

class Profile {

    // TODO: Customize profile for behaviors:
    //  ack for real time producer: "acks" -> all
    //  consumers read only commited
    //  producer as transaction
    //  check classes docs again

    def setDefault(myAppId: String, myGroupId: String, serverList: Array[String]) = {

        val log = LoggerFactory.getLogger(this.getClass)
        log.info(s"ACK @ ${this.getClass} ")

        val prop = new Properties()

        prop.put("application.id", myAppId)
        prop.put("group.id", myGroupId)

        serverList.map(server => prop.put("bootstrap.servers", server))
        // TODO: "WARN org.apache.kafka.clients.consumer.ConsumerConfig - The configuration 'value.serializer' was supplied but isn't a known config"
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

        // TODO: Tunning (e.g.: max.poll.records etc)

        prop
    }

    def setStream(appId: String, groupId: String, serverList: Array[String])= {

        val log = LoggerFactory.getLogger(this.getClass)
        log.info(s"ACK @ ${this.getClass} ")

        val prop = new Properties()
        prop.put("application.id", appId)
        prop.put("group.id", groupId)

        serverList.map(server => prop.put("bootstrap.servers", server))

        prop.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass)
        prop.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass)

        prop
    }

    def setOauth(appId: String, groupId: String, serverList: Array[String]) = {

        val prop = new Properties()
        prop.put("application.id", appId)
        prop.put("group.id", groupId)
        serverList.map(server => prop.put("bootstrap.servers", server))
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        // Oauth
        // prop.put("sasl.jaas.config", "org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required ;")
        // prop.put("security.protocol", "SASL_PLAINTEXT")
        // prop.put("sasl.mechanism", "OAUTHBEARER")
        // prop.put("sasl.client.callback.handler.class", "lib.proper.security.oauthbearer.OauthAuthenticateLoginCallbackHandler")
        // prop.put("sasl.login.callback.handler.class", "lib.proper.security.oauthbearer.OauthAuthenticateLoginCallbackHandler")

        prop
    }


}
