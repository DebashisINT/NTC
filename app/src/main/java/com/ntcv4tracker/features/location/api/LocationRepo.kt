package com.ntcv4tracker.features.location.api

import com.ntcv4tracker.app.Pref
import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.location.model.AppInfoInputModel
import com.ntcv4tracker.features.location.model.AppInfoResponseModel
import com.ntcv4tracker.features.location.model.GpsNetInputModel
import com.ntcv4tracker.features.location.model.ShopDurationRequest
import com.ntcv4tracker.features.location.shopdurationapi.ShopDurationApi
import io.reactivex.Observable

/**
 * Created by Saikat on 17-Aug-20.
 */
class LocationRepo(val apiService: LocationApi) {
    fun appInfo(appInfo: AppInfoInputModel?): Observable<BaseResponse> {
        return apiService.submitAppInfo(appInfo)
    }

    fun getAppInfo(): Observable<AppInfoResponseModel> {
        return apiService.getAppInfo(Pref.session_token!!, Pref.user_id!!)
    }

    fun gpsNetInfo(appInfo: GpsNetInputModel?): Observable<BaseResponse> {
        return apiService.submitGpsNetInfo(appInfo)
    }
}