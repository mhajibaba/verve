package com.verce.challenge.beans

import com.fasterxml.jackson.annotation.JsonProperty

import java.lang.String.format

class Click (@JsonProperty("impression_id")
             var impressionId: String,
             @JsonProperty("revenue")
             var revenue: Double) extends Serializable{

  override def toString: String = format("impression id = %s, revenue = %f", impressionId, revenue)

}
