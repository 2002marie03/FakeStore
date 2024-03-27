package com.baben.apps.appformation3.core.app

import android.content.Context
import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.After

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthLocalStorageTest {

    @Mock
    private lateinit var context: Context


    private lateinit var sut: AuthLocalStorage

    @Mock
    private lateinit var sharedPreferences: SharedPreferences
    @Before
    fun setUp() {

        Mockito.`when`(context.getSharedPreferences(any(String::class.java), any(Int::class.java)))
            .thenReturn(sharedPreferences)
        sut = AuthLocalStorage(context)

    }

    @After
    fun tearDown() {
    }


    @Test
    fun sharedPreference_initialised() {
        val argument = ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(context).getSharedPreferences(argument.capture(), any(Int::class.java))
        assertEquals(argument.value, AuthLocalStorage.PREF)
        assertNotNull(sharedPreferences)
    }

    @Test
    fun saveTokenTest() {
        var token: String = "iduiduiful454"
        sut.saveToken(token)
        assertNotNull(sharedPreferences)
    }

    @Test
    fun getTokenTest() {

        assertEquals("hello", sut.getSavedToken())
    }

    @Test
    fun user_is_connectedTest() {

    }

    @Test
    fun user_is_not_connectedTest() {

    }


}