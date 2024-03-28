package com.baben.apps.appformation3.core.app
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
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

    private lateinit var sut :AuthLocalStorage

    @Mock
    private lateinit var sharedPreferences : SharedPreferences

    @Mock
    private lateinit var editor : Editor

    @Before
    fun setUp() {
        Mockito.`when`(context.getSharedPreferences(any(String::class.java), any(Int::class.java))).thenReturn(sharedPreferences)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)
        Mockito.`when`(editor.putString(anyString(), anyString())).thenReturn(editor)

        sut=AuthLocalStorage(context)
    }

    @Test
    fun Test_SharedPreference_Initialized() {
        val args = ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(context).getSharedPreferences(args.capture(),any(Int::class.java))
        assertEquals(args.value,AuthLocalStorage.Shared_Pref_Label)
        assertNotNull(sharedPreferences)
    }
    @Test
    fun saveTokenTest(){

        val token = "iduiduiful454"
        sut.saveToken(token)

        val captorKey = ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(sharedPreferences.edit()).putString(captorKey.capture(), captorKey.capture())
        Mockito.verify(editor).apply()

        assertEquals("token", captorKey.allValues[0])
        assertEquals(token, captorKey.allValues[1])

       // assertNotNull(editor)


    }
    @Test
    fun getTokenTest(){
        val storedToken = ""
        val captorKey = ArgumentCaptor.forClass(String::class.java)
       // Mockito.verify(sharedPreferences).getString(captorKey.capture(),captorKey.capture())
        Mockito.`when`(sharedPreferences.getString(captorKey.capture(),captorKey.capture())).thenReturn(storedToken)
        val token = sut.getSavedToken()
        assertEquals(storedToken,token)

    }
    @Test
    fun user_is_connectedTest(){
        val token=""
        val captorKey = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(sharedPreferences.getString("token",captorKey.value)).thenReturn(token)
        val logged = sut.isLoggedIn()
        assertNotNull(token)
        assertTrue(logged)

    }
    @Test
    fun user_is_not_connectedTest(){
        val token=null
        val captorKey = ArgumentCaptor.forClass(String::class.java)
        Mockito.`when`(sharedPreferences.getString("token",null)).thenReturn(token)
        val logged = sut.isLoggedIn()
        assertFalse(logged)
    }

    @After
    fun tearDown() {
    }
}