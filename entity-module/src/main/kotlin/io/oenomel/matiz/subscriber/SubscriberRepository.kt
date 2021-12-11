package io.oenomel.matiz.subscriber

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubscriberRepository: JpaRepository<Subscriber, Long> {

    fun findByNameAndSerialNumber(subscriberName: String, serialNumber: String): Subscriber?

    fun findByName(subscriberName: String): Subscriber?
}
