package com.zoom_machine.dota_2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zoom_machine.dota_2.common.getComment
import com.zoom_machine.dota_2.data.Comment
import com.zoom_machine.dota_2.ui.theme.Dota_2Theme
import com.zoom_machine.dota_2.ui.theme.floors.MainInfoAboutGame
import com.zoom_machine.dota_2.ui.theme.floors.ShowStars


class MainActivity : ComponentActivity() {
    // Заглушка данные
    private val listChips = listOf("MOBA", "MULTIPLAYER", "STRATEGY")
    private val countStars = 4
    private val countUser = "70M"
    private val rating = "4.9"
    private val commentList = getComment()
    private val imagesList = listOf(R.drawable.screen_1, R.drawable.screen_2, R.drawable.screen_3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val descriptionGame = stringResource(id = R.string.description_game)
            Dota_2Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = colorResource(id = R.color.black)

                ) {
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = Modifier
                            .verticalScroll(scrollState)
                            .wrapContentHeight()
                    ) {
                        MainInfoAboutGame(
                            image = R.drawable.main_full_banner,
                            description = R.string.main_banner,
                            countStars = countStars,
                            logoGame = R.drawable.logo_main,
                            countUser = countUser,
                            listChips = listChips
                        )
                        DescriptionGame(description = descriptionGame)
                        ShowScreenShots(imagesList = imagesList)
                        ReviewAndRatings(rating = rating, countStars, countUser)
                        ShowCommentsLine(commentList = commentList)
                        ButtonInstall()
                    }
                }
            }
        }
    }
}

@Composable
fun DescriptionGame(description: String) { // Контейнер описания игры
    Text(
        text = description,
        modifier = Modifier.padding(top = 18.dp, start = 18.dp, end = 18.dp),
        fontSize = 14.sp,
        style = TextStyle(lineHeight = 18.sp),
        color = Color.LightGray
    )
}

@Composable
fun ShowScreenShots(@DrawableRes imagesList: List<Int>) { // Контейнер скриншотов
    LazyRow(
        modifier = Modifier.padding(top = 18.dp, start = 8.dp, end = 8.dp)
    ) {
        items(imagesList) { it ->
            ShowItemImage(it)
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun ShowItemImage(@DrawableRes image: Int) { // Item для контейнера скриншотов
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = R.string.screen_shot),
            modifier = Modifier.padding(6.dp)
        )
        IconButton(modifier = Modifier
            .then(Modifier.size(24.dp))
            .align(Alignment.Center),
            onClick = { }) {
            Icon(
                imageVector = Icons.Filled.PlayCircle,
                stringResource(id = R.string.screen_shot),
                tint = Color.White
            )
        }
    }
}

@Composable
fun ReviewAndRatings(
    rating: String,
    countStars: Int,
    countUser: String
) {// Контейнер Review & Ratings
    ConstraintLayout(
        modifier = Modifier.padding(top = 18.dp, start = 12.dp)
    ) {
        val (refRating, refTitle, refCountStar, refCountUser) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(refTitle) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        ) {
            Text(
                text = "Review & Ratings",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(refRating) {
                    start.linkTo(parent.start)
                    top.linkTo(refTitle.bottom, margin = 18.dp)
                }
        ) {
            Text(
                text = rating,
                fontSize = 46.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(refCountStar) {
                    start.linkTo(refRating.end, margin = 18.dp)
                    top.linkTo(refRating.top, margin = 6.dp)
                }
        ) {
            Row {
                var color = Color.Yellow
                for (i in 1..5) {
                    if (i > countStars) color = Color.White
                    ShowStars(color) // Оценка игры "stars"
                }
            }
        }
        Box(
            modifier = Modifier
                .constrainAs(refCountUser) {
                    start.linkTo(refRating.end, margin = 18.dp)
                    bottom.linkTo(refRating.bottom, margin = 6.dp)
                }
        ) {
            Text(
                text = "$countUser Reviews",
                fontSize = 14.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 18.dp)
            )
        }
    }
}

@Composable
fun ShowCommentsLine(commentList: List<Comment>) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        commentList.forEach {
            ItemComment(item = it)
        }
    }

}

@Composable
fun ItemComment(item: Comment) {
    ConstraintLayout(
        modifier = Modifier.padding(top = 18.dp, start = 12.dp)
    ) {
        val (refImage, refName, refData, refComent) = createRefs()
        Box(
            modifier = Modifier
                .constrainAs(refImage) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = item.image),
                contentDescription = stringResource(id = R.string.avatar)
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(refName) {
                    start.linkTo(refImage.end, margin = 18.dp)
                    top.linkTo(parent.top)
                }
        ) {
            Text(
                text = item.name,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(refData) {
                    start.linkTo(refName.start)
                    top.linkTo(refName.bottom, margin = 6.dp)
                }
        ) {
            Text(
                text = item.data,
                fontSize = 14.sp,
                color = Color.DarkGray
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(refComent) {
                    start.linkTo(refImage.start)
                    top.linkTo(refImage.bottom, margin = 18.dp)
                }
        ) {
            Text(
                text = item.text,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }
    }
}

@Preview
@Composable
fun ButtonInstall() {
    val shape = RoundedCornerShape(12.dp)
    Box(
        modifier = Modifier
            .padding(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 64.dp)
            .clip(shape = shape)
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.textButtonColors(backgroundColor = colorResource(id = R.color.yellow_orange)),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(
                "Install", fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
}




