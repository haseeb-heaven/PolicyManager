
package com.policymanager.app.data.repository

import android.content.Context
import com.google.gson.Gson
import com.policymanager.app.data.model.Policy
import com.policymanager.app.data.model.PolicyResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class PolicyRepository(private val context: Context) {
    
    suspend fun getPolicies(): List<Policy> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.assets.open("policies.json").bufferedReader().use { it.readText() }
            val policyResponse = Gson().fromJson(jsonString, PolicyResponse::class.java)
            policyResponse.policies
        } catch (e: IOException) {
            emptyList()
        }
    }
}
