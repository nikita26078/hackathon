package ru.otkrytie.startinvest.ui.investments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ru.otkrytie.startinvest.data.AppRepository
import ru.otkrytie.startinvest.data.models.Comment

class InvestmentCommentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppRepository(application)

    val allData: LiveData<List<Comment>> = repository.allComments

}
