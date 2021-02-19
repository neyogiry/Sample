package com.neyogiry.android.sample

import android.content.SharedPreferences
import com.neyogiry.android.sample.util.SharedPreferencesHelper
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.ArgumentMatchers.eq
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SharedPreferencesHelperTest {

    private val TEST_KEY = "api-key-test"

    private lateinit var apiKey: String
    private lateinit var mockSharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var mockBrokenSharedPreferencesHelper: SharedPreferencesHelper

    @Mock private lateinit var mockSharedPreferences: SharedPreferences
    @Mock private lateinit var mockBrokenSharedPreferences: SharedPreferences
    @Mock private lateinit var mockEditor: SharedPreferences.Editor
    @Mock private lateinit var mockBrokenEditor: SharedPreferences.Editor

    @Before
    fun initMocks() {
        // Create data to persist.
        apiKey = TEST_KEY

        // Create a mocked SharedPreferences.
        mockSharedPreferencesHelper = createMockSharedPreference()

        // Create a mocked SharedPreferences that fails at saving data.
        mockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference()
    }

    @Test
    fun sharedPreferencesHelper_SaveAndReadPersonalInformation() {
        // Save the personal information to SharedPreferences
        assertTrue(mockSharedPreferencesHelper.saveKey(apiKey))

        // Read personal information from SharedPreferences
        val savedEntry = mockSharedPreferencesHelper.getKey()

        // Make sure both written and retrieved personal information are equal.w
        assertEquals(apiKey, savedEntry)
    }

    @Test
    fun sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        // Read personal information from a broken SharedPreferencesHelper
        assertFalse(mockBrokenSharedPreferencesHelper.saveKey(apiKey))
    }

    /**
     * Creates a mocked SharedPreferences.
     */
    private fun createMockSharedPreference(): SharedPreferencesHelper {
        // Mocking reading the SharedPreferences as if mockSharedPreferences was previously written correctly
        given(mockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_TOKEN), anyString()))
                .willReturn(apiKey)

        // Mocking a successful commit.
        given(mockEditor.commit()).willReturn(true)

        // Return the MockEditor when requesting it.
        given(mockSharedPreferences.edit()).willReturn(mockEditor)
        return SharedPreferencesHelper(mockSharedPreferences)
    }

    /**
     * Creates a mocked SharedPreferences that fails when writing.
     */
    private fun createBrokenMockSharedPreference(): SharedPreferencesHelper {
        // Mocking a commit that fails.
        given(mockBrokenEditor.commit()).willReturn(false)

        // Return the broken MockEditor when requesting it.
        given(mockBrokenSharedPreferences.edit()).willReturn(mockBrokenEditor)
        return SharedPreferencesHelper(mockBrokenSharedPreferences)
    }

}