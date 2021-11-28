package ru.otkrytie.startinvest.ui.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.otkrytie.startinvest.data.AppRepository
import ru.otkrytie.startinvest.data.models.News
import ru.otkrytie.startinvest.data.models.Subscription


class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppRepository(application)

    val allNewsData: LiveData<List<News>> = repository.allNews

    val allSubsData: LiveData<List<Subscription>> = repository.allSubscription

}
