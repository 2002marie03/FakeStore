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

        sut=AuthLocalStorage(context)
    }

    @Test
    fun Test_SharedPreference_Initialized(){
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
        val captorVal = ArgumentCaptor.forClass(String::class.java)
        Mockito.verify(sharedPreferences.edit()).putString(captorKey.capture(), captorVal.capture())
        assertEquals("token", captorKey.value)
        //assertEquals(token, captorVal.value)
       // Mockito.verify(editor).apply()
       // assertNotNull(editor)


    }
    @Test
    fun getTokenTest(){
    }
    @Test
    fun user_is_connectedTest(){
    }
    @Test
    fun user_is_not_connectedTest(){
    }

    @After
    fun tearDown() {
    }
}