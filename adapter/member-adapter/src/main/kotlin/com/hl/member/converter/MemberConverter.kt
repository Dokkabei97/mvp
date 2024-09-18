package com.hl.member.converter

import com.hl.member.model.Member
import com.hl.member.output.domain.MemberEntity

class MemberConverter {
    fun toEntity(member: Member): MemberEntity =
        MemberEntity(
            id = member.id,
            nickname = member.username,
            password = member.password,
            email = member.email,
            gender = member.gender,
            birthDate = member.birthDate,
            location = member.location,
        )

    fun toDomain(entity: MemberEntity): Member =
        Member(
            id = entity.id,
            username = entity.nickname,
            password = entity.password,
            email = entity.email,
            gender = entity.gender,
            birthDate = entity.birthDate,
            location = entity.location,
        )
}
