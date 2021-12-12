package io.oenomel.matiz.publisher

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectPublisherRepository: JpaRepository<SubjectPublisher, Long> {

    fun findByPublisher_NameAndPublisher_PublishKeyAndSubject_Active(publisherName: String, publishKey: String, active: Boolean = true): SubjectPublisher?

    @EntityGraph(attributePaths = ["publishHistories"])
    fun findSubjectPublisherById(id: Long): SubjectPublisher?
}
