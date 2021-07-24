package com.yonder.addtolist.common.ui.component.items

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.yonder.addtolist.common.ui.component.items.adapter.ItemListAdapter
import com.yonder.addtolist.common.ui.component.items.model.ItemUiModel
import com.yonder.addtolist.common.ui.component.items.model.ItemUiModelMapper
import com.yonder.addtolist.common.ui.extensions.addVerticalDivider
import com.yonder.addtolist.common.ui.extensions.removeAnimator
import com.yonder.addtolist.databinding.LayoutYoFilteredItemsBinding
import com.yonder.addtolist.local.entity.ProductEntitySummary
import com.yonder.addtolist.local.entity.UserListWithProducts

/**
 * @author yusuf.onder
 * Created on 23.07.2021
 */
class YoItemsView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

  private val binding: LayoutYoFilteredItemsBinding by lazy {
    LayoutYoFilteredItemsBinding.inflate(LayoutInflater.from(context), this, true)
  }

  var adapter: ItemListAdapter? = null

  init {
    initRecyclerView()
  }

  private fun initRecyclerView() = with(binding.rvItems) {
    removeAnimator()
    isNestedScrollingEnabled = false
  }

  fun setVisibility(isVisible : Boolean) {
    this.isVisible = isVisible
  }

  fun bind(
    userListWithProducts: UserListWithProducts,
    list: List<ProductEntitySummary>,
    query: String,
    productOperationListener: ItemOperationListener
  ) {

    val itemsList = ItemUiModelMapper.mapToUiModel(
      addedProducts = userListWithProducts.products,
      filteredProducts = list
    )

    val isVisibleQuery = query.isNotEmpty()
    binding.yoProductQueryItem.isVisible = isVisibleQuery
    if (isVisibleQuery) {
      val entity = userListWithProducts.products.find { it.name == query }
      val queryItemModel = ItemUiModel(query, entity)
      binding.yoProductQueryItem.bind(
        listener = productOperationListener,
        query = query,
        value = queryItemModel,
        boldEnabled = false
      )
    }
    binding.tvHeader.isGone = isVisibleQuery
    if (adapter == null) {
      adapter = ItemListAdapter().apply {
        itemOperationListener = productOperationListener
        this.query = query
        submitList(itemsList)
      }
      binding.rvItems.adapter = adapter
    } else {
      adapter?.apply {
        this.query = query
        submitList(itemsList)
        notifyDataSetChanged()
      }
    }
  }

}
