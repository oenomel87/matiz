package io.oenomel.matiz.operator

import javax.persistence.*

@Entity
@Table
data class Operator (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,

    val password: String? = null,
)
