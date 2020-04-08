package com.animescrap.core.helper

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.animescrap.R
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog.*

class BottomSheetFragment(private val fm: FragmentManager?): BottomSheetDialogFragment() {

    private var url: String = ""

    private val uriParse: Uri
        get() = Uri.parse(url)

    private lateinit var onItemClickListenerEpisodeDownload: (Uri) -> Unit
    private lateinit var onItemClickListenerOpenEpisode: (Uri) -> Unit

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(context!!, theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDialog()
    }

    private fun setupDialog(){
        text_button_download.setOnClickListener {
            onItemClickListenerEpisodeDownload.invoke(uriParse)
        }

        text_button_video.setOnClickListener {
            onItemClickListenerOpenEpisode.invoke(uriParse)
        }
    }

    fun linkEpisode(url: String): BottomSheetFragment{
        this.url = url
        return this
    }

    fun setClickEpisodeDownload(onItemClickListenerEpisodeDownload: (Uri) -> Unit): BottomSheetFragment{
        this.onItemClickListenerEpisodeDownload = onItemClickListenerEpisodeDownload
        return this
    }

    fun setClickOpenEpisode(onItemClickListenerOpenEpisode: (Uri) -> Unit): BottomSheetFragment{
        this.onItemClickListenerOpenEpisode = onItemClickListenerOpenEpisode
        return this
    }

    fun build(){
        if (fm != null) {
            show(fm, this@BottomSheetFragment.tag)
        }
    }
}