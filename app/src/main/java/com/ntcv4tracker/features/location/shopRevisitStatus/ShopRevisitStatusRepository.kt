package com.ntcv4tracker.features.location.shopRevisitStatus

import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.location.model.ShopDurationRequest
import com.ntcv4tracker.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}