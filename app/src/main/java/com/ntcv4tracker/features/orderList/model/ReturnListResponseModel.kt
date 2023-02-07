package com.ntcv4tracker.features.orderList.model

import com.ntcv4tracker.base.BaseResponse


class ReturnListResponseModel: BaseResponse() {
    var return_list: ArrayList<ReturnDataModel>? = null
}