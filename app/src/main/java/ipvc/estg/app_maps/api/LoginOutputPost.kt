package ipvc.estg.app_maps.api

data class LoginOutputPost(
    val success: Boolean,
    val name: String,
    val msg : String
)

data class TicketOutputPost(
    val success: Boolean
)