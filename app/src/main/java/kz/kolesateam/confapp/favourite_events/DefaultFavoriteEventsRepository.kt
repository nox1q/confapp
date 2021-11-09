package kz.kolesateam.confapp.favourite_events

import android.content.Context
import android.util.Log
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kz.kolesateam.confapp.events.data.model.ApiEventData
import java.io.FileInputStream
import java.io.IOException
import java.io.OutputStreamWriter
import java.lang.Exception
import kotlin.reflect.typeOf

private const val FILE_NAME = "favorite_events.json"

class DefaultFavoriteEventsRepository(
    private val context: Context,
    private val objectMapper: ObjectMapper
) : FavoriteEventsRepository {

    private val favoriteEvents: ArrayList<ApiEventData> = readFavoriteEventsFromFile()

    override fun saveFavoriteEvent(apiEventData: ApiEventData) {
        favoriteEvents.add(apiEventData)
        saveFavoriteEventsToFile()
    }

    override fun removeFavoriteEvent(eventId: Int) {
        val apiEventData = favoriteEvents.find { it.id == eventId } ?: return
        favoriteEvents.remove(apiEventData)
        saveFavoriteEventsToFile()
    }

    override fun getAllFavoriteEvents(): List<ApiEventData> {
        return favoriteEvents
    }

    override fun isFavorite(eventId: Int): Boolean {
        return favoriteEvents.find { it.id == eventId } != null
    }

    private fun readFavoriteEventsFromFile(): ArrayList<ApiEventData> {
        val fileInputStream: FileInputStream

        try {
            fileInputStream = context.openFileInput(FILE_NAME)
        } catch (e: Exception) {
            return arrayListOf()
        }

        val jsonString = fileInputStream?.bufferedReader()?.readLines()?.joinToString()
            ?: return arrayListOf()
        val favoriteEvents = objectMapper.readValue(
            jsonString,
            object : TypeReference<ArrayList<ApiEventData>>() {}
        )

        return favoriteEvents ?: arrayListOf()
    }

    private fun saveFavoriteEventsToFile() {
        try {
            val jsonString = objectMapper.writeValueAsString(favoriteEvents)
            val outputFile = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(outputFile)

            outputStreamWriter.write(jsonString)
            outputStreamWriter.close()
        } catch (e: IOException) {
            // pass
        }
    }
}