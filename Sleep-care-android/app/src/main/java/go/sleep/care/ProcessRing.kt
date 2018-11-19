package go.sleep.care

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class ProcessRing @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val progressCircular by lazy {
        findViewById<CircularProgressBar>(R.id.progress_circular)
    }

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.gsc_progress_ring, this, true)

        progressCircular.setProgressWithAnimation(65f, 3000)
    }
}