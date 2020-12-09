package ipvc.estg.app_maps.api

data class LoginOutputPost(
    val success: Boolean,
    val data: data,
    val msg : String
)

data class data(
    val ID: Int,
    val name: String,
    val password: String
)