package io.oenomel.matiz.subscribe

import io.oenomel.matiz.article.ArticleRepository
import io.oenomel.matiz.exception.subscribe.NotFoundSubscriberException
import io.oenomel.matiz.exception.subscribe.SubscribeException
import io.oenomel.matiz.exception.subscribe.SubscribeHistoryException
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import io.oenomel.matiz.subscribe.dto.convertToArticleDTO
import io.oenomel.matiz.subscriber.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class SubscribeService(
    val articleRepository: ArticleRepository,
    val subscriberRepository: SubscriberRepository,
    val subscribeHistoryRepository: SubscribeHistoryRepository,
    val subjectSubscriberRepository: SubjectSubscriberRepository
) {

    private val notFoundSubscriberErrorMessage = "Cannot found subscriber"

    fun fetchUnreadArticles(subscriberName: String, subscribeKey: String): List<ArticleDTO> {
        val subscriber = this.getSubscriber(subscriberName, subscribeKey)
        val unreadArticles = this.articleRepository.findUnreadArticle(subscriber)
        return unreadArticles.map { convertToArticleDTO(it) }
    }

    private fun getSubscriber(subscriberName: String, subscribeKey: String): Subscriber {
        return subscriberRepository.findByNameAndSubscribeKey(subscriberName, subscribeKey)
            ?: throw NotFoundSubscriberException(notFoundSubscriberErrorMessage)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun saveArticleHistory(subscriberName: String, articles: List<ArticleDTO>) {
        val subscriber = this.subscriberRepository.findByName(subscriberName)
            ?: throw SubscribeException(notFoundSubscriberErrorMessage)
        for (articleDTO in articles) {
            val article = this.articleRepository.findArticleById(articleDTO.id)
                ?: throw SubscribeHistoryException("Cannot found article")
            val subjectSubscriber = this.subjectSubscriberRepository.findBySubjectAndSubject_ActiveAndSubscriber(subject = article.subject, subscriber = subscriber)
                    ?: throw NotFoundSubscriberException(notFoundSubscriberErrorMessage)
            val history = SubscribeHistory(subjectSubscriber, article, LocalDateTime.now())
            this.subscribeHistoryRepository.save(history)
        }
    }
}
