import kotlin.math.abs

fun main() {

    fun readLocations(input: List<String>): Pair<MutableList<Long>, MutableList<Long>> {
        val leftLocations = mutableListOf<Long>()
        val rightLocations = mutableListOf<Long>()

        input.forEach { line ->
            val locations = line.split("   ")
            leftLocations.add(locations.first().toLong())
            rightLocations.add(locations.last().toLong())
        }

        return Pair(leftLocations, rightLocations)
    }

    fun part1(input: List<String>): Long {
        val (leftLocations, rightLocations) = readLocations(input)

        leftLocations.sort()
        rightLocations.sort()

        var totalDistance = 0L
        for (i in 0 until leftLocations.size) {
            totalDistance += abs(leftLocations[i] - rightLocations[i])
        }

        return totalDistance
    }

    fun part2(input: List<String>): Long {
        val (leftLocations, rightLocations) = readLocations(input)

        var totalSimilarityScore = 0L
        leftLocations.forEach { location ->
            totalSimilarityScore += location * rightLocations.count { l -> l == location }
        }

        return totalSimilarityScore
    }

    check(part1(listOf("3   9", "5   2")) == 5L)
    check(part1(readInput("Day01_test")) == 11L)
    "Part1 result = ${part1(readInput("Day01"))}".println()

    check(part2(readInput("Day01_test")) == 31L)
    "Part2 result = ${part2(readInput("Day01"))}".println()
}
