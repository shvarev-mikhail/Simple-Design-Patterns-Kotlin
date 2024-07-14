package patterns.behavioral


interface Gun{
    fun accept(visitor: Visitor)
}
interface Visitor {
    fun visitGaussGun(element: Gun)
    fun visitPistolGun(element: Gun)
}

class SoundsGunVisitor: Visitor {
    override fun visitGaussGun(element: Gun) {
        println("Bzzz")
    }

    override fun visitPistolGun(element: Gun) {
        println("Peo")
    }


}

class GaussGun : Gun {
    override fun accept(visitor: Visitor) {
        visitor.visitGaussGun(this)
    }
}
class PistolGun : Gun {
    override fun accept(visitor: Visitor) {
        visitor.visitPistolGun(this)
    }
}