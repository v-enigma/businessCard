package com.example.businesscard
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color =  Color(0xAAC6EDC3)
                ) {
                     BusinessCardApp()
                    //Table()
                }
            }
        }
    }
}
@Composable
fun BusinessCardApp(){
    BusinessCard(painterResource(R.drawable.android_logo),name ="Jennifer Doe", "Android Developer ExtraOrdinarie", mailId = "jen.doe@android.com", phoneNumber = "+11(123)4445556666", share ="@AndroidDev",modifier= Modifier)
}

@Composable
fun BusinessCard(painting: Painter,name:String, role:String,phoneNumber :String, mailId:String,share:String, modifier :Modifier){
    Column(modifier = modifier) {

        InfoCard(painting,name, role, modifier.weight(1f))
        ContactInfo(phoneNumber = phoneNumber, share = share, mailId =mailId , modifier = modifier.weight(1f) )

    }
}
@Composable
fun ContactInfo(phoneNumber:String, share: String, mailId: String, modifier: Modifier){
    Column(
        modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
       Table(phoneNumber, share,mailId)
    }
}


@Composable
fun InfoCard(painting: Painter, name:String, role:String, modifier :Modifier ){
    Column(
        modifier
            .fillMaxHeight(0.5f)
            .fillMaxWidth(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painting, contentScale = ContentScale.FillWidth, contentDescription = null, modifier = Modifier.fillMaxHeight(0.5f)

            .padding(start = 140.dp, end = 140.dp, top = 60.dp)
            .background(Color(0xFF00001C)))
        Text(name, fontSize = 45.sp,modifier = Modifier.padding(top = 8.dp, bottom = 3.dp))
        Text(role, color = Color(0xFF0C6B37), fontWeight = FontWeight.Bold)
    }
}
@Composable
fun ContactInfoCard(contactInfo: ContactInfo){
      val iconColor = Color(0xFF0C6B37)
  when (contactInfo) {
      is ContactInfo.PhoneNumber -> {
          Row(Modifier.padding(5.dp)) {
              Icon(
                  Icons.Default.Phone,
                  null,
                  Modifier.padding(end =15.dp),
                  iconColor
              ); Text(contactInfo.content)
          }
      }

      is ContactInfo.Email -> {
          Row(Modifier.padding(5.dp)) {
              Icon(
                  Icons.Default.Share,
                  null,
                  Modifier.padding(end =15.dp),
                  iconColor
              ); Text(contactInfo.content)
          }
      }

      is ContactInfo.Share -> {
          Row(Modifier.padding(5.dp)) {
              Icon(
                  Icons.Default.Email,
                  null,
                  Modifier.padding(end =15.dp),
                  iconColor
              ); Text(contactInfo.content)
          }
      }
  }
}
@Composable
fun Table(phoneNumber:String, share: String,mailId: String){
   Column(modifier = Modifier.wrapContentHeight(align = Alignment.Top).padding(50.dp)) {
       ContactInfoCard(contactInfo = ContactInfo.PhoneNumber(phoneNumber))
       ContactInfoCard(contactInfo = ContactInfo.Share(share))
       ContactInfoCard(contactInfo = ContactInfo.Email(mailId))
   }
}

 sealed class ContactInfo(val content:String){
     class PhoneNumber(content :String): ContactInfo(content)
     class Email(content: String): ContactInfo(content)
     class Share(content: String): ContactInfo(content)
 }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        //Greeting("Android")
    }
}