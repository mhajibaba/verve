package com.verce.challenge

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.verce.challenge.beans.{AppInfGoal2, Click, Impression}
import scala.collection.parallel.CollectionConverters._


object MetricCalculator {

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def calc(impressions: List[Impression], clicks: List[Click]) = {

    /** ASSUMPTION B.1
     * We suppose each click records equals to a unique click event and we must calculate them
     * if not, we can remove duplicate records
     */
    val resClicks = clicks.groupMapReduce(_.impressionId)(x => (x.revenue, 1))((a, b) => (a._1 + b._1, a._2 + b._2))
    /*
    println("==========resClicks==========")
    println(resClicks)
    */

    /** ASSUMPTION B.2
     * we suppose each impression event has a unique id, so we must remove duplicates
     * Therefore we use distinct here
     * */
    val res = impressions.
      distinctBy(_.id).par.map(x => {
      ((x.appId, x.countryCode), resClicks.get(x.id).getOrElse((0.0, 0)))
    }).toList.groupBy(_._1).map { case (key, values) =>
      val tuple2 = values.map(_._2).reduce((a, b) => (a._1 + b._1, a._2 + b._2))
      new AppInfGoal2(key._1, key._2, values.size, tuple2._2, tuple2._1)
    }

    println(mapper.writerWithDefaultPrettyPrinter.writeValueAsString(res))
  }


}
