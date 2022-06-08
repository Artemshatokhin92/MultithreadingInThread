package com.shatokhin.multithreadinginthread.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shatokhin.multithreadinginthread.domain.MyTimer
import com.shatokhin.multithreadinginthread.domain.NumberPiObj

class ViewModelMain: ViewModel() {

    private lateinit var timer: MyTimer
    private lateinit var numberPiObj: NumberPiObj

    var isEnable = false
    private set

    private val handler = Handler(Looper.getMainLooper())

    init {
        initTimer()
        initNumberPiObj()
    }

    private val mTimeInSecond = MutableLiveData<Int>()
    val timeInSecond: LiveData<Int>
        get() = mTimeInSecond

    private val mNumberPi = MutableLiveData<String>()
    val numberPi: LiveData<String>
        get() = mNumberPi



    private fun initNumberPiObj() {
        val listenerChangingNumberPi = object : NumberPiObj.ChangeListener{
            override fun changingNumberPi(numberPi: String) {
                handler.post {
                    mNumberPi.value = numberPi
                }
            }
        }

        numberPiObj = NumberPiObj(listenerChangingNumberPi)
    }

    private fun initTimer() {
        val listenerChangingTime = object : MyTimer.ChangeListener{
            override fun changingTime(newTimeInSecond: Int) {
                handler.post{
                    mTimeInSecond.value = newTimeInSecond
                }
            }

        }

        timer = MyTimer(listenerChangingTime)
    }


    fun start(){
        isEnable = true
        numberPiObj.start()
        timer.start()
    }

    fun pause(){
        isEnable = false
        numberPiObj.pause()
        timer.pause()
    }

    fun reset(){
        isEnable = true
        numberPiObj.reset()
        timer.reset()
    }

    fun pauseForRestartActivity(){
        numberPiObj.pause()
        timer.pause()
    }
}