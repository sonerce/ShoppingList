package com.yonder.addtolist.scenes.listdetail.domain.category

import com.yonder.core.base.mapper.ListMapperImpl
import com.yonder.addtolist.core.network.exceptions.ServerResultException
import com.yonder.core.network.RestResult
import com.yonder.addtolist.data.local.UserPreferenceDataStore
import com.yonder.addtolist.local.entity.CategoryWithProducts
import com.yonder.addtolist.scenes.listdetail.domain.mapper.CategoryEntityMapper
import com.yonder.addtolist.scenes.listdetail.domain.mapper.ProductEntityMapper
import com.yonder.addtolist.scenes.home.data.local.datasource.CategoryDataSource
import com.yonder.addtolist.scenes.home.data.remote.ApiService
import com.yonder.addtolist.scenes.home.domain.mapper.CategoryProductsMapper
import com.yonder.addtolist.scenes.home.domain.model.CategoryProductsUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author yusuf.onder
 * Created on 19.07.2021
 */
class CategoryListRepositoryImpl @Inject constructor(
  private val apiService: ApiService,
  private val categoryDataSource: CategoryDataSource,
  private val userPreferenceDataStore: UserPreferenceDataStore,
  private val mapper: CategoryProductsMapper
) : CategoryListRepository {

  override fun fetchCategories(): Flow<RestResult<List<CategoryWithProducts>>> = flow {
    if (!userPreferenceDataStore.isFetchedCategoriesAndProducts()) {
      emit(RestResult.Loading)
      val result = apiService.getCategories(null)
      val entities: CategoryProductsUiModel = mapper.map(result)
      entities.list.forEach { category ->
        val categories = ListMapperImpl(
          CategoryEntityMapper(categoryImage = category.image)
        ).map(category.translationResponses).distinctBy {
          it.categoryId + it.languageId
        }
        val products = ListMapperImpl(
          ProductEntityMapper(
            categoryImage = category.image,
            categoryId = "${category.id}"
          )
        ).map(category.products)
        categoryDataSource.insertAll(categories)
        categoryDataSource.insertAllProducts(products)
        userPreferenceDataStore.setFetchedCategoriesAndProducts()
      }
    }
    emit(RestResult.Success(categoryDataSource.getCategories()))
  }.catch { e ->
    e.printStackTrace()
    emit(RestResult.Error(ServerResultException()))
  }
}




