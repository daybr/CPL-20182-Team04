package go.sleep.care

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ChartFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.gsc_chart_fragment, container, false)
        val chart = view.findViewById<LineChart>(R.id.chart)

        var entries = mutableListOf<Entry>()
        entries.add(Entry(1f, 10f))
        entries.add(Entry(2f, 30f))
        entries.add(Entry(3f, 10f))
        entries.add(Entry(4f, 20f))

        var dataSet = LineDataSet(entries, "Test")
        dataSet.color = Color.BLUE

        var lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()


        return view
    }
}
