package com.skillbox.mvvn_authorizewithloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.skillbox.mvvn_authorizewithloader.databinding.FragmentMainBinding
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClickMe.setOnClickListener {
            val login = binding.etLogin.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.onClickMeButtonClick(login, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Loading -> {
                            binding.pbProgress.isVisible = true
                            binding.tilLoginLayout.error = null
                            binding.tilPasswordLayout.error = null
                            binding.btnClickMe.isEnabled = false
                        }

                        State.Success -> {
                            binding.pbProgress.isVisible = false
                            binding.tilLoginLayout.error = null
                            binding.tilPasswordLayout.error = null
                            binding.btnClickMe.isEnabled = true
                        }

                        is State.Error -> {
                            binding.pbProgress.isVisible = false
                            binding.tilLoginLayout.error = state.loginError
                            binding.tilPasswordLayout.error = state.passwordError
                            binding.btnClickMe.isEnabled = true
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.error.collect { message ->
                    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}