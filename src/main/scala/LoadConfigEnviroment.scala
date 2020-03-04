class LoadConfigEnviroment {

    def getCompoundValue(key: String) = {System.getenv(key).split(";")}

    def getSingleValue(key: String) = {System.getenv(key)}

}


