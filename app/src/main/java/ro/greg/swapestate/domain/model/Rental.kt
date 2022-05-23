package ro.greg.swapestate.domain.model


data class Rental (
    var id: String? = null,
    var userId: String? = null,
    var city: String? = null,
    var location: String? = null,
    var rentalState : String? = null,
    var rentalType : String? = null,
    var roomNumber : Int? = null,
    var floorsNumber : Int? = null,
    var buildYear: Int? = 0,
    var isAnimalsAllowed: Boolean? = false,
    var isKidsAllowed: Boolean? = false,
    var rentPrice: Int? = 0,
    var summerServicePrice: Int? = 0,
    var winterServicePrice: Int? = 0,
    var rentPeriod: Int? = 0,
    var deposit: Int? = 0,
    var comment: String? = null,

)