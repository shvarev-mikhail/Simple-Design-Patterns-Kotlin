package patterns.behavioral


interface Handler {
    fun showMessage()
}

abstract class AbstractHandler(open var message: String? = null) : Handler {
    private var child: AbstractHandler? = null
    override fun showMessage() {
        if (message != null) {
            println(message)
        } else {
            println("No message to show, go to next Item")
            child?.showMessage()
        }
    }
    fun addChild(child: AbstractHandler) {
        this.child = child
    }
}

class Button(override var message: String? = null) : AbstractHandler(message = message)
class Dialog(override var message: String? = null) : AbstractHandler(message = message)
