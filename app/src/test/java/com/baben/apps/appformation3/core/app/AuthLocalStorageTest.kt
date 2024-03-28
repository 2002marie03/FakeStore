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
import org.mockito.ArgumentMatchers.anyString
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

    @Mock
    private lateinit var editor: SharedPreferences.Editor
    @Before
    fun setUp() {
        Mockito.`when`(context.getSharedPreferences(any(String::class.java), any(Int::class.java)))
            .thenReturn(sharedPreferences)

        Mockito.`when`(sharedPreferences.edit())
            .thenReturn(editor)
//        Mockito.`when`(sharedPreferences.edit().putString(any(String::class.java), any(String::class.java)))
//            .thenReturn(editor)

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
        val key = ArgumentCaptor.forClass(String::class.java)
        val value = ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(sharedPreferences.edit()).putString(key.capture(),value.capture())
//        assertEquals(token,value.value)
//        assertEquals("token",key.value)
        Mockito.verify(editor).apply()
//        assertNotNull(editor)

    }

    @Test
    fun getTokenTest() {
        val expectedToken = "my_saved_token"
        val key = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(sharedPreferences.getString("token", null)).thenReturn(expectedToken)
        val retrievedToken = sut.getSavedToken()
        assertEquals(expectedToken, retrievedToken)
        Mockito.verify(sharedPreferences.edit(), Mockito.times(0)).putString(anyString(), anyString())
    }

    @Test
    fun user_is_connectedTest() {
        val expected =""
        val key = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(sharedPreferences.getString("token",key.value )).thenReturn(expected)
        val isConnected=sut.userisconnected()
        assertTrue(isConnected)
    }

    @Test
    fun user_is_not_connectedTest() {
        val expected =null
        val captor = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(sharedPreferences.getString("token", captor.value)).thenReturn(expected)
        val isConnected=sut.userisconnected()
        assertFalse(isConnected)

    }

}