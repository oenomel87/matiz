package io.oenomel.matiz.subscribe

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class SubscribeControllerTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun fetchUnreadArticleTest() {
        this.mockMvc.perform(
            get("/subscribe/article")
                .header("subscriber", "abcd")
                .header("subscribe-key", "1234")
        )
            .andDo(print())
            .andExpect(status().isOk)
    }
}
