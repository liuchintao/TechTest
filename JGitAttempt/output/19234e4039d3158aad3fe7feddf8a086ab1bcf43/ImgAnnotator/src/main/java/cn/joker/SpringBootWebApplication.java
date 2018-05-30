diff --git a/ImgAnnotator/src/main/java/cn/joker/SpringBootWebApplication.java b/ImgAnnotator/src/main/java/cn/joker/SpringBootWebApplication.java
new file mode 100644
index 0000000..09da21c
--- /dev/null
+++ b/ImgAnnotator/src/main/java/cn/joker/SpringBootWebApplication.java
@@ -0,0 +1,28 @@
+package cn.joker;
+/**
+ * @author:pis
+ * @description: program starter
+ * @date: 23:52 2018/3/16
+ */
+
+import org.springframework.boot.SpringApplication;
+import org.springframework.boot.autoconfigure.SpringBootApplication;
+import org.springframework.web.bind.annotation.RestController;
+
+@RestController
+@SpringBootApplication
+public class SpringBootWebApplication {
+
+
+    public static void main(String[] args) throws Exception {
+
+
+
+        SpringApplication.run(SpringBootWebApplication.class, args);
+
+    }
+
+
+
+
+}
\ No newline at end of file
