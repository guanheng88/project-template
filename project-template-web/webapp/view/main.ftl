<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache">
        <title>webui framework</title>
        <!-- <link rel="stylesheet/less" type="text/css" href="styles.less" /> -->
        <link rel="stylesheet" type="text/css" href="${request.contextPath}/thirdparty/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${request.contextPath}/thirdparty/css/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" type="text/css" href="${request.contextPath}/thirdparty/css/font-awesome.min.css" />
        <link rel="stylesheet" type="text/css" href="${request.contextPath}/thirdparty/css/bootstrap-sidermenu.css" />

        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/less.min.js" ></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/jq-paginator.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/angular.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/popper.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/bootstrap-datetimepicker.min.js"></script>
        <script type="text/javascript" src="${request.contextPath}/thirdparty/js/bootstrap-sidermenu.js"></script>

        <script type="text/javascript">
            var paginationLayer;

            var app = angular.module("testApp", []).run(function() {
                paginationLayer = $("#layer-pagination");
            });

            app.controller("myCtrl", function($scope) {
                $scope.firstName = "John";
                $scope.lastName = "Doe";

                paginationLayer.jqPaginator({
                    currentPage: 1,
                    pageSize: 10,
                    totalCounts: 135,
                    visiblePages: 7,
                    first: "<li class='page-item'><a class='page-link' href='javascript:;'>首页</a></li>",
                    prev: "<li class='page-item'><a class='page-link' href='javascript:;'>上一页</a></li>",
                    next: "<li class='page-item'><a class='page-link' href='javascript:;'>下一页</a></li>",
                    last: "<li class='page-item'><a class='page-link' href='javascript:;'>末页</a></li>",
                    page: "<li class='page-item'><a class='page-link' href='javascript:;'>{{page}}</a></li>",
                    onPageChange: function(page, type) {
                        console.log("page: " + page + ", type: " + type);
                    }
                });
            });

            app.controller("listCtrl", function($scope) {
                $scope.users = [
                    { name: "test1" },
                    { name: "test2" },
                    { name: "test3" }
                ];

                $scope.testNameBtnClick = function(param) {
                    console.log($scope.testUser.testName);
                    console.log(param);
                    console.log($scope.testUser);

                    paginationLayer.jqPaginator("option", {
                        currentPage: 1,
                        totalCounts: 40
                    });
                };

                $scope.servers = [
                    { id: 1, name: "server1" },
                    { id: 2, name: "server2" },
                    { id: 3, name: "server3" }
                ];
                $scope.serverId = 2;
            });
        </script>
    </head>
    <body ng-app="testApp">
        <nav class="navbar navbar-dark bg-dark">
            <span class="navbar-brand">sdfsdf</span>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <ul class="menu-siderbar nav flex-column">
                    <li class="nav-item open">
                        <a href="javascript:;" class="nav-link nav-link-parent">
                            <i class="menu-icon fa fa-th"></i>菜单1
                        </a>
                        <ul class="menu-siderbar-child nav flex-column">
                            <li class="nav-item">
                                <a href="javascript:;" class="nav-link active">
                                    <i class="menu-icon fa fa-bolt"></i>子菜单1
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:;" class="nav-link">
                                    <i class="menu-icon fa fa-bolt"></i>子菜单2
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:;" class="nav-link">
                                    <i class="menu-icon fa fa-bolt"></i>子菜单3
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="javascript:;" class="nav-link nav-link-parent">
                            <i class="menu-icon fa fa-th"></i>菜单2
                        </a>
                        <ul class="menu-siderbar-child nav flex-column">
                            <li class="nav-item">
                                <a href="javascript:;" class="nav-link">
                                    <i class="menu-icon fa fa-bolt"></i>子菜单1
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:;" class="nav-link">
                                    <i class="menu-icon fa fa-bolt"></i>子菜单2
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="javascript:;" class="nav-link">
                                    <i class="menu-icon fa fa-bolt"></i>子菜单3
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a href="javascript:;" class="nav-link">菜单3</a>
                    </li>
                </ul>
                <div style="padding-left: 25px; width: calc(100% - 241px);">
                    <div ng-controller="myCtrl">
                        <p>名: <input type="text" ng-model="firstName" /></p>
                        <p>姓: <input type="text" ng-model="lastName" /></p>
                        <p>姓名: {{firstName + " " + lastName}}</p>
                    </div>
                    <div id="testDiv" ng-controller="listCtrl">
                        <ul ng-repeat="user in users">
                            <li data-name="{{user.name}}">{{user.name}}</li>
                        </ul>
                        <p><input type="text" ng-model="testUser.testName" /></p>
                        <p>{{testUser.testName}}</p>
                        <p><button ng-click="testNameBtnClick(testUser.testName)">确定</button></p>
                        <p><select ng-model="serverId" ng-options="server.id as server.name for server in servers"></select></p>
                    </div>
                    <ul id="layer-pagination" class="pagination pagination-sm"></ul>
                    <div><@block name="body">父模板的 body</@block></div>
                </div>
            </div>
        </div>
    </body>
</html>