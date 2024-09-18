package com.hl.member.model

import java.time.LocalDate

class Member(
    val name: String,
    val nickname: String,
    val email: String,
    val password: String,
    val gender: Gender,
    val birthDate: LocalDate,
    val location: String,
)
