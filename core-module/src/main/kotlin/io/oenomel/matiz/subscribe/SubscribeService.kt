package io.oenomel.matiz.subscribe

import io.oenomel.matiz.article.ArticleRepository
import io.oenomel.matiz.exception.SubscribeException
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import io.oenomel.matiz.subscribe.dto.convertToArticleDTO
import io.oenomel.matiz.subscriber.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class SubscribeService(
    var articleRepository: ArticleRepository,
    var subscriberRepository: SubscriberRepository,
    var subscribeHistoryRepository: SubscribeHistoryRepository,
    var subjectSubscriberRepository: SubjectSubscriberRepository
) {

    private val notFoundSubscriberErrorMessage = "Cannot found subscriber"

    fun fetchUnreadArticles(subscriberName: String, serialNumber: String): List<ArticleDTO> {
        val subscriber = this.getSubscriber(subscriberName, serialNumber)
        val unreadArticles = this.articleRepository.findUnreadArticle(subscriber)
        return unreadArticles.map { convertToArticleDTO(it) }
    }

    fun getSubscriber(subscriberName: String, serialNumber: String): Subscriber {
        return subscriberRepository.findByNameAndSerialNumber(subscriberName, serialNumber)
            ?: throw SubscribeException(notFoundSubscriberErrorMessage)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun saveArticleHistory(subscriberName: String, articles: List<ArticleDTO>) {
        val subscriber = this.subscriberRepository.findByName(subscriberName)
            ?: throw SubscribeException(notFoundSubscriberErrorMessage)
        for (articleDTO in articles) {
            val article = this.articleRepository.findArticleById(articleDTO.id)
                ?: throw SubscribeException("Cannot found article")
            val subjectSubscriber = this.subjectSubscriberRepository.findBySubjectAndSubscriber(article.subject, subscriber)
                    ?: throw SubscribeException(notFoundSubscriberErrorMessage)
            val history = SubscribeHistory(subjectSubscriber, article, LocalDateTime.now())
            this.subscribeHistoryRepository.save(history)
        }
    }
}
