package io.oenomel.matiz.article

import io.oenomel.matiz.subject.Subject
import io.oenomel.matiz.subject.SubjectRepository
import io.oenomel.matiz.subscriber.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import javax.persistence.EntityManager

@ActiveProfiles("test")
@DataJpaTest
class ArticleRepositoryTests {

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
    lateinit var entityManager: EntityManager

    @Test
    fun findUnreadArticleTest() {
        val subscriber = this.createSampleData()
        val article = this.articleRepository.findUnreadArticle(subscriber.serialNumber)
        assertThat(article)
            .hasSize(1)
            .allMatch { it.content == "consumed article" }
    }

    fun createSampleData(): Subscriber {
        val subject = Subject(name = "sample", active = true)
        val sampleSubject = this.subjectRepository.save(subject)

        val subscriber = Subscriber(name = "sample", serialNumber = "1234")
        val sampleSubscriber = this.subscriberRepository.save(subscriber)

        val subjectSubscriber = SubjectSubscriber(subject = sampleSubject, subscriber = sampleSubscriber)
        val sampleSubjectSubscriber = this.subjectSubscriberRepository.save(subjectSubscriber)

        val consumedArticle = Article(content = "consumed article", createdAt = LocalDateTime.now().minusDays(1L), subject = subject)
        val sampleConsumedArticle = this.articleRepository.save(consumedArticle)

        val history = SubscribeHistory(subjectSubscriber = sampleSubjectSubscriber, article = sampleConsumedArticle, createdAt = sampleConsumedArticle.createdAt)
        this.subscribeHistoryRepository.save(history)

        val article = Article(content = "new article", createdAt = LocalDateTime.now(), subject = subject)
        this.articleRepository.save(article)

        return sampleSubscriber
    }
}