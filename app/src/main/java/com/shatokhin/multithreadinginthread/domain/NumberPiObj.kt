package com.shatokhin.multithreadinginthread.domain

class NumberPiObj(private val changeListener: ChangeListener) {
    private var numberPiString = "3,14"
        private set(value) {
            field = value
            changeListener.changingNumberPi(numberPiString)
        }

    private var threadNumberPi: ThreadNumberPi? = null

    fun start(){
        threadNumberPi?.kill()
        threadNumberPi = ThreadNumberPi()
        threadNumberPi?.start()
    }

    fun pause(){
        threadNumberPi?.kill()
    }

    fun reset(){
        clearNumberPi()
        start()
    }

    private fun clearNumberPi(){
        numberPiString = "3,14"
    }

    inner class ThreadNumberPi: Thread(){
        private var isEnable = true

        fun kill(){
            isEnable = false
        }

        override fun run() {
            super.run()
            while ( isEnable ){
                sleep(500)
                if ( isEnable ) {
                    numberPiString += getNextNumber()
                }
            }
        }
    }

    private fun calculateNumberInPosition(position: Int): Int {
        return (0..9).random()
    }

    fun getNextNumber(): Int {
        return calculateNumberInPosition(numberPiString.length)
    }

    interface ChangeListener{
        fun changingNumberPi(numberPi: String)
    }

}