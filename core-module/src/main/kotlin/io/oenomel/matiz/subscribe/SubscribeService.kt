package io.oenomel.matiz.subscribe

import io.oenomel.matiz.article.Article
import io.oenomel.matiz.article.ArticleRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class SubscribeService(
    var articleRepository: ArticleRepository
) {

    fun fetchArticles() {
        this.articleRepository.findAll()
    }

    fun fetchUnreadArticles(serialNumber: String) {
        this.articleRepository.findUnreadArticle(serialNumber)
    }
}
