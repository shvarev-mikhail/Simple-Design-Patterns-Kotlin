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
 * Behavioral Patterns
	* [Chain of Responsobility](#chain-of-responsobility)
	* [Command](#command)
	* [Interpreter](#interpreter)
  	* [Mediator](#mediator)
  	* [Memento](#memento)
  	* [Observer](#observer)
  	* [State](#state)
  	* [Strategy](#strategy)
  	* [Template Method](#template-method)
  	* [Visitor](#visitor)


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

[Chain of responsobility](/src/patterns/behavioral/ChainOfResponsobility.kt)
--------
#### Example
```Kotlin
interface Handler {
    fun showMessage()
}

abstract class AbstractHandler(open var message: String? = null) : Handler {
    private var child: AbstractHandler? = null
    override fun showMessage() {
        if (message != null) {
            println(message)
        } else {
            println("No message to show, go to next Item")
            child?.showMessage()
        }
    }
    fun addChild(child: AbstractHandler) {
        this.child = child
    }
}

class Button(override var message: String? = null) : AbstractHandler(message = message)
class Dialog(override var message: String? = null) : AbstractHandler(message = message)
```
#### Usage
```Kotlin
    val dialog = Dialog("Dialog")
    val buttonOk = Button("OK")
    val buttonMiddle = Button()
    val buttonCancel = Button("Cancel")

    dialog.addChild(buttonOk)
    buttonOk.addChild(buttonMiddle)
    buttonMiddle.addChild(buttonCancel)

    buttonMiddle.showMessage()
```

[Command](/src/patterns/behavioral/Command.kt)
--------
#### Example
```Kotlin
interface Command {
    fun execute()
}

class CommandOpen(val file: String) : Command {
    override fun execute() {
        println("Open: $file")
    }
}

class CommandSave(val file: String) : Command {
    override fun execute() {
        println("Save: $file")
    }

}


fun executeCommand(command: Command) {
    command.execute()
}
```
#### Usage
```Kotlin
    val commandOpen = CommandOpen("diary.txt")
    val commandSave = CommandSave("diary.txt")
    val listOfCommand = listOf(commandOpen, commandSave)
    listOfCommand.forEach {
        executeCommand(it)
        Thread.sleep(1000)
    }
```

[Interpreter](/src/patterns/behavioral/Interpreter.kt)
--------
#### Example
```Kotlin
interface Expression {
    fun interpret(): Int
}

class NumberExpression(private val number: Int) : Expression {
    constructor(number: String) : this(number = number.toInt())

    override fun interpret(): Int = number

}

class PlusExpression(private val left: Expression, private val right: Expression) : Expression {
    override fun interpret(): Int = left.interpret() + right.interpret()
}

class MinusExpression(private val left: Expression, private val right: Expression) : Expression {
    override fun interpret(): Int = left.interpret() - right.interpret()
}

fun getOperatorInstance(s: String?, left: Expression?, right: Expression?): Expression {
    return when (s) {
        "+" -> PlusExpression(left!!, right!!)
        "-" -> MinusExpression(left!!, right!!)
        else -> throw IllegalArgumentException("Unknown operator $s")
    }
}
```
#### Usage
```Kotlin
    val example = "2 + 3"
    val exampleTransformed = example.split(" ")
    val result = getOperatorInstance(
        exampleTransformed[1],
        NumberExpression(exampleTransformed[0]),
        NumberExpression(exampleTransformed[2])
    )
    println("result: ${result.interpret()}")
```
[Iterator](/src/patterns/behavioral/Iterator.kt)
--------
#### Example
```Kotlin
interface IteratorItem<T> {
    fun hasNext(): Boolean
    fun next(): T
}

class IteratorImpl<T>(val list: List<T>) : IteratorItem<T> {
    private var position = 0
    override fun hasNext(): Boolean = position < list.size

    override fun next(): T {
        val result = list[position]
        position++
        return result
    }
}
```
#### Usage
```Kotlin
    val list = listOf("a", "b", "c")
    val iterator = IteratorImpl<String>(list)
    while (iterator.hasNext()) {
        println(iterator.next())
    }
```

[Mediator](/src/patterns/behavioral/Mediator.kt)
--------
#### Example
```Kotlin
enum class Event {
    CLICK_BTN_OK,
    CLICK_CHECKBOX
}

interface Mediator {
    fun notify(component: Component, event: Event)
}

interface Component {
    fun click()
}

class MediatorImpl : Mediator {
    private var checkBoxState = false
    override fun notify(component: Component, event: Event) {
        when (event) {
            Event.CLICK_BTN_OK -> {
                if (checkBoxState) {
                    println("ButtonOk clicked")
                }else{
                    println("ButtonOk disabled can not clicked")
                }
            }
            Event.CLICK_CHECKBOX -> {
                println("CheckBox clicked: state: $checkBoxState-> ${!checkBoxState}")
                checkBoxState = !checkBoxState
                println("ButtonOk enable: $checkBoxState")
            }
        }
    }

}

class ButtonOk(val mediatorImpl: Mediator) : Component{
    override fun click() {
        mediatorImpl.notify(this, Event.CLICK_BTN_OK)
    }

}
class CheckBox(val mediatorImpl: Mediator) : Component {
    override fun click() {
        mediatorImpl.notify(this, Event.CLICK_CHECKBOX)
    }
}
```
#### Usage
```Kotlin
    val mediator = MediatorImpl()
    val checkbox = CheckBox(mediatorImpl = mediator)
    val button = ButtonOk(mediatorImpl = mediator)
    button.click()
    checkbox.click()
    button.click()
```

[Memento](/src/patterns/behavioral/Memento.kt)
--------
#### Example
```Kotlin
class MementoSpecial(
    private val strength: Int,
    private val perception: Int,
    private val endurance: Int,
    private val charisma: Int,
    private val intelligence: Int,
    private val agility: Int,
    private val luck: Int
) {
    fun getStrength() = strength
    fun getPerception() = perception
    fun getEndurance() = endurance
    fun getCharisma() = charisma
    fun getIntelligence() = intelligence
    fun getAgility() = agility
    fun getLuck() = luck
}

class Person(
    private var strength: Int,
    private var perception: Int,
    private var endurance: Int,
    private var charisma: Int,
    private var intelligence: Int,
    private var agility: Int,
    private var luck: Int
) {
    private fun canLevelUp(value: Int): Int {
        if (value < 10) return value + 1
        return value
    }

    fun levelUp() {
        strength = canLevelUp(strength)
        perception = canLevelUp(perception)
        endurance = canLevelUp(endurance)
        charisma = canLevelUp(charisma)
        intelligence = canLevelUp(intelligence)
        agility = canLevelUp(agility)
        luck = canLevelUp(luck)
    }

    fun save(): MementoSpecial {
        return MementoSpecial(
            strength,
            perception,
            endurance,
            charisma,
            intelligence,
            agility,
            luck
        )
    }

    fun restore(mementoSpecial: MementoSpecial) {
        strength = mementoSpecial.getStrength()
        perception = mementoSpecial.getPerception()
        endurance = mementoSpecial.getEndurance()
        charisma = mementoSpecial.getCharisma()
        intelligence = mementoSpecial.getIntelligence()
        agility = mementoSpecial.getAgility()
        luck = mementoSpecial.getLuck()
    }

    override fun toString(): String = "strength=$strength, perception=$perception, endurance=$endurance, charisma=$charisma, intelligence=$intelligence, agility=$agility, luck=$luck"
}
```
#### Usage
```Kotlin
    val stack = Stack<MementoSpecial>()
    val person = Person(
        strength = 5,
        perception = 1,
        endurance = 3,
        intelligence = 5,
        agility = 2,
        charisma = 8,
        luck = 2
    )
    person.levelUp()
    stack.push(person.save())
    person.levelUp()
    person.levelUp()
    println("current person level: $person")
    person.restore(stack.pop())
    println("restored person level: $person")
```

[Observer](/src/patterns/behavioral/Observer.kt)
--------
#### Example
```Kotlin
interface NewsObserver {
    fun update(news: String)
}

interface NewsPublisher {
    fun addObserver(observer: NewsObserver)
    fun removeObserver(observer: NewsObserver)
    fun notifyObservers()
}

class NewsPublisherImpl : NewsPublisher {
    private var news = ""


    fun publishNews(news: String) {
        this.news = news
        notifyObservers()
    }
    private val observers = mutableListOf<NewsObserver>()
    override fun addObserver(observer: NewsObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: NewsObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.update(news = news) }
    }
}
```
#### Usage
```Kotlin
    val observable = NewsPublisherImpl()
    observable.addObserver(object : NewsObserver {
        override fun update(news: String) {
            println("observer 1 - News: $news")
        }
    })
    observable.addObserver(object : NewsObserver {
        override fun update(news: String) {
            println("observer 2 - News: $news")
        }
    })

    observable.publishNews(news = "Hello News")
    observable.publishNews(news = "Kotlin released")
```

[State](/src/patterns/behavioral/State.kt)
--------
#### Example
```Kotlin
interface State {
    fun doAction()
}

class ConcreteState1 : State {
    override fun doAction() {
        println("do something in ConcreteState - 1")
    }
}

class ConcreteState2 : State {
    override fun doAction() {
        println("do something in ConcreteState - 2")
    }
}

class Context(private var state: State) {
    fun doAction() {
        state.doAction()
    }

    fun changeState(state: State) {
        this.state = state
    }
}
```
#### Usage
```Kotlin
    val state1 = ConcreteState1()
    val state2 = ConcreteState2()

    val context = Context(state = state1)
    context.doAction()
    context.changeState(state2)
    context.doAction()
```

[Strategy](/src/patterns/behavioral/Strategy.kt)
--------
#### Example
```Kotlin
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
```
#### Usage
```Kotlin
    val value = "1 + 4".split(" ")
    val ctx = ContextStrategy()
    when (value[1]) {
        "+" -> {
            ctx.setStrategy(PlusStrategy())
            println("= ${ctx.execute(value[0].toInt(), value[2].toInt())}")
        }

        "-" -> {
            ctx.setStrategy(MinusStrategy())
            println("= ${ctx.execute(value[0].toInt(), value[2].toInt())}")
        }

        else -> {
            throw IllegalArgumentException("Unknown operation")
        }
    }
```

[Template Method](/src/patterns/behavioral/TemplateMethod.kt)
--------
#### Example
```Kotlin
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
```
#### Usage
```Kotlin
    val concreteClass = ConcreteTemplateMethod()
    concreteClass.templateMethod()
```

[Visitor](/src/patterns/behavioral/Visitor.kt)
--------
#### Example
```Kotlin
interface Gun{
    fun accept(visitor: Visitor)
}
interface Visitor {
    fun visitGaussGun(element: Gun)
    fun visitPistolGun(element: Gun)
}

class SoundsGunVisitor: Visitor {
    override fun visitGaussGun(element: Gun) {
        println("Bzzz")
    }

    override fun visitPistolGun(element: Gun) {
        println("Peo")
    }
}

class GaussGun : Gun {
    override fun accept(visitor: Visitor) {
        visitor.visitGaussGun(this)
    }
}
class PistolGun : Gun {
    override fun accept(visitor: Visitor) {
        visitor.visitPistolGun(this)
    }
}
```
#### Usage
```Kotlin
    val gaussianGun = GaussGun()
    val pistolGun = PistolGun()

    val visitor = SoundsGunVisitor()
    println("Gauss gun sound:")
    gaussianGun.accept(visitor)
    println("Pistol gun sound:")
    pistolGun.accept(visitor)
```
