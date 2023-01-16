package com.tkpd.abstraction.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tkpd.abstraction.R

object ComposeUtil {

    @Composable
    fun ImageBuilder(modifier: Modifier, url: String) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_image_black_24dp),
            error = painterResource(R.drawable.ic_broken_image_black_24dp),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )
    }

    @Composable
    fun LoadingView() {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    strokeWidth = 10.dp
                )
            }
        }
    }

    @Composable
    fun ErrorView() {
        Surface {
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_error_black_24dp),
                    contentDescription = "Content description for visually impaired",
                    alignment = Alignment.Center
                )

                Text(
                    text = stringResource(R.string.label_error),
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 100.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}