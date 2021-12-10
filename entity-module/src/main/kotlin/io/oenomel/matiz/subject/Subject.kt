package io.oenomel.matiz.subject

import io.oenomel.matiz.article.Article
import io.oenomel.matiz.subscriber.SubjectSubscriber
import javax.persistence.*

@Entity
@Table
data class Subject (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val active: Boolean,

    @OneToMany(mappedBy = "subject")
    var articles: List<Article>? = null,

    @OneToMany(mappedBy = "subject")
    var subjectSubscribers: List<SubjectSubscriber>? = null
)
