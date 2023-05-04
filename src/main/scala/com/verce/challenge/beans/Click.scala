package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

import java.lang.String.format

/**
 * This entity uses to show click records
 * Advertisement banners are displayed to users in a mobile application (app_id)
 * in a country (country code) from an advertiser (advertiser_id).
 * If the user clicks on the banner, a click event is recorded.
 * Revenue is generated only in the case of a click being triggered.
 * @param impressionId a reference to the UUID of the impression where the click was produced.
 * @param revenue the quantity of money paid by the advertiser when the click is tracked.
 */
class Click (@JsonProperty("impression_id")
             var impressionId: String,
             @JsonProperty("revenue")
             var revenue: Double) extends Serializable{

  override def toString: String = format("impression id = %s, revenue = %f", impressionId, revenue)

}
