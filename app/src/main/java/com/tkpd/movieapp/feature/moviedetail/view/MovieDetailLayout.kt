package com.tkpd.movieapp.feature.moviedetail.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import com.tkpd.movieapp.R

class MovieDetailLayout {

    @Composable
    fun MainView() {
        ConstraintLayout(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
            val (bannerImage, mainImage, title) = createRefs()
            val topGuideline40 = createGuidelineFromTop(0.4f)
            val topGuideline30 = createGuidelineFromTop(0.3f)

            Image(
                painter = debugPlaceholder(R.drawable.images1),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.constrainAs(bannerImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(topGuideline40)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                })
            Image(
                painter = debugPlaceholder(R.drawable.images1),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(200.dp).aspectRatio(2f / 3f).constrainAs(mainImage) {
                    top.linkTo(topGuideline30)
                    start.linkTo(parent.start, 16.dp)
                })
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().constrainAs(title) {
                    top.linkTo(bannerImage.bottom, 16.dp)
                    start.linkTo(mainImage.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                text = "Upin Ipin"
            )
        }
    }

    @Composable
    fun debugPlaceholder(@DrawableRes debugPreview: Int) =
        if (LocalInspectionMode.current) {
            painterResource(id = debugPreview)
        } else {
            rememberAsyncImagePainter("https://image.tmdb.org/t/p/original//zrnzWEQSJ0jtufPGR4SEnB6s1q1.jpg")
        }

    @Preview
    @Composable
    private fun MainViewPreview() {
        Surface {
            MainView()
        }
    }
}