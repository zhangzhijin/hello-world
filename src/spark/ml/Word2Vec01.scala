package spark.ml
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature._;


object Word2Vec01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("Word2Vec01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
   
val documentDF = sparkSession.createDataFrame(Seq(
  "Hi I heard about Spark".split(" "),
  "I wish Java could use case classes".split(" "),
  "Logistic regression models are neat".split(" ")
).map(Tuple1.apply)).toDF("text")


 
val word2Vec = new Word2Vec()
  .setInputCol("text")
  .setOutputCol("result")
  .setVectorSize(5)
  .setMinCount(0)
val model = word2Vec.fit(documentDF)

val result = model.transform(documentDF)

result.show(4,false)
    
   }
    }