package com.trackerbreeze.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.trackerbreeze.app.FileUtils
import com.trackerbreeze.app.Pref
import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.addshop.model.AddLogReqData
import com.trackerbreeze.features.addshop.model.AddShopRequestData
import com.trackerbreeze.features.addshop.model.AddShopResponse
import com.trackerbreeze.features.addshop.model.LogFileResponse
import com.trackerbreeze.features.addshop.model.UpdateAddrReq
import com.trackerbreeze.features.contacts.CallHisDtls
import com.trackerbreeze.features.contacts.CompanyReqData
import com.trackerbreeze.features.contacts.ContactMasterRes
import com.trackerbreeze.features.contacts.SourceMasterRes
import com.trackerbreeze.features.contacts.StageMasterRes
import com.trackerbreeze.features.contacts.StatusMasterRes
import com.trackerbreeze.features.contacts.TypeMasterRes
import com.trackerbreeze.features.dashboard.presentation.DashboardActivity
import com.trackerbreeze.features.login.model.WhatsappApiData
import com.trackerbreeze.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}