package io.oenomel.matiz.subscriber

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubscriberRepository: JpaRepository<Subscriber, Long> {

    fun findByNameAndSubscribeKey(subscriberName: String, subscribeKey: String): Subscriber?

    fun findByName(subscriberName: String): Subscriber?
}
