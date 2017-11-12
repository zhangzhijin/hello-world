package spark.ml
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature._;


object CountVectorizer01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("CountVectorizer01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
   
val df = sparkSession.createDataFrame(Seq(
  (0, Array("a", "b", "c")),
  (1, Array("a", "b", "b", "c", "a"))
)).toDF("id", "words")


val cvModel: CountVectorizerModel = new CountVectorizer()
  .setInputCol("words")
  .setOutputCol("features")
  .setVocabSize(3)
  .setMinDF(2)
  .fit(df)

val result = cvModel.transform(df)

result.show(4,false)
    
   }
    }