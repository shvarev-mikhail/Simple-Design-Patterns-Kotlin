package patterns.generating


interface Prototype {
    fun clone(): Prototype
}

class DataPrototype : Prototype {
    var data1: String = ""
        private set
    var data2: String = ""
        private set

    constructor() {

    }

    constructor(data1: String, data2: String) {
        this.data1 = data1
        this.data2 = data2
    }


    fun setData(data1: String = "UNKNOWN DATA 1", data2: String = "UNKNOWN DATA 2") {
        this.data1 = data1
        this.data2 = data2
    }

    override fun clone(): Prototype {
        return DataPrototype().apply {
            this.data1 = this@DataPrototype.data1 + " cloned"
            this.data2 = this@DataPrototype.data2 + " cloned"
        }
    }

    override fun toString(): String {
        return "DataPrototype(data1=$data1, data2=$data2)"
    }
}