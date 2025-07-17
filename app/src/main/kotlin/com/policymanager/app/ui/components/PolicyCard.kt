
package com.policymanager.app.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.policymanager.app.R
import com.policymanager.app.data.model.Policy
import com.policymanager.app.ui.theme.ActiveGreen
import com.policymanager.app.ui.theme.DarkBlue
import com.policymanager.app.ui.theme.LapsedRed
import com.policymanager.app.ui.theme.LightRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyCard(
    policy: Policy,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Policy Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = policy.policyName,
                        style = MaterialTheme.typography.titleMedium,
                        color = DarkBlue,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${stringResource(R.string.policy_number)}: ${policy.policyNumber}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
                
                // Status Badge
                StatusBadge(status = policy.status)
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Next Premium Due
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.next_premium_due),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = policy.nextPremiumDue,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = LightRed
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Read More/Less Button
            TextButton(
                onClick = onExpandToggle,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.textButtonColors(
                    contentColor = DarkBlue
                )
            ) {
                Text(
                    text = if (isExpanded) stringResource(R.string.read_less) else stringResource(R.string.read_more),
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            
            // Expanded Content
            if (isExpanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                Spacer(modifier = Modifier.height(16.dp))
                
                ExpandedPolicyDetails(policy = policy)
            }
        }
    }
}

@Composable
private fun StatusBadge(status: String) {
    val backgroundColor = if (status == "Active") ActiveGreen else LapsedRed
    val textColor = Color.White
    
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = status,
            style = MaterialTheme.typography.labelMedium,
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ExpandedPolicyDetails(policy: Policy) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PolicyDetailRow(
            label = stringResource(R.string.start_date),
            value = policy.startDate
        )
        PolicyDetailRow(
            label = stringResource(R.string.maturity_date),
            value = policy.maturityDate
        )
        PolicyDetailRow(
            label = stringResource(R.string.sum_assured),
            value = policy.sumAssured
        )
        PolicyDetailRow(
            label = stringResource(R.string.premium_frequency),
            value = policy.premiumFrequency
        )
        PolicyDetailRow(
            label = stringResource(R.string.last_premium_paid),
            value = policy.lastPremiumPaid
        )
        PolicyDetailRow(
            label = stringResource(R.string.next_premium_amount),
            value = policy.nextPremiumAmount,
            valueColor = LightRed
        )
    }
}

@Composable
private fun PolicyDetailRow(
    label: String,
    value: String,
    valueColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            color = valueColor,
            modifier = Modifier.weight(1f)
        )
    }
}
