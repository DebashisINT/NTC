package com.trackerbreeze.features.location.api

import com.trackerbreeze.features.location.shopdurationapi.ShopDurationApi
import com.trackerbreeze.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}