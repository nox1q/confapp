package kz.kolesateam.confapp.events.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiEventData(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("startTime")
    val startTime: String?,
    @JsonProperty("endTime")
    val endTime: String?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("place")
    val place: String?,
    @JsonProperty("speaker")
    val speaker: ApiSpeakerData?,
    @JsonProperty("isSelected")
    @JsonIgnore
    var isSelected: Boolean = false
)