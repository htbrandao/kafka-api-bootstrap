import java.time.LocalDateTime
import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.slf4j.LoggerFactory

class Producer(myProp: Properties, topic: String, sleepTime: Int) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val producer = new KafkaProducer[String, String](myProp)

    def produce(recKey: String, recVal: String) = producer.send(new ProducerRecord(topic, recKey, recVal))

    // TODO: Impelemt your own message producer
    val msg = new Message()

    override def run(): Unit = {
        while (true) {
            // TODO: Plug it here
            val keyVal = msg.dummy()
            produce(keyVal._1, keyVal._2)
            Thread.sleep(sleepTime)
        }
        producer.close()
    }

}
