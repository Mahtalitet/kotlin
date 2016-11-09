package samples.collections

import samples.*
import kotlin.test.*
import java.util.*

@RunWith(Enclosed::class)
class Maps {

    class Instantiation {

        @Sample
        fun mapFromPairs() {
            val map = mapOf(1 to "x", 2 to "y", -1 to "zz")
            assertPrints(map, "{1=x, 2=y, -1=zz}")
        }

        @Sample
        fun mutableMapFromPairs() {
            val map = mutableMapOf(1 to "x", 2 to "y", -1 to "zz")
            assertPrints(map, "{1=x, 2=y, -1=zz}")

            map[1] = "a"
            assertPrints(map, "{1=a, 2=y, -1=zz}")
        }

        @Sample
        fun hashMapFromPairs() {
            val map: HashMap<Int, String> = hashMapOf(1 to "x", 2 to "y", -1 to "zz")
            assertPrints(map, "{-1=zz, 1=x, 2=y}")
        }

        @Sample
        fun linkedMapFromPairs() {
            val map: LinkedHashMap<Int, String> = linkedMapOf(1 to "x", 2 to "y", -1 to "zz")
            assertPrints(map, "{1=x, 2=y, -1=zz}")
        }

        @Sample
        fun emptyReadOnlyMap() {
            val map = emptyMap<String, Int>()
            assertTrue(map.isEmpty())

            val anotherMap = mapOf<String, Int>()
            assertTrue(map == anotherMap, "Empty maps are equal")
        }

        @Sample
        fun emptyMutableMap() {
            val map = mutableMapOf<Int, Any?>()
            assertTrue(map.isEmpty())

            map[1] = "x"
            map[2] = 1.05
            // Now map contains something:
            assertPrints(map, "{1=x, 2=1.05}")
        }

    }


    class Usage {

        @Sample
        fun getOrElse() {
            val map = mutableMapOf<String, Int?>()
            assertPrints(map.getOrElse("x") { 1 }, "1")

            map["x"] = 3
            assertPrints(map.getOrElse("x") { 1 }, "3")

            map["x"] = null
            assertPrints(map.getOrElse("x") { 1 }, "1")
        }

        @Sample
        fun getOrPut() {
            val map = mutableMapOf<String, Int?>()
            assertPrints(map.getOrPut("x") { 2 }, "2")
            assertPrints(map.getOrPut("x") { 3 }, "2") // still

            assertPrints(map.getOrPut("y") { null }, "null")
            assertPrints(map.getOrPut("y") { 42 }, "42") // but!
        }

        @Sample
        fun forOverEntries() {
            val map = mapOf("beverage" to 2.7, "meal" to 12.4, "dessert" to 5.8)

            for ((key, value) in map) {
                println("$key - $value") // prints: beverage - 2.7
                                         // prints: meal - 12.4
                                         // prints: dessert - 5.8
            }

        }
    }

    class Transformations {

        @Sample
        fun mapKeys() {
            val map1 = mapOf("beer" to 2.7, "bisquit" to 5.8)
            val map2 = map1.mapKeys { it.key.length }
            assertPrints(map2, "{4=2.7, 7=5.8}")

            val map3 = map1.mapKeys { it.key.take(1) }
            assertPrints(map3, "{b=5.8}")
        }

        @Sample
        fun mapValues() {
            val map1 = mapOf("beverage" to 2.7, "meal" to 12.4)
            val map2 = map1.mapValues { it.value.toString() + "$" }

            assertPrints(map2, "{beverage=2.7$, meal=12.4$}")
        }

    }

}

