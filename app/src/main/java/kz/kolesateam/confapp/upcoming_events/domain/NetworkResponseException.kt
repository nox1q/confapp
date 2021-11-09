package kz.kolesateam.confapp.upcoming_events.domain

const val SERVER_ERROR = "Server error"

class NetworkResponseException : Exception(SERVER_ERROR)