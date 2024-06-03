package com.trackerbreeze.features.location.shopRevisitStatus

import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.location.model.ShopDurationRequest
import com.trackerbreeze.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}