package ru.otkrytie.startinvest.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import ru.otkrytie.startinvest.MainActivity
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.utils.Constants
import kotlin.system.exitProcess

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val logoutPreference = findPreference<Preference>("pref_logout")
        logoutPreference?.setOnPreferenceClickListener {
            val sp = PreferenceManager.getDefaultSharedPreferences(context)
            sp.edit().putBoolean(Constants.PREF_IS_LOGGED, false).commit()
            startActivity(
                Intent(requireContext(), MainActivity::class.java)
            )
            exitProcess(0)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle(R.string.settings)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            parentFragmentManager.popBackStack()
        }
        return true
    }


}
