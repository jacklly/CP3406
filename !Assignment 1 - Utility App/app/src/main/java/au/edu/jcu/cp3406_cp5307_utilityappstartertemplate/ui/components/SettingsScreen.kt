package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.components

import android.R
import android.graphics.Color.GREEN
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.model.CitySource
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.model.City

/*Screen to display / change settings*/
@Composable
fun SettingsScreen(chosenCity: String, cityChange: (String) -> Unit) {
    CitiesList(
        citiesList = CitySource().loadCities(),
        selectedItem = chosenCity,
        citySelect = cityChange)
}

@Composable
fun CitiesList(citiesList: List<City>, selectedItem: String, citySelect: (String) -> Unit,
               modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    LazyColumn(state = listState, modifier = modifier) {
        this.items(citiesList) { city ->
            //ease of access to city as string
            val currentCity: String = LocalContext.current.getString(city.stringResourceId)
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .selectable(
                        //can only pick one city
                        selected = selectedItem == currentCity,
                        onClick = {
                            //make city pickable
                            citySelect(currentCity)
                        }
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = currentCity,
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 25.sp
                    )
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                    if (selectedItem == currentCity) {
                        Icon(
                            modifier = Modifier
                                .padding(7.dp)
                                .fillMaxHeight(),
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "selection",
                            tint = Color(GREEN)
                        )
                    }
                }
            }
        }
    }
}


