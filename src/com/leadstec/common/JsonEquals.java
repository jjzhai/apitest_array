package com.leadstec.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonEquals {
	/**
	 * �Ƚ�����json���Ƿ���ͬ
	 * @param j1  ��һ��json��(json���в����л���)
	 * @param j2 �ڶ���json��(json���в����л���)
	 * @return �����ͱȽϽ��
	 */
	public boolean jsonEquals(String j1,String j2){
	  
	  //��json�б�ʾlist��[]�滻��{}��˼�룺ֻ������νṹ������������
	  //����ֱ���滻�����ܻᵼ��ĳЩvalue�еķ���Ҳ���滻�����ǲ�Ӱ��������Ϊj1��j2�ı仯����Ե�
	  j1 = j1.replaceAll("\\[", "{");
	  j1 = j1.replaceAll("]", "}");
	  j2 = j2.replaceAll("\\[", "{");
	  j2 = j2.replaceAll("]", "}");
	  //��json�У��ַ����͵�value�е�{},�ַ��滻������ֹ����(��û��ȥ��key�еģ���Ϊ�����ܴ��������ı�����)
	      //δת��regex��(?<=:")(([^"]*\{[^"]*)|([^"]*\}[^"]*)|([^"]*,[^"]*))(?=")
	  Pattern pattern = Pattern.compile("(?<=:\")(([^\"]*\\{[^\"]*)|([^\"]*\\}[^\"]*)|([^\"]*,[^\"]*))(?=\")");
	      j1 = cleanStr4Special(j1, pattern.matcher(j1));
	      j2 = cleanStr4Special(j2, pattern.matcher(j2));
	  //ת���ַ���value�еĿո�
	  //δת��regex:"[^",]*?\s+?[^",]*?"
	  pattern = Pattern.compile("\"[^\",]*?\\s+?[^\",]*?\"");
	      j1 = cleanStr4Space(j1, pattern.matcher(j1));
	      j2 = cleanStr4Space(j2, pattern.matcher(j2));
	      //���ڿ��԰�ȫ��ȫ����ȥ���ո�
	      j1 = j1.replaceAll(" ", "");
	      j2 = j2.replaceAll(" ", "");
	  //���õݹ鷽��
	  return compareAtom(j1,j2);
	}

	/**
	 * �Ƚ��ַ������ĵݹ鷽��
	 * @param j1
	 * @param j2
	 * @return
	 */
	private static boolean compareAtom(String j1,String j2){
	  
	  if(!j1.equals("?:\"?\"")){
	    //ȡ�������ԭ��
	    String a1 = j1.split("\\{",-1)[j1.split("\\{",-1).length-1].split("}",-1)[0];
	    String a2 = j2.split("\\{",-1)[j2.split("\\{",-1).length-1].split("}",-1)[0];
	    String j2_ = j2;
	    String a2_ = a2;
	    //ת����ԭ����
	    String i1[] = a1.split(",");
	    //��ͬ��ԭ����Ѱ����ͬ��ԭ��
	    while(!a2.startsWith(",") &&
	        !a2.endsWith(",") &&
	        a2.indexOf(":,")<0 &&
	        a2.indexOf(",,")<0
	       ){
	      //��������
	      for(String s : i1){
	        a2_ = a2_.replace(s,"");
	      }
	      //��ʱa2_ʣ�µ�ȫ�Ƕ��ţ�����������õ���i1�ĳ���-1��˵�����
	      if(a2_.length() == i1.length-1){
	        //������j1��j2���������������ܼ򵥵��滻����Ϊ����λ�ÿ�������ͬ�Ľṹ������ӵ�ǰλ��������
	        int index = 0;
	        index = j1.lastIndexOf("{" + a1 + "}");
	        j1 = j1.substring(0, index)+j1.substring(index).replace("{" + a1 + "}", "?:\"?\"");
	        index = j2.lastIndexOf("{" + a2 + "}");
	        j2 = j2.substring(0, index)+j2.substring(index).replace("{" + a2 + "}", "?:\"?\"");
	        //�ݹ�
	        return compareAtom(j1, j2);
	      }else{
	        //Ѱ����һ��ͬ��ԭ��
	        j2_ = j2_.replace("{" + a2 + "}", "");
	        a2 = j2_.split("\\{",-1)[j2_.split("\\{",-1).length-1].split("}",-1)[0];
	        a2_ = a2;
	      }
	    }
	    return false;
	  }else{
	    //�Ƚ��Ƿ���ͬ
	    return j1.equals(j2);
	  }
	}

	/**
	 * json�ַ��������ַ�����������
	 * @param j ��Ҫ�����json�ַ���
	 * @param matcher ������ʽƥ�����
	 * @return ������json��
	 */
	private static String cleanStr4Special(String j,Matcher matcher){
	  String group = "";
	  String groupNew = "";
	  while(matcher.find()){
	    group = matcher.group();
	    groupNew = group.replaceAll("\\{", "A");
	    groupNew = groupNew.replaceAll("}", "B");
	    groupNew = groupNew.replaceAll(",", "C");
	    j = j.replace(group, groupNew);
	  }
	  return j;
	}

	/**
	 * json���ַ������͵�value�еĿո�����������
	 * @param j ��Ҫ�����json�ַ���
	 * @param matcher ������ʽƥ�����
	 * @return ������json��
	 */
	private static String cleanStr4Space(String j,Matcher matcher){
	    String group = "";
	      String groupNew = "";
	      while(matcher.find()){
	          group = matcher.group();
	          groupNew = group.replaceAll(" ", "S");
	          j = j.replace(group, groupNew);
	      }
	      return j;
	}
}
