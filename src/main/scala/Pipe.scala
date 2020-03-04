//import java.util
//import java.util.Properties
//import scala.collection.JavaConverters._
//import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
//import org.apache.kafka.clients.consumer.KafkaConsumer
//import org.slf4j.LoggerFactory
//
//class Pipe(myPropOrg: Properties, mytopicOrg: String, pollTimeout: Long,
//           myPropDest: Properties, mytopicDest: String) extends Thread {
//
//    val log = LoggerFactory.getLogger(this.getClass)
//    log.info(s"ACK @ ${this.getClass} ")
//
//    val consumer = new KafkaConsumer[String, String](myPropOrg)
//    consumer.subscribe(util.Collections.singletonList(mytopicOrg))
//    val producer = new KafkaProducer[String, String](myPropDest)
//
//
//    def pipe() = {
//
//            val recPoll = consumer.poll(pollTimeout)
//
//            val msgs = recPoll.asScala.map{msg =>
//                producer.send(new ProducerRecord(mytopicDest, msg.key(), msg.value() ) )
//                val msgToMap = Map("key" -> msg.key(), "value" -> msg.value())
//                // TODO: print
//                println(s"%%% READING @ ${msg.topic()} : $msgToMap \n%%% WRITING TO $mytopicDest")
//                msgToMap
//            }
//        msgs
//    }
//
//
//    // main
//    override def run(): Unit = {
//        while (true) pipe()
//        producer.close()
//    }
//
//
//}
