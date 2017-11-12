package spark.ml.sample
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

object MoviceLens01 {
  
   def main(args: Array[String])={
     
    var sparkConf =new SparkConf().setAppName("movice01").setMaster("local[4]");
    
    sparkConf.set("spark.testing.memory", "471859200");
    
    var sc=new SparkContext(sparkConf);
    
    
    var user_file="D:\\eclipseworkspace\\ml\\ml100k\\u.user";
    
    var userTextRDD=sc.textFile(user_file, 1);
    
    var userTextSplitRDD=userTextRDD.map(line=>line.split('|'));
    
    var userAge=userTextSplitRDD.map(fileds=>fileds(0))
    
   var occpCount= userTextSplitRDD.map(fileds=>fileds(3)).countByValue()
    
    var a=occpCount.iterator
    a.foreach(f=>println(f._1+","+f._2))
    
   }
  
}