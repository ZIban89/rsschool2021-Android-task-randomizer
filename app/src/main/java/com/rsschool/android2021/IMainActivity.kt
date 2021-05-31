package com.rsschool.android2021

interface IMainActivity {
    fun onGenerateBtnPressed(min: Int, max: Int)
    fun onBackBtnPressed(previousNumb: Int)
    fun makeToast(message: Int)
}