import patterns.structural.*

fun structural() {
    println()
    println("========== Structural Patterns ==========")
    adapter()
    bridge()
    composite()
    decorator()
    facade()
    flyweight()
    proxy()
}

fun adapter() {
    println()
    println("===== Adapter")

    val tank = Tank()

    val specialTank = SpecialTank()
    val adapterForSpecialTank = EnemyAdapter(specialTank)
    val units = listOf(tank, adapterForSpecialTank)
    units.forEach { it.attack() }
}

fun bridge() {
    println()
    println("===== Bridge")

    val helicopter = Helicopter()
    val controller = Controller(rc = helicopter)
    controller.start()
    controller.setPower(90)

    val bugger = Buggy()
    val modifiedController = ModifiedController(rc = bugger)
    modifiedController.start()
    modifiedController.turbo()
}

fun composite() {
    println()
    println("===== Composite")

    val root = Composite()
    root.add(Leaf())

    val composite1 = Composite()
    composite1.add(Composite())
    composite1.add(Leaf())

    root.add(composite1)

    root.operation()
}

fun decorator() {
    println()
    println("===== Decorator")

    var armor: Armor = ChainmailArmor(50)
    armor = HeadArmor(armor = armor, 30)
    armor = GlovesArmor(armor = armor, 5)
    armor = ChestArmor(armor = armor, 75)
    println("${armor.getArmor()}: full")
}

fun facade() {
    println()
    println("===== Facade")

    val computer = ComputerFacade(computer = Computer())
    computer.startPc()
}

fun flyweight() {
    println()
    println("===== Flyweight")

    val flyWeightFactory = FlyWeightFactory()

    println("0 ==> ${flyWeightFactory.getSymbol(0).char}")
    println("1 ==> ${flyWeightFactory.getSymbol(1).char}")
    println("3 ==> ${flyWeightFactory.getSymbol(3).char}")
    println("1 ==> ${flyWeightFactory.getSymbol(1).char}")
    println("3 ==> ${flyWeightFactory.getSymbol(3).char}")
    println("2 ==> ${flyWeightFactory.getSymbol(2).char}")
}

fun proxy() {
    println()
    println("===== Proxy")
    val proxy = ProxyService()
    println("wait some time")
    proxy.someOperation()

}