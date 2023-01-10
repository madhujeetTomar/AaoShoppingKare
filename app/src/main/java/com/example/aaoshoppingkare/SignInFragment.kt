package com.example.aaoshoppingkare

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aaoshoppingkare.databinding.FragmentSignInBinding
import com.example.aaoshoppingkare.model.UserRequest
import com.example.aaoshoppingkare.view.user.LoginActivity
import com.example.aaoshoppingkare.viewmodel.UserViewModel
import com.example.aaoshoppingkare.viewmodel.UserViewModelFactory
import com.example.aaoshoppingkare.viewmodel.ViewModelFactory
import javax.inject.Inject

class SignInFragment : Fragment() {


    lateinit var binding: FragmentSignInBinding
    lateinit var viewModel: UserViewModel
    @Inject
    lateinit var viewModelProvider: UserViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentSignInBinding.inflate(layoutInflater)

        (activity as LoginActivity).loginComponent.inject(this)
viewModel = ViewModelProvider(this, viewModelProvider)[UserViewModel::class.java]
        viewModel.userResponse.observe(viewLifecycleOwner, Observer {
            Log.d("Response", "onCreateView: ${it.token}")
        })
        binding.button.setOnClickListener{
            run {
                val userRequest = UserRequest(
                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString()
                )
                viewModel.signIn(userRequest)
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

}