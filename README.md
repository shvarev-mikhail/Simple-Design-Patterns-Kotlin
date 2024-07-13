# Simple Design Patterns

 * Creating Patterns
	* [Abstract Factory](#abstract-factory)
  	* [Builder / Assembler](#builder--assembler)
	* [Factory Method](#factory-method)
  	* [Prototype](#prototype)
	* [Singleton](#singleton)

 * Structural Patterns
	* [Adapter](#adapter)
 	* [Bridge](#bridge)
	* [Composite](#composite)
  	* [Decorator / Wrapper](#decorator--wrapper)
	* [Facade](#facade)
  	* [FlyWeight](#flyweight)
	* [Proxy](#proxy)


[Abstract Factory](/src/patterns/generating/AbstractFactory.kt)
--------
#### Example
```Kotlin
interface Vehicle {
    fun ready(): Vehicle
}

private class Van : Vehicle {
    override fun ready(): Vehicle {
        println("Van is ready")
        return this
    }
}

private class Sedan : Vehicle {
    override fun ready(): Vehicle {
        println("Sedan is ready")
        return this
    }
}

private class Suv : Vehicle {
    override fun ready(): Vehicle {
        println("Suv is ready")
        return this
    }
}

private class Truck : Vehicle {
    override fun ready(): Vehicle {
        println("Truck is ready")
        return this
    }
}

enum class VehicleType {
    VAN,
    SEDAN,
    SUV,
    TRUCK
}

abstract class AbstractFactoryVehicle {
    companion object {
        fun createVehicle(vehicle: VehicleType): Vehicle = when (vehicle) {
            VehicleType.VAN -> Van()
            VehicleType.SEDAN -> Sedan()
            VehicleType.SUV -> Suv()
            VehicleType.TRUCK -> Truck()
            else -> throw Exception("Unknown vehicle type")
        }
    }
}
```
#### Usage
```Kotlin
val abstractFactoryVehicle = AbstractFactoryVehicle.createVehicle(vehicle = VehicleType.TRUCK)
abstractFactoryVehicle.ready()
```
[Builder / Assembler](/src/patterns/generating/Builder.kt)
--------
#### Example
```Kotlin
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
```
#### Usage
```Kotlin
    val computer = Computer.ComputerBuilder().apply {
        setupCpu("Intel i7")
        setupRam("8 GB")
        setupSsd("500 GB")
        setupMotherBoard("Gigabyte")
        setupCase("RGB Case")
    }.build()
    computer.getComputerInfo()
```

[Factory Method](/src/patterns/generating/FactoryMethod.kt)
--------
#### Example
```Kotlin
enum class DeliveryType{
    SHIPPING, TRUCKING
}

interface Transport {
    fun delivery(): String
}


class TruckDelivery : Transport {
    override fun delivery(): String = "Truck delivery"
}

class ShipDelivery : Transport {
    override fun delivery(): String = "Ship delivery"
}

abstract class DeliveryStuff {
    fun ready() {
        println("Ready to delivery by: ${doDelivery()}")
    }

    abstract fun doDelivery(): String
}

class ShipDeliveryStuff : DeliveryStuff() {
    override fun doDelivery(): String = ShipDelivery().delivery()
}

class TruckDeliveryStuff : DeliveryStuff() {
    override fun doDelivery(): String = TruckDelivery().delivery()
}
```

#### Usage
```Kotlin
    val delivery = when (type) {
        DeliveryType.SHIPPING -> ShipDeliveryStuff()
        DeliveryType.TRUCKING -> TruckDeliveryStuff()
    }
    delivery.ready()
```

[Prototype](/src/patterns/generating/Prototype.kt)
--------
#### Example
```Kotlin
interface Prototype {
    fun clone(): Prototype
}

class DataPrototype : Prototype {
    var data1: String = ""
        private set
    var data2: String = ""
        private set

    constructor() {

    }

    constructor(data1: String, data2: String) {
        this.data1 = data1
        this.data2 = data2
    }


    fun setData(data1: String = "UNKNOWN DATA 1", data2: String = "UNKNOWN DATA 2") {
        this.data1 = data1
        this.data2 = data2
    }

    override fun clone(): Prototype {
        return DataPrototype().apply {
            this.data1 = this@DataPrototype.data1 + " cloned"
            this.data2 = this@DataPrototype.data2 + " cloned"
        }
    }

    override fun toString(): String {
        return "DataPrototype(data1=$data1, data2=$data2)"
    }
}
```

#### Usage
```Kotlin
    val prototype = DataPrototype(data1 = "This is data 1", data2 = "This is data 2")
    val prototypeClone = prototype.clone()
    println("Prototype: $prototype")
    println("Prototype Clone: $prototypeClone")
```

[Singleton](/src/patterns/generating/Singleton.kt)
--------
#### Example
```Kotlin
object SingletonObject {
    init {
        println("SingletonObject created: $this")
    }

    fun getInstance() = this
}
```

#### Usage
```Kotlin
println("${SingletonObject.getInstance()} == ${SingletonObject.getInstance()}")
```
