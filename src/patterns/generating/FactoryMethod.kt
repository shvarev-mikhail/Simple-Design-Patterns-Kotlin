package patterns.generating


enum class DeliveryType{
    SHIPPING, TRUCKING
}

interface Transport {
    fun delivery(): String
}


class TruckDelivery : Transport {
    override fun delivery(): String = "Truck delivery"
}

class ShipDelivery : Transport {
    override fun delivery(): String = "Ship delivery"
}

abstract class DeliveryStuff {
    fun ready() {
        println("Ready to delivery by: ${doDelivery()}")
    }

    abstract fun doDelivery(): String
}

class ShipDeliveryStuff : DeliveryStuff() {
    override fun doDelivery(): String = ShipDelivery().delivery()
}

class TruckDeliveryStuff : DeliveryStuff() {
    override fun doDelivery(): String = TruckDelivery().delivery()
}