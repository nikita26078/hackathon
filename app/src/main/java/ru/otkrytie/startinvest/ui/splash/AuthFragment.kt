package ru.otkrytie.startinvest.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.otkrytie.startinvest.R
import ru.otkrytie.startinvest.databinding.AuthFragmentBinding
import ru.otkrytie.startinvest.ui.login.LoginFragment
import ru.otkrytie.startinvest.ui.login.RegisterFragment

class AuthFragment : Fragment() {
    private var _binding: AuthFragmentBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance() = AuthFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AuthFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.let {
            actionBar.setDisplayHomeAsUpEnabled(false)
        }

        binding.btLogin.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
        binding.btRegister.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, RegisterFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }
    }
}