package com.mirego.nwad

import android.app.Application
import com.mirego.trikot.http.HttpConfiguration
import com.mirego.trikot.http.android.ktx.AndroidConnectivityPublisher
import com.mirego.trikot.http.android.ktx.requestFactory.KtorHttpRequestFactory
import com.mirego.trikot.kword.android.AndroidKWord
import com.mirego.trikot.streams.reactive.distinctUntilChanged
import com.mirego.trikot.viewmodels.resources.ImageViewModelResourceManager
import com.mirego.nwad.resource.SampleImageResourceProvider

class TrikotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidKWord.setCurrentLanguageCode("en")
        ImageViewModelResourceManager.provider = SampleImageResourceProvider()
        Environment.flavor = Environment.Flavor.valueOf(BuildConfig.BUILD_TYPE.toUpperCase())

        HttpConfiguration.connectivityPublisher = AndroidConnectivityPublisher(this)
            .distinctUntilChanged()
        HttpConfiguration.httpRequestFactory = KtorHttpRequestFactory()
    }
}
