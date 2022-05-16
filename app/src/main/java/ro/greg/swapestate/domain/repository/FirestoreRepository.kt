package ro.greg.swapestate.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.greg.swapestate.domain.model.Response



interface FirestoreRepository {

    suspend fun firestoreAddUser(id: String, email : String): Flow<Response<Void?>>

    suspend fun firestoreAddInfo(id:String, name: String, phone : String, imageUri: String, userType: String): Flow<Response<Void?>>
}