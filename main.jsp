<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>词频分析</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- 引入 Bootstrap -->
<link href="bootstrap/bootstrap.min.css" rel="stylesheet">
<style>
#nav li a {
	background-color: #fff;
}

#nav li a:hover {
	background-color: #81C6FF;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<ul class="nav nav-tabs" id="nav">
					<li ><a href="main.jsp">首页</a></li>
					<li><a href="shengshu.jsp">生疏度统计</a></li>
					<li><a href="/wordFriend/History">历史总览</a></li>
				</ul>
				<div class="jumbotron">
					<h1>欢迎进入词频分析！</h1>
					<p>在以下文本框输入文章开始词频分析。</p>
				</div>
				<form role="form" method="post" action="/wordFriend/VocaTest">
					<div class="form-group">
						<label>词频分析</label> <br>
							<textarea rows="5" cols="160" name="text" placeholder="请输入：" >
       							
							</textarea>
					</div>

					<button type="submit" class="btn btn-default">词频分析</button>
				</form>

				<table class="table">
					<thead>
						<tr>
							<th>单词</th>
							<th>当前文章频次</th>
							<th>历史频次</th>
							<th>生疏度</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${nowWords}" var="words">
							<tr>
								
								<form action="/wordFriend/UpdateWord?word=${words.word}"
									method="post">
									<input name="method" value="main" type="hidden">
									<td>${words.word}</td>
								  	<td>${words.num}</td>
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
</body>

</html>