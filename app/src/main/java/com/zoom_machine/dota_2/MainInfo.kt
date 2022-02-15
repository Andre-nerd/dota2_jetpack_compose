package com.zoom_machine.dota_2.ui.theme.floors

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zoom_machine.dota_2.R

@Composable
fun MainInfoAboutGame(
    @DrawableRes image: Int,
    @StringRes description: Int,
    countStars: Int,
    @DrawableRes logoGame: Int,
    countUser: String,
    listChips: List<String>
) {
    ConstraintLayout {
        val (mainBanner, buttonStart, buttonMenu, refLogoBox, nameGame, starsLine, chipsline) = createRefs()
        Box(// Главный баннер игры
            modifier = Modifier
                .constrainAs(mainBanner) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = description)
            )
        }
        Box(// Кнопка "Стрелка назад"
            modifier = Modifier
                .constrainAs(buttonStart) {
                    start.linkTo(parent.start, margin = 18.dp)
                    top.linkTo(parent.top, margin = 70.dp)
                }
        ) {
            Image(
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.oval_button),
                contentDescription = stringResource(id = R.string.back_arrow)
            )
            IconButton(modifier = Modifier
                .then(Modifier.size(24.dp))
                .align(Alignment.Center),
                onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    stringResource(id = R.string.back_arrow),
                    tint = Color.White
                )
            }
        }
        Box(//Кнопка "More ..."
            modifier = Modifier
                .constrainAs(buttonMenu) {
                    end.linkTo(parent.end, margin = 24.dp)
                    top.linkTo(parent.top, margin = 70.dp)
                }
        ) {
            Image(
                modifier = Modifier
                    .size(56.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.oval_button),
                contentDescription = stringResource(id = R.string.menu_button)
            )
            IconButton(modifier = Modifier
                .then(Modifier.size(24.dp))
                .align(Alignment.Center),
                onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.MoreHoriz,
                    stringResource(id = R.string.menu_button),
                    tint = Color.White
                )
            }
        }
        Box(
            // Контейнер с Логотипом
            modifier = Modifier
                .constrainAs(refLogoBox) {
                    start.linkTo(parent.start, margin = 18.dp)
                    top.linkTo(parent.top, margin = 286.dp)
                }
                .size(90.dp),
        ) {
            RoundedCorBox()
            Box(modifier = Modifier.align(Alignment.Center)) {
                Image(
                    modifier = Modifier.size(54.dp),
                    painter = painterResource(logoGame),
                    contentDescription = stringResource(id = description)
                )
            }
        }
        Text(
            // Название игры
            modifier = Modifier
                .constrainAs(nameGame) {
                    start.linkTo(refLogoBox.end, margin = 12.dp)
                    top.linkTo(refLogoBox.top, margin = 32.dp)
                },
            text = "DoTA 2",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )
        Row(modifier = Modifier.constrainAs(starsLine) {
            start.linkTo(refLogoBox.end, margin = 12.dp)
            top.linkTo(nameGame.bottom, margin = 8.dp)
        }) {
            var color = Color.Yellow
            for (i in 1..5) {
                if (i > countStars) color = Color.White
                ShowStars(color) // Оценка игры "stars"
            }
            Text(
                text = countUser,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(modifier = Modifier.constrainAs(chipsline) {
            start.linkTo(refLogoBox.start)
            top.linkTo(refLogoBox.bottom, margin = 18.dp)
        }) {
            ShowChipsLine(listChips = listChips) // Чипсы
        }
    }
}

@Composable
fun RoundedCorBox() { // Контенйер для логотипа
    val shape = RoundedCornerShape(6.dp)
    Column(
        modifier = Modifier.wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(shape)
                .background(Color.Black)
                .border(
                    BorderStroke(2.dp, color = Color.DarkGray)
                )
        )
    }
}

@Composable
fun ShowChipsLine(listChips: List<String>) {// Лента чипсов
    Row {
        listChips.forEach {
            CustomChip(value = it)
        }
    }
}

@Composable
fun ShowStars(color: Color) {// Оценка "stars"
    Row(
        modifier = Modifier.size(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            stringResource(id = R.string.menu_button),
            tint = color
        )
    }
}

@Composable
fun CustomChip(value: String) {// Отображение кастомного чипса
    Surface(
        color = colorResource(id = R.color.dark_blue),
        contentColor = colorResource(id = R.color.light_blue),
        shape = CircleShape,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
            text = value,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
        )
    }
}