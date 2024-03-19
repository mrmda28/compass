package dev.jordond.compass.geocoder.web.internal

import dev.jordond.compass.Location
import dev.jordond.compass.Place
import dev.jordond.compass.geocoder.exception.NotSupportedException
import dev.jordond.compass.geocoder.web.HttpApiEndpoint
import dev.jordond.compass.geocoder.web.HttpApiPlatformGeocoder
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

internal class ForwardHttpApiPlatformGeocoder(
    private val endpoint: HttpApiEndpoint<String, List<Location>>,
    private val client: HttpClient,
) : HttpApiPlatformGeocoder {

    override fun isAvailable(): Boolean = true

    override suspend fun locationFromAddress(address: String): List<Location> {
        val url = endpoint.url(address)
        return client.makeRequest(url, endpoint::mapResponse)
    }

    override suspend fun placeFromLocation(latitude: Double, longitude: Double): List<Place> {
        throw NotSupportedException()
    }
}