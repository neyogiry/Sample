package com.neyogiry.android.sample.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.io.IOException
import java.security.GeneralSecurityException

/**
 *  Helper class to manage access to {@link SharedPreferences}.
 */
class SharedPreferencesHelper(private val mSharedPreferences: SharedPreferences) {

    /**
     * Saves the given {@link String} that contains the user's api-key to
     * {@link SharedPreferences}.
     *
     * @param key contains data to save to {@link SharedPreferences}.
     * @return {@code true} if writing to {@link SharedPreferences} succeeded. {@code false}
     *         otherwise.
     */
    fun saveKey(key: String): Boolean {
        val editor = mSharedPreferences.edit()
        editor.putString(KEY_TOKEN, "b4352fc298ecbd257fa347c48d4cd70c")

        return editor.commit()
    }

    /**
     * Retrieves the {@link String} containing the user's api-key information from
     * {@link SharedPreferences}.
     *
     * @return the Retrieved {@link String}.
     */
    fun getKey(): String? {
        return mSharedPreferences.getString(KEY_TOKEN, "")
    }

    /**
     * Cleans the {@link User} that contains the user's settings from
     * {@link SharedPreferences}.
     *
     * @return {@code true} if cleaning to {@link SharedPreferences} succeeded. {@code false}
     *         otherwise.
     */
    fun cleanData(): Boolean {
        val editor = mSharedPreferences.edit()
        editor.clear()

        return editor.commit()
    }

    companion object {
        const val PREF_APP = "com.neyogiry.android.sample"
        const val KEY_TOKEN = "api_key"

        fun getDefaultSharedPreferences(context: Context): SharedPreferences? {
            try {
                val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
                return EncryptedSharedPreferences.create(
                    PREF_APP,
                    masterKeyAlias,
                    context,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                )
            } catch (e: GeneralSecurityException) {
                e.printStackTrace();
            } catch (e: IOException) {
                e.printStackTrace();
            }
            return null
        }
    }

}