package com.sample.vide.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.vide.R
import com.sample.vide.data.model.VideoDto
import com.sample.vide.databinding.FragmentHistoryBinding
import com.sample.vide.di.MyApp
import com.sample.vide.ui.adapter.MyItemRecyclerViewAdapter
import com.sample.vide.ui.viewmodel.HistoryViewModel
import javax.inject.Inject


class HistoryFragment : BaseDataBindingFragment<FragmentHistoryBinding>() {

    private var adapterMyItem: MyItemRecyclerViewAdapter? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: HistoryViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_history

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)
        initObserver()
    }
    private fun initObserver() {
        viewModel.getVideoList().observe(viewLifecycleOwner, Observer { list ->
            initView(list as ArrayList<VideoDto>)
        })
    }

    private fun initView(itemList: ArrayList<VideoDto>) {
        dataBinding?.rvList?.let {
            adapterMyItem =
                MyItemRecyclerViewAdapter(itemList, null)
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapterMyItem

            it.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

}
