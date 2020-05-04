package com.animescrap.feature.history.presentation.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.animescrap.R
import com.animescrap.core.base.BaseFragment
import com.animescrap.core.helper.observeResource
import com.animescrap.core.util.navigateWithAnimations
import com.animescrap.data.model.history.domain.HistoryDomain
import com.animescrap.feature.history.presentation.ui.adapter.HistoryAdapter
import com.animescrap.feature.history.presentation.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private val viewModel by viewModel<HistoryViewModel>()

    private val adapterHistory: HistoryAdapter by lazy {
        HistoryAdapter {
            navDetailFragment(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        swipeRefresh()
    }

    private fun loadData() {
        viewModel.getLiveDataHistory.observeResource(viewLifecycleOwner,
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
        with(recycler_history) {
            adapter = adapterHistory
            val gridLayoutManager = GridLayoutManager(context, 3)
            layoutManager = gridLayoutManager
        }

        setupSearch()
    }

    private fun setupSearch(){
        search_history.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapterHistory.filterList(query!!)
                return true
            }
        })

        search_history.setOnClickListener { search_history.isIconified = false }
    }

    private fun populate(listHistoryDomain: List<HistoryDomain>) {
        ignoreReplaceFragment { adapterHistory.addList(listHistoryDomain) }
        adapterHistory.listItemIsEmpty { viewModel.refreshViewModel() }
    }

    private fun navDetailFragment(historyDomain: HistoryDomain) {
        val navDirections = HistoryFragmentDirections.actionHistoryFragmentToDetailFragment(
            historyDomain.url, historyDomain.title)

        findNavController().navigateWithAnimations(navDirections)
    }

    private fun swipeRefresh() {
        swipe_refresh_history.setOnRefreshListener {
            Handler().postDelayed({
                viewModel.refreshViewModel()
                adapterHistory.clearList()

                swipe_refresh_history?.let { it.isRefreshing = false }
            }, 1000)
        }
    }

    private fun showSuccess(){
        recycler_history.visibility = View.VISIBLE
        include_loading_center.visibility = View.GONE
    }

    private fun showLoading(){
        include_loading_center.visibility = View.VISIBLE
        recycler_history.visibility = View.GONE
    }
}
