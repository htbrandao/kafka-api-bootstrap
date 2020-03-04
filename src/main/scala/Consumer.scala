package kafka_client_bootstrap

import java.util
import java.util.Properties
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory

class Consumer(myProp: Properties, myTopic: String, pollTimeout: Int) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val consumer = new KafkaConsumer[String, String](myProp)
    consumer.subscribe(util.Collections.singletonList(myTopic))


    def consume() = {

        val recPoll = consumer.poll(pollTimeout)

        val msgs = recPoll.asScala.map{ msg =>
            val msgToMap = Map("topic" -> msg.topic(), "partition" -> msg.partition(), "offset" -> msg.offset(),
                                "key" -> msg.key(), "value" -> msg.value(),
                                "timestamp" -> msg.timestamp() )
            // TODO: print
            println(s"*** READING @ ${msg.topic()} : $msgToMap")
            msgToMap
            }
        msgs
    }


    // main
    override def run(): Unit = while (true) consume()

}