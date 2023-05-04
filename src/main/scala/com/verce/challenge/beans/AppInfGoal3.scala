package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This entity uses for showing result of goal 3
 * @param appId
 * @param countryCode
 * @param ids
 */
class AppInfGoal3 (@JsonProperty("app_id")
                   var appId: Int,
                   @JsonProperty("country_code")
                   var countryCode: String,
                   @JsonProperty("recommended_advertiser_ids")
                   var ids: List[Int]) extends Serializable {

}
