package com.trackerbreeze.features.viewAllOrder.interf

import com.trackerbreeze.app.domain.NewOrderGenderEntity
import com.trackerbreeze.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}