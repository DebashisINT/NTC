package com.trackerbreeze.features.lead.api

import com.trackerbreeze.features.NewQuotation.api.GetQuotListRegRepository
import com.trackerbreeze.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}