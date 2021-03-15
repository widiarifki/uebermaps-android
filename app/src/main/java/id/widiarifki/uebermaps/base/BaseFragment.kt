package id.widiarifki.uebermaps.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import id.widiarifki.uebermaps.R

abstract class BaseFragment<Binding: ViewDataBinding> : Fragment() {

    protected abstract val resourceLayout: Int
    protected lateinit var binding: Binding

    private var loadingDialog: LoadingDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, resourceLayout, container, false)
        return binding.root
    }

    fun showToast(message: String, length: Int? = null) {
        Toast.makeText(context, message, length ?: Toast.LENGTH_SHORT).show()
    }

    fun showLoadingDialog() {
        loadingDialog?.dismiss()

        loadingDialog = LoadingDialogFragment.newInstance(Bundle().apply {
            putBoolean(LoadingDialogFragment.PARAM_IS_CANCELLABLE, false)
            putString(LoadingDialogFragment.PARAM_MESSAGE, getString(R.string.msg_loading_logout))
        })
        loadingDialog?.show(childFragmentManager, LoadingDialogFragment.TAG)
    }

    fun hideLoadingDialog() {
        loadingDialog?.dismiss()
    }

    fun showConfirmDialog(message: String?, confirmCallback: () -> Unit) {
        context?.let {
            val confirmDialog = AlertDialog.Builder(it)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_action_yes) { _, _ -> confirmCallback() }
                .setNegativeButton(R.string.dialog_action_no) { _, _ -> }
                .create()

            confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            confirmDialog.show()
        } ?: run {
            confirmCallback()
        }
    }

}