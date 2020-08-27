// 파일시스템을 위한 ztree 기본 설정
const zNodes = [ {
	id : "1",
	name : "Jo",
	open : "true"
}, {
	id : "2",
	pId : "1",
	name : "resource"
}, {
	id : "21",
	pId : "2",
	name : "resource.html",
	iconSkin : "htmlIco",
}, {
	id : "12",
	pId : "1",
	name : "JoYoonKyung.js",
	iconSkin : "jsIco",
}, {
	id : "13",
	pId : "1",
	name : "Hello.java",
	iconSkin : "javaIco"
}, {
	id : "14",
	pId : "1",
	name : "coffee.css",
	iconSkin : "cssIco"
}, {
	id : "15",
	pId : "1",
	name : "coffee.py",
	iconSkin : "pythonIco"
}, {
	id : "16",
	pId : "1",
	name : "coffee.swift",
	iconSkin : "swiftIco"
}, {
	id : "17",
	pId : "1",
	name : "readme.md",
	iconSkin : "mdIco"
} ];
const setting = {
	data : {
		simpleData : {
			enable : true,
		}
	}
};

$(document).ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});
// 새파일 만들기
$("#newFile")
		.click(
				function() {
					var li = document.createElement("li");
					var temp = $(".ui-tabs-anchor").length;
					li.innerHTML = "<a href='#tabs-"
							+ (temp + 1)
							+ "'>Lorem, ipsum.</a><span><i class='fas fa-times'></i></span>";
					$("#codeArea ul").append(li);
					var newTab = document.createElement("div");
					newTab.id = "tabs-" + (temp + 1);
					var textarea = document.createElement("div");
					textarea.id = "code";
					// textarea.cols = "67";
					// textarea.rows = "22";

					var input = document.createElement("input");
					input.type = "text";
					input.className = "form-control";
					input.placeholder = "commit할 메시지 입력";

					var commitBtn = document.createElement("button");
					commitBtn.className = "btn btn-info";
					commitBtn.innerHTML = "<i class='fas fa-arrow-right'></i>";

					newTab.append(textarea);
					newTab.append(input);
					newTab.append(commitBtn);
					$("#codeArea").append(newTab);
					for (var i = 0; i < temp + 1; i++) {
						$("#codeArea ul li:nth-child(" + (i + 1) + ") span")
								.css(
										"background-color",
										$(
												"#codeArea ul li:nth-child("
														+ (i + 1) + ")").css(
												"background-color"));
					}
					$("#codeArea").tabs("refresh");
					if (temp >= 3) {
						$("#codeArea ul li").css("width", 550 / (temp + 1))
								.css("overflow", "hidden");
					}
					tabs.tabs("refresh");
				});
var tabs = $("#codeArea").tabs();

setTimeout(function() {
	var list = document.getElementsByClassName("node_name");
	for (var i = 0; i < list.length; i++) {
		list[i].addEventListener("dblclick", function() {
			var position = event.srcElement.innerHTML.lastIndexOf(".");
			if (position != -1) {
				var extension = event.srcElement.innerHTML.substring(position);
				if (extension == ".js") {
					editor = CodeMirror(document.getElementById("code"), {
						lineNumbers : true,
						mode : "javascript",
						matchBrackets : true,
						extraKeys : {
							"Ctrl-Space" : "autocomplete"
						}
					});
				} else if (extension == ".html") {
					editor = CodeMirror(document.getElementById("code"), {
						lineNumbers : true,
						mode : "text/html",
						matchBrackets : true,
						extraKeys : {
							"Ctrl-Space" : "autocomplete"
						},
						value : "<h1>소스코드입니다22</h1>"
					});
				} else if (extension == ".java") {
				} else if (extension == ".py") {
					editor = CodeMirror(document.getElementById("code"), {
						lineNumbers : true,
						mode : {
							name : "python",
							version : 3,
							singleLineStringErrors : false,
						},
						indentUnit : 4,
						matchBrackets : true,
						extraKeys : {
							"Ctrl-Space" : "autocomplete"
						}
					});
				} else if (extension == ".css") {
					editor = CodeMirror(document.getElementById("code"), {
						lineNumbers : true,
						mode : "css",
						matchBrackets : true,
						extraKeys : {
							"Ctrl-Space" : "autocomplete"
						}
					});
				} else if (extension == ".md") {
					editor = CodeMirror(document.getElementById("code"), {
						lineNumbers : true,
						mode : "markdown",
						matchBrackets : true,
						extraKeys : {
							"Ctrl-Space" : "autocomplete"
						}
					});
				}
			}
		});
	}
}, 100);