package com.trackerbreeze.features.stockAddCurrentStock.api

import com.trackerbreeze.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.trackerbreeze.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}