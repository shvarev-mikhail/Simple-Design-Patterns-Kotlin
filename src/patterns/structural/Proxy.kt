package patterns.structural

interface Service {
    fun someOperation()
}

class RealService : Service {
    init {
        println("init RealService")
    }

    override fun someOperation() {
        println("do some HARD work")
    }
}

class ProxyService : Service {
    init {
        println("init ProxyService")
    }

    private val realService: Service? = null
        get() = field ?: RealService()

    override fun someOperation() {
        realService?.someOperation()
    }

}