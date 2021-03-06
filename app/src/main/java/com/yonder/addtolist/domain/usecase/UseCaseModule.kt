package com.yonder.addtolist.domain.usecase

import com.yonder.addtolist.core.network.thread.CoroutineThread
import com.yonder.addtolist.local.AppDatabase
import com.yonder.addtolist.scenes.home.domain.repository.UserListRepository
import com.yonder.addtolist.scenes.listdetail.domain.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author yusuf.onder
 * Created on 12.01.2022
 */

@[Module InstallIn(ViewModelComponent::class)]
object UseCaseModule {

    @[Provides]
    fun provideCreateListUseCase(
        userListRepository: UserListRepository,
        coroutineThread: CoroutineThread
    ): CreateList {
        return CreateList(userListRepository, coroutineThread)
    }

    @[Provides]
    fun provideDeleteUserListProduct(
        productRepository: ProductRepository
    ): DeleteUserListProduct {
        return DeleteUserListProduct(productRepository)
    }

    @[Provides]
    fun provideUpdateUserListUseCase(
        appDatabase: AppDatabase
    ): UpdateUserListProduct {
        return UpdateUserListProduct(appDatabase)
    }
}
