package patterns.structural

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