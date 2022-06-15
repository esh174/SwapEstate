package ro.greg.swapestate.domain.model


data class User (
    var id: String? = null,
    var email: String? = null,
    var name : String? = null,
    val surname : String? = null,
    var phone : String? = null,
    var userType : String? = null,
    var rating: Double = 0.0,
    var reviewsNumber: Int = 0,
    var chatsList: List<String>? = null,
)