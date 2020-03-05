package com.example.fetchrewardstest.ui.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchrewardstest.FetchRewardsApplication
import com.example.fetchrewardstest.R
import com.example.fetchrewardstest.databinding.MainFragmentBinding
import com.example.fetchrewardstest.model.Item
import com.example.fetchrewardstest.retrofit.RetrofitRepository
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var mAdapter: ListItemAdapter? = null
    private var mainFragmentBinding: MainFragmentBinding? = null
    private var mainFragmentView: View? = null

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    @Inject
    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = FetchRewardsApplication.appComponent
        appComponent.inject(this)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        setupAdapter()
        getList()
        return mainFragmentBinding?.root
    }

    private fun getList() {
        viewModel.getFilteredList().observe(viewLifecycleOwner,
            Observer<List<Item>> { t ->
                t?.apply {
                    mAdapter?.setList(
                        t.filter { item -> !item.name.isNullOrEmpty() }.sortedWith(
                            compareBy({ it.listId }, { it.name?.split(" ")?.get(1)?.toInt() })
                        )
                    )
                }
            })
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setupAdapter() {
        mAdapter = ListItemAdapter(this@MainFragment.requireActivity())
        mainFragmentBinding?.listView?.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            adapter = mAdapter
        }
    }

}
