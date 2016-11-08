package samples.collections

import java.util.*

object Maps {

    object Instantiation {

        fun mapFromPairs() {
            val map = mapOf(1 to "x", 2 to "y", -1 to "zz")
            println(map)
        }

        fun mutableMapFromPairs() {
            val map = mutableMapOf(1 to "x", 2 to "y", -1 to "zz")
            println(map)

            map[1] = "a"
            println(map)
        }

        fun hashMapFromPairs() {
            val map: HashMap<Int, String> = hashMapOf(1 to "x", 2 to "y", -1 to "zz")
            println(map)
        }

        fun linkedMapFromPairs() {
            val map: LinkedHashMap<Int, String> = linkedMapOf(1 to "x", 2 to "y", -1 to "zz")
            println(map)
        }

        fun emptyReadOnlyMap() {
            val map = emptyMap<String, Int>()
            println("Map $map is empty: ${map.isEmpty()}")

            val anotherMap = mapOf<String, Int>()
            println("Empty maps are equal: ${map == anotherMap}")
        }

        fun emptyMutableMap() {
            val map = mutableMapOf<Int, Any?>()
            println("Map is empty: ${map.isEmpty()}")

            map[1] = "x"
            map[2] = 1.05
            println("Now map contains something: $map")
        }

    }


    object Usage {
        fun getOrElse() {
            val map = mutableMapOf<String, Int?>()
            println(map.getOrElse("x") { 1 }) // prints: 1

            map["x"] = 3
            println(map.getOrElse("x") { 1 }) // prints: 3

            map["x"] = null
            println(map.getOrElse("x") { 1 }) // prints: 1
        }

        fun getOrPut() {
            val map = mutableMapOf<String, Int?>()
            println(map.getOrPut("x") { 2 }) // prints: 2
            println(map.getOrPut("x") { 3 }) // prints: 2

            println(map.getOrPut("y") { null }) // prints: null
            println(map.getOrPut("y") { 42 })   // prints: 42
        }

        fun forOverEntries() {
            val map = mapOf("beverage" to 2.7, "meal" to 12.4, "dessert" to 5.8)

            for ((key, value) in map) {
                println("$key - $value") // prints: beverage - 2.7
                                         // prints: meal - 12.4
                                         // prints: dessert - 5.8
            }

        }
    }

    object Transforms {

        fun mapKeys() {
            val map1 = mapOf("beer" to 2.7, "bisquit" to 5.8)
            val map2 = map1.mapKeys { it.key.length }
            println(map2) // prints: { 4=2.7, 7=5.8}

            val map3 = map1.mapKeys { it.key.take(1) }
            println(map3) // prints: { b=5.8 }
        }

        fun mapValues() {
            val map1 = mapOf("beverage" to 2.7, "meal" to 12.4)
            val map2 = map1.mapValues { it.value.toString() + "$" }

            println(map2) // prints: { beverage=2.7$, meal=12.4$ }
        }

    }

}

