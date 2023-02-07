package com.ntcv4tracker.features.location.shopRevisitStatus

import com.ntcv4tracker.features.location.shopdurationapi.ShopDurationApi
import com.ntcv4tracker.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}