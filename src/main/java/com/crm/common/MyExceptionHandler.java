package com.crm.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/***
 * 
 * @author jacy
 *
 */
//异常捕获类
@ControllerAdvice
public class MyExceptionHandler {

//	@Autowired
//	private Properties errConfig;
	
	@ExceptionHandler(value = Exception.class)//捕捉所有的异常
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//返回httpstatus 500
	@ResponseBody//json格式输出
    public MyResEntity defaultErrorHandler(HttpSession session,HttpServletRequest request, Exception e) throws IOException  {
		MyResEntity my=new MyResEntity();
		
		if(e instanceof BaqiException){
			BaqiException be=(BaqiException)e;
			my.setRetcode(String.valueOf(be.getCode()));
			String errMsg=be.getMessage();
			my.setErrMsg(errMsg);
		}
		
		//请求uri
		String uri=request.getRequestURI();
		//请求参数
		Enumeration<String> enu=request.getParameterNames();
		StringBuilder param=new StringBuilder();
		while(enu.hasMoreElements()){
			String paraName=(String)enu.nextElement();
			param.append(paraName+"-"+request.getParameter(paraName)+";");  
		}
		return my;
	}
	
	public String getExceptionStackTrace(Throwable anexcepObj){  
        StringWriter sw = null;  
        PrintWriter printWriter = null;  
        try{  
            if(anexcepObj != null){  
                sw = new StringWriter();  
                printWriter = new PrintWriter(sw);  
                anexcepObj.printStackTrace(printWriter);  
                printWriter.flush();  
                sw.flush();  
                return sw.toString();  
            }  
            else  
                return null;  
        }finally {  
            try{  
                if(sw != null)  
                    sw.close();  
                if(printWriter != null)  
                    printWriter.close();  
            }  
            catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
}
