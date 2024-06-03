package com.trackerbreeze.features.orderList.model

import com.trackerbreeze.base.BaseResponse


class ReturnListResponseModel: BaseResponse() {
    var return_list: ArrayList<ReturnDataModel>? = null
}