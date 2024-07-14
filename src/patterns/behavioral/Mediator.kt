package patterns.behavioral

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