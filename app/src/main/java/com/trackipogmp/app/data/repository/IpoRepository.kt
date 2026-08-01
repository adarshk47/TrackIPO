package com.trackipogmp.app.data.repository

import com.trackipogmp.app.data.local.IpoDao
import com.trackipogmp.app.data.model.IpoItem
import com.trackipogmp.app.data.remote.IpoApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IpoRepository @Inject constructor(
    private val apiService: IpoApiService,
    private val ipoDao: IpoDao
) {
    val allIpos: Flow<List<IpoItem>> = ipoDao.getAllIpos()

    suspend fun refreshIpos() {
        try {
            val ipos = apiService.getIpos()
            ipoDao.insertIpos(ipos)
        } catch (e: Exception) {
            // Mock data for initial testing if network fails (e.g. URL not set yet)
            val mockIpos = listOf(
                IpoItem(
                    id = "1", name = "Tech Solutions Ltd", logoUrl = "", category = "Mainboard",
                    status = "Open", offerPriceRange = "₹450 - ₹475", lotSize = 30,
                    latestGmpAmount = 120.0, gmpPercentage = 25.0, expectedListingGain = "30%",
                    openDate = "2024-05-20", closeDate = "2024-05-22", listingDate = "2024-05-27",
                    allotmentDate = "2024-05-23", issueSize = "₹1200 Cr", registrarName = "Link Intime",
                    allotmentLink = "https://linkintime.co.in"
                ),
                IpoItem(
                    id = "2", name = "SME Growth Corp", logoUrl = "", category = "SME",
                    status = "Upcoming", offerPriceRange = "₹80 - ₹85", lotSize = 1600,
                    latestGmpAmount = 15.0, gmpPercentage = 18.0, expectedListingGain = "20%",
                    openDate = "2024-06-01", closeDate = "2024-06-03", listingDate = "2024-06-10",
                    allotmentDate = "2024-06-07", issueSize = "₹50 Cr", registrarName = "Bigshare Services",
                    allotmentLink = "https://bigshareonline.com"
                )
            )
            ipoDao.insertIpos(mockIpos)
        }
    }
}
