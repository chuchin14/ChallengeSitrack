package com.example.challenge.sitrack.feature.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.challenge.sitrack.databinding.BottomSheetBinding
import com.example.challenge.sitrack.feature.main.ui.MainViewModel
import com.example.challenge.sitrack.feature.main.ui.models.MainUiModel
import com.example.challenge.sitrack.feature.main.ui.models.RandomUserUiModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        randomUserObserver()
    }

    private fun randomUserObserver() {
        val randomUserObserver = Observer<MainUiModel> {
            if(it.randomUserUiModel != null) showUserData(it.randomUserUiModel)
        }
        viewModel.mainUiState.observe(this,randomUserObserver)
    }

    private fun showUserData(randomUserUiModel: RandomUserUiModel) {
        binding.textViewTitle.text = randomUserUiModel.fullName
        binding.textViewLocation.text = randomUserUiModel.fullLocation
        binding.textViewGender.text = randomUserUiModel.gender
        binding.textViewEmail.text = randomUserUiModel.email

        Glide.with(requireContext())
            .load(randomUserUiModel.pictureLarge)
            .into(binding.imageViewUser)
    }

}
