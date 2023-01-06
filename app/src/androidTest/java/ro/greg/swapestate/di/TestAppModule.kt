package ro.greg.swapestate.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ro.greg.swapestate.core.Constants.USERS
import ro.greg.swapestate.data.repository.AuthRepositoryImpl
import ro.greg.swapestate.data.repository.CloudStorageRepositoryImpl
import ro.greg.swapestate.data.repository.FirestoreRepositoryImpl
import ro.greg.swapestate.domain.repository.AuthRepository
import ro.greg.swapestate.domain.repository.CloudStorageRepository
import ro.greg.swapestate.domain.repository.FirestoreRepository
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.AddImageToCloudStorage
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.auth_use_cases.*
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.GetImageUrl
import ro.greg.swapestate.domain.use_case.cloud_storage_use_cases.GetSeveralImages
import ro.greg.swapestate.domain.use_case.firestore_use_cases.*

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class TestAppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth
    ): AuthRepository = AuthRepositoryImpl(auth)

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository),
        signInAnonymously = SignInAnonymously(repository),
        createUserWithEmailAndPassword = CreateUserWithEmailAndPassword(repository),
        signOut = SignOut(repository),
        getAuthState = GetAuthState(repository),
        getUserUid = GetUserUid(repository)
    )

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirestoreUsersRef(db: FirebaseFirestore) = db.collection(USERS)

    @Provides
    fun provideFirestoreRepository(
        dbInstance: FirebaseFirestore,
    ): FirestoreRepository = FirestoreRepositoryImpl(dbInstance)


    @Provides
    fun provideFirestoreUseCases(repository: FirestoreRepository) = FirestoreUseCases(
        addUserToFireStore = AddUserToFireStore(repository),
        addUserInfo =  AddUserInfo(repository),
        getUserInfo =  GetUserInfo(repository),
        addRental =  AddRental(repository),
        getRentals = GetRentals(repository),
        getRental = GetRental(repository),
        getChats = GetChats(repository),
        getChat = GetChat(repository),
        getChatCard = GetChatCard(repository),
        getMessages = GetMessages(repository),
        getReviews = GetReviews(repository),
        addMessage = AddMessage(repository),
    )

    @Provides
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()


    @Provides
    fun provideFirebaseStorageImagesRef(storage: FirebaseStorage): StorageReference = storage.getReference("images")

    @Provides
    fun provideFirebaseStorageRepository(
        imagesRef: StorageReference,
    ): CloudStorageRepository = CloudStorageRepositoryImpl(imagesRef)

    @Provides
    fun provideCloudStorageUseCases(repository: CloudStorageRepository) = CloudStorageUseCases(
        addImageToCloudStorage = AddImageToCloudStorage(repository),
        getImageUrl =  GetImageUrl(repository),
        getSeveralImages = GetSeveralImages(repository)
    )

}

