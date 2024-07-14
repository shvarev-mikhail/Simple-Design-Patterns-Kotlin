package patterns.behavioral


interface Strategy {
    fun execute(a: Int, b: Int): Int
}

class PlusStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int = a + b
}

class MinusStrategy : Strategy {
    override fun execute(a: Int, b: Int): Int = a - b
}

class ContextStrategy(private var strategy: Strategy? = null) {
    fun setStrategy(strategy: Strategy) {
        this.strategy = strategy
    }

    fun execute(a: Int, b: Int): Int = strategy?.execute(a, b) ?: -1
}