package com.TXST.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

public class Dom4jTest {

	public void testPaseHtml() {
		String url = "http://www.javaeye.com/";
		Document document = this.getDoc(url);// 获取document
		String gz = "//xmlns:IMG/@src";// xpath匹配
		List nodes = this.getAttr(document, gz);// 获取属性
		String[] photoUrl = this.getUrl(nodes);// 获取url
		// this.insertPhoto(this.getConn(),photoUrl);//插入图片
	}

	/**
	 * 获取属性
	 * 
	 * @param document
	 * @param gz
	 * @return
	 */
	public List getAttr(Document document, String gz) {
		Map nameSpaces = new HashMap();
		XPath xpath = new DefaultXPath(gz);
		nameSpaces.put("xmlns", "http://www.w3.org/1999/xhtml");
//		xpath.setNamespaceContext(new SimpleNamespaceContext(nameSpaces));
		List nodes = xpath.selectNodes(document);
		return nodes;
	}

	/**
	 * 获取Document
	 * 
	 * @param url
	 * @return
	 */
	public Document getDoc(String url) {

		SAXReader reader = new SAXReader();
		Document document = null;
		try {
			document = reader.read(url);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	/**
	 * 获取图片路径
	 * 
	 * @param nodes
	 * @return
	 */
	public String[] getUrl(List nodes) {
		if (nodes == null) {
			return null;
		}
		Iterator iter = nodes.iterator();
		String[] url = new String[nodes.size()];
		for (int i = 0; iter.hasNext(); i++) {
			Attribute attribute = (Attribute) iter.next();
			url[i] = attribute.getValue();
		}
		return url;
	}

	/**
	 * 获取图片流
	 * 
	 * @param path
	 * @return
	 */
	public InputStream getInput(String path) {
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		URLConnection conn = null;
		try {
			conn = url.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conn.setDoOutput(true);

		InputStream inputStream = null;
		try {
			inputStream = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * 插图片入数据库
	 * 
	 * @param conn
	 * @param photoUrl
	 */
	public void insertPhoto(Connection conn, String[] photoUrl) {
		String sql = "insert into photo set photo = ?";
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < photoUrl.length; i++) {
				if ("".equals(photoUrl[i]))
					continue;
				InputStream inputStream = this.getInput("http://www.javaeye.com/" + photoUrl[i]);
				ps.setBinaryStream(1, inputStream, inputStream.available());
				ps.addBatch();
				inputStream.close();
			}
			ps.executeBatch();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
