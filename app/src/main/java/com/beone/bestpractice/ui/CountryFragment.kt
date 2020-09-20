package com.beone.bestpractice.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.beone.bestpractice.R
import com.example.core.data.Resource
import com.example.core.domain.model.Country
import com.example.core.ui.CountryMultipleTypeAdapter
import com.example.core.ui.OnClickItemListener
import kotlinx.android.synthetic.main.country_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class CountryFragment : Fragment(), OnClickItemListener {
    private val mViewModel: CountryViewModel by viewModel()

    private lateinit var countryMultipleTypeAdapter: CountryMultipleTypeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.country_fragment, container, false)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

       countryMultipleTypeAdapter =  CountryMultipleTypeAdapter("Covid 19", requireContext(), this)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_favoriteList.setOnClickListener {
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.addToBackStack("favorite")?.replace(R.id.framelayout, FavoriteFragment(), null)
                    ?.commit()
        }
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(recyclerview){
            this?.setHasFixedSize(true)
            this?.adapter = countryMultipleTypeAdapter
         }
    }

    override fun <T> onClick(data: T) {
        val i = Intent(requireContext().applicationContext, Class.forName("com.example.favoritefeature.DetailActivity"))
        val datas: Country? =  data as Country?
        i.putExtra("COUNTRYBUNDLE",datas)
        startActivity(i)
    }

    override fun onDestroyView() {
        recyclerview.adapter = null
        super.onDestroyView()
    }

    companion object {
        private val TAG = CountryFragment::class.java.name
    }
}