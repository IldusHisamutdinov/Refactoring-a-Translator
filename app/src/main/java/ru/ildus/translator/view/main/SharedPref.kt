package ru.ildus.translator.view.main

import android.content.Context
import android.content.SharedPreferences
import ru.ildus.translator.view.main.Constants.Companion.SHARED_PREFS
import ru.ildus.translator.view.main.Constants.Companion.WORD_KEY
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun Context.getSharedPref() = lazy { SharedPref(this) }

class SharedPref(context: Context) {
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
    }
    var searchWord by Preference(prefs, WORD_KEY, "")
}

class Preference(
    private val preferences: SharedPreferences,
    private val name: String,
    private val searchWord: String
) : ReadWriteProperty<Any, String> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.getString(name, searchWord).toString()
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        preferences.edit().putString(name, value).apply()
    }
}


