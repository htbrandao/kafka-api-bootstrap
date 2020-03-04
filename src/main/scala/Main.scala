package kafka_client_bootstrap

import org.slf4j.LoggerFactory
import java.time.LocalDateTime

object Main {

    def main(args: Array[String]): Unit = {

        /**
            Docstring example for future reference #badMemory
        */

        val config = new LoadEnviroment()
        val behaviorList = config.getCompoundValue("bhvList").zipWithIndex
        val groupId = config.getSingleValue("groupId")
        val appId = config.getSingleValue("appId")

        val log = LoggerFactory.getLogger(this.getClass)
        log.info(s"STARTING APPLICATION:\n"
                + s"Behaviors found: ${behaviorList.length}\n "
                + s"Group Id: ${config.getSingleValue("groupId")}\n "
                + s"Application Id:${config.getSingleValue("appId")}\n "
                + s"Current Time: ${LocalDateTime.now()}\n")

        val app = new BehaviorLoader(config, behaviorList, groupId, appId)
        app.start()

    }


}


