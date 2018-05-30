diff --git a/resources/static/js/Main2Selector.js b/resources/static/js/Main2Selector.js
new file mode 100644
index 0000000..48729a9
--- /dev/null
+++ b/resources/static/js/Main2Selector.js
@@ -0,0 +1,29 @@
+UrlParm = function () {
+    let
+        u = window.location.search.substr(1),
+        whole = {};
+
+    (function () {
+        if (u !== '') {
+
+            //先根据变量数字来拆解
+            let parms = u.split('&');
+            for (var key of parms)
+
+                //将一个变量按照‘等号’拆解
+                if (key !== '') {
+                    let p = key.split('=');
+                    whole[p[0]] = p[1];
+                }
+        }
+
+    })();
+
+    return {
+        parm: function (o) {
+            return whole[o];
+        }
+    }
+}();
+
+
