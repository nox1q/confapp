package kz.kolesateam.confapp.events.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiSpeakerData(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("fullName")
    val fullName: String?,
    @JsonProperty("job")
    val job: String?,
    @JsonProperty("photoUrl")
    val photoUrl: String?
)