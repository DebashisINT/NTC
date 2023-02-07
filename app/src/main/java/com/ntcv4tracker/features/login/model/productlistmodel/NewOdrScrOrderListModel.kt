package com.ntcv4tracker.features.login.model.productlistmodel

import com.ntcv4tracker.app.domain.NewOrderScrOrderEntity
import com.ntcv4tracker.app.domain.ProductListEntity

class NewOdrScrOrderListModel {
    var status:String? = null
    var message:String? = null
    var user_id:String? = null
    var order_list: ArrayList<NewOrderScrOrderEntity>? = null
}