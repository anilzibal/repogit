package com.sample.vide.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sample.vide.R
import com.sample.vide.data.model.VideoDto
import com.sample.vide.databinding.FragmentVideolistBinding
import com.sample.vide.di.MyApp
import com.sample.vide.ui.activity.VideoDetailActivity
import com.sample.vide.ui.adapter.MyItemRecyclerViewAdapter
import com.sample.vide.ui.adapter.OnListFragmentInteractionListener
import com.sample.vide.ui.viewmodel.ItemViewModel
import javax.inject.Inject

class VideoListFragment : BaseDataBindingFragment<FragmentVideolistBinding>(),
    OnListFragmentInteractionListener {

    private var listener: OnListFragmentInteractionListener? = null
    private var adapterMyItem: MyItemRecyclerViewAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: ItemViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_videolist

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)
        initObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.appComponent.inject(this)
    }

    private fun initObserver() {

        viewModel.snackBarMsg.observe(viewLifecycleOwner, getSnackBarMsgObserver())
        viewModel .progressVisible.observe(viewLifecycleOwner, progressObserver())
        viewModel.itemLiveData.observe(viewLifecycleOwner, dataResponse())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initView(itemList: ArrayList<VideoDto>) {
        dataBinding?.rvList?.let {
            adapterMyItem =
                MyItemRecyclerViewAdapter(itemList, this)
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapterMyItem

            it.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
        }
    }

    private fun dataResponse():Observer<List<VideoDto>>{
        return Observer {
            initView(it as ArrayList<VideoDto>)
        }
    }


    private fun getSnackBarMsgObserver(): Observer<String> {
        return Observer {
            view?.let { it1 ->
                Snackbar.make(
                    it1,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun progressObserver(): Observer<Boolean> {
        return Observer { flag ->
            dataBinding?.let {
                if (flag) it.progressCircular.visibility = View.VISIBLE
                else it.progressCircular.visibility = View.GONE
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onListFragmentInteraction(item: VideoDto) {
        viewModel.addVideo(item)
        val intent = Intent(activity, VideoDetailActivity::class.java).apply {
            putExtra("data", item)
        }
        startActivity(intent)
    }
}
