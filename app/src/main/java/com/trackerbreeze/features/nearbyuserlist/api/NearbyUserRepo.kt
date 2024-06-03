package com.trackerbreeze.features.nearbyuserlist.api

import com.trackerbreeze.app.Pref
import com.trackerbreeze.features.nearbyuserlist.model.NearbyUserResponseModel
import com.trackerbreeze.features.newcollection.model.NewCollectionListResponseModel
import com.trackerbreeze.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}