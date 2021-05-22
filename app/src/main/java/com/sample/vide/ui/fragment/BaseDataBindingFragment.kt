package com.sample.vide.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseDataBindingFragment<ViewBinding : ViewDataBinding> : Fragment() {

    var dataBinding: ViewBinding? = null

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        dataBinding!!.lifecycleOwner = viewLifecycleOwner

        return dataBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        super.onDestroyView()

        /*
            Without nullifying dataBinding ViewPager2 gets data binding related MEMORY LEAKS
         */
        dataBinding = null
    }


}