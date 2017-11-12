package spark.ml
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature._;


object StopWordsRemover01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("StopWordsRemover01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val spark= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import spark.implicits;
    
val dataSet = spark.createDataFrame(Seq(
  (0, Seq("I", "saw", "the", "red", "balloon")),
  (1, Seq("Mary", "had", "a", "little", "lamb"))
)).toDF("id", "raw")

val remover = new StopWordsRemover()
  .setInputCol("raw")
  .setOutputCol("filtered")
  
  remover.transform(dataSet).show(4,false)
 
    
   }
    }