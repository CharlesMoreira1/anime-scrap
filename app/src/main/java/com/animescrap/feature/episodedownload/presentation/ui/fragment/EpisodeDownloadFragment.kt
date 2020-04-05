package com.animescrap.feature.episodedownload.presentation.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.animescrap.R
import com.animescrap.core.constant.FILTER_LAYOUT_EPISODE_DOWNLOAD
import com.animescrap.core.helper.BottomSheetFragment
import kotlinx.android.synthetic.main.fragment_episode_download.*
import kotlinx.android.synthetic.main.fragment_episode_download.include_loading_center

class EpisodeDownloadFragment : Fragment(R.layout.fragment_episode_download) {

    private val args: EpisodeDownloadFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadData() {
        webview_episode_download.apply {
            showLoading()

            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    showSuccess()
                    view?.loadUrl(FILTER_LAYOUT_EPISODE_DOWNLOAD)
                }
            }

            setDownloadListener { url, _, _, _, _ ->
                BottomSheetFragment(activity?.supportFragmentManager)
                    .linkEpisode(url)
                    .setClickEpisodeDownload {
                        startActivity(Intent(Intent.ACTION_VIEW, it))
                    }
                    .setClickOpenEpisode{
                        startActivity(Intent(Intent.ACTION_VIEW, it)
                            .setDataAndType(it, "video/mp4"))
                    }
                    .build()
            }

            loadUrl(args.urlEpisode)
        }
    }

    private fun showSuccess(){
        if (webview_episode_download != null || include_loading_center != null) {
            webview_episode_download.visibility = View.VISIBLE
            include_loading_center.visibility = View.GONE
        }
    }

    private fun showLoading(){
        if (webview_episode_download != null || include_loading_center != null) {
            include_loading_center.visibility = View.VISIBLE
            webview_episode_download.visibility = View.GONE
        }
    }
}
