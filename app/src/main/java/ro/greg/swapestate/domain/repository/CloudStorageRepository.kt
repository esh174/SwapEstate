package ro.greg.swapestate.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import ro.greg.swapestate.domain.model.Response



interface CloudStorageRepository {

    suspend fun cloudStorageAddImage(imageUri : Uri, fileName  : String): Flow<Response<Boolean>>


    suspend fun cloudStorageGetImageUrl(fileName  : String): Flow<Response<String>>



}