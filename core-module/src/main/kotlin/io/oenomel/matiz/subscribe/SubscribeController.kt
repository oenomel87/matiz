package io.oenomel.matiz.subscribe

import io.oenomel.matiz.exception.SubscribeException
import io.oenomel.matiz.subscribe.dto.ArticleDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletRequest

@RequestMapping("/subscribe")
@RestController
class SubscribeController(var subscribeService: SubscribeService) {

    @GetMapping("/article")
    fun fetchArticle(req: HttpServletRequest): List<ArticleDTO> {
        val subscriberName = req.getHeader("subscriber")
        val serial = req.getHeader("subscribe-key")

        return try {
            this.subscribeService.fetchUnreadArticles(subscriberName, serial)
        } catch (e: SubscribeException) {
            Collections.emptyList()
        }
    }
}
