package com.mirego.nwad.android

import android.app.Activity
import android.content.Context
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.unit.dp
import com.mirego.nwad.viewmodels.home.HomeViewModel
import com.mirego.nwad.viewmodels.home.HomeViewModelController
import com.mirego.nwad.viewmodels.home.impl.HomeViewModelImpl
import com.mirego.trikot.streams.cancellable.CancellableManager
import com.mirego.trikot.viewmodels.declarative.compose.extensions.observeAsState
import com.mirego.trikot.viewmodels.declarative.compose.viewmodel.RemoteImage
import com.mirego.trikot.viewmodels.declarative.controller.NavigationDelegate
import com.mirego.trikot.viewmodels.declarative.controller.ViewModelActivity
import com.mirego.trikot.viewmodels.declarative.properties.ImageDescriptor

class MainActivity :
    ViewModelActivity<HomeViewModelController, HomeViewModel, NavigationDelegate>() {
    override val viewModelController: HomeViewModelController by lazy {
        getViewModelController(
            HomeViewModelController::class
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            view(viewModelController.viewModel)
        }
    }

    @Composable
    fun view(homeViewModel: HomeViewModel) {

        val obsText =
            homeViewModel.labelViewModel.observeAsState(property = homeViewModel.labelViewModel::text)
        val moments =
            homeViewModel.moments.observeAsState(property = homeViewModel.moments::elements)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(obsText.value, color = Color.Blue)
            moments.value.forEach { moment ->
                RemoteImage(imageUrl = (moment.image.image as ImageDescriptor.Remote).url)
                Text(moment.title.text, color = Color.Black)
            }
        }
    }
}
