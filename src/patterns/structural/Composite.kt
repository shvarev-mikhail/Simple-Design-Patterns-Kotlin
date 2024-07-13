package patterns.structural

interface Component {
    fun operation()
}

interface ComponentComposite : Component {
    override fun operation()
    fun add(component: Component)
    fun remove(component: Component)
}

class Leaf : Component {
    override fun operation() {
        println("do some work from leaf")
    }
}

class Composite : ComponentComposite {
    private val components = arrayListOf<Component>()
    override fun operation() {
        println("do some work from composite")
        components.forEach {
            it.operation()
        }
    }

    override fun add(component: Component) {
        components.add(component)
    }

    override fun remove(component: Component) {
        components.remove(component)
    }

}