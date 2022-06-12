package com.pd.mydictionary.utils

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.pd.mydictionary.MESSAGE_EXTRA
import com.pd.mydictionary.TITLE_EXTRA

class AlertDialogFragment : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        var alertDialog = getStubAlertDialog(requireContext())
        val args = arguments
        args?.let {
            val title = it.getString(TITLE_EXTRA)
            val message = it.getString(MESSAGE_EXTRA)
            alertDialog = getAlertDialog(requireContext(), title, message)
        }

        return alertDialog
    }

    companion object {


        fun newInstance(title: String?, message: String?): AlertDialogFragment {
            val dialogFragment = AlertDialogFragment()
            val args = Bundle()
            args.putString(TITLE_EXTRA, title)
            args.putString(MESSAGE_EXTRA, message)
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}