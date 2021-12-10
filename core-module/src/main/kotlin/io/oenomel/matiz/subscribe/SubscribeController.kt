package io.oenomel.matiz.subscribe

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RequestMapping("/subscribe")
@RestController
class SubscribeController(var subscribeService: SubscribeService) {

    @GetMapping("/article")
    fun fetchArticle(req: HttpServletRequest) {
    }
}
