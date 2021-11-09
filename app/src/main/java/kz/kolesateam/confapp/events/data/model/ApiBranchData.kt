package kz.kolesateam.confapp.events.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiBranchData(
    @JsonProperty("id")
    val id: Int?,
    @JsonProperty("title")
    val title: String?,
    @JsonProperty("events")
    val events: List<ApiEventData>?
)