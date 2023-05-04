package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

import java.lang.String.format

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
