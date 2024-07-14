package patterns.behavioral

interface IteratorItem<T> {
    fun hasNext(): Boolean
    fun next(): T
}

class IteratorImpl<T>(val list: List<T>) : IteratorItem<T> {
    private var position = 0
    override fun hasNext(): Boolean = position < list.size

    override fun next(): T {
        val result = list[position]
        position++
        return result
    }

}