fun main() {
    // As warmup, compute the 1st puzzle from AoC 2023

    fun part1(input: List<String>): Int {
        return input
            .map { line -> line.filter{ it.isDigit() } }
            .sumOf { numbers -> ("" + numbers.first() + numbers.last()).toInt() }
    }

    // Brut recursive parsing. Not very elegant, but it works...
    fun parseLiteralNumbers(line: String, numbers: String): String {
        return when {
            line.isEmpty() -> numbers // We stop parsing
            line.startsWith("one") or line.startsWith("1") -> parseLiteralNumbers(line.substring(1), numbers + "1")
            line.startsWith("two") or line.startsWith("2") -> parseLiteralNumbers(line.substring(1), numbers + "2")
            line.startsWith("three") or line.startsWith("3") -> parseLiteralNumbers(line.substring(1), numbers + "3")
            line.startsWith("four") or line.startsWith("4") -> parseLiteralNumbers(line.substring(1), numbers + "4")
            line.startsWith("five") or line.startsWith("5") -> parseLiteralNumbers(line.substring(1), numbers + "5")
            line.startsWith("six") or line.startsWith("6") -> parseLiteralNumbers(line.substring(1), numbers + "6")
            line.startsWith("seven") or line.startsWith("7") -> parseLiteralNumbers(line.substring(1), numbers + "7")
            line.startsWith("eight") or line.startsWith("8") -> parseLiteralNumbers(line.substring(1), numbers + "8")
            line.startsWith("nine") or line.startsWith("9") -> parseLiteralNumbers(line.substring(1), numbers + "9")
            line[0].isDigit() -> "" + line.first()
            else -> parseLiteralNumbers(line.substring(1), numbers) // We continue parsing
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
