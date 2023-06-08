object Retrofit  {
    private const val version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$version"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"

    private const val okHttpBomVersion = "4.10.0"
    const val okHttpBom = "com.squareup.okhttp3:okhttp-bom:$okHttpBomVersion"
    const val okHttp = "com.squareup.okhttp3:okhttp"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor"
}

//dependencies {
//    // define a BOM and its version
//    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
//
//    // define any required OkHttp artifacts without version
//    implementation("com.squareup.okhttp3:okhttp")
//    implementation("com.squareup.okhttp3:logging-interceptor")
//}