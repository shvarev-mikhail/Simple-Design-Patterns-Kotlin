package patterns.behavioral

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