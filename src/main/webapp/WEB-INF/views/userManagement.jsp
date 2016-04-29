<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Angular JS $http Example</title>
	<style>
		.username.ng-valid {
			background-color: lightgreen;
		}
		
		.username.ng-dirty.ng-invalid-required {
			background-color: red;
		}
		
		.username.ng-dirty.ng-invalid-minlength {
			background-color: yellow;
		}
		
		.email.ng-valid {
			background-color: lightgreen;
		}
		
		.email.ng-dirty.ng-invalid-required {
			background-color: red;
		}
		
		.email.ng-dirty.ng-invalid-email {
			background-color: yellow;
		}
	</style>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="generic-container" ng-controller="UserController as ctrl">
		<div class="panel panel-default">
			
			<div class="panel-heading">
				<span class="lead">Formulário de Cadastro de Usuário</span>
			</div>
			
			<div class="formcontainer">
				<!-- Formulário -->
				<form ng-submit="ctrl.submit()" name="myForm" id="myForm" class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.id" />
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-table" for="uname">Nome</label>
							<div class="col-md-7">
								<input type="text" id="uname" ng-model="ctrl.user.username" ng-minlength="3" 
									class="username form-control input-sm" 
									required="required" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">Esse é um campo requerido</span>
									<span ng-show="myForm.uname.$error.minlength">Tamanho mínimo é 3 caracteres</span>
									<span ng-show="myForm.uname.$invalid">Valor deste campo é inválido </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-table" for="address">Endereço</label>
							<div class="col-md-7">
								<input type="text" id="address" ng-model="ctrl.user.address" class="username form-control input-sm" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-table" for="email">Email</label>
							<div class="col-md-7">
								<input type="text" id="email" ng-model="ctrl.user.email" 
									class="username form-control input-sm" 
									required="required" />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.uname.$error.required">Esse é um campo requerido</span>
									<span ng-show="myForm.uname.$invalid">Valor deste campo é inválido </span>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class=" form-actions floatRight">
							<input type="submit" value="{{!ctrl.user.id ? 'Inserir' : 'Atualizar'}}"
								class="btn btn-primary btn-sm" 
								ng-disabled="myForm.$invalid" 
							/>
							<button type="button" ng-click="ctrl.reset()"
								class="btn btn-warning btn-sm" 
								ng-disabled="myForm.$pristine"
							>
							Reset Form
							</button>
						</div>
					</div>					
				</form>
				<!-- Fim do Formulário -->
			</div>
		</div>
		<!-- Lista dos Usuários -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">
					Lista de Usuários
				</span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>ID</th>
							<th>Nome</th>
							<th>Endereço</th>
							<th>Email</th>
							<th width="20%">&nbsp;</th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="u in ctrl.users">
							<td><span ng-bind="u.id"></span></td>
							<td><span ng-bind="u.username"></span></td>
							<td><span ng-bind="u.address"></span></td>
							<td><span ng-bind="u.email"></span></td>
							<td>
								<button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Editar</button>
								<button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remover</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.5.5/angular.js"></script>
	<script type="text/javascript" src="<c:url value='/static/js/app.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/service/user_service.js' />"></script>
	<script type="text/javascript" src="<c:url value='/static/js/controller/user_controller.js' />"></script>
</body>
</html>