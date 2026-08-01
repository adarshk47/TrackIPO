package com.trackipogmp.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.trackipogmp.app.ui.components.IpoCardItem
import com.trackipogmp.app.ui.viewmodel.IpoListViewModel
import com.trackipogmp.app.ui.viewmodel.IpoUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IpoHomeScreen(viewModel: IpoListViewModel, onIpoClick: (String) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Mainboard", "SME")

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("TrackIPO - GMP Trends") })
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is IpoUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is IpoUiState.Success -> {
                    val filteredIpos = if (selectedTabIndex == 0) {
                        state.ipos.filter { it.category.equals("Mainboard", ignoreCase = true) }
                    } else {
                        state.ipos.filter { it.category.equals("SME", ignoreCase = true) }
                    }

                    if (filteredIpos.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No IPOs found in this category")
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(8.dp)
                        ) {
                            items(filteredIpos) { ipo ->
                                IpoCardItem(ipo = ipo, onClick = { onIpoClick(ipo.id) })
                            }
                        }
                    }
                }
                is IpoUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
