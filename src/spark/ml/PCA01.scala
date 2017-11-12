package spark.ml
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.ml.feature._;
import org.apache.spark.ml.linalg._


object PCA01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("ngram01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
   
val data = Array(
  Vectors.sparse(5, Seq((1, 1.0), (3, 7.0))),
  Vectors.dense(2.0, 0.0, 3.0, 4.0, 5.0),
  Vectors.dense(4.0, 0.0, 0.0, 6.0, 7.0)
)

val df = sparkSession.createDataFrame(data.map(Tuple1.apply)).toDF("features")

val pca = new PCA()
  .setInputCol("features")
  .setOutputCol("pcaFeatures")
  .setK(3)
  .fit(df)

val result = pca.transform(df)
result.show(false)
    
   }
    }