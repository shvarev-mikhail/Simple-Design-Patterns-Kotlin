package patterns.behavioral

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