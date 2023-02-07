package com.ntcv4tracker.features.photoReg.present

import com.ntcv4tracker.app.domain.ProspectEntity
import com.ntcv4tracker.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}