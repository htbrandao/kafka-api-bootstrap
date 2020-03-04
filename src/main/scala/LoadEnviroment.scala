package kafka_client_bootstrap

class LoadEnviroment {

    def getCompoundValue(key: String) = {System.getenv(key).split(";")}

    def getSingleValue(key: String) = {System.getenv(key)}


}


