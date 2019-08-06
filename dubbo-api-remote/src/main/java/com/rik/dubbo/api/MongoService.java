package com.rik.dubbo.api;

import java.io.Serializable;

import com.rik.dubbo.result.JsonResult;

public interface MongoService extends Serializable{
	public boolean insert(String insertJson, String collectionName);
	public boolean insertMulti(String insertJson, String collectionName);
	public boolean delete(String delJson, String collectionName);
	public boolean drop(String collectionName);
	/**@param query  the selection criteria for the update
	    @param update the modifications to apply
	    @param upsert when true, inserts a document if no document matches the update query criteria
	    @param multi  when true, updates all documents in the collection that match the update query criteria, otherwise only updates one
	*/
	public boolean update(String query, String update, Boolean upsert, Boolean multi, String collectionName);
	public JsonResult find(String findJson, String collectionName);
	public JsonResult aggregate(String pipelineJson, String collectionName);
}
