package com.yonder.addtolist.features.login.di

import android.content.Context
import com.facebook.CallbackManager
import com.yonder.addtolist.common.utils.auth.NewUserProviderImpl
import com.yonder.addtolist.common.utils.auth.NewUserProvider
import com.yonder.addtolist.core.base.BaseDecider
import com.yonder.addtolist.core.base.BaseMapper
import com.yonder.addtolist.core.base.BaseResponse
import com.yonder.addtolist.features.login.data.remote.LoginService
import com.yonder.addtolist.features.login.data.remote.datasource.RemoteLoginDataSource
import com.yonder.addtolist.features.login.data.remote.datasource.RemoteLoginDataSourceImpl
import com.yonder.addtolist.features.login.domain.decider.LoginDecider
import com.yonder.addtolist.features.login.domain.mapper.LoginMapper
import com.yonder.addtolist.features.login.data.remote.response.UserResponse
import com.yonder.addtolist.features.login.domain.model.UserUiModel
import com.yonder.addtolist.features.login.domain.repository.LoginRepository
import com.yonder.addtolist.features.login.domain.repository.LoginRepositoryImpl
import com.yonder.addtolist.features.login.domain.usecase.FacebookGraphUseCase
import com.yonder.addtolist.features.login.domain.usecase.FacebookGraphUseCaseImpl
import com.yonder.addtolist.features.login.domain.usecase.LoginUseCase
import com.yonder.addtolist.features.login.domain.usecase.LoginUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Yusuf Onder on 09,May,2021
 */


@[Module InstallIn(ViewModelComponent::class)]
interface LoginModule {

  @get:[Binds]
  val LoginMapper.loginMapper: BaseMapper<BaseResponse<UserResponse>, UserUiModel>

  @get:[Binds]
  val LoginUseCaseImpl.loginUseCase: LoginUseCase

  @get:[Binds]
  val FacebookGraphUseCaseImpl.facebookUseCase: FacebookGraphUseCase

  @get:[Binds]
  val RemoteLoginDataSourceImpl.remoteLoginDataSource: RemoteLoginDataSource

  @get:[Binds]
  val LoginRepositoryImpl.loginRepository: LoginRepository

  @get:[Binds]
  val LoginDecider.loginDecider: BaseDecider<UserResponse>

  companion object {
    @[Provides]
    fun provideNewUserProvider(@ApplicationContext context: Context): NewUserProvider {
      return NewUserProviderImpl(context)
    }

    @[Provides]
    fun provideFacebookCallbackManager(): CallbackManager {
      return CallbackManager.Factory.create()
    }

    @[Provides]
    fun provideLoginService(retrofit: Retrofit): LoginService {
      return retrofit.create(LoginService::class.java)
    }
  }
}