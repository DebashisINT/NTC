package com.ntcv4tracker.features.photoReg.adapter

import com.ntcv4tracker.features.photoReg.model.ProsCustom
import com.ntcv4tracker.features.photoReg.model.UserListResponseModel

interface ProsListSelectionListner {
    fun getInfo(obj: ProsCustom)
}