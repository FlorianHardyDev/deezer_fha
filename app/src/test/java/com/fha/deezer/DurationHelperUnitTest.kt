package com.fha.deezer

import com.fha.deezer.utils.DurationHelper
import org.junit.Assert.assertEquals
import org.junit.Test

class DurationHelperUnitTest {
    @Test
    fun format_isCorrect() {
        assertEquals(DurationHelper().formatSeconds((60 * 60 * 24).toString()), "24:00:00")
        assertEquals(DurationHelper().formatSeconds("3770"), "01:02:50")
        assertEquals(DurationHelper().formatSeconds("impossible"), "")
    }
}
