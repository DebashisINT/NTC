package com.ntcv4tracker.features.activities.api

import com.ntcv4tracker.features.member.api.TeamApi
import com.ntcv4tracker.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}