package com.example.task.di

import com.example.task.data.remote.AuthenticationInterceptor
import com.example.task.data.remote.StoreRemote
import com.example.task.ui.productDetails.repositories.IProductRepository
import com.example.task.ui.productDetails.repositories.ProductRepository
import com.example.task.ui.productDetails.viewmodels.ProductViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://salla.sa/api/v1/"

// network module used for providing dependencies for calling apis, di used by koin
val networkModule = module {
    single { AuthenticationInterceptor() }
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideRemoteStoreService(get()) }

}

// repositories module used for providing dependencies for repositories, di used by koin

@OptIn(KoinApiExtension::class)
val repositoriesModule = module {
    single<IProductRepository> { ProductRepository() }
}

// viewModels module used for providing dependencies for viewModels, di used by koin

@OptIn(KoinApiExtension::class)
val viewModelsModules = module {
    viewModel { ProductViewModel() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(
    authInterceptor: AuthenticationInterceptor,
    httpInterceptor: HttpLoggingInterceptor
): OkHttpClient {

    return OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(httpInterceptor).build()
}

fun provideRemoteStoreService(retrofit: Retrofit): StoreRemote = retrofit.create(StoreRemote::class.java)


