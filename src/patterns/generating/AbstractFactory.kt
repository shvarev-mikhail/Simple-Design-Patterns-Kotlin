package patterns.generating

interface Vehicle {
    fun ready(): Vehicle
}

private class Van : Vehicle {
    override fun ready(): Vehicle {
        println("Van is ready")
        return this
    }
}

private class Sedan : Vehicle {
    override fun ready(): Vehicle {
        println("Sedan is ready")
        return this
    }
}

private class Suv : Vehicle {
    override fun ready(): Vehicle {
        println("Suv is ready")
        return this
    }
}

private class Truck : Vehicle {
    override fun ready(): Vehicle {
        println("Truck is ready")
        return this
    }
}

enum class VehicleType {
    VAN,
    SEDAN,
    SUV,
    TRUCK
}

abstract class AbstractFactoryVehicle {
    companion object {
        fun createVehicle(vehicle: VehicleType): Vehicle = when (vehicle) {
            VehicleType.VAN -> Van()
            VehicleType.SEDAN -> Sedan()
            VehicleType.SUV -> Suv()
            VehicleType.TRUCK -> Truck()
            else -> throw Exception("Unknown vehicle type")
        }
    }

}


