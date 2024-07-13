package patterns.structural

open class Controller(private val rc: Rc) {

    fun start() = rc.engineStart()

    fun stop() = rc.engineStop()

    fun setPower(power: Int) = rc.setPower(power)

}

class ModifiedController(private val rc: Rc) : Controller(rc) {
    fun turbo() {
        rc.setPower(100)
    }
}

interface Rc {
    fun engineStart()
    fun engineStop()
    fun setPower(power: Int)
}

class Helicopter : Rc {
    override fun engineStart() {
        println("Helicopter engine start")
    }

    override fun engineStop() {
        println("Helicopter engine stop")
    }

    override fun setPower(power: Int) {
        println("Helicopter set power $power")
    }
}

class Buggy : Rc {
    override fun engineStart() {
        println("Buggy engine start")
    }

    override fun engineStop() {
        println("Buggy engine stop")
    }

    override fun setPower(power: Int) {
        println("Buggy set power $power")
    }
}

