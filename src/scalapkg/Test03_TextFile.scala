package scalapkg
import org.apache.spark.SparkContext;
import org.apache.spark.SparkContext._;
import org.apache.spark.SparkConf;
object Test03_TextFile {
  def main(args: Array[String]) {
    
     // val logFile = "hdfs://192.168.1.105:9000/tmp/wordcount/word.txt"; // 读取hdfs上的文件
    
    
    val conf = new SparkConf().setAppName("spark01").setMaster("spark://192.168.1.105:7077");
    
    val logFile ="D:\\eclipseworkspace\\spark02\\testfile\\test01.txt";
   // var conf=new SparkConf().setAppName("APP").setMaster("local");
    conf.set("spark.testing.memory", "471859200");
    
    val sc = new SparkContext(conf);
   
    val logData = sc.textFile(logFile);
     
    var lines=logData.map(s=>(s,1));
    //lines.persist();
    var length=lines.reduceByKey((a,b)=>a+b);
    var lengths=length.collect();
    lengths.foreach(s=>println(s));
   // println("logData:"+lengths);
     
      sc.stop();
  }
}