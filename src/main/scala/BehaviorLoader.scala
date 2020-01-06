package bootstrap_api_kafka

import org.slf4j.LoggerFactory

class BehaviorLoader(config: LoadEnviroment, behaviorList: Array[(String, Int)],
                     groupId: String, appId: String) extends Thread {

    val log = LoggerFactory.getLogger(this.getClass)
    log.info(s"ACK @ ${this.getClass} ")


    def load()= {

        val bhvArray = behaviorList.map {item =>

            val bhv = item._1
            val index = item._2

            bhv match {

                case "consumer" =>
                    val topic = config.getSingleValue(s"bhv${index}_topic")
                    val servers = config.getCompoundValue(s"bhv${index}_bootstrapServers")
                    val profile = new Profile().setDefault(appId, groupId, servers)
                    val action = new Consumer(profile, topic, 200)

                    val log = LoggerFactory.getLogger(action.getClass)
                    log.info(s"STARTING $index, $bhv, $topic, ${servers.toList}")

                    action.start()
                    s"$index, $bhv"

                case "producer" =>
                    val topic = config.getSingleValue(s"bhv${index}_topic")
                    val sleep = config.getSingleValue(s"bhv${index}_sleep").toInt
                    val servers = config.getCompoundValue(s"bhv${index}_bootstrapServers")
                    val profile = new Profile().setDefault(appId, groupId, servers)
                    val action = new Producer(profile, topic, sleep)

                    val log = LoggerFactory.getLogger(action.getClass)
                    log.info(s"STARTING $index, $bhv, $topic, ${servers.toList}")

                    action.start()
                    s"$index, $bhv"

                case "stream" =>
                    val topic_IN = config.getSingleValue(s"bhv${index}_topic_IN")
                    val topic_OUT = config.getSingleValue(s"bhv${index}_topic_OUT")
                    val servers = config.getCompoundValue(s"bhv${index}_bootstrapServers")
                    val profile = new Profile().setStream(appId, groupId, servers)
                    val action = new Streamer(profile, topic_IN, topic_OUT)

                    val log = LoggerFactory.getLogger(action.getClass)
                    log.info(s"STARTING $index, $bhv, $topic_IN, $topic_OUT, ${servers.toList}")

                    action.start()
                    s"$index, $bhv"

                case "sink" =>
                    val topic = config.getSingleValue(s"bhv${index}_topic")
                    val servers = config.getCompoundValue(s"bhv${index}_bootstrapServers")
                    val profile = new Profile().setDefault(appId, groupId, servers)

                    val mg_user = config.getSingleValue(s"bhv${index}_mgUser")
                    val mg_password = config.getSingleValue(s"bhv${index}_mgUserPassword")

                    val mg_servers = config.getCompoundValue(s"bhv${index}_mgServers")
                    val mg_database = config.getSingleValue(s"bhv${index}_mgUserDb")
                    val mg_collection = config.getSingleValue(s"bhv${index}_mgTable")

                    val action = new Database(mg_user, mg_password, mg_servers, mg_database, mg_collection,
                                                profile, topic, 200)

                    val log = LoggerFactory.getLogger(action.getClass)
                    log.info(s"STARTING $index, $bhv, $topic, ${servers.toList}")

                    action.start()
                    s"$index, $bhv"

                case _ =>
                    val log = LoggerFactory.getLogger(this.getClass)
                    log.info(s"\n ERROR: Behavior not found: $index-$bhv.")
                    s"$index, $bhv"
                }
        }
        bhvArray
    }


    // main
    override def run(): Unit = load()


}