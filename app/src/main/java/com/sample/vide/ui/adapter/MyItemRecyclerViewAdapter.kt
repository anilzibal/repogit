package com.sample.vide.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.vide.R
import com.sample.vide.data.model.VideoDto
import com.sample.vide.databinding.FragmentItemBinding

class MyItemRecyclerViewAdapter(
    private var mValues: ArrayList<VideoDto>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(viewGroup.getContext())
        viewHolder = ViewHolder(
            DataBindingUtil.inflate<FragmentItemBinding>(
                inflater,
                R.layout.fragment_item,
                viewGroup,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mValues[position])
    }

    override fun getItemCount(): Int = mValues.size


    inner class ViewHolder(binding: FragmentItemBinding) :
        BindableViewHolder<FragmentItemBinding>(binding) {
        fun bind(
            items: VideoDto
        ) {
            binding?.data = items
            binding.view.setOnClickListener { handleClick(items) }
        }
    }

    private fun handleClick(item: VideoDto) {
        mListener?.onListFragmentInteraction(item)
    }
}

interface OnListFragmentInteractionListener {
    fun onListFragmentInteraction(item: VideoDto)
}
