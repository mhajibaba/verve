# Verve Challange
This project related to Job Opportunity Challenge at Verve Group!
The main challange can be found here: [Verve Data Engineering challenge](https://gist.github.com/mpasa/9a710abe1f93335fa00dddae3d6e9401)

## Requirements
This module requires the followings:

 - Java (prefered 11)
 - Scala (prefered 2.13.10)
 - sbt (prefered 1.6.2)

## Usage
You need to pass atleast two argument to run: one impression file and one click file distinguished by `--ifile` and `--cfile` respectively.
```
   VerveChallenge --ifile impressions_file [--ifile impressions_file] --cfile clicks_file [--cfile clicks_file]
```
However you can pass more than one files with any order.

examples:
```
java -jar appname.jar --ifile d:\impressions.json --cfile d:\clicks.json 
java -jar appname.jar --ifile d:\impressions.json --ifile d:\impressions2.json --cfile d:\clicks.json  --cfile d:\clicks2.json
java -jar appname.jar --ifile d:\impressions.json --cfile d:\clicks.json --ifile d:\impressions2.json --cfile d:\clicks2.json
java -jar appname.jar --ifile d:\impressions.json --cfile d:\clicks.json --ifile d:\impressions2.json --ifile d:\impressions.json
```
Please provide files in absolute path.

## Supposes

- **SUPPOSE A.1**

    This can be supposed that each advertiser on each application is for a unique country.
 So we can improve data by concluding missing country data from other data. 
 For example all records with advertise 12 and appId 5 belong to Italy ("country_code": "IT",) but in some records "country_code" is null or empty, 
 so we can inference that these records has the same country code as others with the same "app_id","advertiser_id".
 However, we don't suppose this in this challenge.

- **SUPPOSE A.2**

  I suppose these two tasks will use as two different services. So I did same method repetedly for each goals.
  We can use intermediate data for goal2 and goal3 to achieve more performance but I solve this two problems separately.
  It should better performance evaluated for each separately.
  
- **SUPPOSE B.1 and C.1**

  I suppose each click records equals to a unique click event and we must calculate them. If not, we can remove duplicate records.
  
 - **SUPPOSE B.2**
 
    I suppose in ***goal2*** each impression event has a unique id, so we must remove duplicate records with same id. Therefore we use distinct for impressions.
 
 - **SUPPOSE C.2**
 
    OPPOSITE to SUPPOSE B.2 I suppose in ***goal3*** each records in impression.json belongs to an impression (i.e. showing ad) 
  and the id used in these records is not for each show ad, so we cannot remove duplicates. 
  I did suppose B2 and C2 to be opposite to each other since I don't know enough information abou impression records.
     
     
## Future Works
In my future work I'm going to consider the followings:

1. **Performance**

    We must run this program over big data and found probable bottlenecks in the program and solve them 
    
2. **Merge two lists**

    I used the simplest way to merge clicks and impressions which is search on the second list for each item in the list. 
    However I use hashmap to get maximum O(n) for combination for future work I decide to find a way to merge this two lists with a better method.
    
3. **Improve input data**

    It seems we can inference missing data from existing data we can fill missing data by some machine learning methods to get better results.
    
4. **Optimize memory usage**

    Memory utilization is not concern for this challenge. However, we can use some method and scala features like lazy values to use a better consumption of memory.
 
