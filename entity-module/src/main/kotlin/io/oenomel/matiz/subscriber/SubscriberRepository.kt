package io.oenomel.matiz.subscriber

import org.springframework.data.jpa.repository.JpaRepository

interface SubscriberRepository: JpaRepository<Subscriber, Long> {

}
