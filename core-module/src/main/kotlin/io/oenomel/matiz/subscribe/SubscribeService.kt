package io.oenomel.matiz.subscribe

import io.oenomel.matiz.article.ArticleRepository
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import io.oenomel.matiz.subscribe.dto.convertToArticleDTO
import org.springframework.stereotype.Service

@Service
class SubscribeService(
    var articleRepository: ArticleRepository
) {

    fun fetchArticles() {
        this.articleRepository.findAll()
    }

    fun fetchUnreadArticles(serialNumber: String): List<ArticleDTO> {
        val unreadArticles = this.articleRepository.findUnreadArticle(serialNumber)
        return unreadArticles.isPresent.let { unreadArticles.get().map { convertToArticleDTO(it) } }
    }
}
