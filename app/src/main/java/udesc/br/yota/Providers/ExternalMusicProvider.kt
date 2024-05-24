package udesc.br.yota.Providers

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ExternalMusicProvider {
    private val BASE_URL = "https://localhost:7258/api/"
    private var retrofit: Retrofit? = null

    fun RetrofitInitializer() {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create()).build()
    }
    fun getCep(): MusicProvider {
        return retrofit!!.create(MusicProvider::class.java)
    }
}