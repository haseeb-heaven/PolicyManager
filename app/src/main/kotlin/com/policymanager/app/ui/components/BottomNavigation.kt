
package com.policymanager.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.policymanager.app.R
import com.policymanager.app.ui.theme.DarkBlue

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun PolicyManagerBottomNavigation(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem(
            title = stringResource(R.string.home),
            icon = Icons.Default.Home,
            route = "home"
        ),
        BottomNavItem(
            title = stringResource(R.string.policies),
            icon = Icons.Default.Policy,
            route = "policies"
        ),
        BottomNavItem(
            title = stringResource(R.string.claims),
            icon = Icons.Default.Assignment,
            route = "claims"
        ),
        BottomNavItem(
            title = stringResource(R.string.profile),
            icon = Icons.Default.Person,
            route = "profile"
        )
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = DarkBlue
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelMedium
                    )
                },
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = DarkBlue,
                    selectedTextColor = DarkBlue,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    indicatorColor = DarkBlue.copy(alpha = 0.1f)
                )
            )
        }
    }
}
