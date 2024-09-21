package com.hl.member.output.infrastructure

import com.hl.member.common.BaseLongIdTable
import com.hl.member.model.Gender
import kotlinx.datetime.LocalDate
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.kotlin.datetime.date

object Members : BaseLongIdTable("member") {
    val name: Column<String> = varchar("name", 100)
    val nickname: Column<String> = varchar("nickname", 50).uniqueIndex()
    val password: Column<String> = varchar("password", 255)
    val email: Column<String> = varchar("email", 100).uniqueIndex()
    val gender: Column<Gender> = enumerationByName("gender", 6, Gender::class).check { it inList listOf(Gender.MALE, Gender.FEMALE) }
    val birthDate: Column<LocalDate> = date("birth_date")
    val location: Column<String> = varchar("location", 100)
}
