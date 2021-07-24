package com.yonder.addtolist.common.ui.component.list.result

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yonder.addtolist.common.ui.component.list.result.model.ItemUiModel
import com.yonder.addtolist.databinding.ItemProductBinding
import com.yonder.addtolist.local.entity.ProductEntitySummary
import com.yonder.addtolist.local.entity.UserListProductEntity

/**
 * @author yusuf.onder
 * Created on 20.07.2021
 */

class ProductListViewHolder(
  view: View,
  private val listener: IProductOperation
) : RecyclerView.ViewHolder(view) {
  private val binding = ItemProductBinding.bind(view)

  fun bind(value: ItemUiModel, query: String) {
    binding.yoProductView.bind(value, listener, query)
  }
}