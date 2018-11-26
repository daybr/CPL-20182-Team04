package go.sleep.care

import android.os.Bundle
import android.os.Environment
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.io.File
import java.io.FileOutputStream

class UpdateFragment : Fragment() {
    val data: ArrayList<String> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view =  inflater.inflate(R.layout.gsc_update_fragment, container, false)
        //imageReaderNew(fullpath)

        val button = view.findViewById<AppCompatButton>(R.id.buttonAwesome)
        button.setOnClickListener {
            Log.w("hello", "me")
            data.clear()
            val uploadList = view.findViewById<RecyclerView>(R.id.upload_list)
            uploadList.adapter?.notifyDataSetChanged()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.w("onViewCreated", context!!.toString())

        addData()
        val uploadList = view.findViewById<RecyclerView>(R.id.upload_list)
        uploadList.layoutManager = LinearLayoutManager(activity)
        uploadList.adapter = UpdateAdapter(data)
    }



    private fun imageReaderNew(root: File) {
        val fileList: ArrayList<File> = ArrayList()
        val listAllFiles = root.listFiles()

        var filename = "blesson.txt"
        // create a File object for the parent directory

        val path = Environment.getExternalStorageDirectory()
        val wallpaperDirectory = File(path,"hello")
        // have the object build the directory structure, if needed.
        if (wallpaperDirectory.mkdirs()) {
            Log.w("created mkdirs", "success")
        } else {
            Log.w("created mkdirs", "failed")
        }

        Log.w("test1", wallpaperDirectory.absolutePath)
        // now attach the OutputStream to the file object, instead of a String representation

        val file = File(wallpaperDirectory, "Records.txt")
        if (!file.exists()) {
            file.createNewFile()
        } else {
            FileOutputStream(file).use {
                Log.w("in the location", "me")
                it.write("record goes here".toByteArray())
            }
        }


        if (listAllFiles != null && listAllFiles.isNotEmpty()) {
            for (currentFile in listAllFiles) {
                if (currentFile.name.endsWith(".jpeg")) {
                    // File absolute path
                    Log.e("downloadFilePath", currentFile.absolutePath)
                    // File Name
                    Log.e("downloadFileName", currentFile.name)
                    fileList.add(currentFile.absoluteFile)
                }
            }
            Log.w("fileList", "" + fileList.size)
        }
    }

    private fun addData() {
        data.add("20181122-110000.HKS")
        data.add("20181122-110000.HKD")
        data.add("20181122-120000.HKS")
        data.add("20181122-120000.HKD")
        data.add("20181122-010000.HKS")
        data.add("20181122-010000.HKD")
        data.add("20181122-020000.HKS")
        data.add("20181122-020000.HKD")
        data.add("20181122-030000.HKS")
        data.add("20181122-030000.HKD")
        data.add("20181122-040000.HKS")
        data.add("20181122-040000.HKD")
        data.add("20181122-050000.HKS")
        data.add("20181122-050000.HKD")
    }
}
