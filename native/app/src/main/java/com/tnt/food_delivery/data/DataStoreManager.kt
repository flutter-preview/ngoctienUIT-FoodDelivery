package com.tnt.food_delivery.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tnt.food_delivery.core.utils.constants.Constants.FOOD_TNT_DATASTORE
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FOOD_TNT_DATASTORE)

class DataStoreManager(val context: Context) {
    suspend fun setString(key: String, value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    fun getString(key: String) = context.dataStore.data.map {
        it[stringPreferencesKey(key)] ?: ""
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }
}