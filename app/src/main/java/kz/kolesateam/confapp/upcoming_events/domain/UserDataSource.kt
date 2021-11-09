package kz.kolesateam.confapp.upcoming_events.domain

import android.content.SharedPreferences

private const val USER_NAME_KEY = "user_name"
private const val DEFAULT_USER_NAME = ""

class UserDataSource(
    private val sharedPreferences: SharedPreferences
) {
    fun getUserName(): String = sharedPreferences.getString(
        USER_NAME_KEY,
        DEFAULT_USER_NAME
    ) ?: ""

    fun saveUserName(
        userName: String
    ) {
        sharedPreferences.edit().putString(USER_NAME_KEY, userName).apply()
    }
}