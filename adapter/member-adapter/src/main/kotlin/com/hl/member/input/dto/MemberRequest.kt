package com.hl.member.input.dto

import com.hl.member.command.MemberCommand
import com.hl.member.model.Gender
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
    fun toCreateMemberCommand(): MemberCommand.CreateMemberCommand =
        MemberCommand.CreateMemberCommand(
            name = name,
            nickname = nickname,
            email = email,
            password = password,
            gender = gender,
            birthDate = birthDate,
            location = location,
        )

    data class UpdateMemberRequest(
        val id: Long,
        val name: String?,
        val nickname: String?,
        val email: String?,
        val password: String?,
        val gender: Gender?,
        val birthDate: LocalDate?,
        val location: String?,
    ) {
        fun toUpdateMemberCommand(): MemberCommand.UpdateMemberCommand =
            MemberCommand.UpdateMemberCommand(
                id = id,
                name = name,
                nickname = nickname,
                email = email,
                password = password,
                gender = gender,
                birthDate = birthDate,
                location = location,
            )
    }
}
