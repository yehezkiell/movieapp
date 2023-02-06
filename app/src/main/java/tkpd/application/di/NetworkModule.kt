package tkpd.application.di

import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.network.MovieAPI
import com.tkpd.abstraction.network.RequestInterceptor
import com.tkpd.abstraction.network.ResponseInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideResponseInterceptor() = ResponseInterceptor()

    @Provides
    @Singleton
    fun provideRequestInterceptor() = RequestInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        requestInterceptor: RequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        client.addInterceptor(requestInterceptor)
        client.addInterceptor(responseInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(MovieConstant.MOVIE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }
}