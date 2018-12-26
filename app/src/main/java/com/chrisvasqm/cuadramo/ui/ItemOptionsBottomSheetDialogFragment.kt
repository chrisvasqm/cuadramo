package com.chrisvasqm.cuadramo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrisvasqm.cuadramo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemOptionsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_item_options, container, false)

        // TODO: Add delete button and it's onClick listener.

        return view
    }


}