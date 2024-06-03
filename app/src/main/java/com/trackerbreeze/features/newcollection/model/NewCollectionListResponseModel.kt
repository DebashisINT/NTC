package com.trackerbreeze.features.newcollection.model

import com.trackerbreeze.app.domain.CollectionDetailsEntity
import com.trackerbreeze.base.BaseResponse
import com.trackerbreeze.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}