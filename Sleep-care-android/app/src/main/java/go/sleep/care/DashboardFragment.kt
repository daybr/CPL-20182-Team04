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
        apneaProgress.setInfoMainTitle("3")
        apneaProgress.setInfoSubTitle("1 TIMES GOAL")

        val exerciseProgress = view.findViewById<ProcessInfo>(R.id.exercise_progress)
        exerciseProgress.setProgress(60f)
        exerciseProgress.setInfoMainTitle("5901")
        exerciseProgress.setInfoSubTitle("10000 STEPS GOAL")

        val sleepProgress = view.findViewById<ProcessInfo>(R.id.sleep_progress)
        sleepProgress.setProgress(85f)
        sleepProgress.setInfoMainTitle("6.5")
        sleepProgress.setInfoSubTitle("8 HOURS GOAL")

        val bpmProgress = view.findViewById<ProcessInfo>(R.id.bpm_progress)
        bpmProgress.setProgress(40f)
        bpmProgress.setInfoMainTitle("100")
        bpmProgress.setInfoSubTitle("200 BPM GOAL")

        return view
    }
}
