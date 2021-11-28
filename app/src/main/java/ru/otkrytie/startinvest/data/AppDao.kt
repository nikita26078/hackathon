package ru.otkrytie.startinvest.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.otkrytie.startinvest.data.models.Comment
import ru.otkrytie.startinvest.data.models.Investment
import ru.otkrytie.startinvest.data.models.News
import ru.otkrytie.startinvest.data.models.Subscription

@Dao
interface AppDao {
    @Query("SELECT * FROM comment")
    fun getAllComments(): LiveData<List<Comment>>

    @Query("SELECT * FROM investment")
    fun getAllInvestments(): LiveData<List<Investment>>

    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT * FROM subscription")
    fun getAllSubscription(): LiveData<List<Subscription>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertComments(items: List<Comment>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertInvestments(items: List<Investment>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(items: List<News>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSubscriptions(items: List<Subscription>)
}
