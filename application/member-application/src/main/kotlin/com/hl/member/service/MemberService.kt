package com.hl.member.service

import com.hl.member.command.MemberCommand
import com.hl.member.model.Member
import com.hl.member.ports.input.MemberReaderUseCase
import com.hl.member.ports.input.MemberStoreUseCase
import com.hl.member.ports.output.MemberReaderPort
import com.hl.member.ports.output.MemberStorePort
import org.springframework.stereotype.Service

@Service
class MemberService(
    val memberReaderPort: MemberReaderPort,
    val memberStorePort: MemberStorePort,
) : MemberReaderUseCase,
    MemberStoreUseCase {
    override fun createMember(command: MemberCommand.CreateMemberCommand) {
        memberStorePort.createMember(command)
    }

    override fun updateMember(command: MemberCommand.UpdateMemberCommand): Member = memberStorePort.updateMember(command)
}
