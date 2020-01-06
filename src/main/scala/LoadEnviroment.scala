package bootstrap_api_kafka

class LoadEnviroment {

    def getCompoundValue(key: String) = {System.getenv(key).split(";")}

    def getSingleValue(key: String) = {System.getenv(key)}


}


