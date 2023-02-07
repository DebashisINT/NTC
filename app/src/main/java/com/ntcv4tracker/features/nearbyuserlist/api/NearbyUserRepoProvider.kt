package com.ntcv4tracker.features.nearbyuserlist.api



object NearbyUserRepoProvider {
    fun getNearbyUserListRepository(): NearbyUserRepo {
        return NearbyUserRepo(NearbyUserApi.create())
    }
}