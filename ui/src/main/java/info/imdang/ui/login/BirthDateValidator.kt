package info.imdang.ui.login

import java.util.Calendar

object BirthDateValidator {
    fun format(input: String): String {
        val digits = input.filter { it.isDigit() }.take(8)

        return buildString {
            digits.forEachIndexed { i, c ->
                append(c)
                if (i == 3 || i == 5) append(".")
            }
        }
    }

    fun validate(input: String): Boolean {
        if (!input.matches(Regex("\\d{4}\\.\\d{2}\\.\\d{2}"))) return false

        val parts = input.split(".")
        val year = parts[0].toIntOrNull() ?: return false
        val month = parts[1].toIntOrNull() ?: return false
        val day = parts[2].toIntOrNull() ?: return false

        if (month !in 1..12) return false

        val maxDay = when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (isLeapYear(year)) 29 else 28
            else -> return false
        }

        if (day !in 1..maxDay) return false

        val today = Calendar.getInstance()
        val inputDate = Calendar.getInstance().apply {
            set(year, month - 1, day, 0, 0, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return !inputDate.after(today)
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}