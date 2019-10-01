package com.sasiddiqui.deliveriessample.screen_delivery_list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.sasiddiqui.deliveriessample.R
import com.sasiddiqui.deliveriessample.databinding.FragmentDeliveryListBinding
import com.sasiddiqui.deliveriessample.di.Injectable
import com.sasiddiqui.deliveriessample.di.injectViewModel
import com.sasiddiqui.deliveriessample.screen_delivery_list.ui.adapter.DeliveryItemListAdapter
import com.sasiddiqui.deliveriessample.ui.VerticalItemDecoration
import com.sasiddiqui.deliveriessample.ui.hide
import javax.inject.Inject

class DeliveryItemListFragment: Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DeliveryItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)

        val binding = FragmentDeliveryListBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = DeliveryItemListAdapter()
        binding.recyclerView.addItemDecoration(
            VerticalItemDecoration(resources.getDimension(R.dimen.margin_normal).toInt(), true) )
        binding.recyclerView.adapter = adapter

        subscribeUi(binding, adapter)

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentDeliveryListBinding, adapter: DeliveryItemListAdapter) {
        viewModel.deliveryItems.observe(viewLifecycleOwner){
            binding.progressBar.hide()
            adapter.submitList(it)
        }
    }
}