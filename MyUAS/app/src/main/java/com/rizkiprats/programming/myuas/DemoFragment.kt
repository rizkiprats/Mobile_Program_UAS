package com.rizkiprats.programming.myuas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rizkiprats.programming.myuas.adapter.DataAdapter
import com.rizkiprats.programming.myuas.adapter.TampilAdapter
import com.rizkiprats.programming.myuas.model.DataModel
import com.rizkiprats.programming.myuas.model.RekapModel
import kotlinx.android.synthetic.main.fragment_demo.*


@Suppress("DEPRECATION")
class DemoFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_demo, container, false)

        val bundle = arguments

        val List = arguments!!.getSerializable("list")

        //recyclerview.adapter = TampilAdapter(List)

        //val fragmentName = arguments?.getString("fragmentName")
        //rootView.fragment_name.text = fragmentName
        print(List)

        return rootView
    }
}
