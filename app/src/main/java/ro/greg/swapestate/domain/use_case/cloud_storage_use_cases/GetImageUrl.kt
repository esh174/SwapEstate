package ro.greg.swapestate.domain.use_case.cloud_storage_use_cases

import android.net.Uri
import ro.greg.swapestate.domain.repository.CloudStorageRepository

class GetImageUrl (
    private val repository: CloudStorageRepository
) {
    suspend operator fun invoke(fileName: String) =
        repository.cloudStorageGetImageUrl(fileName)
}