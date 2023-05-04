package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

import java.lang.String.format

/**
 * This entity uses to show impression records.
 * Advertisement banners are displayed to users in a mobile application (app_id)
 * in a country (country code) from an advertiser (advertiser_id).
 * When this happens, an impression event is recorded and stored.
 * @param id a UUID that identifies the impression.
 * @param appId an identifier of the application showing the impression.
 * @param countryCode a 2-letter code for the country. It doesn't necessarily comply to any standard like ISO 3166.
 * @param advertiserId an identifier of the advertiser that bought the impression.
 */
class Impression (var id: String,
                  @JsonProperty("app_id")
                  var appId: Int,
                  @JsonProperty("country_code")
                  var countryCode: String,
                  @JsonProperty("advertiser_id")
                  var advertiserId : Int) extends Serializable{


  override def toString: String =
    format("<id = %s; app = %d; country = %s, ad = %d>", id, appId, countryCode, advertiserId)
}
