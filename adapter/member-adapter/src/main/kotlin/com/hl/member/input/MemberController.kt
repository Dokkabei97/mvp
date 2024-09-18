package com.hl.member.input

import com.hl.core.response.CommonResponse
import com.hl.member.input.dto.MemberRequest
import com.hl.member.model.Member
import com.hl.member.ports.input.MemberReaderUseCase
import com.hl.member.ports.input.MemberStoreUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        @RequestBody request: MemberRequest.CreateMemberRequest,
    ) {
        memberStoreUseCase.createMember(request.toMember())
    }

    @PutMapping
    fun updateMember(
        @RequestBody request: MemberRequest.UpdateMemberRequest,
    ): CommonResponse<Member> {
        // FIXME: memberStoreUseCase.updateMember(request.toMember())
        val member = memberStoreUseCase.updateMember(request.toMember())
        return CommonResponse.success(member)
    }
}
