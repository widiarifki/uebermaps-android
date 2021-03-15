package id.widiarifki.uebermaps.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.BaseFragmentLoadingDialogBinding

class LoadingDialogFragment : DialogFragment() {

    private var message: String? = null
    private var isCancellable: Boolean? = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = BaseFragmentLoadingDialogBinding.inflate(inflater)
        return binding.also {
            it.loadingMessage = message ?: getString(R.string.msg_loading_global)
        }.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(isCancelable)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    companion object {
        const val PARAM_MESSAGE = "message"
        const val PARAM_IS_CANCELLABLE = "is_cancellable"
        const val TAG = "LoadingDialogFragment"
        fun newInstance(args: Bundle? = null) : LoadingDialogFragment {
            val dialog = LoadingDialogFragment()
            dialog.message = args?.getString(PARAM_MESSAGE)
            dialog.isCancellable = args?.getBoolean(PARAM_IS_CANCELLABLE)
            return dialog
        }
    }
}