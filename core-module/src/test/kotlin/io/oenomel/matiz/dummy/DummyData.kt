package io.oenomel.matiz.dummy

import io.oenomel.matiz.article.ArticleRepository
import io.oenomel.matiz.publisher.Publisher
import io.oenomel.matiz.publisher.PublisherRepository
import io.oenomel.matiz.publisher.SubjectPublisherRepository
import io.oenomel.matiz.subject.Subject
import io.oenomel.matiz.subject.SubjectRepository
import io.oenomel.matiz.subscriber.SubjectSubscriberRepository
import io.oenomel.matiz.subscriber.SubscribeHistoryRepository
import io.oenomel.matiz.subscriber.SubscriberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class DummyData {

    @Autowired
    lateinit var articleRepository: ArticleRepository

    @Autowired
    lateinit var subjectRepository: SubjectRepository

    @Autowired
    lateinit var subscriberRepository: SubscriberRepository

    @Autowired
    lateinit var subjectSubscriberRepository: SubjectSubscriberRepository

    @Autowired
    lateinit var subscribeHistoryRepository: SubscribeHistoryRepository

    @Autowired
    lateinit var publisherRepository: PublisherRepository

    @Autowired
    lateinit var subjectPublisherRepository: SubjectPublisherRepository

    fun generateDummy() {
        val subject = Subject(name = "sample subject", active = true)
        val savedSubject = this.subjectRepository.save(subject)
        val publisher1 = this.generateDummyPublisher("publisher1")
        val publisher2 = this.generateDummyPublisher("publisher2")
    }

    fun generateDummyPublisher(name: String): Publisher {
        val p = Publisher(name = name, publishKey = "$name-1234")
        return this.publisherRepository.save(p)
    }
}