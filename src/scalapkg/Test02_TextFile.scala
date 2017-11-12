package scalapkg
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;
import org.apache.spark.SparkConf;
object Test02_TextFile {
  def main(args: Array[String]) {
    
   // val logFile = "hdfs://192.168.1.105:9000/tmp/wordcount/word.txt"; // 读取hdfs上的文件
    
    
   // val conf = new SparkConf().setAppName("spark01").setMaster("spark://192.168.1.105:7077");
    
    val logFile ="D:\\eclipseworkspace\\spark02\\testfile\\test01.txt";
    var conf=new SparkConf().setAppName("APP").setMaster("local");
    conf.set("spark.testing.memory", "471859200");
    
    val sc = new SparkContext(conf);
   
    val logData = sc.textFile(logFile);
    //val numAs = logData.filter(line=>line.contains("java")).count();
    //val numBs = logData.filter(line=>line.contains("c++")).count();
    var lines=logData.count();
    println("logData:"+lines);
     
      sc.stop();
  }
}