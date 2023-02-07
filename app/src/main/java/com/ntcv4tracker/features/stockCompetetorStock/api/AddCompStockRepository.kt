package com.ntcv4tracker.features.stockCompetetorStock.api

import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.orderList.model.NewOrderListResponseModel
import com.ntcv4tracker.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.ntcv4tracker.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}