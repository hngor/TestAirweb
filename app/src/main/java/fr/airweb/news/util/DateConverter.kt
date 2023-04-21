package fr.airweb.news.util

import java.text.SimpleDateFormat

class DateConverter {
    companion object {
        fun dateToTimestamp(dateString: String): Long {
            val formatter = SimpleDateFormat(Constants.DATE_FORMAT)
            val date = formatter.parse(dateString)

            return date?.time ?: 0
        }
    }
}