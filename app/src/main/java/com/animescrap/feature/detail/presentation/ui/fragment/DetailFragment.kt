package com.animescrap.feature.detail.presentation.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.animescrap.R
import com.animescrap.core.constant.FILTER_LAYOUT_LIST_EPISODE
import com.animescrap.core.util.navigateWithAnimations
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.include_loading_center

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadData() {
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
            urlEpisode, args.titleAnime)

        findNavController().navigateWithAnimations(navDirections)
    }

    private fun showSuccess(){
        if (webview_detail_episode != null || include_loading_center != null) {
            webview_detail_episode.visibility = View.VISIBLE
            include_loading_center.visibility = View.GONE
        }
    }

    private fun showLoading(){
        if (webview_detail_episode != null || include_loading_center != null) {
            include_loading_center.visibility = View.VISIBLE
            webview_detail_episode.visibility = View.GONE
        }
    }
}
