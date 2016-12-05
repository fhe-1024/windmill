##ThreadLocal
One possible (and common) use is when you have some object that is not thread-safe,but you want to avoid synchronizing to that object(I'm lookint at you,SimpleDateFormat).Instead,give each thread its own instance of the object.  
For Example:  
  >     public class Foo{
		    // SimpleDateFormat is not thread-safe, so give one to each thread
		    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
		        @Override
		        protected SimpleDateFormat initialValue()
		        {
		            return new SimpleDateFormat("yyyyMMdd HHmm");
		        }
		    };
		    public String formatIt(Date date)
		    {
		        return formatter.get().format(date);
		    }
     }
