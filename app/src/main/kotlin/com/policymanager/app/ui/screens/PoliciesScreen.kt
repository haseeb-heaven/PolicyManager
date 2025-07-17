
package com.policymanager.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.policymanager.app.R
import com.policymanager.app.ui.components.AppHeader
import com.policymanager.app.ui.components.PolicyCard
import com.policymanager.app.ui.viewmodel.PolicyViewModel

@Composable
fun PoliciesScreen(
    modifier: Modifier = Modifier,
    viewModel: PolicyViewModel = viewModel()
) {
    val policies by viewModel.policies.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val expandedPolicyIds by viewModel.expandedPolicyIds.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AppHeader(title = stringResource(R.string.my_policies))
        
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(policies) { policy ->
                    PolicyCard(
                        policy = policy,
                        isExpanded = viewModel.isPolicyExpanded(policy.id),
                        onExpandToggle = { viewModel.togglePolicyExpansion(policy.id) }
                    )
                }
            }
        }
    }
}
