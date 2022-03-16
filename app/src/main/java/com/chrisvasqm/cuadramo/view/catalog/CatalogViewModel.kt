package com.chrisvasqm.cuadramo.view.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisvasqm.cuadramo.data.CuadreRepository
import com.chrisvasqm.cuadramo.data.local.CuadreEntity
import com.chrisvasqm.cuadramo.data.model.Cuadre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: CuadreRepository
) : ViewModel() {

    private var _cuadres = MutableLiveData<List<Cuadre>>()
    val cuadres: LiveData<List<Cuadre>>
        get() = _cuadres

    fun loadData() {
        viewModelScope.launch { _cuadres.postValue(repository.all())  }
    }

}