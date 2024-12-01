import kotlin.math.abs

fun main() {

    fun readLocations(input: List<String>): Pair<MutableList<Int>, MutableList<Int>> {
        val leftLocations = mutableListOf<Int>()
        val rightLocations = mutableListOf<Int>()

        input.forEach { line ->
            val locations = line.split("   ")
            leftLocations.add(locations[0].toInt())
            rightLocations.add(locations[1].toInt())
        }

        return Pair(leftLocations, rightLocations)
    }

    fun part1(input: List<String>): Int {
        val (leftLocations, rightLocations) = readLocations(input)

        leftLocations.sort()
        rightLocations.sort()

        var totalDistance = 0
        for (i in 0 until leftLocations.size) {
            totalDistance += abs(leftLocations[i] - rightLocations[i])
        }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (leftLocations, rightLocations) = readLocations(input)

        var totalSimilarityScore = 0
        leftLocations.forEach { location ->
            totalSimilarityScore += location * rightLocations.count { l -> l == location }
        }

        return totalSimilarityScore
    }

    check(part1(listOf("3   9", "5   2")) == 1+4)
    check(part1(readInput("Day01_test")) == 11)
    "Part1 result = ${part1(readInput("Day01"))}".println()

    check(part2(readInput("Day01_test")) == 31)
    "Part2 result = ${part2(readInput("Day01"))}".println()
}
