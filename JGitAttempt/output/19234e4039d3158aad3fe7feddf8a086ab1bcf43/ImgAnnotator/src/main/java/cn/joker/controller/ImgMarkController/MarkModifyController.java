diff --git a/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/MarkModifyController.java b/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/MarkModifyController.java
new file mode 100644
index 0000000..66b81f6
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/controller/ImgMarkController/MarkModifyController.java
@@ -0,0 +1,78 @@
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
+ * @description: 修改标注结果
+ * @date: create in 11:14 2018/3/18
+ */
+
+@RestController
+public class MarkModifyController {
+    @RequestMapping(method = RequestMethod.POST, value = "/Worker/Modify")
+    public void ImgModify(HttpServletRequest request, HttpServletResponse response) throws IOException {
+
+
+        BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
+        StringBuilder jsonStr = new StringBuilder();
+        String inputStr;
+        while ((inputStr = streamReader.readLine()) != null) jsonStr.append(inputStr);
+
+        streamReader.close();
+        JSONObject jsonObject = new JSONObject(jsonStr.toString());
+        ImgNoteEntity imgNoteEntity = new ImgNoteEntity();
+        imgNoteEntity.setProvider((String) jsonObject.get("provider"));
+        imgNoteEntity.setImgURL((String) jsonObject.get("imgURL"));
+        imgNoteEntity.setType((String) jsonObject.get("type"));
+        imgNoteEntity.setIsMarked((String) jsonObject.get("isMarked"));
+
+        response.setContentType("text/plain; charset=utf-8");
+        PrintWriter writer = response.getWriter();
+
+        String isMark = imgNoteEntity.getIsMarked();
+
+        String fURL = imgNoteEntity.getImgURL();
+        fURL = System.getProperty("user.dir") + "/resources/static/" + fURL;
+        String imgName = fURL.substring(fURL.lastIndexOf("/") + 1);
+        if (isMark.equals("false")) {
+            File imgFrom = new File(fURL);
+            File imgTo = new File(System.getProperty("user.dir") + "/resources/static/images/UnMarked/" + imgNoteEntity.getType() + "/" + imgName);
+            boolean tag = copyImage.copy(imgFrom, imgTo);
+
+            if (!imgFrom.delete() || tag) {
+                writer.write("001");
+                writer.close();
+                return;
+            }
+        }
+
+
+        imgName = imgName.substring(0, imgName.lastIndexOf("."));
+
+        String resourcePath = System.getProperty("user.dir");
+
+        String URL = imgNoteEntity.getProvider() + "-" + imgName;
+        String resource = resourcePath + "/resources/static/json";
+        JsonServices jsonServices = new JsonServicesImpl();
+        boolean tag = jsonServices.ModifyJson(jsonStr.toString(), URL, resource);
+        if (tag)
+            writer.write("000");
+        else
+            writer.write("001");
+
+
+        writer.close();
+    }
+}
