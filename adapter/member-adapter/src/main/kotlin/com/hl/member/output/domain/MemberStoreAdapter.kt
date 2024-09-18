package com.hl.member.output.domain

import com.hl.member.converter.MemberConverter
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
        memberRepository.save(MemberConverter().toEntity(member))
    }

    override fun updateMember(member: Member): Member {
        val memberEntity = MemberConverter().toEntity(member)

        memberEntity.apply {
        }
    }
}
