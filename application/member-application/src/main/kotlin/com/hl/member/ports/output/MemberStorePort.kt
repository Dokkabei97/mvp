package com.hl.member.ports.output

import com.hl.member.command.MemberCommand
import com.hl.member.model.Member

interface MemberStorePort {
    fun createMember(command: MemberCommand.CreateMemberCommand)

    fun updateMember(command: MemberCommand.UpdateMemberCommand): Member
}
