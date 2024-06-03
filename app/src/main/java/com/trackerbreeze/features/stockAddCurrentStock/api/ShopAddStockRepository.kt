package com.trackerbreeze.features.stockAddCurrentStock.api

import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.location.model.ShopRevisitStatusRequest
import com.trackerbreeze.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.trackerbreeze.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.trackerbreeze.features.stockAddCurrentStock.model.CurrentStockGetData
import com.trackerbreeze.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}