package com.ntcv4tracker.features.stockAddCurrentStock.api

import com.ntcv4tracker.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.ntcv4tracker.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}