package ro.greg.swapestate.data.repository

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
            val userId = usersRef.document().id
            val user = User(
                id = id,
                email = email
            )
            val addition = usersRef.document(userId).set(user).await()
            emit(Success(addition))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

}