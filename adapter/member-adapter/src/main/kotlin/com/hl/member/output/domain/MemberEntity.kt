package com.hl.member.output.domain

import com.hl.member.model.Gender
import com.hl.member.model.Member
import com.hl.persistence.AbstractEntity
import java.time.LocalDate

class MemberEntity(
    val id: Long?,
    var name: String,
    var nickname: String,
    var email: String,
    var password: String,
    var gender: Gender,
    var birthDate: LocalDate,
    var location: String,
) : AbstractEntity() {
    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val NICKNAME_REGEX = "^[가-힣a-zA-Z0-9]{2,10}$"
        private const val NAME_REGEX = "^[가-힣a-zA-Z]{2,10}$"

        private fun validateName(name: String) {
            require(name.isNotBlank()) { "이름은 필수입니다." }
            require(name.length in 2..10) { "이름은 2~10자리여야 합니다." }
            require(name.matches(Regex(NAME_REGEX))) { "이름은 한글, 영문으로만 구성되어야 합니다." }
        }

        private fun validateNickname(nickname: String) {
            require(nickname.isNotBlank()) { "닉네임은 필수입니다." }
            require(nickname.length in 2..10) { "닉네임은 2~10자리여야 합니다." }
            require(nickname.matches(Regex(NICKNAME_REGEX))) { "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다." }
        }

        private fun validateEmail(email: String) {
            require(email.isNotBlank()) { "이메일은 필수입니다." }
            require(email.length <= 30) { "이메일은 30자리 이하여야 합니다." }
            require(email.matches(Regex(EMAIL_REGEX))) { "이메일 형식이 올바르지 않습니다." }
        }

        private fun validatePassword(password: String) {
            require(password.isNotBlank()) { "비밀번호는 필수입니다." }
        }
    }

    fun updateNickname(nickname: String) {
        validateNickname(nickname)
        this.nickname = nickname
    }

    fun updatePassword(password: String) {
        validatePassword(password)
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

    fun toDomain(): Member =
        Member(
            name = name,
            nickname = nickname,
            email = email,
            password = password,
            gender = gender,
            birthDate = birthDate,
            location = location,
        )
}
