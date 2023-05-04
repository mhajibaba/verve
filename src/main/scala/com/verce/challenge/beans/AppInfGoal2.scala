package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

class AppInfGoal2(
            @JsonProperty("app_id")
            var appId: Int,
            @JsonProperty("country_code")
            var countryCode: String,
            var impressions : Int,
            var clicks : Int,
            var revenue : Double) extends Serializable {

}
