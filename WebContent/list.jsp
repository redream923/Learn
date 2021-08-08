<%@page import="entity.Admin"%>
<%@page import="java.util.List"%>
<%@page import="dao.impl.AdminDAOImpl"%>
<%@page import="dao.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" type="text/css" href="./css/userAdmin.css">
<script type="text/javascript" src="./js/jquery.min.js"></script>
<style type="text/css">
.current_page {
	color: red
}
</style>

</head>
<body>
	<div class="normal">
		<div class="main_title">用户管理</div>
		<div class="main_body">
			<div class="nav_title autoH color_909090">
				<label>用户管理</label><span class="jiantou"></span><label
					class="color_0e6fb6">管理员</label>
			</div>
			<!-- 右侧内容 -->
			<div class="content">
				<!-- 选项组 -->
				<div class="options">
					<div class="o_btns">
						<input class="add" value="增加" onclick="location.href='add.html'" />
					</div>
				</div>
				<div class="c_main">
					<table cellspacing="0" cellpadding="0">
						<tr class="thead">
							<td class="col_10">序号</td>
							<td class="col_20">用户名</td>
							<td class="col_20">密码</td>
							<td class="col_20">姓名</td>
							<td class="col_5">操作</td>
						</tr>
						<%
							Integer pageSize = (Integer) request.getAttribute("pageSize");
							Integer currentPage = (Integer) request.getAttribute("page");
							List<Admin> admins = (List<Admin>) request.getAttribute("admins");
							int count = (currentPage - 1) * pageSize + 1;
							for (Admin admin : admins) {
						%>
						<tr>
							<td class="col_10"><%=count++%></td>
							<td class="col_20"><%=admin.getUsername()%></td>
							<td class="col_20"><%=admin.getPassword()%></td>
							<td class="col_20"><%=admin.getRealname()%></td>
							<td class="col_5"><a class="edit"
								href="load.do?id=<%=admin.getId()%>"></a>/<a
								href="del.do?id=<%=admin.getId()%>" class="delete"></a></td>
						</tr>
						<%
							}
						%>


					</table>
					<!-- 分页 -->
					<%
						Integer totalPages = (Integer) request.getAttribute("totalPages");
					%>
					<div class="Page navigation">
						<ul class="pagination">
							<% if (currentPage == 1) {%>
								<li><a href="javascript:;">上一页</a></li>
							<% } else { %>
								<li><a href=list.do?page=<%=currentPage-1%>>上一页</a></li>
							<%}%>
							<% for (int i = 1; i <= totalPages; i++) { %>
								<%	if (i == currentPage) { %>
							<li><a href="list.do?page=<%=i%>" class="current_page"><%=i%></a></li>
								<% }else { %>
							<li><a href="list.do?page=<%=i%>"><%=i%></a></li>
									  <% } 
							   } %>
							<% if (currentPage == totalPages) { %>
								<li><a href="javascript:;">下一页</a></li>
							<% } else { %>
								<li><a href=list.do?page=<%=currentPage+1%>>下一页</a></li>
							<% } %>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>