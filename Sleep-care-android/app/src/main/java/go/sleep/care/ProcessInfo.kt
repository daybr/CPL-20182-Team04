package go.sleep.care

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class ProcessInfo @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0,
        defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    private val progressRing by lazy {
        findViewById<ProcessRing>(R.id.progress_ring)
    }

    private val infoMainTitle by lazy {
        findViewById<TextView>(R.id.info_main1)
    }

    private val infoMainUnit by lazy {
        findViewById<TextView>(R.id.info_main2)
    }

    private val infoSubTitle by lazy {
        findViewById<TextView>(R.id.info_sub)
    }

    init {
        LayoutInflater.from(context)
                .inflate(R.layout.gsc_progress_info, this, true)

        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.ProgressInfo)
            if (a.hasValue(R.styleable.ProgressInfo_gpi_background_image)) {
                var myDrawable = a.getDrawable(R.styleable.ProgressInfo_gpi_background_image)
                var myColor = a.getInt(R.styleable.ProgressInfo_gpi_background_color, Color.BLACK)
                var myUnitTitle = a.getString(R.styleable.ProgressInfo_gpi_unit_title)

                progressRing.setBackgroundImage(myDrawable)
                progressRing.setProgressColor(myColor)
                infoMainUnit.text = myUnitTitle

            }
        }
    }

    fun setProgress(num: Float) {
        progressRing.setProgress(num)
    }

    fun setInfoMainTitle(str: String) {
        infoMainTitle.text = str
    }

    fun setInfoSubTitle(str: String) {
        infoSubTitle.text = str
    }
}