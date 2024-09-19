package com.hl.member.input.dto

import com.hl.member.command.MemberCommand
import com.hl.member.model.Gender
import java.time.LocalDate

data class MemberRequest(
    val name: String,
    val nickname: String,
    val email: String,
    val password: String,
    val gender: Gender,
    val birthDate: LocalDate,
    val location: String,
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

    fun toUpdateMemberCommand(id: Long): MemberCommand.UpdateMemberCommand =
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
