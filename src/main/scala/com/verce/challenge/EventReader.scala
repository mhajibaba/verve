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

  /**
   * FUTURE WORK
   *
   *  1 - Use Polymorphism
   *  Use generic class to remove duplicate codes. So this class should be like this:
   *
   * val impressions = lstImpressionFiles.flatMap(EventReader.parse[Impression](_))
   * val clicks = lstClickFiles.flatMap(EventReader.parse[Click](_))
   *
      def parse[T](filePath: String): IndexedSeq[T] = {

        val fileContents = Source.fromFile(filePath).mkString
        //println(fileContents.size)

        val records = mapper.readValue(fileContents, classOf[IndexedSeq[T]])
        //println(records.length)

        records
      }
  */

  def parseImpressions(filePath: String): Array[Impression] = {

    val fileContents = Source.fromFile(filePath).mkString
    //println(fileContents.size)

    val impressions = mapper.readValue(fileContents, classOf[Array[Impression]])
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
