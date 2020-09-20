package com.beone.bestpractice.ui

import com.beone.bestpractice.di.useCaseModule
import com.example.core.domain.usecase.CountryUseCase
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class CountryFragmentTest:KoinTest {
    private val countryUseCase:CountryUseCase by inject()

    @Before
    fun setUp() {
        loadKoinModules(useCaseModule)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun getAllCountry()= runBlockingTest{
        val testData = countryUseCase.getAllCountry()
        val value = testData.toList(mutableListOf())
        assertNotNull(testData)
    }
}