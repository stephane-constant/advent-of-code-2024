fun main() {
    // As warmup, compute the 1st puzzle from AoC 2023

    fun part1(input: List<String>): Int {
        return input
            .map { line -> line.filter{ it.isDigit() } }
            .sumOf { numbers -> ("" + numbers.first() + numbers.last()).toInt() }
    }

    // Recursive parsing with a map of literal numbers
    fun parseLiteralNumbers(line: String, parsedNumbers: String): String {
        val numbersMap = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
            "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)

        return when {
            line.isEmpty() -> parsedNumbers // We stop parsing
            line[0].isDigit() -> parseLiteralNumbers(line.substring(1), parsedNumbers + line.first())
            line.take(3) in numbersMap.keys -> parseLiteralNumbers(line.substring(1), parsedNumbers + numbersMap[line.take(3)])
            line.take(4) in numbersMap.keys -> parseLiteralNumbers(line.substring(1), parsedNumbers + numbersMap[line.take(4)])
            line.take(5) in numbersMap.keys -> parseLiteralNumbers(line.substring(1), parsedNumbers + numbersMap[line.take(5)])
            else -> parseLiteralNumbers(line.substring(1), parsedNumbers)
        }
    }

    fun part2(input: List<String>): Int {
        return input
            .map { line -> parseLiteralNumbers(line, "") }
            .sumOf { numbers -> ("" + numbers.first() + numbers.last()).toInt() }
    }

    // Part 1
    check(part1(listOf("1abc2")) == 12)
    check(part1(readInput("Day00_part1_test")) == 142)
    part1(readInput("Day00")).println()

    // Part 2
    check(part2(listOf("two1nine")) == 29)
    check(part2(readInput("Day00_part2_test")) == 281)
    part2(readInput("Day00")).println()
}
