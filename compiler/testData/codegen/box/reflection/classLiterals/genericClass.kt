// TODO: muted automatically, investigate should it be ran for JS or not
// IGNORE_BACKEND: JS

// WITH_REFLECT

import kotlin.test.assertEquals

class Generic<K, V>

fun box(): String {
    val g = Generic::class
    assertEquals("Generic", g.simpleName)
    return "OK"
}
