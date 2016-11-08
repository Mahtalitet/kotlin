/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package samples.collections

import java.util.*

object Collections {

    object Transformations {
        fun groupBy() {
            val words = listOf("a", "abc", "ab", "def", "abcd")
            val byLength = words.groupBy { it.length }

            println(byLength.keys) // prints: [1, 3, 2, 4]
            println(byLength.values) // prints: [[a], [abc, def], [ab], [abcd]]

            val mutableByLength: MutableMap<Int, MutableList<String>> = words.groupByTo(mutableMapOf()) { it.length }
            // same content as in byLength map, but the map is mutable
            println(mutableByLength == byLength) // prints: true
        }

        fun groupByKeysAndValues() {
            val nameToTeam = listOf("Alice" to "Marketing", "Bob" to "Sales", "Carol" to "Marketing")
            val namesByTeam = nameToTeam.groupBy({ it.second }, { it.first })
            println(namesByTeam) // prints: {Marketing=[Alice, Carol], Sales=[Bob]}

            val mutableNamesByTeam = nameToTeam.groupByTo(HashMap(), { it.second }, { it.first })
            // same content as in namesByTeam map, but the map is mutable
            println(mutableNamesByTeam == namesByTeam) // prints: true
        }
    }

}