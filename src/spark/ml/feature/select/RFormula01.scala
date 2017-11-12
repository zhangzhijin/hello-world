package spark.ml.feature.select
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import scala.math._;
import scala.StringBuilder._
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql._;
import org.apache.spark.sql.types._
import org.apache.spark.ml.feature._;
import org.apache.spark.ml.attribute._;
import org.apache.spark.ml.linalg._;
import java.util.Arrays


object RFormula01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("RFormula01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
  val dataset = sparkSession.createDataFrame(Seq(
  (7, "US", 18, 1.0),
  (8, "CA", 12, 0.0),
  (9, "NZ", 15, 0.0)
)).toDF("id", "country", "hour", "clicked")


val formula = new RFormula()
  .setFormula("clicked ~ id:hour")
  .setFeaturesCol("features")
  .setLabelCol("label")

val output = formula.fit(dataset).transform(dataset)
output.show(false)
    
   }
    }