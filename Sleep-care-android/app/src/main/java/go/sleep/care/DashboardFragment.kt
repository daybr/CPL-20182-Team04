package go.sleep.care

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DashboardFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.gsc_dashboard_fragment, container, false)
        val apneaProgress = view.findViewById<ProcessInfo>(R.id.apnea_progress)
        apneaProgress.setProgress(90f)
        apneaProgress.setInfoMainTitle("Test Me")
        apneaProgress.setInfoSubTitle("Sub Title Test Me")

        return view
    }
}
