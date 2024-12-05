import kotlin.math.abs

enum class ReportCheck { Safe, Unsafe }
enum class LevelDirection { Increasing, Decreasing, Equal }

fun main() {

    fun getDirection(firstLevel: Int, secondLevel: Int): LevelDirection =
        if (firstLevel == secondLevel) LevelDirection.Equal
        else if (firstLevel < secondLevel) LevelDirection.Increasing
            else LevelDirection.Decreasing

    fun isReportSafe(levels: List<Int>, previousDirection: LevelDirection? = null) : ReportCheck {
        if (levels.size == 1)
            return ReportCheck.Safe // last iteration

        if ((levels[0] == levels[1]) or (abs(levels.first() - levels[1]) > 3))
            return ReportCheck.Unsafe // unsafe case 2

        if (previousDirection == null)
            return isReportSafe(levels.subList(1, levels.size), getDirection(levels[0], levels[1])) // first iteration, continue

        val currentDirection = getDirection(levels[0], levels[1])

        if (currentDirection != previousDirection)
            return ReportCheck.Unsafe // unsafe case 1

        return isReportSafe(levels.subList(1, levels.size), previousDirection) // continue
    }

    fun part1(input: List<String>): Int {
        return input.map { it.split(" ").map { it.toInt() } }
            .map { isReportSafe(it) } // recursive way, I like it :-)
            .count { it == ReportCheck.Safe }
    }

    fun areLevelsSafe(first:Int, second:Int, previousDirection: LevelDirection) : ReportCheck {
        return when {
            ((first == second) or (abs(first - second) > 3)) -> ReportCheck.Unsafe // unsafe case 2
            (previousDirection != getDirection(first, second)) -> ReportCheck.Unsafe // unsafe case 1
            else -> ReportCheck.Safe
        }
    }

    fun isReportSafe(levels: List<Int>, withToleration: Boolean) : ReportCheck {
        val initialDirection = getDirection(levels[0], levels[1])

        for (i in (0..(levels.size - 2))) {
            val levelsSafety = areLevelsSafe(levels[i], levels[i+1], initialDirection)
            if (levelsSafety == ReportCheck.Unsafe) {
                if (withToleration) {
                    "Report $levels is not safe but we tolerate it".println()
                    for (j in (0..(levels.size - 1))) {
                        val newLevels = levels.toMutableList()
                        newLevels.removeAt(j)
                        val newLevelsSafety = isReportSafe(newLevels, withToleration = false)
                        if (newLevelsSafety == ReportCheck.Safe) return ReportCheck.Safe // exit the loop as safe
                    }

                    "Report $levels is definitely not safe despite toleration".println()
                    return ReportCheck.Unsafe
                }
                else {
                    "Report $levels is not safe and we don't tolerate it".println()
                    return ReportCheck.Unsafe
                }
            }
        }
        "Report $levels is safe".println()
        return ReportCheck.Safe
    }

    fun part2(input: List<String>): Int {
        return input.map { it.split(" ").map { it.toInt() } }
            .map { isReportSafe(levels = it, withToleration = true) } // another recursive way
            .count { it == ReportCheck.Safe }
    }

    check(part1(listOf("7 6 4 2 1", "1 2 7 8 9")) == 1)
    check(part1(readInput("Day02_test")) == 2)
    "Part1 result = ${part1(readInput("Day02"))}".println()

    check(part2(listOf("1 3 2 4 5", "8 6 4 4 1")) == 2)
    check(part2(readInput("Day02_test")) == 4)
    "Part2 result = ${part2(readInput("Day02"))}".println()
}
