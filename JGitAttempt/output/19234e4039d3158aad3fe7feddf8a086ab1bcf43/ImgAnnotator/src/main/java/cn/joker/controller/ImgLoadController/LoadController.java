diff --git a/ImgAnnotator/src/main/java/cn/joker/controller/ImgLoadController/LoadController.java b/ImgAnnotator/src/main/java/cn/joker/controller/ImgLoadController/LoadController.java
new file mode 100644
index 0000000..69c3563
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/controller/ImgLoadController/LoadController.java
@@ -0,0 +1,109 @@
+package cn.joker.controller.ImgLoadController;
+
+import cn.joker.service.Load.LoadImg;
+import cn.joker.serviceImpl.LoadImpl.LoadImgImpl;
+import org.json.JSONObject;
+import org.springframework.web.bind.annotation.RequestMapping;
+import org.springframework.web.bind.annotation.RequestMethod;
+import org.springframework.web.bind.annotation.RestController;
+
+import javax.servlet.http.HttpServletRequest;
+import javax.servlet.http.HttpServletResponse;
+import java.io.IOException;
+import java.io.PrintWriter;
+import java.util.ArrayList;
+import java.util.Map;
+
+/**
+ * @author: pis
+ * @description: 加载主界面数据
+ * @date: create in 9:58 2018/3/20
+ */
+@RestController
+public class LoadController {
+    @RequestMapping(method = RequestMethod.GET, value = "/Worker/loadMain")
+    public void LoadMain(HttpServletRequest request, HttpServletResponse response) throws IOException {
+        String resourcePath = System.getProperty("user.dir") + "/resources/static/images/Marked";
+
+
+        ArrayList<String> imgURLs = new ArrayList<>();
+        String polyPath = resourcePath + "/Polygon";
+        String rectanglePath = resourcePath + "/Rectangle";
+        String totalPath = resourcePath + "/Total";
+
+        response.setContentType("application/json; charset=utf-8");
+
+
+        LoadImg LoadImg = new LoadImgImpl();
+        int imgNum = LoadImg.loadImg(0, imgURLs, totalPath, 4);
+        imgNum += LoadImg.loadImg(0, imgURLs, rectanglePath, 4);
+        imgNum += LoadImg.loadImg(0, imgURLs, polyPath, 4);
+
+        JSONObject jsonObject = new JSONObject();
+        jsonObject.put("ImgNum", imgNum);
+        jsonObject.put("ImgURL", imgURLs);
+
+        PrintWriter writer = response.getWriter();
+        writer.append(jsonObject.toString());
+        writer.close();
+
+
+    }
+
+    @RequestMapping(method = RequestMethod.GET, value = "/Worker/loadUnMarked")
+    public void LoadUnMarked(HttpServletRequest request, HttpServletResponse response) throws IOException {
+        String resourcePath = System.getProperty("user.dir") + "/resources/static/images/UnMarked/";
+
+        ArrayList<String> imgURLs = new ArrayList<>();
+
+        Map<String, String[]> map = request.getParameterMap();
+        String page = map.get("page")[0];
+        int passNum = (Integer.valueOf(page) - 1) * 12;
+        String type = map.get("type")[0];
+        resourcePath += type;
+
+        response.setContentType("application/json; charset=utf-8");
+
+
+        LoadImg LoadImg = new LoadImgImpl();
+        int imgNum = LoadImg.loadImg(0, imgURLs, resourcePath, 12, passNum);
+
+        JSONObject jsonObject = new JSONObject();
+        jsonObject.put("ImgNum", imgNum);
+        jsonObject.put("ImgURL", imgURLs);
+
+        PrintWriter writer = response.getWriter();
+        writer.append(jsonObject.toString());
+        writer.close();
+
+
+    }
+
+    @RequestMapping(method = RequestMethod.GET, value = "/Worker/loadMarked")
+    public void LoadMarked(HttpServletRequest request, HttpServletResponse response) throws IOException {
+        String resourcePath = System.getProperty("user.dir") + "/resources/static/images/Marked/";
+
+        ArrayList<String> imgURLs = new ArrayList<>();
+
+        Map<String, String[]> map = request.getParameterMap();
+        String page = map.get("page")[0];
+        int passNum = (Integer.valueOf(page) - 1) * 12;
+        String type = map.get("type")[0];
+        resourcePath += type;
+
+        response.setContentType("application/json; charset=utf-8");
+
+
+        LoadImg LoadImg = new LoadImgImpl();
+        int imgNum = LoadImg.loadImg(0, imgURLs, resourcePath, 12, passNum);
+
+        JSONObject jsonObject = new JSONObject();
+        jsonObject.put("ImgNum", imgNum);
+        jsonObject.put("ImgURL", imgURLs);
+        PrintWriter writer = response.getWriter();
+        writer.append(jsonObject.toString());
+        writer.close();
+
+
+    }
+}
