package ru.otkrytie.startinvest.data

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import ru.otkrytie.startinvest.data.models.Comment
import ru.otkrytie.startinvest.data.models.Investment
import ru.otkrytie.startinvest.data.models.News
import ru.otkrytie.startinvest.data.models.Subscription

class AppRepository(application: Application) {
    private val appDao = AppDatabase.getDatabase(application).appDao()

    val allComments: LiveData<List<Comment>> = appDao.getAllComments()
    val allInvestments: LiveData<List<Investment>> = appDao.getAllInvestments()
    val allNews: LiveData<List<News>> = appDao.getAllNews()
    val allSubscription: LiveData<List<Subscription>> = appDao.getAllSubscription()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertComments(items: List<Comment>) {
        appDao.insertComments(items)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertInvestments(items: List<Investment>) {
        appDao.insertInvestments(items)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertNews(items: List<News>) {
        appDao.insertNews(items)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertSubscriptions(items: List<Subscription>) {
        appDao.insertSubscriptions(items)
    }
}