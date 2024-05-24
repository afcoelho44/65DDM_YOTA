package udesc.br.yota.Providers

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ExternalMusicProvider {
    private val BASE_URL = "http://localhost:5176/api/"
    private var retrofit: Retrofit? = null

    fun retrofitInitializer() {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create()).build()
    }
    fun getMusic(): MusicProvider {
        return retrofit!!.create(MusicProvider::class.java)
    }
}