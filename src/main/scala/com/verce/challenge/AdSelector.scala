package com.verce.challenge

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.verce.challenge.beans.{AppInfGoal3, Click, Impression}
import scala.collection.parallel.CollectionConverters._

object AdSelector {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def collect(impressions: List[Impression], clicks: List[Click]) = {


    /** ASSUMPTION C.1
     * same as ASSUMPTION B.1
     */
    //this show total revenue earned by clicks for each impression
    val revenues: Map[String, Double] = clicks.groupMapReduce(_.impressionId)(x => x.revenue)(_ + _)

    /** ASSUMPTION C.2
     * OPPOSITE to ASSUMPTION B.2
     * we suppose each records in impression.json belongs to an impression (i.e. showing ad)
     * and the id used in these records is not for each show ad, so we cannot remove duplicates
     */
    val adPerAppCountryInf = impressions
      .groupBy(x => (x.appId, x.countryCode, x.advertiserId)).par.map(x => {
      val ids = x._2.collect(_.id)
      (x._1 /*key = app,country,ad */ ,
        ids.length /*num of impressions*/ ,
        ids.distinct /*total different impressions*/ )
    }).map(x => {
      val totalRevs = x._3.foldLeft(0.0)((a: Double, b: String) =>
        //revenues is a hashmap and revenues.get(b) consumes O(1) for lookup
        a + revenues.get(b).getOrElse(0.0)) //total revenues by click
      val totalImpressions = x._2 //impression rate
      (x._1, totalRevs / totalImpressions)
    }).toList.groupBy(x => (x._1._1, x._1._2)).map(x => {
      val sortedIds = x._2
        .sortWith(_._2 > _._2) //sort based the highest revenue per impression rate
        .map(_._1._3) //just need app id not the values
        .take(5) //just first 5 ids
      new AppInfGoal3(x._1._1, x._1._2, sortedIds)
    })

    println("==============")
    println(mapper.writerWithDefaultPrettyPrinter.writeValueAsString(adPerAppCountryInf))

  }

}
