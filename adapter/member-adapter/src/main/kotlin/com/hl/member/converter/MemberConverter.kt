package com.hl.member.converter

import com.hl.member.command.MemberCommand
import com.hl.member.model.Member
import com.hl.member.output.domain.MemberEntity

fun Member.toEntity(): MemberEntity =
    MemberEntity(
        id = null,
        name = this.name,
        nickname = this.nickname,
        email = this.email,
        password = this.password,
        gender = this.gender,
        birthDate = this.birthDate,
        location = this.location,
    )

fun MemberCommand.CreateMemberCommand.toEntity(): MemberEntity =
    MemberEntity(
        id = null,
        name = this.name,
        nickname = this.nickname,
        email = this.email,
        password = this.password,
        gender = this.gender,
        birthDate = this.birthDate,
        location = this.location,
    )
