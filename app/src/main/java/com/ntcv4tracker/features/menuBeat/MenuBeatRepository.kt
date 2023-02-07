package com.ntcv4tracker.features.menuBeat

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.ntcv4tracker.app.FileUtils
import com.ntcv4tracker.app.Pref
import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.addshop.model.*
import com.ntcv4tracker.features.addshop.model.assigntopplist.AddShopUploadImg
import com.ntcv4tracker.features.addshop.model.assigntopplist.AddshopImageMultiReqbody1
import com.ntcv4tracker.features.addshop.presentation.ShopListSubmitResponse
import com.ntcv4tracker.features.addshop.presentation.multiContactRequestData
import com.ntcv4tracker.features.beatCustom.BeatGetStatusModel
import com.ntcv4tracker.features.dashboard.presentation.DashboardActivity
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by saheli on 16-12-2023.
 */
class MenuBeatRepository(val apiService: MenuBeatApi) {

    fun currentTabMenubeat(sessiontoken: String, user_id: String, beat_id: String): Observable<MenuBeatResponse> {
        return apiService.getCurrentTabData(user_id,sessiontoken,beat_id)
    }
    fun hirerchyTabMenubeat(sessiontoken: String, user_id: String): Observable<MenuBeatAreaRouteResponse> {
        return apiService.getHirerchyTabData(user_id,sessiontoken)
    }

}