(function() {
    'use strict';
    angular.module('myApp')
    .controller('UserController', ['$scope', 'UserService', function($scope, UserService){
		var self = this;
		//Cria o modelo vazio
		var getNewEmptyModel = function(){
			return {id:null,username:'',address:'',email:''};
		};
		
		self.user = getNewEmptyModel();
		self.users = [];
		
		//Método de tratamento dos erros
		var printErro = function(mensagem){
			var funcao = function(errResponse){
				console.error(mensagem);
				console.error(errResponse);
			};
			return funcao; 
		}
		
		self.fetchAllUsers = function(){
			UserService.fetchAllUsers()
			.then(
				function(d){
					self.users = d;
				},
				printErro('Erro ao carregar registros (controller)')
			);
		};
		self.createUser = function(user){
			UserService.createUser(user)
			.then(
				self.fetchAllUsers,
				printErro('Erro ao inserir registro (controller)')
			);
		};
		self.updateUser = function(user, id){
			UserService.updateUser(user, id)
			.then(
				self.fetchAllUsers,
				printErro('Erro ao atualizar registro (controller)')
			);
		};
		self.deleteUser = function(id){
			UserService.deleteUser(id)
			.then(
				self.fetchAllUsers,
				printErro('Erro ao excluir registro (controller)')
			);
		};
		
		self.fetchAllUsers();
		
		self.submit = function(){
			if( self.user.id === null ){
				console.log('Inserindo novo registro ', self.user)
				self.createUser(self.user);
			} else {
				console.log('Atualizando novo registro ', self.user)
				self.updateUser(self.user, self.user.id);
			}
			self.reset();
		};
		
		self.edit = function(id){
			console.log('Id do registro que será editado ', id);
			for	(var i = 0; i < self.users.length; i++){
				if(self.users[i].id === id ){
					self.user = self.users[i];
					break;
				}
			}
		};
		
		self.remove = function(id){
			console.log('Id do registro que será excluído ', id);
	        if(self.user.id === id) {
	           self.reset();
	        }
	        self.deleteUser(id);
		};
		
		self.reset = function(){
			self.reset = function(){
	            self.user = getNewEmptyModel();
	            $scope.myForm.$setPristine();
	        };
		};
		
	}]);
 })();