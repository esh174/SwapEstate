package ro.greg.swapestate.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.domain.model.Response.*
import ro.greg.swapestate.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class AuthRepositoryImpl  @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository {
    override fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    override fun getUserUid() = auth.currentUser!!.getUid()

    override suspend fun firebaseSignInAnonymously() = flow {
        try {
            emit(Loading)
            auth.signInAnonymously().await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firebaseSignInEmailPassword(email : String, password  : String) = flow {
        try {
            emit(Loading)
            auth.signInWithEmailAndPassword(email, password).await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firebaseCreateUserWithEmailAndPassword(email : String, password  : String) = flow {
        try {
            emit(Loading)
            auth.createUserWithEmailAndPassword(email, password).await()
            emit(Success(true))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }




//Todo Change from deletion to sign out
    override suspend fun signOut() = flow {
        try {
            emit(Loading)
            auth.currentUser?.apply {
                delete().await()
                emit(Success(true))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override fun getFirebaseAuthState() = callbackFlow  {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose {
            auth.removeAuthStateListener(authStateListener)
        }
    }
}