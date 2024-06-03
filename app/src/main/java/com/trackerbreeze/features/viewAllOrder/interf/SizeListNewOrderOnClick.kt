package com.trackerbreeze.features.viewAllOrder.interf

import com.trackerbreeze.app.domain.NewOrderProductEntity
import com.trackerbreeze.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}