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
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.utils.ColorTemplate


class ChartFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.gsc_chart_fragment, container, false)
        val chart = view.findViewById<LineChart>(R.id.chart)


        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDrawGridBackground(false)
        chart.animateX(3000);

        val l = chart.legend
        l.isEnabled = false

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
        xAxis.textSize = 10f
        xAxis.textColor = Color.WHITE
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = Color.rgb(255, 192, 56)
        xAxis.setCenterAxisLabels(true)
        xAxis.granularity = 1f // one hour

        val leftAxis = chart.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.textColor = ColorTemplate.getHoloBlue()
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f
        leftAxis.axisMaximum = 5000f
        leftAxis.yOffset = -9f
        leftAxis.textColor = Color.rgb(255, 192, 56)

        val rightAxis = chart.axisRight
        rightAxis.isEnabled = false

        var entries = mutableListOf<Entry>()
        entries.add(Entry(6f, 0f))
        entries.add(Entry(7f, 250f))
        entries.add(Entry(8f, 1000f))
        entries.add(Entry(9f, 50f))
        entries.add(Entry(10f, 30f))
        entries.add(Entry(11f, 400f))
        entries.add(Entry(12f, 2f))
        entries.add(Entry(13f, 3653f))
        entries.add(Entry(14f, 1500f))
        entries.add(Entry(15f, 100f))
        entries.add(Entry(16f, 534f))


        var dataSet = LineDataSet(entries, "Test")
        dataSet.lineWidth = 2.5f
        dataSet.setCircleColor(Color.rgb(144, 235, 254))
        dataSet.circleRadius = 5f
        dataSet.color = Color.rgb(144, 235, 254)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.setDrawValues(true)
        dataSet.valueTextSize = 10f
        dataSet.valueTextColor = Color.rgb(144, 235, 254)

        dataSet.axisDependency = YAxis.AxisDependency.LEFT

        var lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate()


        return view
    }
}
