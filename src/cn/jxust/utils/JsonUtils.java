package cn.jxust.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
	
    public static <T> List<T>  convertToList(String json,Class<T[]> cls){
        List<T> list = null;  //目标list
        //因此需要先将其解析成对象数组，
                        //再通过Arrays.asList转换成对象List
        try {
            ObjectMapper mapper = new ObjectMapper(); 
            list = Arrays.asList(mapper.readValue(json, cls)); //执行转换
        }catch (JsonParseException e){
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
    
	@SuppressWarnings("unchecked")
	public static Map<String, Object> string2map(String s)
	{
		return (Map<String, Object>)JSONObject.fromObject(s);
	}

}