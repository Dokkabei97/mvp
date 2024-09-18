package com.hl.member.ports.input

import com.hl.member.model.Gender
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import java.time.LocalDate

sealed class MemberCommand(
    @field:NotBlank
    open val name: String,
    open val nickname: String,
    @field:Email
    open val email: String,
    open val password: String,
    open val gender: Gender,
    open val birthDate: LocalDate,
    open val location: String,
) {
    data class CreateMember(
        override val name: String,
        override val nickname: String,
        override val email: String,
        override val password: String,
        override val gender: Gender,
        override val birthDate: LocalDate,
        override val location: String,
    ) : MemberCommand(name, nickname, email, password, gender, birthDate, location)

    data class UpdateMember(
        val name: String?,
        val nickname: String?,
        val email: String?,
        val password: String?,
        val gender: Gender?,
        val birthDate: LocalDate?,
        val location: String?,
    )
}
