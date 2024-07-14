package patterns.behavioral

interface NewsObserver {
    fun update(news: String)
}

interface NewsPublisher {
    fun addObserver(observer: NewsObserver)
    fun removeObserver(observer: NewsObserver)
    fun notifyObservers()
}

class NewsPublisherImpl : NewsPublisher {
    private var news = ""


    fun publishNews(news: String) {
        this.news = news
        notifyObservers()
    }
    private val observers = mutableListOf<NewsObserver>()
    override fun addObserver(observer: NewsObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: NewsObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.update(news = news) }
    }
}