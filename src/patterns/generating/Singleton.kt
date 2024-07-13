package patterns.generating

object SingletonObject {
    init {
        println("SingletonObject created: $this")
    }

    fun getInstance() = this
}