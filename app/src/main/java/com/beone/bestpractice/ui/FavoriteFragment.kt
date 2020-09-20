package com.beone.bestpractice.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.beone.bestpractice.R
import com.example.core.domain.model.Country
import com.example.core.ui.CountryMultipleTypeAdapter
import com.example.core.ui.OnClickItemListener
import kotlinx.android.synthetic.main.country_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(), OnClickItemListener {

    companion object;
    private val mViewModel: FavoriteViewModel by viewModel()
    private lateinit var countryMultipleTypeAdapter: CountryMultipleTypeAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        countryMultipleTypeAdapter =  CountryMultipleTypeAdapter("Favorite Country",requireContext(),this)
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            mViewModel.favoriteCountry.observe(viewLifecycleOwner, Observer {
                if (it != null){
                    countryMultipleTypeAdapter.updatedata(it)
                }
            })
            with(recyclerview){
                this?.setHasFixedSize(true)
                this?.adapter = countryMultipleTypeAdapter
            }
        }
    }

    override fun onDestroyView() {
        recyclerview.adapter = null
        super.onDestroyView()
    }
    override fun <T> onClick(data: T) {
        val uri = Uri.parse("coronaapp://detail")
        val i = Intent(requireContext().applicationContext, Class.forName("com.example.favoritefeature.DetailActivity"))
        val datas: Country? =  data as Country?
        i.putExtra("COUNTRYBUNDLE",datas)
        startActivity(i)
    }

}