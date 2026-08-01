package com.trackipogmp.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trackipogmp.app.data.model.IpoItem

@Composable
fun IpoCardItem(ipo: IpoItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ipo.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                StatusBadge(status = ipo.status)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                InfoColumn(label = "Price Range", value = ipo.offerPriceRange)
                Spacer(modifier = Modifier.weight(1f))
                InfoColumn(label = "Lot Size", value = "${ipo.lotSize} Shares")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GmpBadge(gmp = ipo.latestGmpAmount, percentage = ipo.gmpPercentage)
                Text(
                    text = "Expected Gain: ${ipo.expectedListingGain ?: "N/A"}",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val color = when (status.lowercase()) {
        "open" -> Color(0xFF4CAF50)
        "upcoming" -> Color(0xFF2196F3)
        else -> Color.Gray
    }
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = status.uppercase(),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun GmpBadge(gmp: Double?, percentage: Double?) {
    val color = if ((gmp ?: 0.0) >= 0) Color(0xFF4CAF50) else Color.Red
    Column {
        Text(text = "Current GMP", fontSize = 12.sp, color = Color.Gray)
        Text(
            text = "₹${gmp ?: 0.0} (${percentage ?: 0.0}%)",
            color = color,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}

@Composable
fun InfoColumn(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 12.sp, color = Color.Gray)
        Text(text = value, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}
