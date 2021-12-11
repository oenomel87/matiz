package io.oenomel.matiz.subscribe

import io.oenomel.matiz.exception.SubscribeException
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest

@RequestMapping("/subscribe")
@RestController
class SubscribeController(var subscribeService: SubscribeService) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/article")
    fun fetchUnreadArticle(req: HttpServletRequest): List<ArticleDTO> {
        val subscriberName = req.getHeader("subscriber")
        val serial = req.getHeader("subscribe-key")

        return try {
            val unreadArticles = this.subscribeService.fetchUnreadArticles(subscriberName, serial)
            this.subscribeService.saveArticleHistory(subscriberName, unreadArticles)
            unreadArticles
        } catch (e: SubscribeException) {
            logger.error(e.message)
            Collections.emptyList()
        }
    }
}
