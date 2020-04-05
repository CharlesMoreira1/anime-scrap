package com.animescrap.feature.home.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.animescrap.R
import com.animescrap.core.helper.addPaginationScroll
import com.animescrap.core.helper.observeResource
import com.animescrap.core.util.navigateWithAnimations
import com.animescrap.data.model.home.domain.NewEpisodeDomain
import com.animescrap.feature.home.presentation.ui.adapter.HomeAdapter
import com.animescrap.feature.home.presentation.ui.adapter.HomeAdapter.Companion.ITEM_BOTTOM
import com.animescrap.feature.home.presentation.ui.adapter.HomeAdapter.Companion.ITEM_LIST
import com.animescrap.feature.home.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by viewModel<HomeViewModel>()

    private val adapterHome: HomeAdapter by lazy {
        HomeAdapter(
            onItemClickListener = {
                navEpisodeDownloadFragment(it)
            },
            onRetryClickListener = {
                errorBottomScroll(false)
                viewModel.backPreviousPage()
            })
    }

    private var enableAddListNewEpisode = true

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        enableAddListNewEpisode = true
    }

    override fun onPause() {
        super.onPause()
        enableAddListNewEpisode = false
    }

    private fun loadData() {
        viewModel.getLiveDataListNewEpisode.observeResource(viewLifecycleOwner,
            onSuccess = {
                populate(it)
                showSuccess()
            },
            onError = {
                showError()
            },
            onLoading = {
                showLoading()
            })
    }

    private fun initUI() {
        with(recycler_home) {
            adapter = adapterHome
            val gridLayoutManager = GridLayoutManager(context, 2)

            gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (adapterHome.getItemViewType(position)) {
                        ITEM_LIST -> 1
                        ITEM_BOTTOM -> gridLayoutManager.spanCount
                        else -> gridLayoutManager.spanCount
                    }
                }
            }

            addPaginationScroll(gridLayoutManager,
                loadMoreItems = {
                    viewModel.nextPage()
                },
                isLoading = {
                    viewModel.releasedLoad
                },
                hideOthersItems = {
                    include_loading_center.visibility = View.GONE
                })

            layoutManager = gridLayoutManager
        }
    }

    private fun populate(listNewEpisodeDomain: List<NewEpisodeDomain>) {
        if (enableAddListNewEpisode) {
            adapterHome.addList(listNewEpisodeDomain)
        }
    }

    private fun navEpisodeDownloadFragment(newEpisodeDomain: NewEpisodeDomain) {
        val navDirections = HomeFragmentDirections.actionHomeFragmentToEpisodeDownloadFragment(
            newEpisodeDomain.url, newEpisodeDomain.title)

        findNavController().navigateWithAnimations(navDirections)
    }

    private fun showSuccess(){
        recycler_home.visibility = View.VISIBLE
        include_loading_center.visibility = View.GONE
    }

    private fun showLoading(){
        if (viewModel.releasedLoad) {
            include_loading_center.visibility = View.VISIBLE
        }
    }

    private fun showError(){
        if (viewModel.currentPage > 1) {
            errorBottomScroll(true)
        }
    }

    private fun errorBottomScroll(showError: Boolean) {
        adapterHome.showErrorRetry(showError)
    }
}
