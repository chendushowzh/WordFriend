<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>历史总览</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<style>
#nav li a {
	background-color: #fff;
}

#nav li a:hover {
	background-color: #81C6FF;
}

input[type=submit] {
	background-color: #EEEEEE;
	border-radius: 5px;
}

input[type=submit]:hover {
	background-color: #81C6FF;
}

select {
	width: 40px;
	height: 30px;
}
</style>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-tabs" id="nav">
					<li><a href="main.jsp">首页</a></li>
					<li><a href="shengshu.jsp">生疏度统计</a></li>
					<li><a href="/wordFriend/History">历史总览</a></li>
				</ul>
				<div class="jumbotron">
					<h1>这里是历史总览。</h1>
					<p>查看历史词汇情况。</p>
				</div>
				<table class="table" id="tab">
					<thead>
						<tr>
							<th>单词</th>
							<th>历史频次</th>
							<th>修改生疏度</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${wordList}" var="words">
							<tr>
								<form action="/wordFriend/UpdateWord?word=${words.word}"
									method="post">
									<input name="method" value="history" type="hidden">
									<td>${words.word}</td>
									<td>${words.history }</td>
									<td><select name="t">
											<option value="0"
												<c:if test="${words.t == 0 }">selected="selected"</c:if>>0</option>
											<option value="1"
												<c:if test="${words.t == 1 }">selected="selected"</c:if>>1</option>
											<option value="2"
												<c:if test="${words.t == 2 }">selected="selected"</c:if>>2</option>
											<option value="3"
												<c:if test="${words.t == 3 }">selected="selected"</c:if>>3</option>
									</select></td>
								<td><input type="submit" value="确认" /></td>
								</form>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script language="javascript">
	
		var oTbody = document.getElementById("tab").tBodies[0];

		var oTrs = oTbody.getElementsByTagName("tr");
		var aTr = [];
		for (var i = 0; i < oTrs.length; i++) {
			aTr[i] = oTrs[i];
		}
		aTr.sort(function(tr1, tr2) {
			id1 = parseInt(tr1.getElementsByTagName("td")[1].innerHTML);
			id2 = parseInt(tr2.getElementsByTagName("td")[1].innerHTML);
			return id2 - id1;
		});
		for (var i = 0; i < aTr.length; i++) {
			oTbody.appendChild(aTr[i]);
		}
	</script>
</body>