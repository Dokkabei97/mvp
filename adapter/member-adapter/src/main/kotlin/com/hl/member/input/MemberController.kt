package com.hl.member.input

import com.hl.member.model.Member
import com.hl.member.ports.input.MemberReaderUseCase
import com.hl.member.ports.input.MemberStoreUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    val memberReaderUseCase: MemberReaderUseCase,
    val memberStoreUseCase: MemberStoreUseCase,
) {
    @PostMapping
    fun createMember(
        @RequestBody member: Member,
    ) {
        memberStoreUseCase.createMember(member)
    }
}
