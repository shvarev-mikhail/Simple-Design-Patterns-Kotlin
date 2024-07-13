import patterns.generating.*

fun generating() {
    println()
    println("========== Generating Patterns ==========")
    abstractFactory()
    builder()
    factoryMethod(type = DeliveryType.TRUCKING)
    prototype()
    singleton()
}

fun abstractFactory() {
    println()
    println("===== AbstractFactory")
    val abstractFactoryVehicle = AbstractFactoryVehicle.createVehicle(vehicle = VehicleType.TRUCK)
    abstractFactoryVehicle.ready()
}

fun builder() {
    println()
    println("===== Builder")
    val computer = Computer.ComputerBuilder().apply {
        setupCpu("Intel i7")
        setupRam("8 GB")
        setupSsd("500 GB")
        setupMotherBoard("Gigabyte")
        setupCase("RGB Case")
    }.build()
    computer.getComputerInfo()
}

fun factoryMethod(type: DeliveryType = DeliveryType.SHIPPING) {
    println()
    println("===== FactoryMethod")
    val delivery = when (type) {
        DeliveryType.SHIPPING -> ShipDeliveryStuff()
        DeliveryType.TRUCKING -> TruckDeliveryStuff()
    }
    delivery.ready()
}

fun prototype() {
    println()
    println("===== Prototype")
    val prototype = DataPrototype(data1 = "This is data 1", data2 = "This is data 2")
    val prototypeClone = prototype.clone()
    println("Prototype: $prototype")
    println("Prototype Clone: $prototypeClone")
}

fun singleton() {
    println()
    println("===== Singleton")
    println("${SingletonObject.getInstance()} == ${SingletonObject.getInstance()}")
}