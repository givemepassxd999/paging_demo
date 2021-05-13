package com.example.paging_demo

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class UsersSearchResponse {
    @SerializedName("total_count")
    var totalCount: Int? = null

    @SerializedName("incomplete_results")
    var incompleteResults: Boolean? = null

    @SerializedName("items")
    var items: List<Item>? = null
}

class Item {
    @SerializedName("login")
    var login: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("node_id")
    var nodeId: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("gravatar_id")
    var gravatarId: String? = null
    var url: String? = null

    @SerializedName("html_url")
    var htmlUrl: String? = null

    @SerializedName("followers_url")
    var followersUrl: String? = null

    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null

    @SerializedName("organizations_url")
    var organizationsUrl: String? = null

    @SerializedName("repos_url")
    var reposUrl: String? = null

    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("score")
    var score: Float? = null
}