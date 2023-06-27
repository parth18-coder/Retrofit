package com.example.retrofit.data.remote

import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

data class ListedDataClass(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("support_whatsapp_number")
    val supportWhatsappNumber: String,
    @SerializedName("extra_income")
    val extraIncome: Double,
    @SerializedName("total_links")
    val totalLinks: Int,
    @SerializedName("total_clicks")
    val totalClicks: Int,
    @SerializedName("today_clicks")
    val todayClicks: Int,
    @SerializedName("top_source")
    val topSource: String,
    @SerializedName("top_location")
    val topLocation: String,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("links_created_today")
    val linksCreatedToday: Int,
    @SerializedName("applied_campaign")
    val appliedCampaign: Int,
    @SerializedName("data")
    val data: Data
)

data class Data(
    @SerializedName("recent_links")
    val recentLinks: List<RecentLink>,
    @SerializedName("top_links")
    val topLinks:List<TopLinks>,
    @SerializedName("overall_url_chart")
    val overallUrlChart:OverallUrlChart
)

data class TopLinks(
    @SerializedName("url_id")
    val urlId:Int,
    @SerializedName("web_link")
    val webLink:String,
    @SerializedName("smart_link")
    val smartLink:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("total_clicks")
    val totalClick :Int,
    @SerializedName("original_image")
    val originalImage: String,
    @SerializedName("thumbnail")
    val thumbnail:Nullable,
    @SerializedName("times_ago")
    val timesAgo: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("url_prefix")
    val urlPrefix: String,
    @SerializedName("url_suffix")
    val urlSuffix: String,
    @SerializedName("app")
    val app: String,




)

data class RecentLink(
    @SerializedName("url_id")
    val urlId: Int,
    @SerializedName("web_link")
    val webLink: String,
    @SerializedName("smart_link")
    val smartLink: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_clicks")
    val totalClicks: Int,
    @SerializedName("original_image")
    val originalImage: String,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("times_ago")
    val timesAgo: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("url_prefix")
    val urlPrefix: String?,
    @SerializedName("url_suffix")
    val urlSuffix: String,
    @SerializedName("app")
    val app: String
)

data class OverallUrlChart(
    val `2023-05-13`: Int,
    val `2023-05-14`: Int,
    val `2023-05-15`: Int,
    val `2023-05-16`: Int,
    val `2023-05-17`: Int,
    val `2023-05-18`: Int,
    val `2023-05-19`: Int,
    val `2023-05-20`: Int,
    val `2023-05-21`: Int,
    val `2023-05-22`: Int,
    val `2023-05-23`: Int,
    val `2023-05-24`: Int,
    val `2023-05-25`: Int,
    val `2023-05-26`: Int,
    val `2023-05-27`: Int,
    val `2023-05-28`: Int,
    val `2023-05-29`: Int,
    val `2023-05-30`: Int,
    val `2023-05-31`: Int,
    val `2023-06-01`: Int,
    val `2023-06-02`: Int,
    val `2023-06-03`: Int,
    val `2023-06-04`: Int,
    val `2023-06-05`: Int,
    val `2023-06-06`: Int,
    val `2023-06-07`: Int,
    val `2023-06-08`: Int,
    val `2023-06-09`: Int,
    val `2023-06-10`: Int,
    val `2023-06-11`: Int,
    val `2023-06-12`: Int
)




