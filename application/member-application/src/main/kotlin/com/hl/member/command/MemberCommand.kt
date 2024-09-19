package com.hl.member.command

import com.hl.member.model.Gender
import java.time.LocalDate

sealed class MemberCommand(
    open val name: String,
    open val nickname: String,
    open val email: String,
    open val password: String,
    open val gender: Gender,
    open val birthDate: LocalDate,
    open val location: String,
) {
    data class CreateMemberCommand(
        override val name: String,
        override val nickname: String,
        override val email: String,
        override val password: String,
        override val gender: Gender,
        override val birthDate: LocalDate,
        override val location: String,
    ) : MemberCommand(name, nickname, email, password, gender, birthDate, location)

    data class UpdateMemberCommand(
        val id: Long,
        val name: String? = null,
        val nickname: String? = null,
        val email: String? = null,
        val password: String? = null,
        val gender: Gender? = null,
        val birthDate: LocalDate? = null,
        val location: String? = null,
    )
}
