package com.trackerbreeze.features.dashboard.presentation.api.dayStartEnd

import com.trackerbreeze.features.stockCompetetorStock.api.AddCompStockApi
import com.trackerbreeze.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}