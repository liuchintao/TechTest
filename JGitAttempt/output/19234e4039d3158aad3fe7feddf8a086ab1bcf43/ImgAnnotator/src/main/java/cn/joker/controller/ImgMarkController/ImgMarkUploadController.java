diff --git a/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/ImgMarkUploadController.java b/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/ImgMarkUploadController.java
new file mode 100644
index 0000000..2d5c37c
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/ImgMarkUploadController.java
@@ -0,0 +1,89 @@
+package cn.joker.controller.ImgMarkController;
+
+import cn.joker.Model.ImgNoteEntity;
+import cn.joker.service.Json.JsonServices;
+import cn.joker.serviceImpl.Json.JsonServicesImpl;
+import cn.joker.utils.copyImage;
+import org.json.JSONObject;
+import org.springframework.web.bind.annotation.RequestMapping;
+import org.springframework.web.bind.annotation.RequestMethod;
+import org.springframework.web.bind.annotation.RestController;
+
+import javax.servlet.http.HttpServletRequest;
+import javax.servlet.http.HttpServletResponse;
+import java.io.*;
+
+/**
+ * @author: pis
+ * @description: 图片标注上传控制器
+ * @date: create in 18:46 2018/3/17
+ */
+@RestController
+public class ImgMarkUploadController {
+    @RequestMapping(method = RequestMethod.POST, value = "/Worker/postMark")
+    public void SaveImgMark(HttpServletRequest request, HttpServletResponse response) throws IOException {
+
+
+
+        InputStream inputStream = request.getInputStream();
+        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
+        StringBuilder jsonStr = new StringBuilder();
+        String inputStr;
+        while ((inputStr = streamReader.readLine()) != null){
+            jsonStr.append(inputStr);
+        }
+
+
+        streamReader.close();
+
+        JSONObject jsonObject = new JSONObject(jsonStr.toString());
+        ImgNoteEntity imgNoteEntity = new ImgNoteEntity();
+        imgNoteEntity.setProvider((String) jsonObject.get("provider"));
+        imgNoteEntity.setImgURL((String) jsonObject.get("imgURL"));
+        imgNoteEntity.setType((String) jsonObject.get("type"));
+
+
+        response.setContentType("text/plain; charset=utf-8");
+
+        PrintWriter writer = response.getWriter();
+
+
+        String fURI = imgNoteEntity.getImgURL();
+        fURI = System.getProperty("user.dir") + "/resources/static/" + fURI;
+
+        File imgFrom = new File(fURI);
+        String imgName = fURI.substring(fURI.lastIndexOf("/") + 1);
+        String type = imgNoteEntity.getType();
+        File imgTo = new File(System.getProperty("user.dir") + "/resources/static/images/Marked/" + type + "/" + imgName);
+        boolean tag = copyImage.copy(imgFrom, imgTo);
+
+        if (!imgFrom.delete() || !tag) {
+            writer.write("001");
+            writer.close();
+            return;
+        }
+        imgName = imgName.substring(0, imgName.lastIndexOf("."));
+        String provider = imgNoteEntity.getProvider();
+        String jsonName = provider + "-" + imgName + ".json";
+
+        String resourcePath = System.getProperty("user.dir");
+
+        String resource = resourcePath + "/resources/static/json/";
+        String jsonURL = resource + jsonName;
+
+        JsonServices jsonServices = new JsonServicesImpl();
+
+        tag = jsonServices.AddJson(jsonStr.toString(), jsonURL);
+
+        if (tag)
+            writer.write("000");
+        else
+            writer.write("001");
+
+
+        writer.close();
+    }
+
+
+}
+
