package patterns.behavioral


abstract class TemplateMethod {

    fun templateMethod() {
        operation1()
        operation2()
        operation3()
    }

    abstract fun operation1()
    abstract fun operation2()
    abstract fun operation3()
}

class ConcreteTemplateMethod : TemplateMethod() {
    override fun operation1() {
        println("Operation 1 overridden in ConcreteTemplateMethod")
    }

    override fun operation2() {
        println("Operation 2 overridden in ConcreteTemplateMethod")
    }

    override fun operation3() {
        println("Operation 3 overridden in ConcreteTemplateMethod")
    }

}