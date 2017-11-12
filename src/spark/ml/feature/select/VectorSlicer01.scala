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


object VectorSlicer01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("VectorSlicer01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    val sparkSession= SparkSession.builder.config(sparkConf).getOrCreate();
    
    import sparkSession.implicits;
    
 val data = Arrays.asList(
  Row(Vectors.dense(-1.0, 4.3, 3.0)),
  Row(Vectors.dense(-2.0, 2.3, 0.0))
 )
 




val defaultAttr = NumericAttribute.defaultAttr

val attrs = Array("f1", "f2", "f3").map( word=>defaultAttr.withName(word));
 
val attrGroup = new AttributeGroup("userFeatures", attrs.asInstanceOf[Array[Attribute]])


val a=Array(attrGroup.toStructField());

val dataset = sparkSession.createDataFrame(data, StructType(a))

val slicer = new VectorSlicer().setInputCol("userFeatures").setOutputCol("features")

slicer.setIndices(Array(1))
// or slicer.setIndices(Array(1, 2)), or slicer.setNames(Array("f2", "f3"))

val output = slicer.transform(dataset)
output.show(false)
    
   }
    }