package io.oenomel.matiz.publisher

import org.springframework.data.jpa.repository.JpaRepository

interface PublishHistoryRepository: JpaRepository<PublishHistory, Long> {
}