package spark.ml
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature._;


object ngram01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("ngram01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
   
val wordDataFrame = sparkSession.createDataFrame(Seq(
  (0, Array("Hi", "I", "heard", "about", "Spark")),
  (1, Array("I", "wish", "Java", "could", "use", "case", "classes")),
  (2, Array("Logistic", "regression", "models", "are", "neat"))
)).toDF("id", "words")


val ngram = new NGram().setN(2).setInputCol("words").setOutputCol("ngrams")

val ngramDataFrame = ngram.transform(wordDataFrame)
 
 ngramDataFrame.show(4,false)
    
   }
    }