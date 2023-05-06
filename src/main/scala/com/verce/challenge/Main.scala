package com.verce.challenge

import java.io.File
import scala.collection.mutable.ListBuffer
import scala.sys.exit
import scala.collection.parallel.CollectionConverters._

/**
 * FUTURE WORKS:
 *    1. work on performance
 *       we must run this program over big data and found
 *       probable bottlenecks in the program and solve them
 *       2. Merge two lists
 *       I used the simplest way to merge clicks and impressions
 *       which is search on the second list for each item in the list
 *       However I use hashmap to get maximum O(n) for combination
 *       for future work I decide to find a way to merge this two lists
 *       with a better method
 *       3. Improve input data
 *       It seems we can inference missing data from existing data
 *       we can fill missing data by some machine learning methods
 *       to get better results
 *       4. Optimize memory usage
 *       Memory utilization is not concern for this challenge
 *       However, we can use some method and scala features like lazy values
 *       to use a better consumption of memory
 *
 *
 */
object Main {

  val usage =
    """
      Usage: VerveChallenge --ifile impressions_file [--ifile impressions_file] --cfile clicks_file [--cfile clicks_file]
  """

  def main(args: Array[String]) = {

    /** ============Goal 1=========== **/

    if (args.isEmpty || args.length <= 2) {
      println(usage)
      exit(1)
    }

    val lstImpressionFiles = new ListBuffer[String]()
    val lstClickFiles = new ListBuffer[String]()
    args.sliding(2, 2).toList.collect {
      case Array("--cfile", filename: String) =>
        lstClickFiles += filename
      case Array("--ifile", filename: String) =>
        lstImpressionFiles += filename
    }

    if(lstClickFiles.isEmpty) {
      println("Please provide at least one file contains Clicks")
      exit(1)
    }

    if (lstImpressionFiles.isEmpty) {
      println("Please provide at least one file contains Impressions")
      exit(1)
    }

    //println("impressions: " + lstImpressionFiles.toList)
    //println("clicks: " + lstClickFiles.toList)

    def checkFile(s: String) {
      val f = new File(s)
      if (!f.exists() || f.isDirectory) {
        println("Please provide file on path: " + s)
        exit(1)
      }
    }

    lstImpressionFiles.foreach(checkFile(_))
    lstClickFiles.foreach(checkFile(_))

    val impressions = lstImpressionFiles.flatMap(EventReader.parseImpressions(_))

    //--------------------------------------------//
    // filter waste data
    // common for goal 2 and 3 so used inPlace
    //--------------------------------------------//
    /** ASSUMPTION A.1
     * This can be supposed that each advertiser on each application is for a unique country
     * So we can improve data by concluding missing country data from other data
     * for example all records with advertise 12 and appId 5 belong to Italy ("country_code": "IT",)
     * but in some records "country_code" is null or empty, so we can inference that these records
     * has the same country code as others with the same "app_id","advertiser_id".
     * However, we don't suppose this in this challenge.
     */
    val impressionsRefined = impressions.filterInPlace(x => x.id != null && x.countryCode != null && !x.countryCode.isBlank).
      toList //convert impressions to immutable list

    /**
     * use parallel flatmap
     */
    val clicks = lstClickFiles.par.flatMap(EventReader.parseClicks(_)).
      toList //convert clicks to immutable list


    /** ASSUMPTION A.2
     * These two tasks will use as two different services
     * So performance calculated for each separately
     * We can use intermediate data for goal2 and goal3 to achieve more performance
     * but I solve this two problems separately
     */
    /** ============Goal 2=========== **/
    MetricCalculator.calc(impressionsRefined, clicks)

    /** ============Goal 3=========== **/

    AdSelector.collect(impressionsRefined, clicks)
  }

}
