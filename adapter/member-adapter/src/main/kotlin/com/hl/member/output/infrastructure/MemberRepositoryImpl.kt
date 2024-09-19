package com.hl.member.output.infrastructure

import com.hl.member.model.Gender
import com.hl.member.output.domain.MemberEntity
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class MemberRepositoryImpl : MemberRepository {
    override fun save(entity: MemberEntity) {
        transaction {
            MemberTable.insert {
                it[name] = entity.name
                it[nickname] = entity.nickname
                it[email] = entity.email
                it[password] = entity.password
                it[gender] = entity.gender.name.first()
                it[birthDate] = entity.birthDate.toKotlinLocalDate()
                it[location] = entity.location
            }
        }
    }

    override fun findById(id: Long): MemberEntity? {
        val member =
            MemberTable
                .select(
                    MemberTable.id,
                    MemberTable.name,
                    MemberTable.nickname,
                    MemberTable.email,
                    MemberTable.password,
                    MemberTable.gender,
                    MemberTable.birthDate,
                    MemberTable.location,
                ).where {
                    MemberTable.id.eq(id.toInt())
                }.singleOrNull()

        return member!!.let {
            MemberEntity(
                id = it[MemberTable.id].toLong(),
                name = it[MemberTable.name],
                nickname = it[MemberTable.nickname],
                email = it[MemberTable.email],
                password = it[MemberTable.password],
                gender = it[MemberTable.gender].let { gen -> Gender.valueOf(gen.toString()) },
                birthDate = it[MemberTable.birthDate]!!.toJavaLocalDate(),
                location = it[MemberTable.location]!!,
            )
        }
    }
}
