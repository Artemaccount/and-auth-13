package ru.netology.nmedia.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.databinding.SignDialogBinding
import ru.netology.nmedia.viewmodel.SignInViewModel

class SignInFragment : Fragment() {
    private val viewModel: SignInViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SignDialogBinding.inflate(inflater, container, false)

        binding.signInButton.setOnClickListener {
            val login = binding.signEmail.text.toString()
            val password = binding.signPassword.text.toString()
            viewModel.sign(login, password)
            findNavController().navigateUp()
        }

        return binding.root
    }
}