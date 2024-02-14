package com.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.dropcat.pojo.ResultMessage;

// 用來測試WS 2024/1/28
public class MessageUtils {
	
	
	
	
	public static String getMessage(boolean isSystemMessage,String fromName,Object message){
        try 
        {
            ResultMessage result = new ResultMessage();
            result.setSystem(isSystemMessage);
            result.setMessage(message);
            if (fromName!=null)
            {
                result.setFromName(fromName);
            }
            else
            {
            	//把字符串转成json格式的字符串
            	 ObjectMapper mapper = new ObjectMapper();
                 return mapper.writeValueAsString(result);
            }
           
           
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return null;
        
    }

	
	
	
	
	
	
	
	
	
	
	
	

}
