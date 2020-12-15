/**
 * 
 */
package com.nuaa.sys.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.UUID;

import com.nuaa.sys.StarTree;

/**
 * @author mahao
 *
 */
public class PublicUtil {
	/*
	 * 增加获取树对象实现方法
	 */
	public static StarTree getStarTree(){
		return (StarTree)AppInsFactory.getBean("StarTree");
	}
	/*
	 * sql专用日期
	 */
	public static Date getSqlDate(){
		return new java.sql.Date(System.currentTimeMillis());
	}
	
	/*
	 * 日期格式字符串 2008-07-15
	 */
	public static String getDateString(){
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd");
		String curDateStr = sDF.format(new Date(System.currentTimeMillis()));
		return curDateStr;		
	}
	
	/*
	 * 全格式字符串 2008-07-15 20:57:51
	 */
	public static String getCurDateFullString(){
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDateStr = sDF.format(new Date(System.currentTimeMillis()));
		return curDateStr;		
	}
	
	/*
	 * 当前年字符串
	 */
	public static String getCurYearStr() {
		String curYearStr = "";
		SimpleDateFormat sDF = new SimpleDateFormat("yyyy");
		curYearStr = sDF.format(new Date(System.currentTimeMillis()));
		return curYearStr;
	}

	/*
	 * 当前月字符串
	 */
	public static String getCurMonthStr() {
		String curMonthStr = "";
		SimpleDateFormat sDF = new SimpleDateFormat("MM");
		curMonthStr = sDF.format(new Date(System.currentTimeMillis()));
		return curMonthStr;
	}

	/*
	 * 当前日字符串
	 */
	public static String getCurDateStr() {
		String curDateStr = "";
		SimpleDateFormat sDF = new SimpleDateFormat("dd");
		curDateStr = sDF.format(new Date(System.currentTimeMillis()));
		return curDateStr;
	}
	
	/*
	 * 当前星期字符串
	 */
	public static String getCurDayStr() {
		String curDayStr = "";
		SimpleDateFormat sDF = new SimpleDateFormat("EEE");
		curDayStr = sDF.format(new Date(System.currentTimeMillis()));
		return curDayStr;
	}
	
	/*
	 * 当前时间戳字符串
	 */
	public static Timestamp getCurDateTimestamp() {
		Timestamp curDate = new Timestamp(System.currentTimeMillis());
		return curDate;

	}
	
	/*
	 * guid()
	 */
	public static String getGuid(){
		return UUID.randomUUID().toString();
		
	}
	public  static void main(String[]args){
		System.out.println(PublicUtil.getSqlDate());//sql专用日期
		System.out.println(PublicUtil.getDateString());//日期格式字符串 2008-07-15
		System.out.println(PublicUtil.getCurDateFullString());//全格式字符串 2008-07-15 20:57:51
		System.out.println(PublicUtil.getCurYearStr());//年
		System.out.println(PublicUtil.getCurMonthStr());//月
		System.out.println(PublicUtil.getCurDateStr());//日
		System.out.println(PublicUtil.getCurDayStr());//星期
		System.out.println(PublicUtil.getCurDateTimestamp());//时间戳
		System.out.println(PublicUtil.getGuid());//guid()
	}	
}
