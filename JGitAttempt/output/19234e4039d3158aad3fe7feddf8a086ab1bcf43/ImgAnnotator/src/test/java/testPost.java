diff --git a/ImgAnnotator/src/test/java/testPost.java b/ImgAnnotator/src/test/java/testPost.java
new file mode 100644
index 0000000..0ccfd20
--- /dev/null
+++ b/ImgAnnotator/src/test/java/testPost.java
@@ -0,0 +1,77 @@
+import org.junit.Test;
+import org.springframework.http.HttpEntity;
+import org.springframework.http.HttpHeaders;
+import org.springframework.web.client.RestTemplate;
+
+/**
+ * @author: pis
+ * @description: good good study
+ * @date: create in 17:09 2018/3/22
+ */
+
+
+public class testPost {
+    /**
+    *@author:pis
+    *@description: 测试增加图片标记
+    *@date: 18:16 2018/3/22
+    */
+    @Test
+    public void testAdd(){
+        String url = "http://localhost:8080/Worker/postMark";
+        HttpHeaders requestHeaders = new HttpHeaders();
+        requestHeaders.set("Accept", "text/plain");
+        requestHeaders.set("Content-Type", "application/json");
+
+        String jsonStr = "{\"type\":\"Polygon\",\"isMarked\":\"true\",\"imgURI\":\"images/UnMarked/Polygon/000000000512.jpg\",\"provider\":\"provider\",\"noteRectangle\":[{\"author\":\"xxx\",\"top\":286,\"left\":161,\"width\":52,\"height\":37,\"mark\":\"people\",\"id\":\"2018#03#20#12#24#35\"}],\"notePolygon\":[{\"author\":\"xxx\",\"points\":[{\"x\":\"\",\"y\":\"\"}],\"mark\":\"people\",\"id\":\"2018#03#20#12#24#35\"}],\"noteTotal\":[{\"author\":\"xxx\",\"mark\":\"fghgfh\",\"id\":\"2018#03#20#12#24#35\"}]}";
+
+        RestTemplate restTemplate = new RestTemplate();
+        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonStr, requestHeaders);
+        String jsonData = restTemplate.postForObject(url, httpEntity, String.class);
+
+        System.out.println(jsonData);
+
+    }
+    /**
+    *@author:pis
+    *@description: 测试去除图片的标记
+    *@date: 18:10 2018/3/22
+    */
+    @Test
+    public void testDelete(){
+        String url = "http://localhost:8080/Worker/Modify";
+        HttpHeaders requestHeaders = new HttpHeaders();
+        requestHeaders.set("Accept", "text/plain");
+        requestHeaders.set("Content-Type", "application/json");
+
+        String jsonStr = "{\"type\":\"Polygon\",\"isMarked\":\"false\",\"imgURI\":\"images/Marked/Polygon/000000000545.jpg\",\"provider\":\"provider\",\"noteRectangle\":[{\"author\":\"xxx\",\"top\":286,\"left\":161,\"width\":52,\"height\":37,\"mark\":\"people\",\"id\":\"2018#03#20#12#24#35\"}],\"notePolygon\":[{\"author\":\"xxx\",\"points\":[{\"x\":\"\",\"y\":\"\"}],\"mark\":\"people\",\"id\":\"2018#03#20#12#24#35\"}],\"noteTotal\":[{\"author\":\"xxx\",\"mark\":\"fghgfh\",\"id\":\"2018#03#20#12#24#35\"}]}";
+
+        RestTemplate restTemplate = new RestTemplate();
+        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonStr, requestHeaders);
+        String jsonData = restTemplate.postForObject(url, httpEntity, String.class);
+
+        System.out.println(jsonData);
+
+    }
+    /**
+    *@author:pis
+    *@description:测试修改图片的标记
+    *@date: 18:11 2018/3/22
+    */
+    @Test
+    public void testModify(){
+        String url = "http://localhost:8080/Worker/Modify";
+        HttpHeaders requestHeaders = new HttpHeaders();
+        requestHeaders.set("Accept", "text/plain");
+        requestHeaders.set("Content-Type", "application/json");
+
+        String jsonStr = "{\"type\":\"Polygon\",\"isMarked\":\"true\",\"imgURI\":\"images/Marked/Polygon/000000000545.jpg\",\"provider\":\"provider\",\"noteRectangle\":[{\"author\":\"xx\",\"top\":286,\"left\":161,\"width\":52,\"height\":37,\"mark\":\"people\",\"id\":\"2018#03#20#12#24#35\"}],\"notePolygon\":[{\"author\":\"xxx\",\"points\":[{\"x\":\"\",\"y\":\"\"}],\"mark\":\"people\",\"id\":\"2018#03#20#12#24#35\"}],\"noteTotal\":[{\"author\":\"xxx\",\"mark\":\"fghgfh\",\"id\":\"2018#03#20#12#24#35\"}]}";
+
+        RestTemplate restTemplate = new RestTemplate();
+        HttpEntity<String> httpEntity = new HttpEntity<String>(jsonStr, requestHeaders);
+        String jsonData = restTemplate.postForObject(url, httpEntity, String.class);
+
+        System.out.println(jsonData);
+
+    }
+}
