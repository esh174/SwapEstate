package ro.greg.swapestate.domain.use_case

import android.net.Uri
import ro.greg.swapestate.domain.repository.AuthRepository
import ro.greg.swapestate.domain.repository.CloudStorageRepository


class AddImageToCloudStorage (
    private val repository: CloudStorageRepository
) {
    suspend operator fun invoke(imageUri: Uri, fileName: String) =
        repository.cloudStorageAddImage(imageUri, fileName)
}