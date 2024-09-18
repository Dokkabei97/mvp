package com.hl.member.ports.output

import com.hl.member.model.Member

interface MemberStorePort {
    fun createMember(member: Member)

    fun updateMember(member: Member): Member
}
