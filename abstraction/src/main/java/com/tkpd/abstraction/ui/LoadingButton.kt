package com.tkpd.abstraction.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.graphicsLayer

private const val IndicatorSize = 8
private const val AnimationDurationMillis = 300
private const val NumIndicators = 3

//1
private const val AnimationDelayMillis = AnimationDurationMillis / NumIndicators

@Composable
private fun LoadingDot(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = color)
    )
}

@Composable
private fun LoadingIndicator(
    animating: Boolean,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.primary,
    indicatorSpacing: Dp = 2.dp,
) {
    // 1
    val animatedValues = List(NumIndicators) { index ->
        var animatedValue by remember(key1 = animating) {
            mutableStateOf(0f)
        }
        LaunchedEffect(key1 = animating) {
            if (animating) {
                animate(
                    initialValue = IndicatorSize / 2f,
                    targetValue = -IndicatorSize / 2f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = AnimationDurationMillis),
                        repeatMode = RepeatMode.Reverse,
                        initialStartOffset = StartOffset(AnimationDelayMillis * index),
                    ),
                ) { value, _ -> animatedValue = value }
            }
        }
        animatedValue
    }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        // 2
        animatedValues.forEach { animatedValue ->
            LoadingDot(
                modifier = Modifier
                    .padding(horizontal = indicatorSpacing)
                    .width(IndicatorSize.dp)
                    .aspectRatio(1f)
                    // 3
                    .offset(y = animatedValue.dp),
                color = color,
            )
        }
    }
}

@Composable
// 1
fun LoadingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    indicatorSpacing: Dp = 2.dp,
    content: @Composable () -> Unit,
) {
    val contentAlpha by animateFloatAsState(targetValue = if (loading) 0f else 1f)
    val loadingAlpha by animateFloatAsState(targetValue = if (loading) 1f else 0f)

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = colors,
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            LoadingIndicator(
                animating = loading,
                // 7
                modifier = Modifier.graphicsLayer { alpha = loadingAlpha },
                color = colors.contentColor(enabled = enabled).value,
                indicatorSpacing = indicatorSpacing,
            )
            Box(
                modifier = Modifier.graphicsLayer { alpha = contentAlpha }
            ) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun LoadingPreview() {
    var loading by remember {
        mutableStateOf(false)
    }

    Surface {
        LoadingButton(onClick = {
            loading = !loading
        }, modifier = Modifier.padding(16.dp),
            loading = loading,
            content = {
                Text("Login")
            })
    }
}