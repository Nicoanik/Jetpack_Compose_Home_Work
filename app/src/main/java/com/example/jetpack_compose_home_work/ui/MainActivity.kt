package com.example.jetpack_compose_home_work.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_home_work.R
import com.example.jetpack_compose_home_work.domain.Contact

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactDetails(
                Contact(
                    name = "Евгений",
                    surname = "Андреевич",
                    familyName = "Лукашин",
                    imageRes = null,
                    isFavorite = true,
                    phone = "+7 495 495 95 95",
                    address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                    email = "ELukashin@practicum.ru"
                )
            )
        }
    }

    @Composable
    fun ContactDetails(contact: Contact) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(contact)
            Row {
                Text(
                    style = MaterialTheme.typography.h6,
                    text = contact.name
                )
                Text(
                    style = MaterialTheme.typography.h6,
                    text = " ${contact.surname.orEmpty()}"
                )
            }
            Row(
                modifier = Modifier.padding(bottom = 50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    style = MaterialTheme.typography.h5,
                    text = contact.familyName
                )
                if (contact.isFavorite) androidx.compose.foundation.Image(
                    modifier = Modifier.padding(start = 8.dp),
                    painter = painterResource(android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
            InfoRow(stringResource(R.string.phone), contact.phone)
            InfoRow(stringResource(R.string.address), contact.address)
            contact.email?.let {
                InfoRow(stringResource(R.string.email), contact.email)
            }
        }
    }

    @Composable
    fun Image(contact: Contact) {
        if (contact.imageRes == null) {
            Box(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .size(width = 100.dp, height = 66.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.matchParentSize(),
                    painter = painterResource(R.drawable.circle),
                    contentDescription = null,
                    tint = Color.LightGray
                )
                Text(
                    style = MaterialTheme.typography.h6,
                    text = "${contact.name.take(1)}${contact.familyName.take(1)}"
                )
            }
        } else {
            androidx.compose.foundation.Image(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(width = 100.dp, height = 66.dp),
                painter = painterResource(contact.imageRes),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
    }

    @Composable
    fun InfoRow(title: String, text: String) {
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                text = title,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp),
                text = text
            )
        }
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreviewFirst() {
        ContactDetails(
            Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "ELukashin@practicum.ru"
            )
        )
    }

    @Preview(name = "portrait", showSystemUi = true)
    @Composable
    fun ContactDetailsPreviewSecond() {
        ContactDetails(
            Contact(
                name = "Василий",
                surname = null,
                familyName = "Кузякин",
                imageRes = R.drawable.img,
                isFavorite = false,
                phone = "---",
                address = "Ивановская область, дер. Крутово, д. 4",
                email = null
            )
        )
    }

}