import patterns.behavioral.*
import java.util.*

fun behavioral() {
    println()
    println("===== Behavioral Patterns =====")

    chainOfResponsibility()
    command()
    interpreter()
    iterator()
    mediator()
    memento()
    observer()
    state()
    strategy()
    templateMethod()
    visitor()
}

fun chainOfResponsibility() {
    println()
    println("===== Chain of Responsibility")

    val dialog = Dialog("Dialog")
    val buttonOk = Button("OK")
    val buttonMiddle = Button()
    val buttonCancel = Button("Cancel")

    dialog.addChild(buttonOk)
    buttonOk.addChild(buttonMiddle)
    buttonMiddle.addChild(buttonCancel)

    buttonMiddle.showMessage()
}

fun command() {
    println()
    println("===== Command")

    val commandOpen = CommandOpen("diary.txt")
    val commandSave = CommandSave("diary.txt")
    val listOfCommand = listOf(commandOpen, commandSave)
    listOfCommand.forEach {
        executeCommand(it)
        Thread.sleep(1000)
    }

}

fun interpreter() {
    println()
    println("===== Interpreter")

    val example = "2 + 3"
    val exampleTransformed = example.split(" ")
    val result = getOperatorInstance(
        exampleTransformed[1],
        NumberExpression(exampleTransformed[0]),
        NumberExpression(exampleTransformed[2])
    )
    println("result: ${result.interpret()}")
}

fun iterator() {
    println()
    println("===== Iterator")

    val list = listOf("a", "b", "c")
    val iterator = IteratorImpl<String>(list)
    while (iterator.hasNext()) {
        println(iterator.next())
    }
}

fun mediator() {
    println()
    println("===== Mediator")

    val mediator = MediatorImpl()
    val checkbox = CheckBox(mediatorImpl = mediator)
    val button = ButtonOk(mediatorImpl = mediator)
    button.click()
    checkbox.click()
    button.click()
}

fun memento() {
    println()
    println("===== Memento")
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
}

fun observer() {
    println()
    println("===== Observer")

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
}

fun state() {
    println()
    println("===== State")

    val state1 = ConcreteState1()
    val state2 = ConcreteState2()

    val context = Context(state = state1)
    context.doAction()
    context.changeState(state2)
    context.doAction()
}

fun strategy() {
    println()
    println("===== Strategy")
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
}

fun templateMethod() {
    println()
    println("===== Template Method")

    val concreteClass = ConcreteTemplateMethod()
    concreteClass.templateMethod()
}

fun visitor() {
    println()
    println("===== Visitor")


    val gaussianGun = GaussGun()
    val pistolGun = PistolGun()

    val visitor = SoundsGunVisitor()
    println("Gauss gun sound:")
    gaussianGun.accept(visitor)
    println("Pistol gun sound:")
    pistolGun.accept(visitor)
}