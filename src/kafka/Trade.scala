package kafka

class Trade {
  private var  user:User=new User();
   private var amt:Int=0;
   private var stat_date:String=""
  
  //¹¹ÔìÆ÷
    def this(user:User,amt:Int,stat_date:String)={
    this();
     this.user=user;
      this.amt=amt;
       this.stat_date=stat_date;
    
    }
    
   
}