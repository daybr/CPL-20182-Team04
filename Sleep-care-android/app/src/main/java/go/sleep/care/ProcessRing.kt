package go.sleep.care

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class ProcessRing @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.gsc_progress_ring, this, true)
    }
}