import java.util.Properties
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import org.slf4j.LoggerFactory

class Streamer(properties: Properties, topicOrigin: String, topicDestiny: String) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val builder = new StreamsBuilder()
    builder.stream(topicOrigin).to(topicDestiny)

    val topology = builder.build()

    // Implement parser
    log.info(s"STREAMER TOPOLOGY: ${topology.describe()}")

    val trans = new KafkaStreams(topology, properties)

    def open() = {
        trans.start()
        topology
    }

    def close() = {
        trans.close()
        topology
    }

    override def run(): Unit = open()

}
