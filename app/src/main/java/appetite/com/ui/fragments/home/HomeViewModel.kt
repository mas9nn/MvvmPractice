package appetite.com.ui.fragments.home

import android.app.Activity
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.zxing.integration.android.IntentIntegrator

class HomeViewModel : ViewModel() {


    fun scanNow(view: View){
        val scanner = IntentIntegrator(view.context as Activity?)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setBeepEnabled(true)
        scanner.initiateScan()
    }


}
