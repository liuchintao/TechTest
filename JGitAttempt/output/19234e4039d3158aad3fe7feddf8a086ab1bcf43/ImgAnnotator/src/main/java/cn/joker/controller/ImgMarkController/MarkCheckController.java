diff --git a/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/MarkCheckController.java b/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/MarkCheckController.java
new file mode 100644
index 0000000..0698e9e
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/MarkCheckController.java
@@ -0,0 +1,87 @@
+package cn.joker.controller.ImgMarkController;
+
+import cn.joker.service.Json.JsonServices;
+import cn.joker.serviceImpl.Json.JsonServicesImpl;
+import org.json.JSONArray;
+import org.json.JSONObject;
+import org.springframework.web.bind.annotation.RequestMapping;
+import org.springframework.web.bind.annotation.RequestMethod;
+import org.springframework.web.bind.annotation.RestController;
+
+import javax.servlet.http.HttpServletRequest;
+import javax.servlet.http.HttpServletResponse;
+import java.io.*;
+import java.util.ArrayList;
+import java.util.Map;
+
+/**
+ * @author:pis
+ * @description:查看图片标注结果
+ * @date: 9:50 2018/3/18
+ */
+@RestController
+public class MarkCheckController {
+    @RequestMapping(method = RequestMethod.GET, value = "/Worker/Check")
+    public void ImgCheck(HttpServletRequest request, HttpServletResponse response) {
+
+        Map<String, String[]> map = request.getParameterMap();
+
+        String imgURL = map.get("imgURL")[0];
+
+        String imgName = imgURL.substring(imgURL.lastIndexOf("/") + 1);
+        imgName = imgName.substring(0, imgName.lastIndexOf("."));
+
+        String provider = map.get("provider")[0];
+        ArrayList<File> jsonFiles = new ArrayList<>();
+
+        String resourcePath = System.getProperty("user.dir");
+
+        String resource = resourcePath + "/resources/static/json";
+        JsonServices jsonServices = new JsonServicesImpl();
+        imgName = provider + "-" + imgName;
+        jsonServices.CheckJson(jsonFiles, imgName, resource);
+
+        response.setContentType("application/json; charset=utf-8");
+
+        PrintWriter writer = null;
+        try {
+
+            writer = response.getWriter();
+
+
+            if (jsonFiles.size() == 0) {
+                JSONObject jsonObject = new JSONObject();
+                jsonObject.put("type", "Polygon");
+                jsonObject.put("isMarked", "false");
+                JSONArray jsonArray = new JSONArray();
+                jsonObject.put("noteRectangle", jsonArray);
+                jsonObject.put("notePolygon", jsonArray);
+                jsonObject.put("noteTotal", jsonArray);
+                jsonObject.put("imgURL", imgURL);
+                jsonObject.put("provider", provider);
+                File file = new File(resource + "/" + imgName + ".json");
+                FileWriter fileWriter = new FileWriter(file, false);
+                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
+                bufferedWriter.write(jsonObject.toString());
+                bufferedWriter.close();
+                writer.write(jsonObject.toString());
+            } else {
+                for (File file : jsonFiles) {
+                    FileReader fileReader = new FileReader(file);
+                    BufferedReader bufferedReader = new BufferedReader(fileReader);
+                    String str;
+                    while ((str = bufferedReader.readLine()) != null) {
+
+                        writer.append(str);
+                    }
+                }
+            }
+
+            writer.close();
+        } catch (Exception e) {
+            assert writer != null;
+            writer.write("File Not Found!");
+        }
+
+    }
+}
\ No newline at end of file
