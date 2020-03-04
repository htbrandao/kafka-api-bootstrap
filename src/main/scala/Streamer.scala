import java.util.Properties
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}
import org.slf4j.LoggerFactory

class Streamer(myProp: Properties, mytopicOrg: String, mytopicDest: String) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val builder = new StreamsBuilder()
    builder.stream(mytopicOrg).to(mytopicDest)

    val topology = builder.build()

    // Implement parser
    log.info(s"STREAMER TOPOLOGY: ${topology.describe()}")

    val trans = new KafkaStreams(topology, myProp)

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
