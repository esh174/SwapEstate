package ro.greg.swapestate.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.greg.swapestate.domain.model.Response

interface AuthRepository {
    fun isUserAuthenticatedInFirebase(): Boolean

    fun getUserUid(): String

    suspend fun firebaseSignInAnonymously(): Flow<Response<Boolean>>

    suspend fun signOut(): Flow<Response<Boolean>>

    suspend fun firebaseCreateUserWithEmailAndPassword(email : String, password  : String): Flow<Response<Boolean>>

    fun getFirebaseAuthState(): Flow<Boolean>
}