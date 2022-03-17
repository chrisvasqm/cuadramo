package com.chrisvasqm.cuadramo.view.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisvasqm.cuadramo.data.CuadreRepository
import com.chrisvasqm.cuadramo.data.model.Cuadre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: CuadreRepository
) : ViewModel() {

    private var _cuadres = MutableLiveData<MutableList<Cuadre>>()
    val cuadres: LiveData<MutableList<Cuadre>>
        get() = _cuadres

    init {
        viewModelScope.launch {
            _cuadres.value = repository.all()
        }
    }

    fun remove(cuadre: Cuadre) {
        viewModelScope.launch {
            repository.remove(cuadre)
            _cuadres.postValue(repository.all())
        }
    }

    fun save(cuadre: Cuadre) {
        viewModelScope.launch {
            repository.add(cuadre)
        }
    }

}