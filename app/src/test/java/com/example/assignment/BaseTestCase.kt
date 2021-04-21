package com.example.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
open class BaseTestCase : TestCase() {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var gson: Gson

    @Before
    public override fun setUp() {
        gson = Gson()
    }

    @Test
    open fun testDummyData() {
        assertEquals(4, 2 + 2)
    }


}
