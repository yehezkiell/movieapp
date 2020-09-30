package tkpd.application.di

import com.tkpd.abstraction.constant.MovieConstant
import com.tkpd.abstraction.di.ApplicationScope
import com.tkpd.abstraction.network.MovieAPI
import com.tkpd.abstraction.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRequestInterceptor() = RequestInterceptor()

    @Provides
    @ApplicationScope
    fun provideLoggingInterceptor() : HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(requestInterceptor: RequestInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        client.addInterceptor(requestInterceptor)
        return client.build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(MovieConstant.MOVIE_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }

}