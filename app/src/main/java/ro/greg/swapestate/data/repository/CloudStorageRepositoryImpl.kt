package ro.greg.swapestate.data.repository

import android.net.Uri
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import ro.greg.swapestate.domain.model.Response
import ro.greg.swapestate.domain.repository.CloudStorageRepository
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
@ExperimentalCoroutinesApi
class CloudStorageRepositoryImpl  @Inject constructor(
    private val imagesRef: StorageReference
): CloudStorageRepository {

    override suspend fun cloudStorageAddImage(imageUri: Uri, fileName: String) = flow {
        try {
            emit(Response.Loading)
            imagesRef.child(fileName).putFile(imageUri)
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun cloudStorageGetImageUrl(fileName: String) = flow {
        try {
            emit(Response.Loading)
            val imageUrl = imagesRef.child(fileName).downloadUrl.await().toString()
            emit(Response.Success(imageUrl))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
    override suspend fun cloudStorageGetSeveralImagesUrl(fileNameBase: String, fileCount: Int) = flow {
        try {
            emit(Response.Loading)
            val imagesList = mutableListOf<String>()
            for(i in 0 until fileCount){
                imagesList.add(imagesRef.child(fileNameBase+i.toString()).downloadUrl.await().toString())
            }
            emit(Response.Success(imagesList))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }



}