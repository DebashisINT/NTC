package com.ntcv4tracker.features.dashboard.presentation.api.dayStartEnd

import com.ntcv4tracker.features.stockCompetetorStock.api.AddCompStockApi
import com.ntcv4tracker.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}