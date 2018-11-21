package go.sleep.care

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
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

    private val progressImage by lazy {
        findViewById<ImageView>(R.id.progress_image)
    }

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.gsc_progress_ring, this, true)
    }

    fun setBackgroundImage(ref: Drawable) {
        progressImage.setImageDrawable(ref)
    }

    fun setProgressColor(color: Int) {
        progressCircular.color = color
    }

    fun setProgress(num: Float) {
        progressCircular.setProgressWithAnimation(num, 3000)
    }
}