package com.trackerbreeze.features.location.shopRevisitStatus

import com.trackerbreeze.features.location.shopdurationapi.ShopDurationApi
import com.trackerbreeze.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}