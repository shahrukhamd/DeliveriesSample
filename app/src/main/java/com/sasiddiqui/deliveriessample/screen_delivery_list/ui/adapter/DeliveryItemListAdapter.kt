package com.sasiddiqui.deliveriessample.screen_delivery_list.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sasiddiqui.deliveriessample.databinding.ListItemDeliveryBinding
import com.sasiddiqui.deliveriessample.screen_delivery_list.data.model.DeliveryItem
import com.sasiddiqui.deliveriessample.screen_delivery_list.ui.DeliveryItemListFragmentDirections
import timber.log.Timber

class DeliveryItemListAdapter : PagedListAdapter<DeliveryItem, DeliveryItemListAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder(private val itemBinding: ListItemDeliveryBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind (listener: View.OnClickListener, item: DeliveryItem?) {
            itemBinding.apply {
                clickListener = listener
                deliveryItem = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deliveryItem = getItem(position)
        holder.apply {
            bind(createClickListener(deliveryItem), deliveryItem)
            itemView.tag = deliveryItem
        }
    }

    private fun createClickListener(item: DeliveryItem?) : View.OnClickListener {
        return View.OnClickListener {
            Timber.d("Clicked: $item")
            val direction = DeliveryItemListFragmentDirections.actionListFragmentToDetailsFragment(item?.location)
            it.findNavController().navigate(direction)
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<DeliveryItem>() {
    override fun areItemsTheSame(oldItem: DeliveryItem, newItem: DeliveryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DeliveryItem, newItem: DeliveryItem): Boolean {
        return oldItem == newItem
    }
}