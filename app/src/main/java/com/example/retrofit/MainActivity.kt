package com.example.retrofit

import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.retrofit.data.remote.ListedApi
import com.example.retrofit.data.remote.ListedDataClass
import com.example.retrofit.data.remote.MakeApiRequest.makeApiRequest
import com.example.retrofit.data.remote.TopLinks
import com.example.retrofit.domain.model.MyViewModel
import com.example.retrofit.domain.model.MyViewModelFactory
import com.example.retrofit.fetchingReqData.TodayData
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import kotlin.reflect.full.memberProperties
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    
    val token = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.inopenapp.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ListedApi::class.java)
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_status_bar)

        setContent {

            MyComposableFunction(apiService)
            
        }
    }
}

@Composable
fun MyComposableFunction(apiService:ListedApi){

    val viewModel: MyViewModel = viewModel(factory = MyViewModelFactory(apiService))
    val dataState: ListedDataClass? by viewModel.dataState


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            myAppBar()
            Box(
                modifier = Modifier
                    .size(700.dp)
                    .background(Color(0xFFF5F5F5))
                    .border(
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(8.dp)
                    ))

            {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    item{
                        Spacer(modifier = Modifier.height(20.dp))
                        NameComposable()
                    }
                    item {
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth())
                    }
                    item {
                        ChartComposable(dataState)
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        TodayComposable(dataState = dataState)
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        ViewAnalyticsButton()
                    }
                    item {
                        ListOfLinks(dataState = dataState)

                    }
                }

            }
        }
    }
}

@Composable
fun NameComposable(){
    Box(modifier = Modifier
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Good Morning", fontSize = 16.sp,color=Color(0xFF999CA0))
            Spacer(modifier = Modifier.height(5.dp))
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Ajay Manva ", fontSize = 24.sp,color=Color(0xFF000000))
                Image(
                    painter = painterResource(id = R.drawable.hey_image),
                    contentDescription = "hey Emoji"
                )
            }

        }
    }
}

@Composable
fun ChartComposable(dataState: ListedDataClass?){
    Box(modifier = Modifier
        .height(200.dp)
        .fillMaxWidth()
        .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
        .background(Color(0xFFFFFFFF)).padding(top=8.dp,bottom=16.dp), contentAlignment = Alignment.Center) {
        
        Canvas(modifier = Modifier.padding(8.dp).aspectRatio(3/2f).fillMaxSize()){
            val barWidthPx=1.dp.toPx()
            drawRect(color=Color(0xFF000000), style = Stroke(barWidthPx))


            val verticalLines=12
            val verticalSize=size.width/(verticalLines+1)

            repeat(verticalLines){i->
                val startX=verticalSize*(i+1)

                drawLine(
                    Color(0xFFF2F2F2),
                    start= Offset(startX,0f),
                    end=Offset(startX,size.height),
                    strokeWidth = barWidthPx
                )
            }

            val horizontalLines=12
            val horizontalSize=size.height/(horizontalLines+1)

            repeat(horizontalLines){i->
                val startY=horizontalSize*(i+1)

                drawLine(
                    Color(0xFFF2F2F2),
                    start= Offset(0f,startY),
                    end=Offset(size.width,startY),
                    strokeWidth = barWidthPx
                )
            }
        }
    }
}

//fun generatePath(dataState: ListedDataClass?,size:Size):Path{
//    val path=Path()
//
//    if(dataState?.data?.overallUrlChart!=null) {
//        val properties=dataState::class.memberProperties
//
//        for (property in properties) {
//            val value = property.get(dataState as Nothing)
//            println("${property.name} = $value")
//        }
//    }
//
//}

@Composable
fun TodayComposable(dataState: ListedDataClass?){
    Box(modifier = Modifier
        .height(120.dp)
        .fillMaxWidth()
        .padding(8.dp)) {
        Row(modifier = Modifier.fillMaxSize()) {
            TodayRowItems(
                imageId = R.drawable.today_click_icon,
                descriptionImage = "today clicks icon",
                dataText = "Today's clicks",
                data = dataState?.todayClicks.toString() ?: "",
                modifier= Modifier
                    .weight(1f)
                    .height(120.dp)
                    .width(80.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier
                .width(10.dp)
                .fillMaxHeight())
            TodayRowItems(
                imageId = R.drawable.top_location_icon,
                descriptionImage = "location icon",
                dataText = "Top Location",
                data = dataState?.topLocation ?: "",
                modifier= Modifier
                    .weight(1f)
                    .height(120.dp)
                    .width(80.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier
                .width(10.dp)
                .fillMaxHeight())
            TodayRowItems(
                imageId = R.drawable.top_source_icon,
                descriptionImage = "globe icon",
                dataText = "Top source",
                data = dataState?.topSource ?: "",
                modifier= Modifier
                    .weight(1f)
                    .height(120.dp)
                    .width(80.dp)
                    .background(Color(0xFFFFFFFF))
                    .padding(8.dp)
            )
        }
        
    }
}

@Composable
fun TodayRowItems(
    imageId: Int,
    descriptionImage: String,
    dataText: String,
    data: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier)
    {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(imageId),
                contentDescription = descriptionImage,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .weight(1f)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = data, modifier = Modifier.weight(1f),color=Color(0xFF000000))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = dataText, modifier = Modifier.weight(1f),color=Color(0xFF999CA0))
        }

    }
}

