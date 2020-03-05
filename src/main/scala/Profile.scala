import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.slf4j.LoggerFactory

class Profile {

    def setDefault(appId: String, groupId: String, serverList: Array[String]) = {

        val log = LoggerFactory.getLogger(this.getClass)
        log.info(s"ACK @ ${this.getClass} ")

        val prop = new Properties()

        prop.put("application.id", appId)
        prop.put("group.id", groupId)

        serverList.map(server => prop.put("bootstrap.servers", server))
        
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

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

}
