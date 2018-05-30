diff --git a/ImgAnnotator/src/main/java/cn/joker/service/Json/JsonServices.java b/ImgAnnotator/src/main/java/cn/joker/service/Json/JsonServices.java
new file mode 100644
index 0000000..d82e81b
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/service/Json/JsonServices.java
@@ -0,0 +1,18 @@
+package cn.joker.service.Json;
+
+import java.io.File;
+import java.io.IOException;
+import java.util.List;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 22:19 2018/3/20
+ */
+public interface JsonServices {
+    void CheckJson(List<File> file, String name, String path);
+
+    boolean ModifyJson(String jsonStr, String name, String path);
+
+    boolean AddJson(String jsonStr, String name) throws IOException;
+}
