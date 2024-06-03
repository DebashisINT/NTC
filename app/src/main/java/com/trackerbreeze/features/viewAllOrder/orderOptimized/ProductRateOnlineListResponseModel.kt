package com.trackerbreeze.features.viewAllOrder.orderOptimized

import com.trackerbreeze.app.domain.ProductOnlineRateTempEntity
import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}