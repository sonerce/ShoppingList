package com.yonder.addtolist.scenes.listdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yonder.addtolist.core.extensions.EMPTY_STRING
import com.yonder.addtolist.domain.usecase.DeleteUserListProduct
import com.yonder.addtolist.domain.usecase.GetUserList
import com.yonder.addtolist.domain.usecase.UpdateUserListProduct
import com.yonder.addtolist.local.entity.CATEGORY_OTHER_IMAGE
import com.yonder.addtolist.scenes.home.domain.model.UserListProductUiModel
import com.yonder.addtolist.scenes.listdetail.domain.category.ProductQueryUseCase
import com.yonder.addtolist.scenes.listdetail.domain.product.ProductUseCase
import com.yonder.addtolist.scenes.listdetail.domain.AddProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author yusuf.onder
 * Created on 19.07.2021
 */

private const val QUERY_LIMIT = 10


@HiltViewModel
class ListDetailViewModel @Inject constructor(
  private val productQueryUseCase: ProductQueryUseCase,
  private val productUseCase: ProductUseCase,
  private val addProductUseCase: AddProductUseCase,
  private val updateProductUseCase: UpdateUserListProduct,
  private val deleteProductUseCase: DeleteUserListProduct,
  private val getUserListUseCase: GetUserList
) : ViewModel() {

  private val _state: MutableStateFlow<ListDetailViewState> =
    MutableStateFlow(ListDetailViewState.Initial)
  val state: StateFlow<ListDetailViewState> get() = _state
  var job: Job? = null


  fun fetchProducts(listUUID: String, query: String = EMPTY_STRING) {
    val flow1 = getUserListUseCase.invoke(listUUID)
    val flow2 = if (query.trim().isEmpty()) {
      productQueryUseCase.fetchPopularProducts()
    } else {
      productQueryUseCase.fetchProductByQuery(
        query = query,
        limit = QUERY_LIMIT
      )
    }
    job?.cancel()
    job = viewModelScope.launch {
      flow1.combine(flow2) { userList, listingProducts ->
        if (userList.products.isEmpty()) {
          _state.value = ListDetailViewState.OpenKeyboard
        }
        _state.value = ListDetailViewState.UserListContent(
          userList,
          listingProducts,
          query
        )
      }.collect()
    }

  }

  fun addProduct(listId: Int?, userListUUID: String, productName: String) {
    listId?.let {
      viewModelScope.launch {
        productUseCase.getProductEntityForName(productName).collect { productEntity ->
          val categoryImage = productEntity?.categoryImage ?: CATEGORY_OTHER_IMAGE
          addProductUseCase.invoke(
            listId = listId.toString(),
            listUUID = userListUUID,
            productName = productName,
            productCategoryImage = categoryImage
          ).collect()
        }
      }
    }
  }


  fun increaseQuantity(product: UserListProductUiModel) {
    product.quantityValue = product.quantityValue.plus(1.0)
    update(product)
  }

  fun decreaseQuantity(product: UserListProductUiModel) {
    product.quantityValue = product.quantityValue.minus(1.0)
    update(product)
  }

  fun toggleDone(product: UserListProductUiModel) {
    product.isDone = !product.isDone
    update(product)
  }

  private fun update(product: UserListProductUiModel,productName: String = product.name) {
    viewModelScope.launch {
      updateProductUseCase(
        productName = productName,
        listUUID = product.listUUID,
        product = product
      )
    }
  }

  fun deleteProduct(product: UserListProductUiModel) {
    viewModelScope.launch {
      deleteProductUseCase.invoke(product)
    }
  }
}

