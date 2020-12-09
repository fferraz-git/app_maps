package ipvc.estg.app_maps.api

data class LoginOutputPost(
    val success: Boolean,
    val data: Data,
    val msg : String
)

data class Data(
    val id: Int,
    val username: String,
    val password: String
)