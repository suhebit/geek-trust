package com.assignment.news.data.dto

import com.google.gson.annotations.SerializedName

/**
 * Represents an error response from the news API.
 *
 * @property status The status of the response.
 * @property code The error code.
 * @property message The error message.
 */
data class ApiErrorResponse(
    @SerializedName("status") val status: String,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String
)
