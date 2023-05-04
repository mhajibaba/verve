package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This entity uses for showing result of goal 3
 * @param appId  an identifier of the application showing the impression.
 * @param countryCode a 2-letter code for the country. It doesn't necessarily comply to any standard like ISO 3166.
 * @param ids list of top 5 advertiser ids with the highest revenue per impression rate in this application and country.
 */
class AppInfGoal3 (@JsonProperty("app_id")
                   var appId: Int,
                   @JsonProperty("country_code")
                   var countryCode: String,
                   @JsonProperty("recommended_advertiser_ids")
                   var ids: List[Int]) extends Serializable {

}
