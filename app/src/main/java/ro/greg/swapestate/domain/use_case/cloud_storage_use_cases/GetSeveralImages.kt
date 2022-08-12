package ro.greg.swapestate.domain.use_case.cloud_storage_use_cases

import ro.greg.swapestate.domain.repository.CloudStorageRepository



class GetSeveralImages (
    private val repository: CloudStorageRepository
) {
    suspend operator fun invoke(fileNameBase: String, fileCount: Int) =
        repository.cloudStorageGetSeveralImagesUrl(fileNameBase, fileCount)
}