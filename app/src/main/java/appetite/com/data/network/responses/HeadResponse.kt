package appetite.com.data.network.responses

class HeadResponse(
    val status: String,
    val totalResults: String,
    val articles: List<Articles>
)