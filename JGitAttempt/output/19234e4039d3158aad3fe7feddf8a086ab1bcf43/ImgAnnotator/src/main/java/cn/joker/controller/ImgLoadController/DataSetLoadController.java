diff --git a/ImgAnnotator/src/main/java/cn/joker/controller/ImgLoadController/DataSetLoadController.java b/ImgAnnotator/src/main/java/cn/joker/controller/ImgLoadController/DataSetLoadController.java
new file mode 100644
index 0000000..b0fb2bb
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/controller/ImgLoadController/DataSetLoadController.java
@@ -0,0 +1,93 @@
+package cn.joker.controller.ImgLoadController;
+
+
+import com.google.gson.JsonArray;
+import com.google.gson.JsonObject;
+import com.google.gson.JsonParser;
+import org.json.JSONObject;
+import org.springframework.web.bind.annotation.RequestMapping;
+import org.springframework.web.bind.annotation.RequestMethod;
+import org.springframework.web.bind.annotation.RestController;
+
+import javax.servlet.http.HttpServletRequest;
+import javax.servlet.http.HttpServletResponse;
+import java.io.FileReader;
+import java.io.IOException;
+import java.io.PrintWriter;
+import java.util.ArrayList;
+import java.util.Map;
+
+/**
+ * @author: pis
+ * @description: 加载图片
+ * @date: create in 17:39 2018/3/15
+ */
+@RestController
+public class DataSetLoadController {
+
+
+    /**
+     * @author:pis
+     * @description: 返回json文件，含图片的个数和uri
+     * @date: 2018/3/20
+     */
+
+    @RequestMapping(method = RequestMethod.GET, value = "/Worker/loadImage")
+    public void LoadImg(HttpServletRequest request, HttpServletResponse response) {
+
+        Map<String, String[]> map = request.getParameterMap();
+        String type = map.get("dataSetNum")[0];
+        String offset = map.get("offset")[0];
+
+        int offsetNum = Integer.valueOf(offset) * 20;
+
+
+        ArrayList<String> imgURLs = new ArrayList<>();
+
+        String resourcePath = System.getProperty("user.dir");
+
+        String path = resourcePath + "/resources/static/dataset/" + type + "/unlabled.json";
+
+        JsonParser jsonParser = new JsonParser();
+
+
+        PrintWriter writer = null;
+        JsonObject jsonObject;
+        try {
+            writer = response.getWriter();
+            jsonObject = (JsonObject) jsonParser.parse(new FileReader(path));
+        } catch (IOException e) {
+            assert writer != null;
+            writer.write("dataSet Not Found!");
+            return;
+        }
+
+
+        JsonArray jsonArray = jsonObject.getAsJsonArray("images");
+        int imgNum = 0;
+        for (Object o : jsonArray) {
+            JsonObject object = (JsonObject) o;
+            String str = object.get("coco_url").toString();
+            if(offsetNum > 0){
+                offsetNum--;
+            }
+            else {
+                imgURLs.add(str.substring(1, str.length() - 1));
+                imgNum++;
+            }
+            if(imgNum == 20)
+                break;
+        }
+
+
+        response.setContentType("application/json; charset=utf-8");
+
+
+        JSONObject json = new JSONObject();
+        json.put("ImgNum", imgNum);
+        json.put("ImgURL", imgURLs);
+        writer.append(json.toString());
+        writer.close();
+    }
+
+}
