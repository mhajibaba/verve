package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * To show output of goal 2.
 * @param appId an identifier of the application showing the impression.
 * @param countryCode a 2-letter code for the country. It doesn't necessarily comply to any standard like ISO 3166.
 * @param impressions Count of impressions
 * @param clicks Count of clicks
 * @param revenue Sum of revenue
 */
class AppInfGoal2(
            @JsonProperty("app_id")
            var appId: Int,
            @JsonProperty("country_code")
            var countryCode: String,
            var impressions : Int,
            var clicks : Int,
            var revenue : Double) extends Serializable {

}
