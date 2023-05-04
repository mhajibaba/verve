package com.verce.challenge

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.verce.challenge.beans.{Click, Impression}

import scala.io.Source

object EventReader {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  /** support for objects which not comply with the provided schema * */
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

  def parseImpressions(filePath: String): Array[Impression] = {

    val fileContents = Source.fromFile(filePath).mkString
    //println(fileContents.size)

    val impressions = mapper.readValue(fileContents, new TypeReference[Array[Impression]] {})
    //println(impressions.length)

    impressions
  }

  def parseClicks(filePath: String): Array[Click] = {

    val fileContents = Source.fromFile(filePath).mkString
    //println(fileContents.size)

    val clicks = mapper.readValue(fileContents, new TypeReference[Array[Click]] {})
    //println(clicks.length)

    clicks
  }
}
