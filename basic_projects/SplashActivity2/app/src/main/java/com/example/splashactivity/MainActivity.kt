package com.example.splashactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.splashactivity.ui.theme.SplashActivityTheme

class MainActivity : ComponentActivity() {


    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }



        setContent {
            SplashActivityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFCCBC)
                ) {
                    OnBoardingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {


    val pagerState = rememberPagerState(0, 0F) {
        3
    }

    var heading = remember { "" }
    var description = remember { "" }
    var resource = remember { 0 }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.TopEnd
    )
    {

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB31312))
        ) {
            Text(text = "Skip")

        }


        HorizontalPager(state = pagerState) {

            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    when (pagerState.currentPage) {

                        0 -> {
                            heading = stringResource(id = R.string.heading_one)
                            description = stringResource(id = R.string.desc_one)
                            resource = R.drawable.peopleonboarding1
                        }

                        1 -> {
                            heading = stringResource(id = R.string.heading_two)
                            description = stringResource(id = R.string.desc_two)
                            resource = R.drawable.peopleonboarding2
                        }

                        2 -> {
                            heading = stringResource(id = R.string.heading_three)
                            description = stringResource(id = R.string.desc_three)
                            resource = R.drawable.peopleonboarding3
                        }

                    }

                    Image(
                        painter = painterResource(id = resource),
                        contentDescription = "",
                        modifier = Modifier
//                            .fillMaxWidth()
//                            .wrapContentSize(Alignment.Center)
                            .scale(1.9f)
//                            .weight(0.5f)
                            .height(500.dp)
                    )


                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = heading, color = Color(0xFFB31312),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = description, color = Color.Black,
                        textAlign = TextAlign.Center,

                        modifier = Modifier
                            .fillMaxWidth()
                    )


                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Row {
                repeat(3) {
                    CustomIndicator(isSelected = pagerState.currentPage == it)
                }
            }
        }


    }


}

@Composable
fun CustomIndicator(isSelected: Boolean) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(color = if (isSelected) Color.Red else Color.White, shape = CircleShape)
            .size(15.dp)
    )

}

@Preview(showBackground = true)
@Composable
fun onBoardingPreview() {
    SplashActivityTheme {
        OnBoardingScreen()
    }
}