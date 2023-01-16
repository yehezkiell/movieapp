package com.tkpd.moviedetail.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.tkpd.abstraction.data.MovieDetail
import com.tkpd.abstraction.extension.createImageUrl
import com.tkpd.moviedetail.R

class MovieDetailLayout {

    @Composable
    fun MovieDetailContent(movieDetail: MovieDetail) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (bannerImage,
                mainImage,
                title,
                additionalSectionRow,
                descriptionSection) = createRefs()

            val topGuideline40 = createGuidelineFromTop(0.4f)
            val topGuideline30 = createGuidelineFromTop(0.3f)

            val barrierHeader = createBottomBarrier(mainImage, additionalSectionRow)

            Image(
                painter = debugPlaceholder(
                    debugPreview = R.drawable.ic_broken_image_black_24dp,
                    productionImageUrl = movieDetail.backdropPath.createImageUrl()
                ),
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
                painter = debugPlaceholder(
                    debugPreview = R.drawable.ic_broken_image_black_24dp,
                    productionImageUrl = movieDetail.posterPath.createImageUrl()
                ),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(200.dp).aspectRatio(2f / 3f).constrainAs(mainImage) {
                    top.linkTo(topGuideline30)
                    start.linkTo(parent.start, 16.dp)
                }
            )

            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().constrainAs(title) {
                    top.linkTo(bannerImage.bottom, 16.dp)
                    start.linkTo(mainImage.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                text = debugText("Upin Ipin", movieDetail.title)
            )

            AdditionalSectionRow(
                modifier = Modifier.padding(horizontal = 16.dp)
                    .constrainAs(additionalSectionRow) {
                        top.linkTo(title.bottom)
                        start.linkTo(mainImage.end)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    },
                ratingText = movieDetail.voteAverage.toString(),
                popularityText = movieDetail.popularity.toString(),
                releaseDate = movieDetail.releaseDate
            )

            DescriptionSection(
                modifier = Modifier
                    .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(descriptionSection) {
                        top.linkTo(barrierHeader)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    },
                description = movieDetail.overview
            )
        }
    }

    @Composable
    fun DescriptionSection(modifier: Modifier, description: String) {
        Column(modifier = modifier.fillMaxSize()) {
            Text("Description")

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = debugText(stringResource(R.string.lorem_ipsum), description),
                lineHeight = 20.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    @Composable
    fun AdditionalSectionRow(
        modifier: Modifier,
        ratingText: String,
        popularityText: String,
        releaseDate: String
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AdditionalInfoItem(
                text = debugText("4.7", ratingText),
                icon = R.drawable.ic_stars_yellow_24dp
            )
            AdditionalInfoItem(
                text = debugText("5.2rb", popularityText),
                icon = R.drawable.ic_favorite_red_24dp
            )
            AdditionalInfoItem(
                text = debugText("2023-01-13", releaseDate),
                icon = R.drawable.ic_date_range_blue_24dp
            )
        }
    }

    @Composable
    fun AdditionalInfoItem(
        text: String,
        @DrawableRes icon: Int
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(icon),
                modifier = Modifier.size(50.dp),
                contentDescription = ""
            )
            Text(
                text = debugText(
                    debugText = "4.7",
                    prodText = text
                )
            )
        }
    }

    @Composable
    fun debugPlaceholder(@DrawableRes debugPreview: Int, productionImageUrl: String) =
        if (LocalInspectionMode.current) {
            painterResource(id = debugPreview)
        } else {
            rememberAsyncImagePainter(
                model = productionImageUrl
            )
        }

    @Composable
    fun debugText(debugText: String, prodText: String) =
        if (LocalInspectionMode.current) {
            debugText
        } else {
            prodText
        }

    @Preview
    @Composable
    private fun MainViewPreview() {
        Surface {
            MovieDetailContent(MovieDetail())
            //            AdditionalSectionRow()
            //            AdditionalSectionRow()
        }
    }
}