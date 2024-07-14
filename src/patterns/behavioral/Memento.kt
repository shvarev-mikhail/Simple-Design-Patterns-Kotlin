package patterns.behavioral

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