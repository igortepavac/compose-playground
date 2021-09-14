package hr.vub.itepavac.ui.home

sealed class MainViewState {

    object Loading : MainViewState()
    object Ready : MainViewState()
}
