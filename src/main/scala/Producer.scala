import java.time.LocalDateTime
import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.slf4j.LoggerFactory

class Producer(myProp: Properties, myTopic: String, sleepTime: Int) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val producer = new KafkaProducer[String, String](myProp)

    def produce(recKey: String, recVal: String) = producer.send(new ProducerRecord(myTopic, recKey, recVal) )


    // main
    override def run(): Unit = {
        while (true) {
            produce("________I-DO-IT________", s"________${LocalDateTime.now()}________")
            // TODO: print
            println(s"### WRITING @ $myTopic, ts: ${LocalDateTime.now()}")
            Thread.sleep(sleepTime)
        }
        producer.close()
    }


}
