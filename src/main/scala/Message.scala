import java.time.LocalDateTime

class Message {

    def dummy() = ("__THIS_IS_A_KEY__", s"__VAL_IS__${LocalDateTime.now()}__")

    def simpleMessage(key: String, value: String) = (key, value)

    /*
        Add your functions here
    */

}
