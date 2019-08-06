package com.rik.dubbo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import redis.clients.jedis.JedisPool;

import com.mongodb.AggregationOptions;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.rik.dubbo.api.DataSrcService;
import com.rik.dubbo.api.FormModelService;
import com.rik.dubbo.vo.FormModelViewVo;

@Configuration
@Component
public class GenerageUtil {
	
	@Autowired
	public FormModelService formService;
	
	@Autowired
	public DataSrcService dataSrcService;
	
	public static GenerageUtil generageUtil;
	
	public void init(){
		generageUtil =this;
	}
	
	public void test(DB db){
		/*测试执行mongo聚合函数
		   db.inventory.aggregate([{$group: {_id: null, total: { $sum: "$qty" }}}])
		   db.inventory.insertMany( [
			   { item: 'canvas', qty: 100, size: { h: 28, w: 35.5, uom: 'cm' }, status: 'A' },
			   { item: 'journal', qty: 25, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' },
			   { item: 'mat', qty: 85, size: { h: 27.9, w: 35.5, uom: 'cm' }, status: 'A' },
			   { item: 'mousepad', qty: 25, size: { h: 19, w: 22.85, uom: 'cm' }, status: 'P' },
			   { item: 'notebook', qty: 50, size: { h: 8.5, w: 11, uom: 'in' }, status: 'P' },
			   { item: 'paper', qty: 100, size: { h: 8.5, w: 11, uom: 'in' }, status: 'D' },
			   { item: 'planner', qty: 75, size: { h: 22.85, w: 30, uom: 'cm' }, status: 'D' },
			   { item: 'postcard', qty: 45, size: { h: 10, w: 15.25, uom: 'cm' }, status: 'A' },
			   { item: 'sketchbook', qty: 80, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' },
			   { item: 'sketch pad', qty: 95, size: { h: 22.85, w: 30.5, uom: 'cm' }, status: 'A' }
			] );
		   db.inventory.updateOne({ item: 'paper' },{$set: { 'size.uom': 'cm', status: 'P' },$currentDate: { lastModified: true }})
		   db.geo.find({polygons:{$geoIntersects:{$geometry:{"type" : "Point","coordinates" : [113.330908,23.155678] }}}})
		 */
		if (null == db) return;
		List<DBObject> pipeline = new ArrayList<DBObject>();
		DBObject group = (DBObject) JSON.parse("{$group:{_id:'total',total:{$sum:'$qty'}}}");
		pipeline.add(group);
		//		通过集合函数，获取到数据库游标，在游标上进行操作；
		Cursor cursor = db.getCollection("inventory").aggregate(pipeline,AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build());
		//		遍历游标打印函数结果集
		while (cursor.hasNext()) {
			DBObject dbObject = (DBObject) cursor.next();
			System.out.println(dbObject.toMap().toString());
		}
		//		批量插入
//		DBObject[] dbObjects = new DBObject[10];
//		dbObjects[0] = (DBObject) JSON.parse("{ item: 'canvas', qty: 100, size: { h: 28, w: 35.5, uom: 'cm' }, status: 'A' }");
//		dbObjects[1] = (DBObject) JSON.parse("{ item: 'journal', qty: 25, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' }");
//		dbObjects[2] = (DBObject) JSON.parse("{ item: 'mat', qty: 85, size: { h: 27.9, w: 35.5, uom: 'cm' }, status: 'A' }");
//		dbObjects[3] = (DBObject) JSON.parse("{ item: 'mousepad', qty: 25, size: { h: 19, w: 22.85, uom: 'cm' }, status: 'P' }");
//		dbObjects[4] = (DBObject) JSON.parse("{ item: 'notebook', qty: 50, size: { h: 8.5, w: 11, uom: 'in' }, status: 'P' }");
//		dbObjects[5] = (DBObject) JSON.parse("{ item: 'paper', qty: 100, size: { h: 8.5, w: 11, uom: 'in' }, status: 'D' }");
//		dbObjects[6] = (DBObject) JSON.parse("{ item: 'planner', qty: 75, size: { h: 22.85, w: 30, uom: 'cm' }, status: 'D' }");
//		dbObjects[7] = (DBObject) JSON.parse("{ item: 'postcard', qty: 45, size: { h: 10, w: 15.25, uom: 'cm' }, status: 'A' }");
//		dbObjects[8] = (DBObject) JSON.parse("{ item: 'sketchbook', qty: 80, size: { h: 14, w: 21, uom: 'cm' }, status: 'A' }");
//		dbObjects[9] = (DBObject) JSON.parse("{ item: 'sketch pad', qty: 95, size: { h: 22.85, w: 30.5, uom: 'cm' }, status: 'A' }");
//		
//		WriteResult insertResult = db.getCollection("inventory").insert(Arrays.asList(dbObjects));
//		System.out.println(insertResult.wasAcknowledged()?"写入成功":"没有写入成功！");
//		
//		DBObject querySET = (DBObject) JSON.parse("{item: 'journal'}"),
//				updateSET = (DBObject) JSON.parse("{$set: { 'size.uom': 'sm', status: 'P',},$currentDate: { lastModified: true }}");
//		WriteResult updateResult = db.getCollection("inventory").update(querySET, updateSET);
//		System.out.println(updateResult.isUpdateOfExisting()?"更新成功":"没有更新成功！");
//		
//		db.getCollection("inventory").remove((DBObject) JSON.parse("{item: 'journal'}"));
//		db.getCollection("inventory").drop();
//		
//		DBObject queryFind = (DBObject) JSON.parse("{}");
//		DBCursor dbCursor = db.getCollection("inventory").find(queryFind);
//		while (dbCursor.hasNext()) {
//			DBObject dbObt = (DBObject) dbCursor.next();
//			System.out.println(dbObt.toMap().toString());
//		}
	}
	
	public void test(String tableName){
		try {
			if (null == tableName) return;
			FormModelViewVo formModelViewVo = new FormModelViewVo();
			formModelViewVo.setFormName(tableName);
			System.out.println("数据大小："+generageUtil.formService.getList(formModelViewVo).size());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("formService自动注入无效!");
			return;
		}
		
	}
	
	public void test(JedisPool jedisPool){
		if (null == jedisPool) return ;
		try {
			System.out.println(jedisPool.getResource().info());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static <E> boolean isLoop(Node<E> node){
		int count=0;
		if (node == null || node.next == null) {
			return false;
		}
		
		Node<E> first = node,second = node;
		while (second!= null && second.next != null && second.next.next !=null) {
			count++;
			first = first.next;
			second = second.next.next;
			System.out.print("F:"+first.e+"~S:"+second.e +"--");
			if (first== second) {
				System.out.println("|= count："+count);
				return true;
			}
		}
		return false;
	}
	
	public static <E> void show(Node<E> node){
		int count=0;
		List<Node<E>> list = new ArrayList<Node<E>>();
		System.out.print(node.e);
		while (node.next != null) {
			node = node.next;
			if (!list.contains(node)) {
				System.out.print("->"+node.e);
				list.add(node);
				count++;
			}else {
				System.out.println("....这是一个闭环("+count+")");
				break;
			}
		}
	}
	
	public static <E> void reverse(Node<E> node, Node<E> nx){
    	if (node == null) return ;
    	Node<E> next = node.getNext();
    	node.setNext(nx);
    	reverse(next, node);
    }
    
    public static <E> void print(Node<E> node){
    	System.out.print(node.e+"->");
    	if (node.getNext() != null) print(node.getNext());
    }
	
	public static <E> void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(generageUtil.formService.toString());
		System.out.println(RequestMethod.GET);
	}

}
