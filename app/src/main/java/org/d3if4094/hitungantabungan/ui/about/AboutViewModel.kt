package org.d3if4094.hitungantabungan.ui.about

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if4094.hitungantabungan.model.TentangAplikasi
import org.d3if4094.hitungantabungan.network.Api
import org.d3if4094.hitungantabungan.network.ApiStatus

class AboutViewModel(private val applicationContext: Application) : ViewModel() {

    private val tentangAplikasi = MutableLiveData<TentangAplikasi>()
    private val status = MutableLiveData<ApiStatus>()

    init{
        retrieveData()
    }

    private fun retrieveData(){
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                tentangAplikasi.postValue(Api.service.getAboutMe())
                status.postValue(ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("AboutViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getImage(imageTarget: ImageView, errorImage: Int){
        status.value = ApiStatus.LOADING
        Glide.with(applicationContext).load(Api.getImageUrl())
            .error(errorImage)
            .listener(object: RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    status.value = ApiStatus.FAILED
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    status.value = ApiStatus.SUCCESS
                    return false
                }
            })
            .into(imageTarget)
    }

    fun getTentangAplikasi(): LiveData<TentangAplikasi> = tentangAplikasi

    fun getStatus(): LiveData<ApiStatus> = status


}