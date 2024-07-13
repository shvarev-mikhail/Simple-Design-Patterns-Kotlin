package patterns.structural

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