package com.example.gladkikhvlasovtinkoff.ui.ui.toolbar

interface ToolbarHolder {
    fun setToolbarTitle(title : String)
    fun setToolbarNavigationButtonIcon(resourceId : Int)
    fun hideToolbar()
    fun showToolbar()
}