package io.oenomel.matiz.subscribe

import io.oenomel.matiz.article.ArticleRepository
import io.oenomel.matiz.exception.SubscribeException
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import io.oenomel.matiz.subscribe.dto.convertToArticleDTO
import io.oenomel.matiz.subscriber.Subscriber
import io.oenomel.matiz.subscriber.SubscriberRepository
import org.springframework.stereotype.Service

@Service
class SubscribeService(
    var articleRepository: ArticleRepository,
    var subscriberRepository: SubscriberRepository
) {
    fun fetchUnreadArticles(subscriberName: String, serialNumber: String): List<ArticleDTO> {
        val subscriber = this.getSubscriber(subscriberName, serialNumber)
        val unreadArticles = this.articleRepository.findUnreadArticle(subscriber)
        return unreadArticles.map { convertToArticleDTO(it) }
    }

    fun getSubscriber(subscriberName: String, serialNumber: String): Subscriber {
        return subscriberRepository.findByNameAndSerialNumber(subscriberName, serialNumber)
            ?: throw SubscribeException("Cannot found subscriber")
    }
}
