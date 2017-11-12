package spark.ml.sample
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature._;


object MoviceLens02 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("TFIDF01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
    val sentenceData = sparkSession.createDataFrame(Seq(
  (0.0, "Hi I heard about Spark"),
  (0.0, "I wish Java could use case classes"),
  (1.0, "Logistic regression models are neat")
)).toDF("label", "sentence")

 

val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
val wordsData = tokenizer.transform(sentenceData)

 val hashingTF = new HashingTF()
  .setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(20)

val featurizedData = hashingTF.transform(wordsData)
// alternatively, CountVectorizer can also be used to get term frequency vectors

val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
val idfModel = idf.fit(featurizedData)

val rescaledData = idfModel.transform(featurizedData)
rescaledData.show(4,false)
    
   }
    }