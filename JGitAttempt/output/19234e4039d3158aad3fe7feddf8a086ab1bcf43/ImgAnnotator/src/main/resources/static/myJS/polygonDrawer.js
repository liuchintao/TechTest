diff --git a/ImgAnnotator/src/main/resources/static/myJS/polygonDrawer.js b/ImgAnnotator/src/main/resources/static/myJS/polygonDrawer.js
new file mode 100644
index 0000000..9399d48
--- /dev/null
+++ b/ImgAnnotator/src/main/resources/static/myJS/polygonDrawer.js
@@ -0,0 +1,314 @@
+//请先导入global
+//getClearJson,getLocX,getLocY在global那里
+
+var poly_isDrawingPolygon = false;
+
+var poly_startBtnID = "switchEdit";
+var poly_originalTxt = "开始添加";
+var poly_inDrawingTxt = "停止添加"
+
+var poly_inputMarkID = "markInput";
+var poly_updateMark = "updateMark";
+var poly_deleteNote = "deleteNote";
+var poly_showAll = "showAll";
+var poly_hideAll = "hideAll";
+var poly_submit = "commit";
+
+var poly_hoverFillStyle = 'rgba(255, 0, 0, 0.5)';
+
+var poly_canvasID = "canvas";
+var poly_penColor = "red";
+var poly_penWidth = 3;
+
+//千万注意这传的是note的信息
+function poly_loadPolygon(notePolygons) {
+    for (let note in notePolygons) {
+        $("#" + poly_canvasID).drawLine({
+            strokeStyle: poly_penColor,
+            strokeWidth: poly_penWidth,
+            rounded: true,
+            layer: true,
+            name: "polygon" + note.id,
+            data: note,
+            mouseover: function (layer) {
+                $(this).animateLayer(layer, {
+                    fillStyle: poly_hoverFillStyle,
+                }, 100);
+                var pointCenter = getPolygonAreaCenter(note.points);
+                drawHintTextOnLayer(this, layer, pointCenter.x, pointCenter.y);
+            },
+
+
+        });
+    }
+}
+
+function NotePolygon(author, points, mark, id) {
+    this.author = author;
+    this.points = points;
+    this.mark = mark;
+    this.id = id;
+}
+
+function poly_mouseOut(layer) {
+    $(this).animateLayer(layer, {
+        fillStyle: 'transparent',
+    }, 0);
+    removeHintTextOnLayer(this);
+}
+
+function poly_changeFocus(inputLayer) {
+    $("#" + poly_canvasID).getLayers(function (layer) {
+        if (layer.name === inputLayer.name) {
+            layer.strokeStyle = '#000';
+        } else {
+            layer.strokeStyle = poly_penColor;
+        }
+        return false; // do not generate the array
+    });
+    $("#" + poly_canvasID).drawLayers();
+}
+
+function poly_setShowNote(layer) {
+    $("#" + poly_inputMarkID).val(layer.data.mark);
+    poly_changeFocus(layer);
+}
+
+function poly_setUpdateNote(layer) {
+    $("#" + poly_updateMark).unbind();
+    $("#" + poly_updateMark).click(() => {
+        layer.data.mark = $("#" + poly_inputMarkID).val();
+        $("#" + poly_inputMarkID).val("");
+        refreshLabels(poly_canvasID,labelSaverID);
+    });
+}
+
+function poly_setDeleteNote(layer) {
+    $("#" + poly_deleteNote).unbind();
+    $("#" + poly_deleteNote).click(() => {
+        $("#" + poly_inputMarkID).val("");
+        $("#" + poly_canvasID).removeLayer(layer.name);
+        refreshLabels(poly_canvasID,labelSaverID);
+    });
+}
+
+function poly_layerMsgDivide(layer) {
+    poly_setShowNote(layer);
+    poly_setUpdateNote(layer);
+    poly_setDeleteNote(layer);
+}
+
+function poly_handleLayerClicked(layer) {
+    if (!poly_isDrawingPolygon) {
+        poly_layerMsgDivide(layer);
+    }
+}
+
+function poly_setStartDrawingBtn(startBtnID, originalTxt, inDrawingTxt, author) {
+    $("#" + startBtnID).click(() => {
+        if (!poly_isDrawingPolygon) {
+            $("#" + startBtnID).text(inDrawingTxt);
+            poly_startDrawing(poly_canvasID, poly_penColor, poly_penWidth, author);
+        } else {
+            $("#" + startBtnID).text(originalTxt);
+        }
+        poly_isDrawingPolygon = !poly_isDrawingPolygon;
+    });
+}
+
+function poly_btnStartToNormal(startBtnID, originalTxt) {
+    $("#" + startBtnID).text(originalTxt);
+    poly_isDrawingPolygon = false;
+}
+
+function poly_ActualBtnStartToNormal() {
+    poly_btnStartToNormal(poly_startBtnID, poly_originalTxt);
+}
+
+function getIDByTime() {
+    return new Date().getTime();
+}
+
+function poly_Point(x, y) {
+    this.x = x;
+    this.y = y;
+}
+
+function poly_startDrawing(canvasID, penColor, penWidth, author) {
+
+    var canvas = $("#" + canvasID).get(0);
+
+    var canvasPoly = canvas.getBoundingClientRect();
+
+    var canvasLeft = canvasPoly.left;
+
+    var canvasTop = canvasPoly.top;
+
+    var canvasWidth = canvasPoly.width;
+
+    var canvasHeight = canvasPoly.height;
+
+    var firstPoint = true;
+
+    var secondPoint = false;
+
+    var id = "";
+
+    var polyPrefix = "polygon";
+
+    var layerName = polyPrefix + id;
+
+    var obj = {
+        strokeStyle: penColor,
+        strokeWidth: penWidth,
+        rounded: true,
+        layer: true,
+        name: layerName,
+    };
+
+    var points = [];
+
+    var refreshLayerMsg = function (inputId) {
+        id = inputId;
+        layerName = polyPrefix + id;
+        obj.name = layerName;
+    }
+
+
+    var drawLines = function () {
+        $("#" + canvasID).removeLayer(layerName);
+        for (let p = 0; p < points.length; p += 1) {
+            obj['x' + (p + 1)] = points[p].x;
+            obj['y' + (p + 1)] = points[p].y;
+        }
+        $("#" + canvasID).drawLine(obj);
+    };
+
+    var testIfCloseEnough = function (a, b, delta) {
+        if (Math.abs(a - b) < delta) {
+            return true;
+        } else {
+            return false;
+        }
+    }
+
+    canvas.onclick = (e) => {
+        if (poly_isDrawingPolygon) {
+            if (firstPoint && !secondPoint) {
+                points.push(new poly_Point(getLocX(e, canvasLeft), getLocY(e, canvasTop)));
+                points.push(new poly_Point(getLocX(e, canvasLeft), getLocY(e, canvasTop)));
+
+                id = getIDByTime();
+                refreshLayerMsg(id);
+
+                // draw a dot
+                drawLines();
+
+                firstPoint = false;
+                secondPoint = true;
+            } else if (!firstPoint && secondPoint) {
+                points[1] = new poly_Point(getLocX(e, canvasLeft), getLocY(e, canvasTop));
+                // draw the line
+                drawLines();
+                secondPoint = false;
+            } else {
+                if (testIfCloseEnough(getLocX(e, canvasLeft), points[0].x, 8) && testIfCloseEnough(getLocY(e, canvasTop), points[0].y, 8)) {
+                    //close the shape
+                    obj['closed'] = true;
+                    obj['mouseover'] = function (layer) {
+                        $(this).animateLayer(layer, {
+                            fillStyle: poly_hoverFillStyle,
+                        }, 100);
+                        var pointCenter = getPolygonAreaCenter(points);
+                        drawHintTextOnLayer(this, layer, pointCenter.x, pointCenter.y);
+                    };
+                    obj['mouseout'] = poly_mouseOut;
+                    obj['click'] = poly_handleLayerClicked;
+
+                    //把note注入图层的data中
+                    obj['data'] = new NotePolygon(author, points, "", id);
+
+                    drawLines();
+                    poly_ActualBtnStartToNormal();
+
+                    poly_layerMsgDivide($("#"+poly_canvasID).getLayer(layerName));
+
+                    //这步很重要，结束绘画动作
+                    canvas.onclick = null;
+                } else {
+                    points.push(new poly_Point(getLocX(e, canvasLeft), getLocY(e, canvasTop)));
+                    //redraw the lines
+                    drawLines();
+                }
+            }
+        }
+    };
+}
+
+function poly_getRefreshedJson() {
+    var toReturn = globalImgMsg;
+    var notePolygon = [];
+    $("#" + poly_canvasID).getLayers(function (layer) {
+        if (layer.type === 'line') {
+            notePolygon.push(layer.data);
+        }
+        return false;
+    });
+    toReturn.notePolygon = notePolygon;
+    return toReturn;
+}
+
+
+function poly_getJsonFromServerAndLoadPoly(imgURL, provider) {
+    if(!firstTimeEdit) {
+        $.getJSON("http://localhost:8080/Worker/Check", {imgURL: imgURL, provider: provider}, function (data) {
+            writeGlobalImgMsg(data);
+            poly_loadPolygon(globalImgMsg.notePolygon);
+        });
+    }
+}
+
+function poly_sentJsonToServer() {
+    var keywordPostRect = firstTimeEdit? "postMark" : "Modify";
+
+    $.ajax({
+        type:'POST',
+        url:"http://localhost:8080/Worker/"+keywordPostRect,
+        data:JSON.stringify(poly_getRefreshedJson()),
+        success:function(result){
+            console.log(result);
+        },
+        contentType:'application/json',
+        dataType:'json'
+    });
+}
+
+function poly_setBtnShowAll() {
+    $("#"+poly_showAll).click(()=>{
+        showAllLayers(poly_showAll);
+    });
+}
+
+function poly_setBtnHideAll() {
+    $("#"+poly_hideAll).click(()=>{
+        hideAllLayers(poly_showAll);
+    });
+}
+
+function poly_setButtonCommit() {
+    $("#"+poly_submit).click(()=>{
+        poly_sentJsonToServer();
+    });
+}
+
+function poly_switchPolygonModuleStarted() {
+    poly_setBtnShowAll();
+    poly_setBtnHideAll();
+    poly_getJsonFromServerAndLoadPoly(global_imgURL,"provider");
+    poly_setButtonCommit();
+    poly_setStartDrawingBtn(poly_startBtnID, poly_originalTxt, poly_inDrawingTxt, "author");
+}
+
+function poly_actualSwitchOn() {
+    setCanvasSizeAndSwitchOn(global_imgURL,"canvas","canvasSaver",poly_switchPolygonModuleStarted);
+}
\ No newline at end of file
