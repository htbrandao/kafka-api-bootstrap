import java.util.Properties

class LoadConfig(filePath: String) {

    val properties = new Properties()
    val propFile = Thread.currentThread.getClass.getResourceAsStream(filePath)
    properties.load(propFile)

    def getCompoundValue(key: String) = properties.getProperty(key).split(";")

    def getSingleValue(key: String) = properties.getProperty(key)


}