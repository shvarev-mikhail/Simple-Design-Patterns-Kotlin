package patterns.generating

class Computer private constructor(computerBuilder: ComputerBuilder) {
    private val cpu = computerBuilder.cpu
    private val ram = computerBuilder.ram
    private val ssd = computerBuilder.ssd
    private val motherboard = computerBuilder.motherboard
    private val case = computerBuilder.case

    fun getComputerInfo() {
        if (cpu != null) {
            println("CPU: $cpu")
        }
        if (ram != null) {
            println("RAM: $ram")
        }
        if (ssd != null) {
            println("SSD: $ssd")
        }
        if (motherboard != null) {
            println("Motherboard: $motherboard")
        }
        if (case != null) {
            println("Case: $case")
        }
    }

    class ComputerBuilder {
        var cpu: String? = null
            private set
        var ram: String? = null
            private set
        var ssd: String? = null
            private set
        var motherboard: String? = null
            private set
        var case: String? = null
            private set


        fun setupCpu(name: String): ComputerBuilder {
            cpu = name
            return this
        }

        fun setupRam(name: String): ComputerBuilder {
            ram = name
            return this
        }

        fun setupSsd(name: String): ComputerBuilder {
            ssd = name
            return this
        }

        fun setupMotherBoard(name: String): ComputerBuilder {
            motherboard = name
            return this
        }

        fun setupCase(name: String): ComputerBuilder {
            case = name
            return this
        }

        fun build(): Computer {
            return Computer(computerBuilder = this)
        }
    }
}

