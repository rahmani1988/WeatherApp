package com.reza.home.ui

import com.reza.core.ui.base.BasePresenter
import com.reza.core.ui.base.BaseView

interface HomeContract {
    interface View : BaseView {
        // TODO functions related to view should placed here
    }

    interface Presenter : BasePresenter<View> {
        // TODO function related to presenter should be place here
    }
}