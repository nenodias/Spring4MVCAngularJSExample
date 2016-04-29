(function() {
    'use strict';
    angular.module('myApp', [])
    .config(['$compileProvider', function ($compileProvider) {
    	$compileProvider.debugInfoEnabled(false);
	}]);
 })();