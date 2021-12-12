package io.oenomel.matiz.publish

import io.oenomel.matiz.article.Article
import io.oenomel.matiz.article.ArticleRepository
import io.oenomel.matiz.exception.publish.NotFoundPublisherException
import io.oenomel.matiz.publish.dto.PublishResultDTO
import io.oenomel.matiz.publish.dto.convertPublishHistoryToDTO
import io.oenomel.matiz.publisher.PublishHistory
import io.oenomel.matiz.publisher.PublishHistoryRepository
import io.oenomel.matiz.publisher.SubjectPublisher
import io.oenomel.matiz.publisher.SubjectPublisherRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PublishService (
    var subjectPublisherRepository: SubjectPublisherRepository,
    var publishHistoryRepository: PublishHistoryRepository,
    var articleRepository: ArticleRepository
) {

    private val notFoundPublisherErrorMessage = "Cannot found publisher"

    fun publishArticle(publisherName: String, publishKey: String, articleContent: String): PublishResultDTO {
        val publisher = this.getPublisher(publisherName, publishKey)
        val article = Article(content = articleContent, subject = publisher.subject, createdAt = LocalDateTime.now())
        val publishedArticle = this.articleRepository.save(article)
        val history = PublishHistory(subjectPublisher = publisher, article = publishedArticle,
            createdAt = publishedArticle.createdAt)
        val savedHistory = this.publishHistoryRepository.save(history)
        return convertPublishHistoryToDTO(savedHistory)
    }

    private fun getPublisher(publisherName: String, publishKey: String): SubjectPublisher {
        return this.subjectPublisherRepository.findByPublisher_NameAndPublisher_PublishKeyAndSubject_Active(publisherName, publishKey)
            ?: throw NotFoundPublisherException(notFoundPublisherErrorMessage)
    }
}
