package com.yourhealth.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.yourhealth.common.service.ChbcUtils;

@Service("chbcUtils")
public class ChbcUtilsImpl implements ChbcUtils {

	@Override
	public Sort getSortBySidxAndSord(String sidx, String sord) {
		sidx = sidx.trim();
		sord = sord.trim();
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		String[] orderStrings = sidx.split(",");
		for (String o:orderStrings) {
			o = o.trim();
			String[] os = o.split(" ");
			if (os.length == 2) {
				orders.add(new Order(Sort.Direction.fromString(os[1]), os[0]));
			} else {
				orders.add(new Order(Sort.Direction.fromString(sord), os[0]));
			}
		}
		Sort sort = new Sort(orders);
		return sort;
	}

	@Override
	public String getFiletypeByMimetype(String mimetype) {
		switch (mimetype) {
			case "text/html":
				return "html";
			case "text/richtext":
				return "richtext";
			case "application/xml":
				return "xml";
			case "text/css":
				return "css";
			case "application/x-javascript":
				return "javascript";
			case "application/json":
				return "json";
			case "image/png":
				return "image/png";
			case "image/jpeg":
				return "image/jpeg";
			case "image/bmp":
				return "image/bmp";
			case "image/gif":
				return "image/gif";
			case "image/tiff":
				return "image/tiff";
			case "image/x-icon":
				return "image/x-icon";
			case "application/x-shockwave-flash":
				return "flash";
			case "application/pdf":
				return "pdf";
			case "application/msword":
			case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
				return "ms office word";
			case "application/vnd.ms-excel": 			
			case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
				return "ms office excel";
			case "application/vnd.ms-powerpoint":
			case "application/vnd.openxmlformats-officedocument.presentationml.presentation":
				return "ms office powerpoint";
			case "application/vnd.ms-project":
				return "ms project";
			case "application/x-autocad":
				return "Autocad";
			case "application/vnd.android.package-archive":
				return "android apk";
			case "application/java-archive":
				return "java archive";
			case "text/plain":
				return "text/plain";
			case "application/octet-stream":
				return "binary";
			case "audio/x-pn-realaudio":
				return "rmvb";
			case "video/x-msvideo":
				return "video/avi";
			case "video/mpeg":
				return "video/mpeg";
			case "video/mp4":
				return "video/mp4";
			case "application/zip":
				return "zip";
			case "application/x-gzip":
				return "gz";
			case "application/x-bzip2":
				return "bz2";
			default:
				return "other";
		}		
	}
}
