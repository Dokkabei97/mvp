package com.hl.member.output.domain

import com.hl.member.output.infrastructure.MemberRepository
import com.hl.member.ports.output.MemberReaderPort
import org.springframework.stereotype.Component

@Component
class MemberReaderAdapter(
    val memberRepository: MemberRepository,
) : MemberReaderPort
