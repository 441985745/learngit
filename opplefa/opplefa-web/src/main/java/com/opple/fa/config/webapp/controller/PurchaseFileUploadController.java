package com.opple.fa.config.webapp.controller;

/*
 import com.opple.fa.config.entity.FileUpload;
 import com.opple.fa.config.service.FileUploadService;*/

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.opple.fa.config.entity.PurchaseOrderFile;
import com.opple.fa.config.service.PurchaseOrderFileService;
import com.opple.fa.webapp.controller.BaseOpenApiController;
import com.opple.security.SessionSecurityConstants;
import com.opple.util.Env;

/**
 * Created by lk on 10/24/16.
 */
@Controller
@RequestMapping("/fileUpload")
public class PurchaseFileUploadController extends BaseOpenApiController {
	@Resource
	private PurchaseOrderFileService purchaseOrderFileService;

	/*
	 * @RequestMapping("/searchFileUploads") public JSONObject
	 * searchFileUploads(Model model, HttpServletRequest req, Long offset, Long
	 * pageSize, FileUpload fileUpload) { Pager<FileUpload> pager = new
	 * Pager<FileUpload>(); pager.setUri(req.getRequestURI());
	 * pager.setOffset(null == offset ? pager.getOffset() : offset);
	 * pager.setPageSize(null == pageSize ? pager.getPageSize() : pageSize);
	 * 
	 * Pager<FileUpload> fileUploadsPager =
	 * fileUploadService.searchFileUploads(pager, fileUpload);
	 * fileUploadsPager.setUri(req.getRequestURI());
	 * //model.addAttribute("fileUploadsPager", fileUploadsPager); return
	 * this.pagerToDataGridObject(fileUploadsPager); }
	 */

	@RequestMapping(value = "/deletePurchaseOrderFilesByIds", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deletePurchaseOrderFilesByIds(String ids, String fileUIds) {
		JSONObject jso = new JSONObject();
		String[] uIds = fileUIds.split(",");
		for (int i = 0; i < uIds.length; i++) {
			String absolutePath = Env.getProperty(Env.FILE_UPLOAD_URL) + uIds[i];
			File file = new File(absolutePath);
			if (file.exists()) {
				file.delete();
			}
		}
		Integer n = purchaseOrderFileService.deletePurchaseOrderFilesByIds(ids);
		// model.addAttribute("purchaseOrderFilesPager",
		// purchaseOrderFilesPager);
		// return this.pagerToDataGridObject(purchaseOrderFilesPager);
		if (n > 0) {
			jso.put("success", "删除成功，共删除" + n + "行");
		}
		return jso;
	}

	@RequestMapping(value = "/searchPurchaseOrderFilesList", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray searchPurchaseOrderFilesList(
			PurchaseOrderFile purchaseOrderFile) {

		List<PurchaseOrderFile> purchaseOrderFiles = purchaseOrderFileService
				.searchPurchaseOrderFilesList(purchaseOrderFile);
		// model.addAttribute("purchaseOrderFilesPager",
		// purchaseOrderFilesPager);
		// return this.pagerToDataGridObject(purchaseOrderFilesPager);
		if (purchaseOrderFiles == null) {
			return null;
		} else {
			JsonConfig config = new JsonConfig();
			config.setIgnoreDefaultExcludes(false);
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			// 只要设置这个数组，指定过滤哪些字段。
			config.setExcludes(new String[] {"createDate", "updateDate"});
			net.sf.json.JSONArray jsonArr = net.sf.json.JSONArray.fromObject(
					purchaseOrderFiles, config);
			return jsonArr;
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addUser(@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request) {

		for (int i = 0; i < files.length; i++) {
			System.out.println("fileName---------->"
					+ files[i].getOriginalFilename());

			if (!files[i].isEmpty()) {
				int pre = (int) System.currentTimeMillis();
				try {
					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream("D:/"
							+ new Date().getTime()
							+ files[i].getOriginalFilename());
					// 拿到上传文件的输入流
					FileInputStream in = (FileInputStream) files[i]
							.getInputStream();

					// 以写字节的方式写文件
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
					int finaltime = (int) System.currentTimeMillis();
					System.out.println(finaltime - pre);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("上传出错");
				}
			}
		}
		return "/success";
	}

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 */
	@RequestMapping(value = "/importFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JSONObject importFile(MultipartHttpServletRequest request,
			HttpSession session, PurchaseOrderFile orderFile, String fileN) {
		// String result="fail";
		JSONObject json = new JSONObject();
		json.put("success", "上传失败");
		String userName = (String) session
				.getAttribute(SessionSecurityConstants.KEY_USER_NICK_NAME);
		String userId = (String) session
				.getAttribute(SessionSecurityConstants.KEY_USER_NAME);
		// 获取上传的File
		MultipartFile myfile = request
				.getFile(fileN == null || "" == fileN ? "fileToUpload" : fileN);
		String upFileName = myfile.getOriginalFilename();
		Integer index = upFileName.lastIndexOf(".") + 1;
		String fileType = upFileName.substring(index, upFileName.length());
		String fName = upFileName.substring(0, index - 1);
		String fileUId = UUID.randomUUID().toString() + "." + fileType; // ;new Date().getTime() + upFileName;
		FileOutputStream out = null;
		String path = null;
		/*
		 * File directory = new File("."); try { path =
		 * directory.getCanonicalPath();//获取当前路径 } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		// path +="\\fileUpLoad";
		path = Env.getProperty(Env.FILE_UPLOAD_URL);
		File fileTo = new File(path);
		if (!fileTo.exists()) {
			try {
				fileTo.mkdirs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			fileTo = new File(path + "\\" + fileUId); // 
			out = new FileOutputStream(fileTo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream in = null;
		// Map<String, Object> jsonMap = new HashMap<String, Object>();

		try {
			// System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			// int byteread = 0;
			in = myfile.getInputStream();
			// ReadFromFile.showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((in.read(tempbytes)) != -1) {
				out.write(tempbytes);
				// System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
					out.close();
					orderFile.setCreateBy(userName);
					orderFile.setCreateByCode(userId);
					orderFile.setFileUId(fileUId);
					orderFile.setFileName(fName);
					orderFile.setFileType(fileType);
					purchaseOrderFileService.save(orderFile);
					// orderFile.setFileUId(fileUId);
					// orderFile.setCreateDate(new Date());
					json.put("success", "上传成功");
				} catch (IOException e1) {
					e1.printStackTrace();
					// int i = 0;
				}
			}
		}
		return json;
	}

	@RequestMapping(value = "/downloadModel", method = RequestMethod.GET)
	public void downloadModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileUId = request.getParameter("fileUId");
		String fileName = request.getParameter("fileName");
		String path = Env.getProperty(Env.FILE_UPLOAD_URL) + fileUId;
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//String ctxPath = Env.getProperty(Env.KEY_DYNAMIC_URL);
		String downLoadPath = path; // ctxPath +
		/*String realName = path.substring(path.lastIndexOf("/") + 1,
				path.length());*/
		long fileLength = new File(downLoadPath).length();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(fileName.getBytes("utf-8"), "ISO8859-1"));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(downLoadPath));

		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}

}
