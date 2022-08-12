package ro.greg.swapestate.domain.model

data class Review(
    val id: Int? = null,
    val rentalId: String? = null,
    val rentalName: String? = null,
    val rentalLocation: String? = null,
    val userId: String? = null,
    val userName: String? = null,
    val rating: Int? = null,
    val text: String? = null,
    val date: String? = null,

)
{
}