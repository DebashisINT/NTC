package com.ntcv4tracker.features.stockAddCurrentStock.api

import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.location.model.ShopRevisitStatusRequest
import com.ntcv4tracker.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.ntcv4tracker.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.ntcv4tracker.features.stockAddCurrentStock.model.CurrentStockGetData
import com.ntcv4tracker.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}