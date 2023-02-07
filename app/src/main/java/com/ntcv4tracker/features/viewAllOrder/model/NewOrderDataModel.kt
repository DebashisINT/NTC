package com.ntcv4tracker.features.viewAllOrder.model

import com.ntcv4tracker.app.domain.NewOrderColorEntity
import com.ntcv4tracker.app.domain.NewOrderGenderEntity
import com.ntcv4tracker.app.domain.NewOrderProductEntity
import com.ntcv4tracker.app.domain.NewOrderSizeEntity
import com.ntcv4tracker.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

