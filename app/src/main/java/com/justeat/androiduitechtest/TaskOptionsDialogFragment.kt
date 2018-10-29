package com.justeat.androiduitechtest

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_task_options.view.*

class TaskOptionsDialogFragment : BottomSheetDialogFragment() {

    private var onCompleteListener: () -> Unit = {}
    private var onDeleteListener: () -> Unit = {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_task_options, container, false)
        view.apply {
            task_options_complete.setOnClickListener {
                onCompleteListener()
                dismiss()
            }
            task_options_delete.setOnClickListener {
                onDeleteListener()
                dismiss()
            }
        }

        return view
    }

    fun setListener(onComplete: () -> Unit, onDelete: () -> Unit): TaskOptionsDialogFragment {
        this.onCompleteListener = onComplete
        this.onDeleteListener = onDelete
        return this
    }

}
