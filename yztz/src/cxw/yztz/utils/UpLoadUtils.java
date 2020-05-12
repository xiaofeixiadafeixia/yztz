package cxw.yztz.utils;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpLoadUtils {
	private static DiskFileItemFactory df = new DiskFileItemFactory();
	private static ServletFileUpload sf = new ServletFileUpload(df);
	
	private UpLoadUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static Iterator<?> getIterator(HttpServletRequest req){
		try {
			return sf.parseRequest(req).iterator();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
