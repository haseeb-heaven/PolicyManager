
package com.policymanager.app.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.policymanager.app.data.model.Policy
import com.policymanager.app.data.repository.PolicyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PolicyViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = PolicyRepository(application)
    
    private val _policies = MutableStateFlow<List<Policy>>(emptyList())
    val policies: StateFlow<List<Policy>> = _policies.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _expandedPolicyIds = MutableStateFlow<Set<String>>(emptySet())
    val expandedPolicyIds: StateFlow<Set<String>> = _expandedPolicyIds.asStateFlow()
    
    init {
        loadPolicies()
    }
    
    private fun loadPolicies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val policiesList = repository.getPolicies()
                _policies.value = policiesList
            } catch (e: Exception) {
                _policies.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun togglePolicyExpansion(policyId: String) {
        val currentExpanded = _expandedPolicyIds.value.toMutableSet()
        if (currentExpanded.contains(policyId)) {
            currentExpanded.remove(policyId)
        } else {
            currentExpanded.add(policyId)
        }
        _expandedPolicyIds.value = currentExpanded
    }
    
    fun isPolicyExpanded(policyId: String): Boolean {
        return _expandedPolicyIds.value.contains(policyId)
    }
}
