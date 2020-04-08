package com.animescrap.core.constant

const val HOME_URL = "https://www.superanimes.org/ultimos-adicionados?valor=video-lancamento&pagina="

const val CATALOG_URL = "https://www.superanimes.org/busca?parametro=%"

const val FILTER_LAYOUT_EPISODE_DOWNLOAD = "javascript:(function() { " +
        "var element = document.getElementById('topo').style.display='none';" +
        "var element = document.getElementsByClassName('menuRodape')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('conteudoBox')[3].style.display='none'; " +
        "var element = document.getElementsByClassName('conteudoBox')[4].style.display='none'; " +
        "var element = document.getElementsByClassName('conteudoBox')[5].style.display='none'; " +
        "var element = document.getElementsByClassName('conteudoBox')[6].style.display='none'; " +
        "var element = document.getElementsByClassName('boxMenuEps2')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('boxBarraInfo')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('boxBarraInfo')[1].style.display='none'; " +
        "var element = document.getElementsByClassName('box_content')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('btns_sidebar_fixed')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('btns_sidebar_fixedRight')[0].style.display='none'; " +
        "})()"

const val FILTER_LAYOUT_LIST_EPISODE = "javascript:(function() { " +
        "var element = document.getElementById('topo').style.display='none';" +
        "var element = document.getElementsByClassName('box_content')[2].style.display='none'; " +
        "var element = document.getElementsByClassName('menuRodape')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('btns_sidebar_fixed')[0].style.display='none'; " +
        "var element = document.getElementsByClassName('btns_sidebar_fixedRight')[0].style.display='none'; " +
        "var elements = document.getElementsByClassName('boxBarraInfo');" +
        "for (var i = 0; i < elements.length; i ++) {" +
        "    elements[i].style.display = 'none';" +
        "}" +
        "var elements = document.getElementsByClassName('conteudoBox');" +
        "for (var i = 0; i < elements.length; i ++) {" +
        "    elements[i].style.display = 'none';" +
        "}" +
        "})()"