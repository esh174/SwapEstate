package ro.greg.swapestate.domain.model

data class Message(
    var id: Int? = null,
    val sentBy: String? = null,
    val sentOn: String? = null,
    val text: String? = "",
){
    fun Message() {}
}
