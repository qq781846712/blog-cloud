var app = angular.module('sentinelDashboardApp');

app.controller('SentinelClusterAppServerListController', ['$scope', '$stateParams', 'ngDialog',
    'MachineService', 'ClusterStateService',
    function ($scope, $stateParams, ngDialog, MachineService, ClusterStateService) {
        $scope.app = $stateParams.app;
        const UNSUPPORTED_CODE = 4041;

        const CLUSTER_MODE_CLIENT = 0;
        const CLUSTER_MODE_SERVER = 1;
        const DEFAULT_CLUSTER_SERVER_PORT = 18730;
        const DEFAULT_NAMESPACE = 'default';
        const DEFAULT_MAX_ALLOWED_QPS = 20000;

        // tmp for dialog temporary data.
        $scope.tmp = {
            curClientChosen: [],
            curRemainingClientChosen: [],
            curChosenServer: {},
        };

        $scope.remainingMachineList = [];

        function convertSetToString(set) {
            if (set === undefined) {
                return '';
            }
            if (set.length === 1 && set[0] === DEFAULT_NAMESPACE) {
                return DEFAULT_NAMESPACE;
            }
            let s = '';
            for (let i = 0; i < set.length; i++) {
                let ns = set[i];
                if (ns !== DEFAULT_NAMESPACE) {
                    s = s + ns;
                    if (i < set.length - 1) {
                        s = s + ',';
                    }
                }
            }
            return s;
        }

        function convertStrToNamespaceSet(str) {
            if (str === undefined || str === '') {
                return [];
            }
            let arr = [];
            let spliced = str.split(',');
            spliced.forEach((v) => {
                arr.push(v.trim());
            });
            return arr;
        }

        function processAppSingleData(data) {
            if (data.state.server && data.state.server.namespaceSet) {
                data.state.server.namespaceSetStr = convertSetToString(data.state.server.namespaceSet);
                data.mode = data.state.stateInfo.mode;
            }
        }

        function removeFromArr(arr, v) {
            for (let i = 0; i < arr.length; i++) {
                if (arr[i] === v) {
                    arr.splice(i, 1);
                    break;
                }
            }
        }

        function removeFromArrIf(arr, f) {
            for (let i = 0; i < arr.length; i++) {
                if (f(arr[i]) === true) {
                    arr.splice(i, 1);
                    break;
                }
            }
        }

        function resetAssignDialogChosen() {
            $scope.tmp.curClientChosen = [];
            $scope.tmp.curRemainingClientChosen = [];
        }

        function generateMachineId(e) {
            return e.ip + '@' + e.commandPort;
        }

        function applyClusterMap(appClusterMachineList) {
            if (!appClusterMachineList) {
                return;
            }
            let tmpMap = new Map();
            let serverCommandPortMap = new Map();
            $scope.clusterMap = [];
            $scope.remainingMachineList = [];
            let tmpServerList = [];
            let tmpClientList = [];
            appClusterMachineList.forEach((e) => {
                if (e.mode === CLUSTER_MODE_CLIENT) {
                    tmpClientList.push(e);
                } else if (e.mode === CLUSTER_MODE_SERVER) {
                    tmpServerList.push(e);
                } else {
                    $scope.remainingMachineList.push(generateMachineId(e));
                }
            });
            tmpServerList.forEach((e) => {
                let ip = e.ip;
                let machineId = ip + '@' + e.commandPort;
                let group = {
                    ip: ip,
                    machineId: machineId,
                    port: e.state.server.port,
                    clientSet: [],
                    namespaceSetStr: e.state.server.namespaceSetStr,
                    maxAllowedQps: e.state.server.flow.maxAllowedQps,
                    belongToApp: true,
                };
                if (!tmpMap.has(machineId)) {
                    tmpMap.set(machineId, group);
                }
                serverCommandPortMap.set(ip + ':' + e.state.server.port, e.commandPort);
            });
            tmpClientList.forEach((e) => {
                let ip = e.ip;
                let machineId = ip + '@' + e.commandPort;

                let targetServer = e.state.client.clientConfig.serverHost;
                let targetPort = e.state.client.clientConfig.serverPort;
                if (targetServer === undefined || targetServer === '' ||
                    targetPort === undefined || targetPort <= 0) {
                    $scope.remainingMachineList.push(generateMachineId(e));
                    return;
                }

                let serverHostPort = targetServer + ':' + targetPort;

                if (serverCommandPortMap.has(serverHostPort)) {
                    let serverCommandPort = serverCommandPortMap.get(serverHostPort);
                    let g;
                    if (serverCommandPort < 0) {
                        // Not belong to this app.
                        g = tmpMap.get(serverHostPort);
                    } else {
                        // Belong to this app.
                        g = tmpMap.get(targetServer + '@' + serverCommandPort);
                    }
                    g.clientSet.push(machineId);
                } else {
                    let group = {
                        ip: targetServer,
                        machineId: serverHostPort,
                        port: targetPort,
                        clientSet: [machineId],
                        belongToApp: false,
                    };
                    tmpMap.set(serverHostPort, group);
                    // Indicates that it's not belonging to current app.
                    serverCommandPortMap.set(serverHostPort, -1);
                }

                // if (!tmpMap.has(serverHostPort)) {
                //     let group = {
                //         ip: targetServer,
                //         machineId: targetServer,
                //         port: targetPort,
                //         clientSet: [machineId],
                //         belongToApp: false,
                //     };
                //     tmpMap.set(targetServer, group);
                // } else {
                //     let g = tmpMap.get(targetServer);
                //     g.clientSet.push(machineId);
                // }
            });
            tmpMap.forEach((v) => {
                if (v !== undefined) {
                    $scope.clusterMap.push(v);
                }
            });
        }

        $scope.notChosenServer = (id) => {
            return id !== $scope.serverAssignDialogData.serverData.currentServer;
        };

        $scope.onCurrentServerChange = () => {
            resetAssignDialogChosen();
        };

        $scope.moveToServerGroup = () => {
            $scope.tmp.curRemainingClientChosen.forEach(e => {
                $scope.serverAssignDialogData.serverData.clientSet.push(e);
                removeFromArr($scope.remainingMachineList, e);
            });
            resetAssignDialogChosen();
        };

        $scope.moveToRemainingSharePool = () => {
            $scope.tmp.curClientChosen.forEach(e => {
                $scope.remainingMachineList.push(e);
                removeFromArr($scope.serverAssignDialogData.serverData.clientSet, e);
            });
            resetAssignDialogChosen();
        };

        function parseIpFromMachineId(machineId) {
            if (machineId.indexOf(':') !== -1) {
                return machineId.split(':')[0];
            }
            if (machineId.indexOf('@') === -1) {
                return machineId;
            }
            let arr = machineId.split('@');
            return arr[0];
        }

        function retrieveClusterAssignInfoOfApp() {
            ClusterStateService.fetchClusterUniversalStateOfApp($scope.app).success(function (data) {
                if (data.code === 0 && data.data) {
                    $scope.loadError = undefined;
                    $scope.appClusterMachineList = data.data;
                    $scope.appClusterMachineList.forEach(processAppSingleData);
                    applyClusterMap($scope.appClusterMachineList);
                } else {
                    $scope.appClusterMachineList = {};
                    if (data.code === UNSUPPORTED_CODE) {
                        $scope.loadError = {message: '???????????? Sentinel ????????????????????????????????????????????? 1.4.0 ????????????????????????????????????'}
                    } else {
                        $scope.loadError = {message: data.msg};
                    }
                }
            }).error(() => {
                $scope.loadError = {message: '????????????'};
            });
        }


        $scope.newServerDialog = () => {
            retrieveClusterAssignInfoOfApp();
            $scope.serverAssignDialogData = {
                title: '?????? Token Server',
                type: 'add',
                confirmBtnText: '??????',
                serverData: {
                    serverType: 0,
                    clientSet: [],
                    serverPort: DEFAULT_CLUSTER_SERVER_PORT,
                    maxAllowedQps: DEFAULT_MAX_ALLOWED_QPS,
                }
            };
            $scope.serverAssignDialog = ngDialog.open({
                template: '/app/views/dialog/cluster/cluster-server-assign-dialog.html',
                width: 1000,
                overlay: true,
                scope: $scope
            });
        };

        $scope.modifyServerAssignConfig = (serverVO) => {
            let id = serverVO.id;
            ClusterStateService.fetchClusterUniversalStateOfApp($scope.app).success(function (data) {
                if (data.code === 0 && data.data) {
                    $scope.loadError = undefined;
                    $scope.appClusterMachineList = data.data;
                    $scope.appClusterMachineList.forEach(processAppSingleData);
                    applyClusterMap($scope.appClusterMachineList);
                    let clusterMap = $scope.clusterMap;
                    let d;
                    for (let i = 0; i < clusterMap.length; i++) {
                        if (clusterMap[i].machineId === id) {
                            d = clusterMap[i];
                        }
                    }
                    if (!d) {
                        alert('????????????');
                        return;
                    }
                    $scope.serverAssignDialogData = {
                        title: 'Token Server ????????????',
                        type: 'edit',
                        confirmBtnText: '??????',
                        serverData: {
                            currentServer: d.machineId,
                            belongToApp: serverVO.belongToApp,
                            serverPort: d.port,
                            clientSet: d.clientSet,
                        }
                    };
                    if (d.maxAllowedQps !== undefined) {
                        $scope.serverAssignDialogData.serverData.maxAllowedQps = d.maxAllowedQps;
                    }
                    $scope.serverAssignDialog = ngDialog.open({
                        template: '/app/views/dialog/cluster/cluster-server-assign-dialog.html',
                        width: 1000,
                        overlay: true,
                        scope: $scope
                    });
                } else {
                    if (data.code === UNSUPPORTED_CODE) {
                        $scope.loadError = {message: '???????????? Sentinel ????????????????????????????????????????????? 1.4.0 ????????????????????????????????????'}
                    } else {
                        $scope.loadError = {message: data.msg};
                    }
                }
            }).error(() => {
                $scope.loadError = {message: '????????????'};
            });
        };

        function getRemainingMachineList() {
            return $scope.remainingMachineList.filter((e) => $scope.notChosenServer(e));
        }

        function doApplyNewSingleServerAssign() {
            let ok = confirm('???????????????????????????');
            if (!ok) {
                return;
            }
            let serverData = $scope.serverAssignDialogData.serverData;
            let belongToApp = serverData.serverType == 0; // don't modify here!
            let machineId = serverData.currentServer;
            let request = {
                clusterMap: {
                    machineId: machineId,
                    ip: parseIpFromMachineId(machineId),
                    port: serverData.serverPort,
                    clientSet: serverData.clientSet,
                    belongToApp: belongToApp,
                    maxAllowedQps: serverData.maxAllowedQps,
                },
                remainingList: getRemainingMachineList(),
            };
            ClusterStateService.applyClusterSingleServerAssignOfApp($scope.app, request).success((data) => {
                if (data.code === 0 && data.data) {
                    let failedServerSet = data.data.failedServerSet;
                    let failedClientSet = data.data.failedClientSet;
                    if (failedClientSet.length === 0 && failedServerSet.length === 0) {
                        alert('??????????????????');
                    } else {
                        let failedSet = [];
                        if (failedServerSet) {
                            failedServerSet.forEach((e) => {
                                failedSet.push(e);
                            });
                        }
                        if (failedClientSet) {
                            failedClientSet.forEach((e) => {
                                failedSet.push(e);
                            });
                        }

                        alert('????????????????????????????????????' + JSON.stringify(failedSet));
                    }

                    location.reload();
                } else {
                    if (data.code === UNSUPPORTED_CODE) {
                        alert('???????????? Sentinel ????????????????????????????????????????????? 1.4.0 ????????????????????????????????????');
                    } else {
                        alert('???????????????' + data.msg);
                    }
                }
            }).error(() => {
                alert('????????????');
            });
        }

        function doApplySingleServerAssignEdit() {
            let ok = confirm('???????????????????????????');
            if (!ok) {
                return;
            }
            let serverData = $scope.serverAssignDialogData.serverData;
            let machineId = serverData.currentServer;
            let request = {
                clusterMap: {
                    machineId: machineId,
                    ip: parseIpFromMachineId(machineId),
                    port: serverData.serverPort,
                    clientSet: serverData.clientSet,
                    belongToApp: serverData.belongToApp,
                },
                remainingList: $scope.remainingMachineList,
            };
            if (serverData.maxAllowedQps !== undefined) {
                request.clusterMap.maxAllowedQps = serverData.maxAllowedQps;
            }
            ClusterStateService.applyClusterSingleServerAssignOfApp($scope.app, request).success((data) => {
                if (data.code === 0 && data.data) {
                    let failedServerSet = data.data.failedServerSet;
                    let failedClientSet = data.data.failedClientSet;
                    if (failedClientSet.length === 0 && failedServerSet.length === 0) {
                        alert('??????????????????');
                    } else {
                        let failedSet = [];
                        failedServerSet.forEach(failedSet.push);
                        failedClientSet.forEach(failedSet.push);
                        alert('????????????????????????????????????' + JSON.stringify(failedSet));
                    }

                    location.reload();
                } else {
                    if (data.code === UNSUPPORTED_CODE) {
                        alert('???????????? Sentinel ????????????????????????????????????????????? 1.4.0 ????????????????????????????????????');
                    } else {
                        alert('???????????????' + data.msg);
                    }
                }
            }).error(() => {
                alert('????????????');
            });
        }

        $scope.saveAssignForDialog = () => {
            if (!checkAssignDialogValid()) {
                return;
            }
            if ($scope.serverAssignDialogData.type === 'add') {
                doApplyNewSingleServerAssign();
            } else if ($scope.serverAssignDialogData.type === 'edit') {
                doApplySingleServerAssignEdit();
            } else {
                alert('???????????????');
            }
        };

        function checkAssignDialogValid() {
            let serverData = $scope.serverAssignDialogData.serverData;
            if (serverData.currentServer === undefined || serverData.currentServer === '') {
                alert('?????????????????? Token Server');
                return false;
            }
            if (serverData.serverPort === undefined || serverData.serverPort <= 0 || serverData.serverPort > 65535) {
                alert('???????????????????????????');
                return false;
            }
            if (serverData.maxAllowedQps !== undefined && serverData.maxAllowedQps < 0) {
                alert('?????????????????????????????? QPS');
                return false;
            }
            return true;
        }

        $scope.viewConnectionDetail = (serverVO) => {
            $scope.connectionDetailDialogData = {
                serverData: serverVO
            };
            $scope.connectionDetailDialog = ngDialog.open({
                template: '/app/views/dialog/cluster/cluster-server-connection-detail-dialog.html',
                width: 700,
                overlay: true,
                scope: $scope
            });
        };

        function generateRequestLimitDataStr(limitData) {
            if (limitData.length === 1 && limitData[0].namespace === DEFAULT_NAMESPACE) {
                return 'default: ' + limitData[0].currentQps + ' / ' + limitData[0].maxAllowedQps;
            }
            for (let i = 0; i < limitData.length; i++) {
                let crl = limitData[i];
                if (crl.namespace === $scope.app) {
                    return '' + crl.currentQps + ' / ' + crl.maxAllowedQps;
                }
            }
            return '0';
        }

        function processServerListData(serverVO) {
            if (serverVO.state && serverVO.state.namespaceSet) {
                serverVO.state.namespaceSetStr = convertSetToString(serverVO.state.namespaceSet);
            }
            if (serverVO.state && serverVO.state.requestLimitData) {
                serverVO.state.requestLimitDataStr = generateRequestLimitDataStr(serverVO.state.requestLimitData);
            }
        }

        $scope.generateConnectionSet = (data) => {
            let connectionSet = data;
            let s = '';
            if (connectionSet) {
                s = s + '[';
                for (let i = 0; i < connectionSet.length; i++) {
                    s = s + connectionSet[i].address;
                    if (i < connectionSet.length - 1) {
                        s = s + ', ';
                    }
                }
                s = s + ']';
            } else {
                s = '[]';
            }
            return s;
        };

        function retrieveClusterServerInfo() {
            ClusterStateService.fetchClusterServerStateOfApp($scope.app).success(function (data) {
                if (data.code === 0 && data.data) {
                    $scope.loadError = undefined;
                    $scope.serverVOList = data.data;
                    $scope.serverVOList.forEach(processServerListData);
                } else {
                    $scope.serverVOList = {};
                    if (data.code === UNSUPPORTED_CODE) {
                        $scope.loadError = {message: '???????????? Sentinel ????????????????????????????????????????????? 1.4.0 ????????????????????????????????????'}
                    } else {
                        $scope.loadError = {message: data.msg};
                    }
                }
            }).error(() => {
                $scope.loadError = {message: '????????????'};
            });
        }

        retrieveClusterServerInfo();

        let confirmUnbindServerDialog;
        $scope.unbindServer = (id) => {
            $scope.pendingUnbindIds = [id];
            $scope.confirmDialog = {
                title: '?????? Token Server',
                type: 'unbind_token_server',
                attentionTitle: '??????????????????????????? Token Server?????? server ?????? client ?????????????????????',
                attention: id + '',
                confirmBtnText: '??????',
            };
            confirmUnbindServerDialog = ngDialog.open({
                template: '/app/views/dialog/confirm-dialog.html',
                scope: $scope,
                overlay: true
            });
        };

        function apiUnbindServerAssign(ids) {
            ClusterStateService.applyClusterServerBatchUnbind($scope.app, ids).success((data) => {
                if (data.code === 0 && data.data) {
                    let failedServerSet = data.data.failedServerSet;
                    let failedClientSet = data.data.failedClientSet;
                    if (failedClientSet.length === 0 && failedServerSet.length === 0) {
                        alert('??????');
                    } else {
                        alert('????????????????????????????????????????????????' + JSON.stringify(failedClientSet));
                    }

                    location.reload();
                } else {
                    if (data.code === UNSUPPORTED_CODE) {
                        alert('???????????? Sentinel ????????????????????????????????????????????? 1.4.0 ????????????????????????????????????');
                    } else {
                        alert('???????????????' + data.msg);
                    }
                }
            }).error(() => {
                alert('????????????');
            });
            // confirmUnbindServerDialog.close();
        }

        // Confirm function for confirm dialog.
        $scope.confirm = () => {
            if ($scope.confirmDialog.type === 'unbind_token_server') {
                apiUnbindServerAssign($scope.pendingUnbindIds);
            } else {
                console.error('Error dialog when unbinding token server');
            }
        };
    }]);
