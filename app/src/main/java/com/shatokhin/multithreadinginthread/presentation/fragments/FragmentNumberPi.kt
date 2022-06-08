package com.shatokhin.multithreadinginthread.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.shatokhin.multithreadinginthread.R
import com.shatokhin.multithreadinginthread.databinding.FragmentInterfaceBinding
import com.shatokhin.multithreadinginthread.databinding.FragmentNumberPiBinding
import com.shatokhin.multithreadinginthread.domain.MyTimer
import com.shatokhin.multithreadinginthread.presentation.viewmodel.ViewModelMain

class FragmentNumberPi: Fragment(R.layout.fragment_number_pi) {
    private val viewModelMain: ViewModelMain by activityViewModels()

    private var _binding: FragmentNumberPiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNumberPiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelMain.numberPi.observe(viewLifecycleOwner){ numberPi ->
            binding.tvNumberPi.text = numberPi
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}