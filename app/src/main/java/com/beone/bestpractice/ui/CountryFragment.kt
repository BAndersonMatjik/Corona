package com.beone.bestpractice.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.beone.bestpractice.R
import com.beone.bestpractice.core.data.Resource
import com.beone.bestpractice.core.ui.adapter.CountryMultipleTypeAdapter
import com.beone.bestpractice.core.ui.adapter.OnClickItemListener
import org.koin.android.viewmodel.ext.android.viewModel

class CountryFragment : Fragment(), OnClickItemListener {
    private val mViewModel: CountryViewModel by viewModel()

    private var recyclerView: RecyclerView? = null
    private lateinit var countryMultipleTypeAdapter: CountryMultipleTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.country_fragment, container, false)
        recyclerView = v.findViewById(R.id.recyclerview)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

       countryMultipleTypeAdapter =  CountryMultipleTypeAdapter("Covid 19",requireContext(),this)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            mViewModel.country.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    when (it) {
                        is Resource.Loading -> Log.d(TAG, "onViewCreated: Loading")
                        is Resource.Success -> {
                            Log.d(TAG, "onViewCreated: Success")
                            countryMultipleTypeAdapter.updatedata(it.data)
                        }
                        is Resource.Error -> {
                            Log.d(TAG, "onViewCreated: Error")
                        }
                    }
                }
            })

        with(recyclerView){
            this?.setHasFixedSize(true)
            this?.adapter = countryMultipleTypeAdapter
        }
        }
    }

    override fun <T> onClick(data: T) {
        Log.d(TAG, "onClick: ")
    }

    companion object {
        private val TAG = CountryFragment::class.java.name

    }
}