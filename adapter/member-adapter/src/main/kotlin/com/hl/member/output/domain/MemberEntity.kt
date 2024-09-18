package com.hl.member.output.domain

import com.hl.member.model.Gender
import com.hl.persistence.AbstractEntity
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var nickname: String,
    var email: String,
    var password: String,
    @Enumerated(EnumType.STRING)
    var gender: Gender,
    var birthDate: LocalDate,
    var location: String,
) : AbstractEntity() {
    fun updateNickname(nickname: String) {
        require(nickname.isNotBlank()) { "닉네임은 빈 문자열이 아니어야 합니다." }
        this.nickname = nickname
    }

    fun updatePassword(password: String) {
        require(password.isNotBlank()) { "비밀번호는 빈 문자열이 아니어야 합니다." }
        this.password = password
    }

    fun updateGender(gender: Gender) {
        this.gender = gender
    }

    fun updateBirthDate(birthDate: LocalDate) {
        this.birthDate = birthDate
    }

    fun updateLocation(location: String) {
        require(location.isNotBlank()) { "지역은 빈 문자열이 아니어야 합니다." }
        this.location = location
    }
}
