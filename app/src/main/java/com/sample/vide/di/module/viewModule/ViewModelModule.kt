package com.sample.vide.di.module.viewModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.vide.di.ViewModelKey
import com.sample.vide.ui.viewmodel.HistoryViewModel


import com.sample.vide.ui.viewmodel.ItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ItemViewModel::class)
    internal abstract fun bindItemViewModel(viewModel: ItemViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}