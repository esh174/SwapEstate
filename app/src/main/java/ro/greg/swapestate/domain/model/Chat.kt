package ro.greg.swapestate.domain.model

data class Chat (
   val id: String = "",
   val userList : List<String>? = null,
   val rentalId: String = "",
   val messageCount: Int? = null
)