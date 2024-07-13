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

[Adapter](/src/patterns/structural/Adapter.kt)
--------
#### Example
```Kotlin
interface Enemy {
    fun attack()
}

class Tank : Enemy {
    override fun attack() {
        println("Tank Attack")
    }
}

class SpecialTank {
    fun megaAttack() {
        println("Special Tank Mega Attack")
    }
}

class EnemyAdapter(private val specialTank: SpecialTank = SpecialTank()) : Enemy {
    override fun attack() {
        specialTank.megaAttack()
    }
}
```

#### Usage
```Kotlin
    val tank = Tank()
    val specialTank = SpecialTank()
    val adapterForSpecialTank = EnemyAdapter(specialTank)
    val units = listOf(tank, adapterForSpecialTank)
    units.forEach { it.attack() }
```

[Bridge](/src/patterns/structural/Bridge.kt)
--------
#### Example
```Kotlin
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
```

#### Usage
```Kotlin
    val helicopter = Helicopter()
    val controller = Controller(rc = helicopter)
    controller.start()
    controller.setPower(90)

    val bugger = Buggy()
    val modifiedController = ModifiedController(rc = bugger)
    modifiedController.start()
    modifiedController.turbo()
```

[Composite](/src/patterns/structural/Composite.kt)
--------
#### Example
```Kotlin
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
```

#### Usage
```Kotlin
    val root = Composite()
    root.add(Leaf())

    val composite1 = Composite()
    composite1.add(Composite())
    composite1.add(Leaf())

    root.add(composite1)

    root.operation()
```

[Decorator / Wrapper](/src/patterns/structural/Decorator.kt)
--------
#### Example
```Kotlin
interface Armor {
    var armorHealth: Int
    fun getArmor(): Int
}

class ChainmailArmor(override var armorHealth: Int) : Armor {
    override fun getArmor(): Int {
        println("$armorHealth: chainmail")
        return armorHealth
    }
}

open class ArmorWrapper(var armor: Armor, override var armorHealth: Int) : Armor {
    override fun getArmor(): Int = armor.getArmor()
}

class HeadArmor(armor: Armor, armorHealth: Int) : ArmorWrapper(
    armor = armor,
    armorHealth = armorHealth
) {
    override fun getArmor(): Int {
        println("$armorHealth: head")
        return super.getArmor() + armorHealth
    }
}

class GlovesArmor(armor: Armor, armorHealth: Int) : ArmorWrapper(
    armor = armor,
    armorHealth = armorHealth
) {
    override fun getArmor(): Int {
        println("$armorHealth: gloves")
        return super.getArmor() + armorHealth
    }
}

class ChestArmor(armor: Armor, armorHealth: Int) : ArmorWrapper(
    armor = armor,
    armorHealth = armorHealth
) {
    override fun getArmor(): Int {
        println("$armorHealth: chest")
        return super.getArmor() + armorHealth
    }
}
```

#### Usage
```Kotlin
    var armor: Armor = ChainmailArmor(50)
    armor = HeadArmor(armor = armor, 30)
    armor = GlovesArmor(armor = armor, 5)
    armor = ChestArmor(armor = armor, 75)
    println("${armor.getArmor()}: full")
```

[Facade](/src/patterns/structural/Facade.kt)
--------
#### Example
```Kotlin
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
```

#### Usage
```Kotlin
    val computer = ComputerFacade(computer = Computer())
    computer.startPc()
```

[FlyWeight](/src/patterns/structural/FlyWeight.kt)
--------
#### Example
```Kotlin
abstract class Symbol(var char: Char? = null)

class SymbolA(char: Char = 'A') : Symbol(char = char) {
    init {
        println("Symbol A created")
    }
}

class SymbolB(char: Char = 'B') : Symbol(char = char) {
    init {
        println("Symbol B created")
    }
}

class SymbolC(char: Char = 'C') : Symbol(char = char) {
    init {
        println("Symbol C created")
    }
}

class SymbolD(char: Char = 'D') : Symbol(char = char) {
    init {
        println("Symbol D created")
    }
}


class FlyWeightFactory {
    private val symbols = mutableMapOf<Int, Symbol>()

    fun getSymbol(index: Int): Symbol {
        return symbols.getOrPut(index) {
            when (index) {
                0 -> SymbolA()
                1 -> SymbolB()
                2 -> SymbolC()
                3 -> SymbolD()
                else -> throw IllegalArgumentException("Unknown symbol: $index")
            }
        }
    }
}
```

#### Usage
```Kotlin
    val flyWeightFactory = FlyWeightFactory()

    println("0 ==> ${flyWeightFactory.getSymbol(0).char}")
    println("1 ==> ${flyWeightFactory.getSymbol(1).char}")
    println("3 ==> ${flyWeightFactory.getSymbol(3).char}")
    println("1 ==> ${flyWeightFactory.getSymbol(1).char}")
    println("3 ==> ${flyWeightFactory.getSymbol(3).char}")
    println("2 ==> ${flyWeightFactory.getSymbol(2).char}")
```

[Proxy](/src/patterns/structural/Proxy.kt)
--------
#### Example
```Kotlin
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
```

#### Usage
```Kotlin
    val proxy = ProxyService()
    println("wait some time")
    proxy.someOperation()
```
