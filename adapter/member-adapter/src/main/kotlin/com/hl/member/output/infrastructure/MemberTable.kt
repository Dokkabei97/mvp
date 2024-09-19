package com.hl.member.output.infrastructure

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.date
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object MemberTable : Table("members") {
    val id: Column<Int> = integer("member_id").autoIncrement()
    val name: Column<String> = varchar("name", 100)
    val nickname: Column<String> = varchar("nickname", 50).uniqueIndex()
    val password: Column<String> = varchar("password", 255)
    val email: Column<String> = varchar("email", 100).uniqueIndex()
    val gender: Column<Char> = char("gender").check { it inList listOf('M', 'F') }
    val birthDate: Column<LocalDate?> = date("birth_date").nullable()
    val location: Column<String?> = varchar("location", 100).nullable()
    val createdAt: Column<Instant> = timestamp("created_at").defaultExpression(CurrentTimestamp)
    val updatedAt: Column<Instant> = timestamp("updated_at").defaultExpression(CurrentTimestamp)

    override val primaryKey = PrimaryKey(id, name = "PK_Member_ID")
}
