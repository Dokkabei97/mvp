package com.hl.member.output.domain

import com.hl.member.converter.MemberConverter
import com.hl.member.model.Member
import com.hl.member.output.infrastructure.MemberRepository
import com.hl.member.ports.output.MemberReaderPort
import com.hl.member.ports.output.MemberStorePort
import org.springframework.stereotype.Component

@Component
class MemberAdapter(
    val memberRepository: MemberRepository,
) : MemberReaderPort,
    MemberStorePort {
    override fun createMember(member: Member) {
        memberRepository.save(MemberConverter().toEntity(member))
    }
}
