
package com.policymanager.app.data.model

data class Policy(
    val id: String,
    val policyName: String,
    val policyNumber: String,
    val status: String,
    val nextPremiumDue: String,
    val startDate: String,
    val maturityDate: String,
    val sumAssured: String,
    val premiumFrequency: String,
    val lastPremiumPaid: String,
    val nextPremiumAmount: String
)

data class PolicyResponse(
    val policies: List<Policy>
)
