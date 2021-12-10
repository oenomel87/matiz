package io.oenomel.matiz.subscriber

import org.springframework.data.jpa.repository.JpaRepository

interface SubjectSubscriberRepository: JpaRepository<SubjectSubscriber, Long> {}