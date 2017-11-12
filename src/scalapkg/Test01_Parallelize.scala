package scalapkg
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;
import org.apache.spark.SparkConf;
object Test01_Parallelize {
  def main(args: Array[String]) {
    
   // val logFile = "hdfs://192.168.1.105:9000/tmp/wordcount/word.txt"; // 读取hdfs上的文件
    val conf = new SparkConf().setAppName("spark01").setMaster("local[4]");
    
    conf.set("spark.testing.memory", "471859200");
    
    val sc = new SparkContext(conf);
   val data = Array(1, 2, 3, 4, 5);
    val logData = sc.parallelize(data);
    //val numAs = logData.filter(line=>line.contains("java")).count();
    //val numBs = logData.filter(line=>line.contains("c++")).count();
    
    var lines=logData.count();
    println("logData:"+lines);
     
      sc.stop();
  }
}