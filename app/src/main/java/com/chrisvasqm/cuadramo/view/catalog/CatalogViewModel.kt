package com.chrisvasqm.cuadramo.view.catalog

import androidx.lifecycle.*
import com.chrisvasqm.cuadramo.data.CuadreRepository
import com.chrisvasqm.cuadramo.data.model.Cuadre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val repository: CuadreRepository
) : ViewModel() {

    private var _cuadres = MutableLiveData<List<Cuadre>>()
    val cuadres: LiveData<List<Cuadre>>
        get() = _cuadres

    fun fetch() {
        viewModelScope.launch {
            repository.getAll().collect {
                _cuadres.value = it
            }
        }
    }

    fun remove(cuadre: Cuadre) {
        viewModelScope.launch { repository.remove(cuadre) }
    }

    fun save(cuadre: Cuadre) {
        viewModelScope.launch { repository.add(cuadre) }
    }

}