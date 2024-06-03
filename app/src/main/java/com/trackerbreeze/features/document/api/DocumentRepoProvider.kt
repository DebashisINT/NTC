package com.trackerbreeze.features.document.api

import com.trackerbreeze.features.dymanicSection.api.DynamicApi
import com.trackerbreeze.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}