package com.ntcv4tracker.features.location.api

import com.ntcv4tracker.features.location.shopdurationapi.ShopDurationApi
import com.ntcv4tracker.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}