package io.oenomel.matiz.publish

import io.oenomel.matiz.exception.publish.NotFoundPublisherException
import io.oenomel.matiz.exception.publish.PublishException
import io.oenomel.matiz.publish.dto.PublishResultDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RequestMapping("/publish")
@RestController
class PublishController(val publishService: PublishService) {

    @PostMapping("/article")
    fun publish(req: HttpServletRequest, content: String): ResponseEntity<PublishResultDTO> {
        val publisherName = req.getHeader("publisher")
        val publishKey = req.getHeader("publish-key")
        return try {
            val result = this.publishService.publishArticle(publisherName, publishKey, content)
            ResponseEntity(result, HttpStatus.OK)
        } catch (e: NotFoundPublisherException) {
            ResponseEntity(HttpStatus.UNAUTHORIZED)
        } catch (e: PublishException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
