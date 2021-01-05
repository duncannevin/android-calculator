package com.duncannevin.calculator

data class Calculator(val sum: Double? = null, val pendingNumber: String = "", val pendingOperator: Operator? = null) {
    fun operatorPressed(operator: Operator): Calculator {
        if (pendingNumber.isBlank()) {
            return Calculator(sum, pendingNumber, operator)
        }

        return when(operator) {
            Operator.EQUALS -> Calculator(computeSum(), "", null)
            else -> Calculator(computeSum(), "", operator)
        }
    }

    fun numPressed(numPart: String): Calculator {
        return Calculator(sum, pendingNumber + numPart, pendingOperator)
    }

    fun dotPressed(): Calculator {
        if (pendingNumber.isNotEmpty() && pendingNumber.last() == '.') {
            return this
        }

        return Calculator(sum, "$pendingNumber.", pendingOperator)
    }

    private fun computeSum(): Double? {
        if (pendingNumber.isBlank()) {
            return sum
        }

        val doubled: Double = pendingNumber.toDouble()

        if (sum == null) {
            return doubled
        }

        return when(pendingOperator) {
            Operator.PLUS -> sum + doubled
            Operator.MINUS -> sum - doubled
            Operator.MULTIPLY -> sum * doubled
            Operator.DIVIDE -> sum / doubled
            Operator.EQUALS -> sum
            null -> sum
        }
    }
}
