package com.trackerbreeze.features.beatCustom.api



object GetBeatRegProvider {

    fun provideSaveButton(): GetBeatListRegRepository {
        return GetBeatListRegRepository(GetBeatProductListApi.create())
    }


}