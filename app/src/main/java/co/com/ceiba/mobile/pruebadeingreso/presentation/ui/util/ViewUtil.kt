package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.data.Constants.FIVE_SEC
import com.google.android.material.snackbar.Snackbar

@SuppressLint("WrongConstant")
fun showSnackbar(view: View, message: String, context: Context, action: () -> Unit) {
    val snackBar = Snackbar.make(
        view, message,
        Snackbar.LENGTH_LONG
    ).setAction(context.getString(R.string.try_again)) { action.invoke() }
    snackBar.setActionTextColor(ContextCompat.getColor(context, R.color.white))
    snackBar.setTextColor(ContextCompat.getColor(context, R.color.white))
    snackBar.duration = FIVE_SEC//LENGTH_INDEFINITE
    val snackBarView = snackBar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
    val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.maxLines = 4
    snackBar.show()
}

@SuppressLint("WrongConstant")
fun showSnackbar(view: View, message: String, context: Context) {
    val snackBar = Snackbar.make(
        view, message,
        Snackbar.LENGTH_LONG
    )
    snackBar.setTextColor(ContextCompat.getColor(context, R.color.white))
    snackBar.duration = FIVE_SEC
    val snackBarView = snackBar.view
    snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_500))
    val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.maxLines = 4
    snackBar.show()
}