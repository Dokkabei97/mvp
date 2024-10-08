package com.hl.member.output.domain

import com.hl.core.utils.logger
import com.hl.member.command.MemberCommand
import com.hl.member.converter.toEntity
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
    private val log by logger()

    override fun createMember(command: MemberCommand.CreateMemberCommand) {
        log.info("Create Member: $command")
        memberRepository.save(command.toEntity())
    }

    // JPA 더티체크로 인해 JPA에 의존성이 강해져 업데이트 로직이 adapter단에 구현되어 있음
    // TODO: JPA -> Exposed로 변경
    override fun updateMember(command: MemberCommand.UpdateMemberCommand): Member {
        val member = memberRepository.findById(command.id)

        return member!!.toDomain()
    }
}
