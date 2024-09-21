package com.hl.member.output.infrastructure

import com.hl.member.model.Gender
import com.hl.member.output.domain.MemberEntity
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Component

@Component
class MemberRepositoryImpl : MemberRepository {
    override fun save(entity: MemberEntity) {
        transaction {
            Members.insert {
                it[name] = entity.name
                it[nickname] = entity.nickname
                it[email] = entity.email
                it[password] = entity.password
                it[gender] = entity.gender
                it[birthDate] = entity.birthDate.toKotlinLocalDate()
                it[location] = entity.location
            }
        }
    }

    override fun update(entity: MemberEntity): MemberEntity {
        transaction {
            Members.update({ Members.id.eq(entity.id!!) }) {
                it[name] = entity.name
                it[nickname] = entity.nickname
                it[email] = entity.email
                it[password] = entity.password
                it[gender] = entity.gender
                it[birthDate] = entity.birthDate.toKotlinLocalDate()
                it[location] = entity.location
            }
        }
        return entity
    }

    override fun findById(id: Long): MemberEntity? {
        val member =
            Members
                .select(
                    Members.id,
                    Members.name,
                    Members.nickname,
                    Members.email,
                    Members.password,
                    Members.gender,
                    Members.birthDate,
                    Members.location,
                ).where {
                    Members.id.eq(id)
                }.singleOrNull()

        return member!!.let {
            MemberEntity(
                id = it[Members.id].value,
                name = it[Members.name],
                nickname = it[Members.nickname],
                email = it[Members.email],
                password = it[Members.password],
                gender = it[Members.gender].let { gen -> Gender.valueOf(gen.toString()) },
                birthDate = it[Members.birthDate].toJavaLocalDate(),
                location = it[Members.location],
            )
        }
    }
}
