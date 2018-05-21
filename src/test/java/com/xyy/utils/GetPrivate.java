package com.xyy.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xyy.baiduTranslate.TransApi;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GetPrivate {

	private static final String APP_ID = "201805180001614951";
	private static final String SECURITY_KEY = "liHWdmsoIbVgra5qMZeu";

	public static void main(String[] args) throws IOException {
		List<List<String>> list = new ArrayList<List<String>>();
		File file = new File("D:/111.txt");
		list = getChange(file);
		outToFile(list);
		System.out.println("成功");
	}
	private static List<List<String>> getChange(File file) {
		List<String> listBefore = new ArrayList<String>();
		List<String> listAfter = new ArrayList<String>();
		List<List<String>> listAll = new ArrayList<List<String>>();
	    BufferedReader reader = null;
	    String tempString = null;
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        while ((tempString = reader.readLine()) != null) {
	        	//常用
//	        	String usefull = getUseFull(tempString); //��ȡԭ�����    �ֶμ�ע��
//	        	listBefore.add(usefull);
				//获取单词
				String[] list = tempString.split("[^a-zA-Z]+");
				List<String> listLowerCase = new ArrayList<String>();
				for (int i=0; i<list.length;i++){
					String str = list[i].toLowerCase();
					listLowerCase.add(str);
				}
				listBefore.addAll(listLowerCase);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        if(reader != null){
	            try {
	                reader.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    listAll.add(listBefore);
	    listAll.add(listAfter);
		return listAll;
	}
	private static String getUseFull(String before) {
		String[] list = before.split("\\s{2,}|\t| ");
//		String lower = getAfter(list[0]);
//		String usefull = list[0].trim()+"&"+list[1].trim()+"&"+list[2].trim();
//		String usefull = list[0].trim()+"&"+list[1].trim();
		String usefull = list[0].trim();
		System.out.println(usefull);
		return usefull;
	}
	/**获取驼峰字段
	 * @param usefull
	 * @return
	 */
	private static String getAfter(String usefull) {
		String after = "";
		String[] afterList = usefull.split("_");
		for(int i=0;i<afterList.length;i++){
			StringBuilder change = new StringBuilder(afterList[i].toLowerCase());
			if(i!=0){
				change.setCharAt(0, Character.toUpperCase(change.charAt(0)));
			}
			afterList[i] = change.toString();
			after = after+afterList[i];
		}
		return after;
	}
	/**
	 *首字母也大写
	 * @param usefull
	 * @return
	 */
	private static String getAfter2(String usefull) {
		String after = "";
		String[] afterList = usefull.split("_");
		for(int i=0;i<afterList.length;i++){
			StringBuilder change = new StringBuilder(afterList[i].toLowerCase());
			change.setCharAt(0, Character.toUpperCase(change.charAt(0)));
			afterList[i] = change.toString();
			after = after+afterList[i];
		}
		return after;
	}
	private static void outToFile(List<List<String>> list) throws IOException {
		List<String> listBefore = new ArrayList<String>();
		listBefore = list.get(0);
		BufferedWriter in=null;
		in = new BufferedWriter(new FileWriter("D:/lastName2.txt",true));
		try{
			for (String string : listBefore) {
//				in.write(string);
//				in.newLine();
			}
//			getRecord(listBefore,in);
//			getDate1(listBefore,in);
//			getDate2(listBefore,in);
//			getDate3(listBefore,in);
//			getDate4(listBefore,in);
//			getDate5(listBefore,in);
//			getDate6(listBefore,in);
//			getDate7(listBefore,in);
//			getDate8(listBefore,in);
//			getDate9(listBefore,in);
//			getDate10(listBefore,in);
//			getDate11(listBefore,in);
//			getDate12(listBefore,in);
//			getDate13(listBefore,in);
//			getDate14(listBefore,in);
//			getWordsBySum(listBefore,in);
			getWordsByWord(listBefore,in);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
	        if(in != null){
	            try {
	                in.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}
	}
	private static void getDate1(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			<refData refId="txDate"/>
			in.write("<refData refId=\""+str[1]+"\"/>");
			in.newLine();
		}
	}
	private static void getDate2(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			<refData access="private" refId="errorCode"/>
			in.write("<refData access=\"private\" refId=\""+str[1]+"\"/>");
			in.newLine();
		}
	}
	private static void getDate3(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			<refData needDesc="false" constant="false" refId="txDate" fullTag="true" xmlTag="txDate"/>
			in.write("<refData needDesc=\"false\" constant=\"false\" refId=\""+str[1]+"\" fullTag=\"true\" xmlTag=\""+str[1]+"\"/>");
			in.newLine();
		}
	}
	private static void getDate4(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			 <p>平台交易日期* <input type = "text" name = "txDate" value ="3"></p> 
			in.write("<p>"+str[0]+"* <input type = \"text\" name = \""+str[1]+"\" value =\"\"></p>");
			in.newLine();
		}
	}
	private static void getDate5(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			String cityId = request.getParameter("cityId");
			in.write("String "+str[1]+" = request.getParameter(\""+str[1]+"\");//"+str[0]);
			in.newLine();
		}
	}
	private static void getDate6(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			sb.append("cityId："+cityId);
			if(i==0)
				in.write("sb.append(\""+str[1]+":\"+"+str[1]+");");
			else
				in.write("sb.append(\"|"+str[1]+":\"+"+str[1]+");");
			in.newLine();
		}
	}
	private static void getDate7(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			test|test|test
			in.write(str[1]+"|");
//			in.newLine();
		}
	}
	private static void getDate8(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			<dataElement id="AddWord"  access="private" label="退款说明"/>
			in.write("<dataElement id=\""+str[1]+"\"  access=\"private\" label=\""+str[0]+"\"/>");
			in.newLine();
		}
	}
	private static void getRecord(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
			if(i==listBefore.size()-1){
				in.write("@XStreamAlias(value = \""+str[0]+"\")\r"+"private String "+str[1]+";\t"+"//" +str[2]);
//				in.write("private String "+str[1]+";\t"+"//" +str[0]);
			}else{
				in.write("@XStreamAlias(value = \""+str[0]+"\")\r"+"private String "+str[1]+";\t"+"//" +str[2]);
//				in.write("private String "+str[1]+";\t"+"//" +str[0]);
			}
			in.newLine();
		}
	}
	private static void getDate9(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
//			String srcData = (String)context.getDataValue("");//请求原文
			in.write("String "+str[1]+" = (String)context.getDataValue(\""+str[1]+"\");//"+str[0]);
			in.newLine();
		}
	}
	private static void getDate10(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
			StringBuilder change = new StringBuilder(str[1].trim());
			change.setCharAt(0, Character.toUpperCase(change.charAt(0)));
			str[0] = getAfter(str[0]);
			StringBuilder change2 = new StringBuilder(str[0].trim());
			change2.setCharAt(0, Character.toUpperCase(change2.charAt(0)));
			String str1 = change.toString();
			String str2 = change2.toString();
//			reqDetails.setBusNo(body.getCommMsgRefId());	//	业务操作员
			in.write("reqDetails.set"+str1.trim()+"(body.get"+str2+"());"+"\t"+"//" +str[2]);
			in.newLine();
		}
	}
	private static void getDate11(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
			str[0] = getAfter(str[0]);//驼峰字段
//			<dataField name="activeUser" desc="设备某APP唯一标识符" />
			in.write("<dataField name=\""+str[0]+"\" desc=\""+str[1]+"\" />");
			in.newLine();
		}
	}
	private static void getDate12(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
			String str1 = getAfter(str[0]);//驼峰字段
//			active_user = #activeUser,
			in.write(str[0]+" = #"+str1+",");
			in.newLine();
		}
	}
	private static void getDate13(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
			str[0] = getAfter(str[0]);//驼峰字段
//			test1||test2
//			in.write("#"+str[0]+"||");
			in.write(str[0]+"||");
//			in.write(str[0]+",");
//			in.write("#"+str[0]+",");
//			in.write(str[0]+",");
//			in.newLine();
		}
	}
	private static void getDate14(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		for (int i=0;i<listBefore.size();i++) {
			String[] str = listBefore.get(i).split("&");
			str[0] = getAfter2(str[0]);
//			model.setId(equipEntry.getId());
//			in.write("#"+str[0]+"||");
			in.write("model.set"+str[0]+"(equipEntry.get"+str[0]+"());");
//			in.write(str[0]+",");
//			in.write("#"+str[0]+",");
//			in.write(str[0]+",");
			in.newLine();
		}
	}

	private static void getWordsBySum(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		List<String> words = new ArrayList<>();
		Map<String, Integer> sumWordsMap = new TreeMap<String, Integer>();
//		Map<String,Integer> sumWordsMap = new HashMap<>();
		for (int i=0;i<listBefore.size();i++) {
			String word = listBefore.get(i);
			if (!words.contains(word)) {
				words.add(word);
				sumWordsMap.put(word,1);
			} else {
				Integer sum = sumWordsMap.get(word);
				sum++;
				sumWordsMap.put(word,sum);
			}
		}
		//这里将map.entrySet()转换成list
		List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(sumWordsMap.entrySet());
		//然后通过比较器来实现排序
		Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
			//降序排序
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});

		for(Map.Entry<String,Integer> mapping:list){
			in.write(mapping.getKey()+":"+mapping.getValue());
			in.newLine();
		}
	}
	private static void getWordsByWord(List<String> listBefore, BufferedWriter in) throws IOException {
		in.write("------------------------------------------------------------------------------");
		in.newLine();
		List<String> words = new ArrayList<>();
		Map<String, Integer> sumWordsMap = new TreeMap<String, Integer>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		for (int i=0;i<listBefore.size();i++) {
			String word = listBefore.get(i);
			if (!words.contains(word)) {
				words.add(word);
				sumWordsMap.put(word,1);
			} else {
				Integer sum = sumWordsMap.get(word);
				sum++;
				sumWordsMap.put(word,sum);
			}
		}

		for(String key : sumWordsMap.keySet()){
			TransApi api = new TransApi(APP_ID, SECURITY_KEY);
//			String query = "happy";
			String str=api.getTransResult(key, "auto", "zh");
//			System.out.println(str);
			JSONObject jsonObject = JSONObject.parseObject(str);
			if(null != jsonObject.get("trans_result")){
				String str1=jsonObject.get("trans_result").toString();
				str1=str1.replace("[", "");
				str1=str1.replace("]", "");
				JSONObject json1=JSONObject.parseObject(str1);
				String zh = json1.get("dst").toString();
				in.write(key+":"+sumWordsMap.get(key) + "-->" + zh);
				in.newLine();
			}
		}
	}
	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}
}
