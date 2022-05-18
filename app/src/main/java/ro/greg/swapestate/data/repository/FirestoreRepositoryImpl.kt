package ro.greg.swapestate.data.repository

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.domain.model.Response.*
import ro.greg.swapestate.domain.model.User
import ro.greg.swapestate.domain.repository.FirestoreRepository
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
@ExperimentalCoroutinesApi
class FirestoreRepositoryImpl @Inject constructor(
    private val usersRef: CollectionReference,
): FirestoreRepository {

    override suspend fun firestoreAddUser(id: String, email: String) = flow {
        try {
            emit(Loading)
            val user = User(
                id = id,
                email = email
            )
            val addition = usersRef.document(id).set(user).await()
            emit(Success(addition))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun firestoreAddInfo(id:String, name: String, phone : String, userType: String) = flow {
        try {
            emit(Loading)
            val detailsAddition = usersRef.document(id).update(mapOf(
                "name" to name,
                "phone" to phone,
                "userType" to userType
            )).await()
            emit(Success(detailsAddition))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }


    override  fun firestoreGetUserInfo(id:String) = callbackFlow {
          val snapshotListener = usersRef.document(id).addSnapshotListener{ snapshot, e ->
                val response = if (snapshot != null) {
                    val user = snapshot.toObject(User::class.java)
                    Success(user)
                } else {
                    Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
          awaitClose {
              snapshotListener.remove()
          }

        }

}

