package patterns.structural

class Computer {
    fun getElectric() {
        println("electricity supply")
    }

    fun startFans() {
        println("starting fans")
    }

    fun startBios() {
        println("starting bios")
    }

    fun loadOs() {
        println("loading os")
    }
}

class ComputerFacade(private val computer: Computer) {
    fun startPc() {
        println("-=launching pc=-")
        computer.getElectric()
        computer.startFans()
        computer.startBios()
        computer.loadOs()
    }
}