package au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.data

import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.R
import au.edu.jcu.cp3406_cp5307_utilityappstartertemplate.model.Task

class Datasource() {
    fun loadTasks(): List<Task> {
        return listOf<Task>(
            Task(R.string.Task1),
            Task(R.string.Task2),
            Task(R.string.Task3),
            Task(R.string.Task4),
            Task(R.string.Task5),
            Task(R.string.Task6),
            Task(R.string.Task7),
            Task(R.string.Task8),
            Task(R.string.Task9),
            Task(R.string.Task10))
    }
}