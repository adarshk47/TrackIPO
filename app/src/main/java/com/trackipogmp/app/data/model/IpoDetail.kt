package com.trackipogmp.app.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IpoDetail(
    val ipoItem: IpoItem,
    val subscriptionStatus: SubscriptionStatus,
    val gmpHistory: List<GmpPoint>
)

@Serializable
data class SubscriptionStatus(
    val qib: Double,
    val nii: Double,
    val rii: Double,
    val overall: Double
)

@Serializable
data class GmpPoint(
    val date: String,
    val amount: Double
)
