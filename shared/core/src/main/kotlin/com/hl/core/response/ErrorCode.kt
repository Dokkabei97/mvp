package com.hl.core.response

enum class ErrorCode(
    val errorMessage: String,
) {
    COMMON_SYSTEM_ERROR("일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
}
