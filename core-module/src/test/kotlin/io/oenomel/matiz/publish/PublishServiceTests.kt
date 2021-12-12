package io.oenomel.matiz.publish

import io.oenomel.matiz.publisher.Publisher
import io.oenomel.matiz.publisher.PublisherRepository
import io.oenomel.matiz.publisher.SubjectPublisher
import io.oenomel.matiz.publisher.SubjectPublisherRepository
import io.oenomel.matiz.subject.Subject
import io.oenomel.matiz.subject.SubjectRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class PublishServiceTests {

    @Autowired
    lateinit var subjectRepository: SubjectRepository

    @Autowired
    lateinit var publisherRepository: PublisherRepository

    @Autowired
    lateinit var subjectPublisherRepository: SubjectPublisherRepository

    @Autowired
    lateinit var publishService: PublishService

    @Test
    fun publishArticleTest() {
        val publisher = this.createSampleData()
        val result = this.publishService.publishArticle(publisher.name, publisher.publishKey, "sample article")
        val history = this.subjectPublisherRepository.findSubjectPublisherById(result.publishId!!)
        assertThat(history?.publisher?.name).isEqualTo(publisher.name)
    }

    fun createSampleData(): Publisher {
        val subject = Subject(name = "sample subject", active = true)
        val sampleSubject = this.subjectRepository.save(subject)

        val publisher = Publisher(name = "sample publisher", publishKey = "1234")
        val samplePublisher = this.publisherRepository.save(publisher)

        val subjectPublisher = SubjectPublisher(subject = sampleSubject, publisher = samplePublisher)
        this.subjectPublisherRepository.save(subjectPublisher)

        return samplePublisher
    }
}
