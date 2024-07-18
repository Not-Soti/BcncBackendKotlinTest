package com.example.bcnctest

import org.mockito.MockitoAnnotations
import kotlin.test.BeforeTest

open class BaseTestClass {

    @BeforeTest
    open fun setUp() {
        MockitoAnnotations.openMocks(this)
    }
}