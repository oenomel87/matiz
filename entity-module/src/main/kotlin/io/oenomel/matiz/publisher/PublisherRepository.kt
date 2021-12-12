package io.oenomel.matiz.publisher

import org.springframework.data.jpa.repository.JpaRepository

interface PublisherRepository: JpaRepository<Publisher, Long> {
}