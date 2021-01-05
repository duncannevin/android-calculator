package com.duncannevin.calculator

import java.util.*

enum class Operator {
    DIVIDE,
    MULTIPLY,
    MINUS,
    PLUS,
    EQUALS;

    override fun toString(): String {
        val first = super.toString().first()
        val rest = super.toString().drop(1).toLowerCase(Locale.ROOT)
        return first + rest
    }

    fun toSymbol(): String {
        return when(super.toString()) {
            "DIVIDE" -> "/"
            "MULTIPLY" -> "*"
            "MINUS" -> "-"
            "PLUS" -> "+"
            "EQUALS" -> "="
            else -> "nothing"
        }
    }
}
