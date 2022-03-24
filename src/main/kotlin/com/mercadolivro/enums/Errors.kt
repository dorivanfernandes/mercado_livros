package com.mercadolivro.enums

enum class Errors(
    val code: String,
    val message: String
) {
    ML0001("ML-0001", "Book [%s] not exists")

}