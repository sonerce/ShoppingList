package com.yonder.addtolist.scenes.detail.domain

import com.yonder.addtolist.core.network.responses.Result
import com.yonder.addtolist.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

/**
 * @author yusuf.onder
 * Created on 19.07.2021
 */
interface CategoryListUseCase {
  fun getCategories(): Flow<Result<List<CategoryEntity>>>
}