package io.oenomel.matiz.subscribe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class SubscribeServiceTests {

    @Autowired
    lateinit var subscribeService: SubscribeService

    @Test
    fun fetchUnreadArticlesTest() {
        assertThat(subscribeService.fetchUnreadArticles("1234"))
            .isNotEmpty
    }
}