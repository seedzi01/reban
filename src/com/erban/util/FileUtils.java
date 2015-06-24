package com.erban.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

public class FileUtils {

//	http://www.2cto.com/kf/201203/123919.html
	
	public static String  uploadSubmit(String url,  File file) throws Exception {  
		return uploadSubmit(url, null, file);
	}
	
	/** 
     * 提交参数里有文件的数据 
     *  
     * @param url 
     *            服务器地址 
     * @param param 
     *            参数 
     * @return 服务器返回结果 
     * @throws Exception 
     */  
    public static String uploadSubmit(String url, Map<String, String> param,  
            File file) throws Exception {  
    	HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);  
  
        MultipartEntity entity = new MultipartEntity();  
        if (param != null && !param.isEmpty()) {  
            for (Map.Entry<String, String> entry : param.entrySet()) {  
                if (entry.getValue() != null  
                        && entry.getValue().trim().length() > 0) {  
                    entity.addPart(entry.getKey(),  
                            new StringBody(entry.getValue()));  
                }  
            }  
        }  
        // 添加文件参数  
        if (file != null && file.exists()) {  
            entity.addPart("imgFile", new FileBody(file));  
        }  
        post.setEntity(entity);  
        HttpResponse response = httpClient.execute(post);  
        int stateCode = response.getStatusLine().getStatusCode();  
        StringBuffer sb = new StringBuffer();  
        if (stateCode == HttpStatus.SC_OK) {  
            HttpEntity result = response.getEntity();  
            if (result != null) {  
                InputStream is = result.getContent();  
                BufferedReader br = new BufferedReader(  
                        new InputStreamReader(is));  
                String tempLine;  
                while ((tempLine = br.readLine()) != null) {  
                    sb.append(tempLine);  
                }  
            }  
        }  
        post.abort();  
//        android.util.Log.d("111", sb.toString());
        return new String(sb.toString().getBytes(), "UTF-8");
//        return sb.toString();  
    }  

}
