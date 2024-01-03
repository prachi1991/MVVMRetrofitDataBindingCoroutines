package com.applexinfotech.hrmapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    var dismissEvent: MutableLiveData<Boolean> = MutableLiveData()
}