package com.sns.dropcat.replyfomat;

//package return result
public class Result {
	private boolean flag;  //執行結果
    private String message; //結果訊息
    private Object data; //返回數據
    
    // 新增無參構造器
    public Result() {
    }
    
    
	public Result(boolean flag, String message, Object data) {
		super();
		this.flag = flag;
		this.message = message;
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
	
	// 新增成功的方法
	 public static Result success(Object data)
	    {
	        return new Result(true, "success", data);
	    }
	    public static Result success()
	    {
	        return new Result(true, "success", null);
	    }
	    
	
	
	  // 新增
	    public static Result error(String msg)
	    {
	        return new Result(false,msg,null);
	    }
	
	
	
	
	
    
}
