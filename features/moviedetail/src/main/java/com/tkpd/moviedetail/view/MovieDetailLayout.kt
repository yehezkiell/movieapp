package com.tkpd.moviedetail.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import com.tkpd.abstraction.extension.createImageUrl
import com.tkpd.abstraction.util.ComposeUtil.ImageBuilder
import com.tkpd.moviedetail.R
import com.tkpd.moviedetail.model.MovieDetail

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

            ImageBuilder(
                modifier = Modifier.constrainAs(bannerImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(topGuideline40)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                url = movieDetail.backdropPath.createImageUrl()
            )

            ImageBuilder(
                modifier = Modifier.height(200.dp).aspectRatio(2f / 3f).constrainAs(mainImage) {
                    top.linkTo(topGuideline30)
                    start.linkTo(parent.start, 16.dp)
                },
                url = movieDetail.posterPath.createImageUrl()
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
                text = debugText(lorem, description),
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
//            MovieListItem()
            //            AdditionalSectionRow()
            //            AdditionalSectionRow()
        }
    }
}

val lorem = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.\n" +
        "    The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham."