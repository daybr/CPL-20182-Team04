package go.sleep.care

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
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
    var myChartView: LineChart? = null;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.gsc_chart_fragment, container, false)
        val myChartView = view.findViewById<LineChart>(R.id.chart)


        myChartView.getDescription().setEnabled(false);
        myChartView.setTouchEnabled(true);
        myChartView.setDrawGridBackground(false)

        val l = myChartView.legend
        l.isEnabled = false

        val xAxis = myChartView.xAxis
        xAxis.position = XAxis.XAxisPosition.TOP_INSIDE
        xAxis.textSize = 10f
        xAxis.textColor = Color.WHITE
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(true)
        xAxis.textColor = Color.rgb(255, 192, 56)
        xAxis.setCenterAxisLabels(true)
        xAxis.granularity = 1f // one hour

        val leftAxis = myChartView.axisLeft
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.textColor = ColorTemplate.getHoloBlue()
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = -250f
        leftAxis.axisMaximum = 4100f
        leftAxis.yOffset = -9f
        leftAxis.textColor = Color.rgb(255, 192, 56)

        val rightAxis = myChartView.axisRight
        rightAxis.isEnabled = false

        var entries = mutableListOf<Entry>()
        entries.add(Entry(6f, 0f))
        entries.add(Entry(7f, 253f))
        entries.add(Entry(8f, 1012f))
        entries.add(Entry(9f, 90f))
        entries.add(Entry(10f, 63f))
        entries.add(Entry(11f, 412f))
        entries.add(Entry(12f, 2f))
        entries.add(Entry(13f, 3653f))
        entries.add(Entry(14f, 1521f))
        entries.add(Entry(15f, 106f))
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
        myChartView.data = lineData
        myChartView.invalidate()

        myChartView?.animateX(1500);


        return view
    }

    override fun onStart() {
        super.onStart()
        Log.w("onResume", "hey")
    }
}
