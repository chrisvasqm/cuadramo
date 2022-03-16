package com.chrisvasqm.cuadramo.view.dialogs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisvasqm.cuadramo.data.CuadreRepository
import com.chrisvasqm.cuadramo.data.model.Cuadre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemOptionsViewModel @Inject constructor(
    private val repository: CuadreRepository
) : ViewModel() {

    fun delete(cuadre: Cuadre) {
        viewModelScope.launch { repository.remove(cuadre) }
    }

}