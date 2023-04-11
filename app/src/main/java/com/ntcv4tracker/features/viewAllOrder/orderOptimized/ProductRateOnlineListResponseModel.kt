package com.ntcv4tracker.features.viewAllOrder.orderOptimized

import com.ntcv4tracker.app.domain.ProductOnlineRateTempEntity
import com.ntcv4tracker.base.BaseResponse
import com.ntcv4tracker.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}