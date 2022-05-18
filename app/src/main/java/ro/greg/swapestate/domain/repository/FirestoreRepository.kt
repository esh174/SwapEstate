package ro.greg.swapestate.domain.repository

import kotlinx.coroutines.flow.Flow
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User


interface FirestoreRepository {

    suspend fun firestoreAddUser(id: String, email : String): Flow<Response<Void?>>

    suspend fun firestoreAddInfo(id:String, name: String, phone : String, userType: String): Flow<Response<Void?>>

    fun firestoreGetUserInfo(id:String): Flow<Response<User?>>

}