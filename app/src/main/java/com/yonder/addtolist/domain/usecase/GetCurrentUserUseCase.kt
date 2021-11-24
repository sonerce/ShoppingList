package com.yonder.addtolist.domain.usecase

import com.yonder.addtolist.core.base.InputLessUseCase
import com.yonder.addtolist.core.network.responses.Result
import com.yonder.addtolist.core.network.thread.CoroutineThread
import com.yonder.addtolist.data.local.UserPreferenceDataStore
import com.yonder.addtolist.scenes.login.domain.mapper.LoginMapper
import com.yonder.addtolist.scenes.login.domain.model.UserUiModel
import com.yonder.addtolist.scenes.login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

/**
 * @author yusuf.onder
 * Created on 17.11.2021
 */

class GetCurrentUserUseCase @Inject constructor(
  private val dispatcher: CoroutineThread,
  private val loginMapper: LoginMapper,
  private val loginRepository: LoginRepository,
  private val userPreferenceDataStore: UserPreferenceDataStore
) : InputLessUseCase<Result<UserUiModel>> {

  override suspend fun invoke(): Flow<Result<UserUiModel>> {
    return flow {
      emit(Result.Loading)
      val userUiModel = loginMapper.map(loginRepository.getCurrentUser())
      Timber.d("userUiModel => $userUiModel")
      userPreferenceDataStore.setProviderType(userUiModel.providerType)
      emit(Result.Success(userUiModel))
    }.catch { error ->
      emit(Result.Error(error))
    }.flowOn(dispatcher.io)
  }

}
