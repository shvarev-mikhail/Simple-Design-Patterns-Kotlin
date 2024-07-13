package patterns.structural

interface Armor {
    var armorHealth: Int
    fun getArmor(): Int
}

class ChainmailArmor(override var armorHealth: Int) : Armor {
    override fun getArmor(): Int {
        println("$armorHealth: chainmail")
        return armorHealth
    }
}

open class ArmorWrapper(var armor: Armor, override var armorHealth: Int) : Armor {
    override fun getArmor(): Int = armor.getArmor()
}

class HeadArmor(armor: Armor, armorHealth: Int) : ArmorWrapper(
    armor = armor,
    armorHealth = armorHealth
) {
    override fun getArmor(): Int {
        println("$armorHealth: head")
        return super.getArmor() + armorHealth
    }
}

class GlovesArmor(armor: Armor, armorHealth: Int) : ArmorWrapper(
    armor = armor,
    armorHealth = armorHealth
) {
    override fun getArmor(): Int {
        println("$armorHealth: gloves")
        return super.getArmor() + armorHealth
    }
}

class ChestArmor(armor: Armor, armorHealth: Int) : ArmorWrapper(
    armor = armor,
    armorHealth = armorHealth
) {
    override fun getArmor(): Int {
        println("$armorHealth: chest")
        return super.getArmor() + armorHealth
    }
}