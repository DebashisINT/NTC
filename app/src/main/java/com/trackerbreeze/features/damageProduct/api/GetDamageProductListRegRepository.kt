package com.trackerbreeze.features.damageProduct.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.trackerbreeze.app.FileUtils
import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.NewQuotation.model.*
import com.trackerbreeze.features.addshop.model.AddShopRequestData
import com.trackerbreeze.features.addshop.model.AddShopResponse
import com.trackerbreeze.features.damageProduct.model.DamageProductResponseModel
import com.trackerbreeze.features.damageProduct.model.delBreakageReq
import com.trackerbreeze.features.damageProduct.model.viewAllBreakageReq
import com.trackerbreeze.features.login.model.userconfig.UserConfigResponseModel
import com.trackerbreeze.features.myjobs.model.WIPImageSubmit
import com.trackerbreeze.features.photoReg.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GetDamageProductListRegRepository(val apiService : GetDamageProductListApi) {

    fun viewBreakage(req: viewAllBreakageReq): Observable<DamageProductResponseModel> {
        return apiService.viewBreakage(req)
    }

    fun delBreakage(req: delBreakageReq): Observable<BaseResponse>{
        return apiService.BreakageDel(req.user_id!!,req.breakage_number!!,req.session_token!!)
    }

}