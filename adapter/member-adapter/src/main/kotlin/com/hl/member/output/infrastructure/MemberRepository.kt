package com.hl.member.output.infrastructure

import com.hl.member.output.domain.MemberEntity
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository {
    fun save(entity: MemberEntity)

    fun findById(id: Long): MemberEntity?
}
