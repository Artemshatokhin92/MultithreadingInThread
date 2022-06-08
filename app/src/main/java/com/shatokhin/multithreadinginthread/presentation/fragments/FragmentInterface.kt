package com.shatokhin.multithreadinginthread.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.shatokhin.multithreadinginthread.R
import com.shatokhin.multithreadinginthread.databinding.FragmentInterfaceBinding
import com.shatokhin.multithreadinginthread.presentation.viewmodel.ViewModelMain
import kotlin.random.Random

class FragmentInterface: Fragment(R.layout.fragment_interface) {
    private val viewModelMain: ViewModelMain by activityViewModels()

    private var _binding: FragmentInterfaceBinding? = null
    private val binding get() = _binding!!

    private val periodInSecondForChangingColor = 20

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInterfaceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickListenerButton()

        viewModelMain.timeInSecond.observe(viewLifecycleOwner){ newTimeInSecond ->
            binding.tvTimer.text = newTimeInSecond.toString()
            if (newTimeInSecond > 0 && newTimeInSecond % periodInSecondForChangingColor == 0) binding.root.setBackgroundColor(getRandomColor())
        }
    }

    private fun getRandomColor(): Int {
        return -Random.nextInt(255 * 255 * 255)
    }

    private fun initClickListenerButton() {
        binding.btnStart.setOnClickListener { start() }
        binding.btnPause.setOnClickListener { pause() }
        binding.btnReset.setOnClickListener { reset() }
    }

    private fun start(){
        viewModelMain.start()

        binding.btnStart.isClickable = false
        binding.btnStart.background.setTint(ContextCompat.getColor(requireContext(), R.color.active))
        binding.btnPause.isClickable = true
        binding.btnPause.background.setTint(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun pause(){
        viewModelMain.pause()

        binding.btnPause.isClickable = false
        binding.btnPause.background.setTint(ContextCompat.getColor(requireContext(), R.color.active))
        binding.btnStart.isClickable = true
        binding.btnStart.background.setTint(ContextCompat.getColor(requireContext(), R.color.white))
    }

    private fun reset(){
        viewModelMain.reset()

        binding.btnStart.isClickable = false
        binding.btnStart.background.setTint(ContextCompat.getColor(requireContext(), R.color.active))
        binding.btnPause.isClickable = true
        binding.btnPause.background.setTint(ContextCompat.getColor(requireContext(), R.color.white))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}