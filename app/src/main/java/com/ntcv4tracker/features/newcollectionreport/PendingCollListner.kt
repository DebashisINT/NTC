package com.ntcv4tracker.features.newcollectionreport

import com.ntcv4tracker.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}