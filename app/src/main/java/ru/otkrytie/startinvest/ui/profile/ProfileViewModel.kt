package ru.otkrytie.startinvest.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.otkrytie.startinvest.data.AppRepository
import ru.otkrytie.startinvest.data.models.News

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppRepository(application)

    val allNewsData: LiveData<List<News>> = repository.allNews
}
