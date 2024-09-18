package com.hl.member.input.dto

import com.hl.member.model.Gender
import com.hl.member.model.Member
import java.time.LocalDate

sealed class MemberRequest(
    open val name: String,
    open val nickname: String,
    open val email: String,
    open val password: String,
    open val gender: Gender,
    open val birthDate: LocalDate,
    open val location: String,
) {
    data class CreateMemberRequest(
        override val name: String,
        override val nickname: String,
        override val email: String,
        override val password: String,
        override val gender: Gender,
        override val birthDate: LocalDate,
        override val location: String,
    ) : MemberRequest(name, nickname, email, password, gender, birthDate, location) {
        fun toMember() =
            Member(
                name = name,
                nickname = nickname,
                email = email,
                password = password,
                gender = gender,
                birthDate = birthDate,
                location = location,
            )
    }

    data class UpdateMemberRequest(
        val id: Long,
        val name: String?,
        val nickname: String?,
        val email: String?,
        val password: String?,
        val gender: Gender?,
        val birthDate: LocalDate?,
        val location: String?,
    )
}
