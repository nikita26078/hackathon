package ru.otkrytie.startinvest.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.ui.pager.PagerFragment
import ru.otkrytie.startinvest.utils.Constants

class SplashFragment : Fragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            actionBar?.show()
            val sp = PreferenceManager.getDefaultSharedPreferences(context)
            val logged = sp.getBoolean(Constants.PREF_IS_LOGGED, false)
            if (logged) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, PagerFragment.newInstance())
                    .commit()
            } else {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, AuthFragment.newInstance())
                    .commit()
            }
        }, 1000)
    }

}