package io.oenomel.matiz.subscriber

import org.springframework.data.jpa.repository.JpaRepository

interface SubscribeHistoryRepository: JpaRepository<SubscribeHistory, Long> {}