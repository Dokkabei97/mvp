package com.hl.member.output.domain

import com.hl.member.converter.toEntity
import com.hl.member.model.Member
import com.hl.member.output.infrastructure.MemberRepository
import com.hl.member.ports.output.MemberStorePort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class MemberStoreAdapter(
    val memberRepository: MemberRepository,
) : MemberStorePort {
    override fun createMember(member: Member) {
        memberRepository.save(member.toEntity())
    }

    override fun updateMember(member: Member): Member =
        // TODO: null값을 통해 동적 업데이트를 구현해야 함
        member
            .toEntity()
            .apply {
                updateNickname(member.nickname)
                updatePassword(member.password)
                updateGender(member.gender)
                updateBirthDate(member.birthDate)
                updateLocation(member.location)
            }.toDomain()
}
