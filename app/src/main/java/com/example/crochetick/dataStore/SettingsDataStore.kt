package com.example.crochetick.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsDataStore(private val context: Context) {
    // Создаем DataStore с именем "settings"
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    // Ключ для хранения состояния switch
    private val switchKey = booleanPreferencesKey("switch_state")

    // Функция для сохранения состояния
    suspend fun saveSwitchState(isChecked: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[switchKey] = isChecked
        }
    }

    // Функция для получения состояния в виде Flow
    fun getSwitchState(): Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[switchKey] ?: false // false - значение по умолчанию
        }
}