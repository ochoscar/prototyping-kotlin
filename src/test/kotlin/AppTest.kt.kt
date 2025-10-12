package com.ochoscar

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AppTest {

    @Test
    fun testGreetings() {
        val result = greetings("Oscar")
        assertEquals("Hi, Oscar!", result)
    }
}