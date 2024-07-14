package patterns.behavioral

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