import java.util
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory

class Pipe(propOrigin: Properties, topicOrigin: String, pollTimeout: Long,
           propDestiny: Properties, topicDesinty: String) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val consumer = new KafkaConsumer[String, String](propOrigin)
    consumer.subscribe(util.Collections.singletonList(topicOrigin))
    val producer = new KafkaProducer[String, String](propDestiny)

    def pipe() = {

            val recPoll = consumer.poll(pollTimeout)

            val msgs = recPoll.asScala.map{msg =>
                producer.send(new ProducerRecord(topicDesinty, msg.key(), msg.value() ) )
                val msgToMap = Map("key" -> msg.key(), "value" -> msg.value())
                msgToMap
            }
        msgs
    }

    override def run(): Unit = {
        while (true) pipe()
        producer.close()
    }

}
