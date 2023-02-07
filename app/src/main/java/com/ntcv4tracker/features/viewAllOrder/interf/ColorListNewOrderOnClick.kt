package com.ntcv4tracker.features.viewAllOrder.interf

import com.ntcv4tracker.app.domain.NewOrderColorEntity
import com.ntcv4tracker.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}