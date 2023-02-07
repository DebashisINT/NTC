package com.ntcv4tracker.features.nearbyuserlist.api

import com.ntcv4tracker.app.Pref
import com.ntcv4tracker.features.nearbyuserlist.model.NearbyUserResponseModel
import com.ntcv4tracker.features.newcollection.model.NewCollectionListResponseModel
import com.ntcv4tracker.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}