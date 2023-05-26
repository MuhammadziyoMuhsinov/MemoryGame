package com.froxelx8.memorygame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.froxelx8.memorygame.databinding.FragmentHomeBinding


class FragmentHome : Fragment() {

private lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
       binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnStart.setOnClickListener { findNavController().navigate(R.id.fragmentGame) }
        return binding.root
    }


}