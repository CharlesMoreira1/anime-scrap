package com.animescrap.feature.detail.presentation.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.animescrap.R
import com.animescrap.core.base.BaseFragment
import com.animescrap.core.constant.FILTER_LAYOUT_LIST_EPISODE
import com.animescrap.core.helper.observeResource
import com.animescrap.core.util.navigateWithAnimations
import com.animescrap.data.model.detail.domain.DetailDomain
import com.animescrap.feature.detail.presentation.viewmodel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.layout_bottom_sheet_episode.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val args: DetailFragmentArgs by navArgs()

    private val viewModel by viewModel<DetailViewModel>()

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<NestedScrollView>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        setupBottomSheet()
    }

    private fun loadData() {
        viewModel.fetchDetail(args.urlAnime)
        viewModel.getLiveDataDetail.observeResource(viewLifecycleOwner,
            onSuccess = {
                populate(it)
            },
            onError = {
            })
    }

    private fun populate(detailDomain: DetailDomain) {
        text_detail_title.text = args.titleAnime
        text_detail_sinopse.text = detailDomain.sinopse
        image_cover.load(detailDomain.imageCover)

        setupWebViewEpisode()
    }

    private fun setupBottomSheet() {
        bottomSheetBehavior = BottomSheetBehavior.from(nested_scroll_view_detail)

        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> showOrHideEffectBottomSheet(false)
                    else -> showOrHideEffectBottomSheet(true)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        }

        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebViewEpisode() {
        webview_detail_episode.apply {
            showLoading()

            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    showSuccess()
                    view?.loadUrl(FILTER_LAYOUT_LIST_EPISODE)
                }

                override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                    return if (url == args.urlAnime) {
                        false
                    } else {
                        navEpisodeDownloadFragment(url.plus("/baixar"))
                        true
                    }
                }
            }

            loadUrl(args.urlAnime)
        }
    }

    private fun navEpisodeDownloadFragment(urlEpisode: String) {
        val navDirections = DetailFragmentDirections.actionDetailFragmentToEpisodeDownloadFragment2(
            urlEpisode, args.titleAnime
        )

        findNavController().navigateWithAnimations(navDirections)
    }

    private fun showSuccess() {
        if (webview_detail_episode != null || include_loading_center != null) {
            coordinator_detail.visibility = View.VISIBLE
            include_loading_center.visibility = View.GONE
        }
    }

    private fun showLoading() {
        if (webview_detail_episode != null || include_loading_center != null) {
            include_loading_center.visibility = View.VISIBLE
            coordinator_detail.visibility = View.GONE
        }
    }

    private fun showOrHideEffectBottomSheet(isVisible: Boolean) {
        layout_effect.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
