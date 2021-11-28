package ru.otkrytie.startinvest.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.PagerFragmentBinding

internal class PagerFragment : Fragment() {
    private val pagerViewModel: PagerViewModel by activityViewModels()
    private var _binding: PagerFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = PagerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PagerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.show()
            actionBar.setDisplayHomeAsUpEnabled(false)
            actionBar.setTitle(R.string.app_name)
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(requireActivity(), R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_setting, R.id.navigation_listinvest
            )
        )
        setupActionBarWithNavController(requireActivity() as AppCompatActivity, navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
