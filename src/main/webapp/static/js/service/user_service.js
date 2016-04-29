(function() {
    'use strict';
	angular.module('myApp')
	.factory('UserService', ['$http', '$q', function($http, $q){
		var raiz = 'http://localhost:8080/Spring4MVCAngularJSExample';
		var trataResposta = function(response){
			return response.data;
		};
		var trataErro = function(mensagem){
			var retorno = function(errResponse){
				console.error(mensagem);
				return $q.reject(errResponse);
			};
			return retorno; 
		};
		return {
			fetchAllUsers: function(){
				return $http.get(raiz + '/user/')
				.then(
					trataResposta,
					trataErro('Erro ao carregar registros')
				);
			},
			createUser: function(user){
				return $http.post(raiz + '/user/', user)
				.then(
					trataResposta,
					trataErro('Erro ao inserir registro')
				);
			},
			updateUser: function(user, id){
				return $http.put(raiz + '/user/'+id, user)
				.then(
					trataResposta,
					trataErro('Erro ao atualizar registro')
				);
			},
			deleteUser: function(id){
				return $http.delete(raiz + '/user/'+id)
				.then(
					trataResposta,
					trataErro('Erro ao excluir registro')
				);
			}
		};
	}]);
 })();