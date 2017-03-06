package com.yangyang.m3u8;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.yangyang.util.HttpGet;

public class M3U8 {

	public static void main(String[] args) {
		System.out.println(m3u8Parser("http://124.193.230.153:1863:1863/zijian.hls.video.qq.com/02AEC8A4F9B5DF38C3AFA3E8071FE79C05E2675E7AADFA06574FDDDED0B2683ABFEC8DD2A74675EA57315969EED5EF0B5488F6FCAAF8AED9E122B50824DD87AE04B0874522D22D389F8B42AF9F79855CB231A3946706A8B2/406349202.m3u8?cdncode=%2f18907E7BE0798990%2f&time=1484018233&cdn=zijian&sdtfrom=v160331&platform=880303&butype=16&scheduleflag=1&backframes=e3HJMSyMa61LI0Xck2F1g1t2_aJiDCck&backseconds=MrNmQgh5prBqUlVVCthYLj88gqkOFK1O&apptype=android&buname=qqlive&vkey=02AEC8A4F9B5DF38C3AFA3E"));
	}

	public static ArrayList<String> m3u8Parser(String m3u8_url) {
		ArrayList<String> list_ts = new ArrayList<String>();
		String m3u8 = HttpGet.ReadByGet(m3u8_url);
		byte[] by = m3u8.getBytes();
		//解析m3u8文件  
		try 
		{   
			InputStream in = new ByteArrayInputStream(by);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));   
			String line = "";   
			while((line = reader.readLine()) != null)
			{   
				if(line.startsWith("#")){   
					//这里是Metadata信息   
				}else if(line.length() >0)
				{   
					//这里是一个指向的视频流路径 ,可能是绝对地址，也可能是相对地址  
					if (line.startsWith("http")) {//如果以http开头，一定是绝对地址了  
					list_ts.add(line);  
					}else 
					{//不以http开头，是相对地址，需要进行拼接。  
						String ts_url = "";  
						String m3u8_postfixname = m3u8_url.substring(m3u8_url.lastIndexOf("/") + 1, m3u8_url.length());
						if(line.matches("\\./.*?"))
							ts_url=m3u8_url.replace(m3u8_postfixname, line.substring(line.lastIndexOf('/')+1, line.length()));
						else
							ts_url=m3u8_url.replace(m3u8_postfixname, line);
						  
						//Log.d(TAG, "往list_ts中添加的ts_url是 ： "+ts_url);  
						list_ts.add(ts_url);  
					}  
				}   
			}   
			in.close();   
			return list_ts;  
		} catch (Exception e) {   
			e.printStackTrace();   
		}   
			return null;  
	}
	
	public static void mergeFile(ArrayList<String> srcFileList, String dstFileName)
	{
		String files = srcFileList.get(0);
		for(int i = 1; i < srcFileList.size(); i++ )
			files += "|" + srcFileList.get(i);
		System.out.println(files);
		try {
			Runtime.getRuntime().exec("cmd /c start ffmpeg\\bin\\ffmpeg.exe -i \"concat:" + files + "\" -acodec copy -vcodec copy -absf aac_adtstoasc " + dstFileName +".mp4");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
