package ro.greg.swapestate.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import ro.greg.swapestate.domain.model.Chat
import ro.greg.swapestate.domain.model.Rental
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.model.User


interface FirestoreRepository {

    suspend fun firestoreAddUser(id: String, email : String): Flow<Response<Void?>>

    suspend fun firestoreAddInfo(id:String, name: String, phone : String, userType: String): Flow<Response<Void?>>

    fun firestoreGetUserInfo(id:String): Flow<Response<User?>>

    suspend fun firestoreGetRentals(): Flow<Response<MutableList<Rental>>>

    suspend fun firestoreAddRental(rental: Rental): Flow<Response<Void?>>

    suspend fun firestoreGetChats(user: User): Flow<Response<List<Chat>>>

    suspend fun firestoreGetChat(chatId: String): Flow<Response<Rental?>>

    suspend fun firestoreGetChatCard(ownerId: String, rentalId: String): Flow<Response<HashMap<String, String>>>


}