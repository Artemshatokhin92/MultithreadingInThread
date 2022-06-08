package com.shatokhin.multithreadinginthread.domain

class MyTimer(private val changeListener: ChangeListener) {
    private var timeInSecond = 0
    private set(value) {
        field = value
        changeListener.changingTime(timeInSecond)
    }

    private var threadTimer: ThreadTimer? = null

    fun increment(){
        timeInSecond++
    }

    fun start(){
        threadTimer?.kill()
        threadTimer = ThreadTimer()
        threadTimer?.start()
    }

    fun pause(){
        threadTimer?.kill()
    }

    private fun clearCounter(){
        timeInSecond = 0
    }

    fun reset(){
        clearCounter()
        start()
    }

    interface ChangeListener{
        fun changingTime(newTimeInSecond: Int)
    }

    inner class ThreadTimer: Thread(){
        private var isEnable = true

        fun kill(){
            isEnable = false
        }

        override fun run() {
            super.run()
            while ( isEnable ){
                sleep(1000)
                if ( isEnable ) increment() // если во время sleep не вызвали pause(), то выполняем increment()
            }
        }
    }

}