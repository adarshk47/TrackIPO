package com.trackipogmp.app.data.model

import kotlinx.serialization.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Serializable
@Entity(tableName = "ipos")
data class IpoItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val logoUrl: String,
    val category: String, // Mainboard / SME
    val status: String, // Upcoming, Open, Closed
    val offerPriceRange: String,
    val lotSize: Int,
    val latestGmpAmount: Double?,
    val gmpPercentage: Double?,
    val expectedListingGain: String?,
    val openDate: String,
    val closeDate: String,
    val listingDate: String,
    val allotmentDate: String,
    val issueSize: String,
    val registrarName: String,
    val allotmentLink: String
)
