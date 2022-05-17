package ro.greg.swapestate.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
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
import ro.greg.swapestate.domain.use_case.AddImageToCloudStorage
import ro.greg.swapestate.domain.use_case.CloudStorageUseCases
import ro.greg.swapestate.domain.use_case.auth_use_cases.*
import ro.greg.swapestate.domain.use_case.firestore_use_cases.AddUserInfo
import ro.greg.swapestate.domain.use_case.firestore_use_cases.AddUserToFireStore
import ro.greg.swapestate.domain.use_case.firestore_use_cases.FirestoreUseCases

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
class AppModule {
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
        usersRef: CollectionReference,
    ): FirestoreRepository = FirestoreRepositoryImpl(usersRef)


    @Provides
    fun provideFirestoreUseCases(repository: FirestoreRepository) = FirestoreUseCases(
        addUserToFireStore = AddUserToFireStore(repository),
        addUserInfo =  AddUserInfo(repository)
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
        addImageToCloudStorage = AddImageToCloudStorage(repository)
    )




}
