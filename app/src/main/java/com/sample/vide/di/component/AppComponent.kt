package com.sample.vide.di.component

import com.sample.vide.data.repoistory.HistoryRepository
import com.sample.vide.data.repoistory.ItemRepository
import com.sample.vide.di.module.NetworkModule
import com.sample.vide.di.module.RoomModule
import com.sample.vide.di.module.viewModule.ViewModelModule
import com.sample.vide.ui.fragment.HistoryFragment
import com.sample.vide.ui.fragment.VideoListFragment
import com.sample.vide.ui.fragment.ViewPagerContainerFragment


import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class, RoomModule::class])
interface AppComponent {
    fun inject(fragment: VideoListFragment)
    fun inject(fragment: ViewPagerContainerFragment)
    fun inject(fragment: HistoryFragment)

    fun inject(repository: HistoryRepository)
    fun inject(repository: ItemRepository)

}