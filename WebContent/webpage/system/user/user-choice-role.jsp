<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/29
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<title>用户角色选择</title>



	<link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.staticfile.org/font-awesome/4.4.0/css/font-awesome.css?v=4.4.0" rel="stylesheet">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/plugins/iCheck/custom.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/animate.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/hplus/css/style.css?v=4.1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in-ui/css/global.css?5dsdsd5">

	<link rel="stylesheet" href="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.css">
	<script>document.write('<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/iconfont.css?' + new Date().getTime() + '">');</script>

	<style>

		.margauto{
			margin:0 auto;
		}
		.wid60{
			width:60%;
		}

		.navbar-top-links li a {
			padding: 15px 10px;
		}
		.ibox{
			margin-bottom: 0;
		}

		#page-wrapper .datagrid-btable tr{height: 40px;}
		.sort{
			cursor: pointer;
		}
		.clients-list table tr td{
			border: 1px solid #e7eaec;
		}
	</style>

	<script>document.write('<script src="${pageContext.request.contextPath}/config/cg.js?' + new Date().getTime() + '"><\/script>');</script>
	<script>
		var $dom = {};
		var temp = {};
		var this_data = {
			name: '',
			sort: '',
			order: 'asc',
			roleId: '${role.id}',
			userId: '${user.id}',
			roleName: '',
			roleCode: ''
		};

	</script>

</head>

<body class="gray-bg height_per_100">

<div class="ibox float-e-margins height_per_100">


	<div class="ibox-title">
		<h5>点击选择角色（可多选）</h5>
		<div class="ibox-tools">
			<a onclick="window.parent.setRoleUserCallback()"><i class="fa fa-times"></i></a>
		</div>
	</div>


	<div class="ibox-content" style="height: calc(100% - 60px);overflow: scroll;">

		<div class="row">

			<div class="col-md-12">
				<div class="form-inline">
					<div class="form-group">
						<div class="input-group">

							<input type="text" placeholder="角色编号、名称" class="form-control" onchange="this_data.roleName=this.value;this_data.roleCode=this.value;">
							<span class="input-group-btn">
								<button type="button" class="btn btn btn-primary" onclick="initRole()"> <i class="fa fa-search"></i> 搜索</button>

							</span>
						</div>
						<button type="button" class="btn btn-primary" onclick="roleOk()" >确定</button>
					</div>

				</div>
			</div>


		</div>
		<div class="clients-list">

			<div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
				<div class="full-height-scroll" style="width: auto; height: 100%;">
					<table class="table table-bordered table-hover" id="fontawesome-icon-list">
						<thead>
						<tr>
							<td style="width: 50px;">选择</td>
							<td class="sort" data-property="roleName">
								角色名称
								<i class="iconfont icon-paixu"></i>
							</td>
							<td class="sort" data-property="roleCode">
								角色编码
								<i class="iconfont icon-paixu"></i>
							</td>
						</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>

			</div>


		</div>


	</div>


</div>



<div id="temp_icon_list">
	<!-- <tr id="{id}">
		<td><input type="checkbox" class="i-checks"></td>
		<td>{roleName}</td>
		<td>{roleCode}</td>
	</tr>-->
</div>


<script src="https://cdn.staticfile.org/jquery/2.1.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/sweetalert/1.1.3/sweetalert.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/layer-v3.0.3/layer/layer.js"></script>
<script>document.write('<script src="${pageContext.request.contextPath}/plug-in/tools/tools.js?' + new Date().getTime() + '"><\/script>');</script>
<script src="${pageContext.request.contextPath}/plug-in-ui/hplus/js/plugins/iCheck/icheck.min.js"></script>


<script>

	var roles = [];
	<c:forEach items="${roles}" var="role">
		roles.push('${role.id}')
	</c:forEach>

	$(document).ready(function(){


		$dom.fontawesomeIconList = $('#fontawesome-icon-list tbody');
		temp.temp_icon_list = toComment( $('#temp_icon_list').html() );


		initRole();


		orderDataGrid();

	});


	function initRole() {
		$.ajax({
			url: cg_vte.service,
			type: 'post',
			dataType: 'text',
			data: {
				f: 930001,
				rows: 1000,
				roleName: this_data.roleName,
				roleCode: this_data.roleCode,
				sort: this_data.sort,
				order: this_data.order,
			},
			success: function (data) {

				$dom.fontawesomeIconList.empty();

				data = JSON.parse(data);

				var rows = data.rows;

				for (var i in rows) {
					if (rows.hasOwnProperty(i)){
						var sub = temp.temp_icon_list.format({
							roleName: rows[i].roleName,
							roleCode: rows[i].roleCode,
							id: rows[i].id
						});


						//这种使用将会频繁地操作DOM节点。
						$dom.fontawesomeIconList.append( $(sub).data("data",rows[i]) );
					}
				}


				$dom.fontawesomeIconList.find('tr').each(function (index, item) {
					var $this = $(item);
					for (var i in roles){
						if (roles.hasOwnProperty(i)){
							if ($this.data("data").id == roles[i]){
								$this.find('.i-checks').attr('checked','checked')
							}
						}
					}
				})

				$(".i-checks").iCheck({checkboxClass: "icheckbox_square-green", radioClass: "iradio_square-green",});

			}
		})
	}

	function orderDataGrid() {
		$('table thead td.sort').unbind('click').bind('click', function (event) {
			var $this = $(this);
			$this.siblings().find("i.iconfont").attr("class", "iconfont icon-paixu");
			this_data.sort = $this.attr('data-property');

			var $i = $this.find('i');


			if( $i.hasClass("icon-paixu") || $i.hasClass("icon-paixuxia") ) {
				this_data.order = 'desc';
				$i.get(0).className = 'iconfont icon-paixushang';

				initRole();
				return;
			}

			if( $i.hasClass("icon-paixushang")) {
				this_data.order = 'asc';
				$i.get(0).className = 'iconfont icon-paixuxia';
			}
			initRole()



		})
	}

	var roleOK_obj = {}
	function roleOk() {

		var formData = {};
		var $input_checked = $dom.fontawesomeIconList.find('input:checked');
		if ($input_checked && $input_checked.length > 0){
			if (roleOK_obj.layer)
				layer.close(roleOK_obj.layer);
			$input_checked.each(function (index, item) {
				//console.log($(item).parents('tr').data("data"));

				formData["roleUsers["+index+"].role.id"] = $(item).parents('tr').data("data").id;
				formData["roleUsers["+index+"].user.id"] = this_data.userId;
			});
			formData.id = this_data.userId;

			submitForm(formData)

		} else {
			roleOK_obj.layer = layer.msg('至少选择一个角色')
		}

	}

	function submitForm(formData) {

		$.ajax({
			url: cg_vte.path + "userController.do?setUserRole",
			type: 'post',
			dataType: 'text',
			data: formData,
			success: function (data) {

				show_msg(JSON.parse(data));
			},
			error: function (data) {

				console.log(data);
			}
		})
	}

	function show_msg(data) {

		switch (data.status){
			case 10000:
				swal({
					title: "提示",
					text: "success",
					type: "success",
					showCancelButton: true,
					confirmButtonColor: "#1AB394",
					confirmButtonText: "ok",
					cancelButtonText: "Cancel",
					closeOnConfirm: true
				}, function () {
					window.parent.initDataGrid();
					window.parent.setRoleUserCallback();
				});
				break;
			default:
				layer.msg(data.msg);
				break;
		}
	}

</script>

</body>
</html>
