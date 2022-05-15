package ro.greg.swapestate.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ro.greg.swapestate.core.Constants.ID
import ro.greg.swapestate.core.Constants.USERS
import ro.greg.swapestate.data.repository.AuthRepositoryImpl
import ro.greg.swapestate.data.repository.FirestoreRepositoryImpl
import ro.greg.swapestate.domain.repository.AuthRepository
import ro.greg.swapestate.domain.repository.FirestoreRepository
import ro.greg.swapestate.domain.use_case.*
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
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirestoreRef(db: FirebaseFirestore) = db.collection(USERS)

    @Provides
    fun provideFirestoreQuery(usersRef: CollectionReference) = usersRef.orderBy(ID)

    @Provides
    fun provideFirestoreRepository(
        usersRef: CollectionReference,
        usersQuery: Query
    ): FirestoreRepository = FirestoreRepositoryImpl(usersRef)

    @Provides
    fun provideUseCases(repository: AuthRepository) = UseCases(
        isUserAuthenticated = IsUserAuthenticated(repository),
        signInAnonymously = SignInAnonymously(repository),
        createUserWithEmailAndPassword = CreateUserWithEmailAndPassword(repository),
        signOut = SignOut(repository),
        getAuthState = GetAuthState(repository)
    )
    @Provides
    fun provideFirestoreUseCases(repository: FirestoreRepository) = FirestoreUseCases(
        addUserToFireStore = AddUserToFireStore(repository)
    )
}
