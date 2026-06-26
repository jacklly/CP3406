package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.ui.model

import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.R

class CitySource {
    fun loadCities(): List<City> {
        return listOf<City>(
        City(R.string.city1),
        City(R.string.city2),
        City(R.string.city3),
        City(R.string.city4),
        City(R.string.city5),
        City(R.string.city6),
        City(R.string.city7),
        City(R.string.city8),
        City(R.string.city9),
        City(R.string.city10)
        )
    }
}