package shafirov

import kotlin.math.max

class WordMask() {
    private val map = mutableMapOf<Char, Int>()
    private val matchedPositions = mutableMapOf<Int, Char>()
    private val blockedPositions = mutableMapOf<Int, MutableSet<Char>>()

    private val blocked = mutableSetOf<Char>()

    private fun inc(c: Char, pos: Int) {
        map[c] = get(c) + 1
        blockedAt(pos).add(c)
    }

    private fun blockedAt(idx: Int): MutableSet<Char> {
        return blockedPositions.getOrPut(idx) { hashSetOf() }
    }

    private fun fix(c: Char, pos: Int) {
        inc(c, -1)
        matchedPositions[pos] = c
    }

    private fun block(c: Char) {
        blocked += c
    }

    private fun get(c: Char): Int = map.getOrDefault(c, 0)

    fun learn(word: String, mask: String) {
        updateWith(build(word, mask))
    }

    private fun updateWith(other: WordMask) {
        for ((w, m) in other.map) {
            map.put(w, max(get(w), m))
        }

        blocked.addAll(other.blocked)

        for (e in other.matchedPositions) {
            matchedPositions[e.key] = e.value
        }

        for (e in other.blockedPositions) {
            blockedAt(e.key).addAll(e.value)
        }
    }

    fun matches(w: String): Boolean {
        val counts = mutableMapOf<Char, Int>()

        map.forEach { (c, _) -> counts[c] = 0 }

        w.forEach { c ->
            counts.put(c, counts.getOrDefault(c, 0) + 1)
        }

        return matchedPositions.all { e -> w[e.key] == e.value } &&
                counts.all { e ->
                    if (e.key in blocked) counts[e.key] == get(e.key)
                    else (counts[e.key] ?: 0) >= get(e.key)
                } &&
                (0..4).all { idx -> w[idx] !in blockedAt(idx) }

    }

    companion object {
        fun build(word: String, mask: String): WordMask {
            return WordMask().apply {
                for (i in 0..4) {
                    val letter = word[i]
                    when (mask[i]) {
                        'g' -> fix(letter, i)
                        'y' -> inc(letter, i)
                        'b' -> block(letter)
                    }
                }
            }
        }
    }
}
