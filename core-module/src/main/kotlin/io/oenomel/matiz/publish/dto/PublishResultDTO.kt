package io.oenomel.matiz.publish.dto

import io.oenomel.matiz.publisher.PublishHistory
import java.time.format.DateTimeFormatter

data class PublishResultDTO (
    val publishId: Long?,
    val publisherName: String,
    val subjectName: String,
    val publishedAt: String,
)

fun convertPublishHistoryToDTO(publishHistory: PublishHistory): PublishResultDTO {
    return PublishResultDTO(
        publishId = publishHistory.id,
        publisherName = publishHistory.subjectPublisher.publisher.name,
        subjectName = publishHistory.subjectPublisher.subject.name,
        publishedAt = publishHistory.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    )
}
