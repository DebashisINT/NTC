package com.trackerbreeze.features.viewAllOrder.interf

import com.trackerbreeze.app.domain.NewOrderColorEntity
import com.trackerbreeze.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}