package com.ntcv4tracker.features.viewAllOrder.interf

import com.ntcv4tracker.app.domain.NewOrderProductEntity
import com.ntcv4tracker.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}