package com.yonder.addtolist.core.base

import com.yonder.addtolist.core.extensions.orFalse
import com.yonder.addtolist.core.network.responses.BaseResponse
import com.yonder.addtolist.core.network.responses.BaseUiResult

/**
 * Yusuf Onder on 09,May,2021
 */

open class BaseDecider<T> {
  fun toBaseUiResult(baseResponse: BaseResponse<T>): BaseUiResult {
    return BaseUiResult(baseResponse.success.orFalse(), baseResponse.message.orEmpty())
  }
}
