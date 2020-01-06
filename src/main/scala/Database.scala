package bootstrap_api_kafka

import java.util
import java.util.Properties
import collection.JavaConverters._
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.slf4j.LoggerFactory
import org.bson.Document
import com.mongodb.client.MongoClients

class Database(mongoUser: String, mongoUserPassword: String, mongoServers: Array[String], mongoDatabase: String, mongoCollection: String,
               myProp: Properties, myTopic: String, pollTimeout: Int) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")

    val consumer = new KafkaConsumer[String, String](myProp)
    consumer.subscribe(util.Collections.singletonList(myTopic))

    val cliUri = s"mongodb://$mongoUser:$mongoUserPassword@${mongoServers.mkString(",")}/$mongoDatabase"
    val cli = MongoClients.create(cliUri)

    val database = cli.getDatabase(s"$mongoDatabase")
    val collection = database.getCollection(s"$mongoCollection")


    def sink() = {

        val recPoll = consumer.poll(pollTimeout)

        //TODO: Test insertMany so insertOne may be deprecated
//        val msgsDoc = recPoll.asScala.map { msg =>
//            val msgToDocMap = Document(Map("topic" -> msg.topic(), "key" -> msg.key(), "value" -> msg.value())).append("ts", msg.timestamp())
//            collection.insertOne(msgToDocMap)
//            println(s"___SINKING @ ${msg.topic()} : $msgToDocMap")
//            msgToDocMap
//        }
//        msgsDoc

        val out = recPoll.asScala.map(msg => new Document().append("key", msg.key()).append("value", msg.value()) ).toList.asJava
        collection.insertMany(out)
        out

    }


    // main
    override def run(): Unit = while (true) sink()


}
