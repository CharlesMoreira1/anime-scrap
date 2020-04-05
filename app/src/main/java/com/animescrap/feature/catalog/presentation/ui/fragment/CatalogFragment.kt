package com.animescrap.feature.catalog.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.animescrap.R
import com.animescrap.core.helper.observeResource
import com.animescrap.core.util.navigateWithAnimations
import com.animescrap.data.model.catalog.domain.CatalogDomain
import com.animescrap.feature.catalog.presentation.ui.adapter.CatalogAdapter
import com.animescrap.feature.catalog.presentation.viewmodel.CatalogViewModel
import kotlinx.android.synthetic.main.fragment_catalog.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private val viewModel by viewModel<CatalogViewModel>()

    private val adapterCatalog: CatalogAdapter by lazy {
        CatalogAdapter {
            navDetailFragment(it)
        }
    }

    private var enableAddListCatalog = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        enableAddListCatalog = true
    }

    override fun onPause() {
        super.onPause()
        enableAddListCatalog = false
    }

    private fun loadData() {
        viewModel.getLiveDataListAnime.observeResource(viewLifecycleOwner,
            onSuccess = {
                populate(it)
                showSuccess()
            },
            onError = {
            },
            onLoading = {
                showLoading()
            })
    }

    private fun initUI() {
        with(recycler_catalog) {
            adapter = adapterCatalog
            val gridLayoutManager = GridLayoutManager(context, 3)
            layoutManager = gridLayoutManager
        }

        setupSearch()
    }

    private fun setupSearch(){
        search_catalog.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapterCatalog.filterList(query!!)
                return true
            }
        })
    }

    private fun navDetailFragment(catalogDomain: CatalogDomain) {
        val navDirections = CatalogFragmentDirections.actionCatalogFragmentToDetailFragment(
            catalogDomain.url, catalogDomain.title)

        findNavController().navigateWithAnimations(navDirections)
    }

    private fun populate(listCatalogDomain: List<CatalogDomain>) {
        if (enableAddListCatalog) {
            adapterCatalog.addList(listCatalogDomain)
        }
    }

    private fun showSuccess(){
        recycler_catalog.visibility = View.VISIBLE
        include_loading_center.visibility = View.GONE
    }

    private fun showLoading(){
        include_loading_center.visibility = View.VISIBLE
        recycler_catalog.visibility = View.GONE
    }
}
