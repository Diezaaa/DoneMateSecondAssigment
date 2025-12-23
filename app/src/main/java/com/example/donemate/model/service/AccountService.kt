package com.example.donemate.model.service

import com.example.donemate.model.User
import com.example.donemate.model.service.impl.AuthResult
import kotlinx.coroutines.flow.Flow
interface AccountService {
    val currentUserId: String
    val hasUser: Flow<Boolean>
    val currentUser: Flow<User?>
    suspend fun authenticate(email: String, password: String): AuthResult
    suspend fun createAnonymousAccount()
    suspend fun createAccount(email: String, password: String): AuthResult

}
