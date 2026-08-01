package com.trackipogmp.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TrackIPO - GMP Trends") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (val state = uiState) {
                is IpoUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is IpoUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(state.ipos) { ipo ->
                            IpoCardItem(ipo = ipo, onClick = { onIpoClick(ipo.id) })
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
