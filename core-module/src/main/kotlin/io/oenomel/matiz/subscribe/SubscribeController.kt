package io.oenomel.matiz.subscribe

import io.oenomel.matiz.exception.subscribe.NotFoundSubscriberException
import io.oenomel.matiz.exception.subscribe.SubscribeException
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest

@RequestMapping("/subscribe")
@RestController
class SubscribeController(val subscribeService: SubscribeService) {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/article")
    fun fetchUnreadArticle(req: HttpServletRequest): ResponseEntity<List<ArticleDTO>> {
        val subscriberName = req.getHeader("subscriber")
        val subscribeKey = req.getHeader("subscribe-key")

        return try {
            val unreadArticles = this.subscribeService.fetchUnreadArticles(subscriberName, subscribeKey)
            this.subscribeService.saveArticleHistory(subscriberName, unreadArticles)
            ResponseEntity(unreadArticles, HttpStatus.OK)
        } catch (e: NotFoundSubscriberException) {
            logger.error(e.message)
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        } catch (e: SubscribeException) {
            logger.error(e.message)
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
