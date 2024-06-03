package com.trackerbreeze.features.photoReg.present

import com.trackerbreeze.app.domain.ProspectEntity
import com.trackerbreeze.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}