package com.trackerbreeze.features.newcollectionreport

import com.trackerbreeze.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}