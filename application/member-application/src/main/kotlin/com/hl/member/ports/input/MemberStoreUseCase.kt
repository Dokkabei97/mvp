package com.hl.member.ports.input

import com.hl.member.command.MemberCommand
import com.hl.member.model.Member

interface MemberStoreUseCase {
    fun createMember(command: MemberCommand.CreateMemberCommand)

    fun updateMember(command: MemberCommand.UpdateMemberCommand): Member
}
