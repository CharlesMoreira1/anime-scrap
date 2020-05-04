package com.animescrap.feature.episodedownload.presentation.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.animescrap.R
import com.animescrap.core.constant.FILTER_LAYOUT_EPISODE_DOWNLOAD
import com.animescrap.core.helper.BottomSheetFragment
import com.animescrap.data.model.history.entity.HistoryResponse
import com.animescrap.feature.episodedownload.presentation.ui.viewmodel.EpisodeDownloadViewModel
import kotlinx.android.synthetic.main.fragment_episode_download.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.nio.charset.Charset

class EpisodeDownloadFragment : Fragment(R.layout.fragment_episode_download) {

    private val args: EpisodeDownloadFragmentArgs by navArgs()

    private val viewModel by viewModel<EpisodeDownloadViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadData() {
        val titleEncoded = Base64.encodeToString(args.titleAnime.toByteArray(Charset.defaultCharset()), Base64.DEFAULT)

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
                        insertAnimeInHistory(titleEncoded)
                        startActivity(Intent(Intent.ACTION_VIEW, it))
                    }
                    .setClickOpenEpisode{
                        insertAnimeInHistory(titleEncoded)
                        startActivity(Intent(Intent.ACTION_VIEW, it)
                            .setDataAndType(it, "video/mp4"))
                    }
                    .build()
            }

            loadUrl(args.urlEpisode)
        }
    }

    private fun insertAnimeInHistory(titleEncoded: String) {
        viewModel.addAnimeToHistory(HistoryResponse(titleEncoded, 0, ""))
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
