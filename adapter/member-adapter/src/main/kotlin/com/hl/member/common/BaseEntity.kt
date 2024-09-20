package com.hl.member.common

import kotlinx.datetime.LocalDateTime
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

abstract class BaseLongIdTable(
    name: String,
    idName: String = "id",
) : LongIdTable(name, idName) {
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").clientDefault { LocalDateTime.now() }
}

abstract class BaseEntity(
    id: EntityID<Long>,
    table: BaseLongIdTable,
) : LongEntity(id) {
    var createdAt by table.createdAt
    var updatedAt by table.updatedAt
}

abstract class BaseEntityClass<E : BaseEntity>(
    table: BaseLongIdTable,
) : LongEntityClass<E>(table)
