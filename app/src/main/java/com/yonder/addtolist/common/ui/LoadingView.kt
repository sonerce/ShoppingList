package com.yonder.addtolist.common.ui

/**
 * @author yusuf.onder
 * Created on 17.11.2021
 */

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.yonder.addtolist.R
import com.yonder.addtolist.theme.padding_8

@Composable
fun LoadingView(centerTextResId: Int = R.string.loading) {
  Box(
    modifier = Modifier
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      CircularProgressIndicator(
        modifier = Modifier
          .padding(padding_8)
      )
      Text(text = stringResource(id = centerTextResId))
    }
  }
}
