package com.hl.member.ports.input

import com.hl.member.model.Member

interface MemberStoreUseCase {
    fun createMember(member: Member)

    fun updateMember(member: Member)
}
