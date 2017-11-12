package kafka

class User {
  private var userName:String="";
   private var age:Int=0;
  private var addreess:String="";
  
    
  //¹¹ÔìÆ÷
    def this(userName:String,age:Int)={
    this();
     this.userName=userName;
      this.age=age;
    
    }
    
  def this(userName:String,age:Int,addreess:String)={
    this();
     this.userName=userName;
    this.age=age;
    this.addreess=addreess;
  }
  
  def getUserName():String={
    
    return this.userName
  }
}