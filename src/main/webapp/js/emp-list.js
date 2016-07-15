employee.factory('employeeListFactory', [ '$rootScope', '$http',
		function($rootScope, $http) {
			var employeeListFactory = {};

			employeeListFactory.getEmployees = function() {
				return $http.get("rest/employee/empList");
			};

			return employeeListFactory;
		} ]);

employee.controller('EmployeeController', [ '$rootScope', '$scope', '$filter',
		'employeeListFactory',
		function($rootScope, $scope, $filter, employeeListFactory) {

			$scope.employeeList = [];

			getEmployees();
			
			function getEmployees() {
				employeeListFactory.getEmployees().success(function(data) {
					$scope.employeeList = data;
				}).error(function(data) {
					alert("Error" + data);
				});
			}

		} ]);