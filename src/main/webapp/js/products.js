admin.factory('productsFactory', [ '$rootScope', '$http',
		function($rootScope, $http) {
			var productsFactory = {};

			var urlBase = $rootScope.baseUrl + '/product/';
			var updtPrdt = urlBase + "updtPrdt";
			var getPrdts = urlBase + "getPrdts";
			var delPrdt = urlBase + "delPrdt";

			productsFactory.updateProduct = function(product) {

				var fd = new FormData();
				fd.append('picture', product.picture);
				fd.append('serviceId', product.productId);
				fd.append('nameEn', product.nameEn);
				fd.append('nameFr', product.nameFr);
				fd.append('nameEs', product.nameEs);
				fd.append('descriptionEn', product.descriptionEn);
				fd.append('descriptionFr', product.descriptionFr);
				fd.append('descriptionEs', product.descriptionEs);
				fd.append('videoLink', product.videoLink);
				return $http.post(updtPrdt, fd, {
					transformRequest : angular.identity,
					headers : {
						'Content-Type' : undefined
					}
				});
			}

			productsFactory.deleteProduct = function(product) {

				return $http({
					method : 'POST',
					url : delPrdt,
					data : angular.toJson(product),
					headers : {
						'Content-Type' : 'application/json; charset=UTF-8'
					}
				});
			}

			productsFactory.getProducts = function() {
				return $http.get(getPrdts);
			};

			return productsFactory;
		} ]);

admin.controller('ProductsController', [
		'$rootScope',
		'$scope',
		'$filter',
		'productsFactory',
		function($rootScope, $scope, $filter, productsFactory) {
			$scope.prdtList = [];
			$scope.product = {};

			$scope.toggleForm = false;
			$scope.isCreate = true;

			getPrdts();

			function getPrdts() {
				productsFactory.getProducts().success(function(data) {
					$scope.prdtList = data;
				}).error(function(data) {
					alert(data.status);
					window.location.replace("beta-login.html");
				});
			}

			$scope.editProduct = function(prdt) {
				$scope.product = prdt;
				$scope.toggleForm = true;
				$scope.isCreate = false;
			}

			$scope.deleteProduct = function(prdt) {

				if (!confirm("Confirm Delete ?")) {
					return;
				}

				productsFactory.deleteProduct(prdt).success(function(data) {
					alert("Delete Success.");
					$scope.prdtList = data;

					$scope.cancel();
				}).error(function(data) {
					alert(data.status);
					window.location.replace("beta-login.html");
				});
			}

			$scope.createSrvc = function() {

				if ($scope.product.productId == undefined
						|| $scope.product.productId == 0
						|| $scope.product.productId == null) {
					$scope.product.productId = 0;
				}
				if ($scope.product.videoLink == 'undefined'
						|| $scope.product.videoLink == undefined) {
					$scope.product.videoLink = '';
				}
				productsFactory.updateProduct($scope.product).success(
						function(data) {
							alert("Operation Success.");
							$scope.prdtList = data;

							$scope.cancel();
						}).error(function(data) {
					alert(data.status);
					window.location.replace("beta-login.html");
				});
			}

			$scope.showEditButton = function() {
				return !$scope.isCreate;
			};
			$scope.showCreateButton = function() {
				return $scope.isCreate;
			};

			$scope.showForm = function() {
				$scope.toggleForm = true;
				$scope.isCreate = true;
				resetValues();
			};

			$scope.hideForm = function() {
				$scope.toggleForm = false;
			};

			$scope.cancel = function() {
				$scope.toggleForm = false;
				$scope.isCreate = true;
				resetValues();
			};

			function resetValues() {
				$scope.productForm.$setPristine();
				$scope.product = {};
			}
			;

			$scope.shownavi = function() {
				return false;
			}
		} ]);