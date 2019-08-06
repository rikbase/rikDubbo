package com.rik.dubbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rik.dubbo.api.DataSrcService;
import com.rik.dubbo.api.FormModelService;
import com.rik.dubbo.api.IDubboRemoteService;
import com.rik.dubbo.api.MongoService;
import com.rik.dubbo.result.JsonResult;

@RestController
@RequestMapping("/dubbo/service")
public class CustomerController {
	@Autowired
	private IDubboRemoteService iDubboRemoteService;
	@Autowired
	private DataSrcService dataSrcService;
	@Autowired
	private MongoService mongoService;
	@Autowired
	private FormModelService formModelService;
	 
	@RequestMapping(value="sayHi",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String sayHi(String c){
		iDubboRemoteService.sayHello(c);
		return "²¿ÊðÍ¨¹ý²âÊÔ";
	}
	
	@RequestMapping(value="data",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String data(String c){
		JsonResult jsonResult = dataSrcService.getInfo(c);
		return jsonResult.toString();
	}
	
	@RequestMapping(value="form",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String form(String c){
		JsonResult jsonResult = new JsonResult("1", formModelService.getFormDDL(c));
		return jsonResult.toString();
	}
	
	@RequestMapping(value="dataInit",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String dataInit(String c){
		JsonResult jsonResult = dataSrcService.init(c);
		return jsonResult.toString();
	}
	
	@RequestMapping(value="mongo",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String mongo(String... param){
		JsonResult jsonResult = mongoService.find(param[0], param[1]);
		return jsonResult.toString();
	}
}