@Composable
fun ViewAnalyticsButton(){
    Column() {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(8.dp)){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { }
                    .border(
                        BorderStroke(1.dp, color = Color(0xFFD8D8D8)),
                        shape = RoundedCornerShape(8.dp)
                    ),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.view_analystics_button),
                    contentDescription = "zic-zac arrow",
                    modifier= Modifier
                        .height(32.dp)
                        .width(32.dp)
                )
                Text(text="  View Analytics",color=Color(0xFF000000))
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
    }
}

@Composable
fun ListOfLinks(dataState: ListedDataClass?) {

    val ovalShape = remember { RoundedCornerShape(50.dp) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFF0E6FFF), ovalShape)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .clickable { }, contentAlignment = Alignment.Center
                ) {
                    Text(text = "Top Links", fontSize = 16.sp,color=Color(0xFFFFFFFF))
                }
                Spacer(modifier = Modifier.width(6.dp))
                Box(
                    modifier = Modifier
                        .background(Color(0xFF0E6FFF), ovalShape)
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
                        .clickable { }, contentAlignment = Alignment.Center
                ) {
                    Text(text = "Recent Links", fontSize = 16.sp,color=Color(0xFFFFFFFF))
                }
            }

        }
    
    Spacer(modifier = Modifier.height(8.dp))


    Box(modifier = Modifier.height(200.dp)) {
        val topLinksList = dataState?.data?.topLinks

        val showItemCount = remember { mutableStateOf(2) }


        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            if (topLinksList != null) {
                items(topLinksList.subList(0, showItemCount.value)) { item ->
                    topListItem(item)
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                    if (showItemCount.value < topLinksList.size) {
                        Button(onClick = {
                            showItemCount.value = topLinksList.size
                        }, modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFF5F5F5),
                                contentColor = Color(0xFF000000)
                            )) {
                            Text(text = "View all links")
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun topListItem(item: TopLinks?){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFFFFFFFF))) {
        Column(modifier= Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                val painter= OriginalImageFetching(url = item?.originalImage)
                Box(modifier= Modifier
                    .size(120.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(150.dp)
                    )
                }

                Spacer(modifier = Modifier.width(6.dp))

                Column(modifier=Modifier.weight(1f)) {
                    Text(text = item?.urlPrefix?: "")
                    Text(text= item?.createdAt?: "")
                }
                
                Spacer(modifier = Modifier.width(20.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(text = item?.totalClick.toString()?: "")
                    Text(text= "Clicks")
                }
            }
        }

    }

}

@Composable
fun OriginalImageFetching(url: String?, modifier: Modifier = Modifier): ImagePainter {
    return rememberImagePainter(url)
}

@Composable
fun myAppBar(){
    TopAppBar(
        title = {
            Text(text = "DashBoard", fontSize = pxToSp(24))
        },
        backgroundColor = Color(0xFF0E6FFF),
        modifier = Modifier.height(120.dp),
        contentColor = Color(0xFFFFFFFF),
        elevation = 4.dp // Optional: Add elevation to the app bar
    )
}

fun pxToSp(px: Int): TextUnit {
    return px.sp
}



@Composable
fun MyScreen() {
    var result by remember { mutableStateOf("") }

    var dataResponse:ListedDataClass?=null

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { GlobalScope.launch {makeApiRequest() { response ->
                result = when {
                    response.isSuccessful -> {
                        dataResponse = response.body()
                        var recentLinksArray=dataResponse?.data?.recentLinks
                        recentLinksArray?.get(0)?.webLink?: "No data available"
                    }
                    else -> "Error: ${response.code()}"
                }
            }} },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Fetch Data")
        }

        if (result.isNotEmpty()) {
            Text(
                text = result,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        Box(modifier = Modifier
            .height(200.dp)
            .border(border = BorderStroke(2.dp, Color.Red))) {
            var todayDataObj= TodayData()
            var todayDataTypeObj= todayDataObj.todayData(dataResponse)

            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = todayDataTypeObj.todayClick.toString())
                Text(text = todayDataTypeObj.topLocation.toString())
                Text(text=todayDataTypeObj.topSource.toString())
            }
            
        }
    }
}












//suspend fun makeApiRequests() {
//    val apiService = RetrofitInstance.returnListedApi()
//    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MjU5MjcsImlhdCI6MTY3NDU1MDQ1MH0.dCkW0ox8tbjJA2GgUx2UEwNlbTZ7Rr38PVFJevYcXFI"
//    val call = apiService.getListing(token)
//
//    call.enqueue(object : Callback<ListedDataClass> {
//        override fun onResponse(call: Call<ListedDataClass>, response: Response<ListedDataClass>) {
//            if (response.isSuccessful) {
//                val dataResponse = response.body()
//                val resultText = dataResponse?.message?: "No data available"
//                //result = resultText
//            } else {
//                //result = "Error: ${response.code()}"
//            }
//        }
//
//        override fun onFailure(call: Call<ListedDataClass>, t: Throwable) {
//            //result = "Error: ${t.message}"
//        }
//    })
//}
