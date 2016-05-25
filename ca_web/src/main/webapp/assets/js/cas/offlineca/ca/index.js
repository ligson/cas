$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 80},
            {
                field: 'serialNumber', title: '证书序列号', width: 70
            },
            {
                field: 'issuerDn', title: '颁发者', width: 70
            },
            {
                field: 'subjectDn', title: '颁发给', width: 100
            },
            {
                field: 'notAfter', title: '开始日期', width: 60, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'notBefore', title: '结束日期', width: 60, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'reqBufType', title: 'CSR类型', width: 50
            },
            {
                field: 'reqBuf', title: 'CSR', width: 50, formatter: function (value, row, idx) {
                if (value != null) {
                    return "已生成,<a class='easyui-linkbutton' onclick='viewCsr(" + idx + ")'>查看</a>";
                } else {
                    return "未生成";
                }
            }
            },
            {
                field: 'signDate', title: '入库日期', width: 60, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: "downloadCert", title: "证书信息", formatter: function (value, row, idx) {
                return "<a href='/ca/offlineCa/download.do?certId=" + row.id + "'>查看</a>";
            }
            },
            {
                field: "downloadCertChain", title: "证书链信息", formatter: function (value, row, idx) {
                return "<a href='/ca/offlineCa/downloadChain.do?certId=" + row.id + "'>查看</a>";
            }
            },
            {
                field: "exportPrivateOpt", title: "导出私钥", formatter: function (value, row, idx) {
                return "<a onclick='exportPrivate(\"" + row.id + "\")'>导出</a>";
            }
            },
            {
                field: "createSubCAOpt", title: "创建下级证书", formatter: function (value, row) {
                return "<a href='/ca/offlineCa/createSubCert.html?issueCertId=" + row.id + "'>创建</a>";
            }
            }
        ]],
        nowrap: false,
        toolbar: [{
            id: 'revokeBtn',
            text: '吊销',
            iconCls: 'icon-ok',
            handler: function () {
                showRevokeDlg();
            }
        }, {
            id: 'delBtn',
            text: '删除',
            iconCls: 'icon-ok',
            handler: function () {
                delOfflineCert();
            }
        }, {
            id: "exportJks",
            text: "导出jks",
            iconCls: 'icon-add',
            handler: function () {
                showExportJksDlg();
            }
        }]
    });
});
function delOfflineCert() {
    var userGrid = $("#tt");
    var rows = userGrid.datagrid("getSelections");
    if (rows.length == 0) {
        alert("请选择要删除的证书");
        return;
    }
    var certIds = "";
    for (var i = 0; i < rows.length; i++) {
        if (i != rows.length - 1) {
            certIds += rows[i].id + ",";
        } else {
            certIds += rows[i].id;
        }
    }
    $.messager.confirm("确认", "确定要删除选中的证书吗?", function (data) {
        if (data) {
            var isBreak = false;
            for (var i = 0; i < rows.length; i++) {
                $.ajax({
                    url: baseUrl + "ca/offlineCa/deleteOfflineCert.json",
                    dataType: "json",
                    data: {id: rows[i].id},
                    async: false,
                    type: "POST",
                    success: function (data) {
                        if (!data.success) {
                            alert("删除证书[" + rows[i].subjectDn + "]失败!");
                            isBreak = true;
                        }
                    }
                });
                if (isBreak) {
                    break;
                }
            }
            userGrid.datagrid("reload");
        }
    });
}
function showRevokeDlg() {
    var userGrid = $("#tt");
    var rows = userGrid.datagrid("getSelections");
    if (rows.length == 0) {
        alert("请选择要吊销的证书");
        return;
    }
    var certIds = "";
    for (var i = 0; i < rows.length; i++) {
        if (i != rows.length - 1) {
            certIds += rows[i].id + ",";
        } else {
            certIds += rows[i].id;
        }
    }
    $("#revokeDlg").dialog("open");
    $("#revokeForm").find("input[name=certIds]").val(certIds);
}
function revokeCert() {
    var userGrid = $("#tt");
    var form = $("#revokeForm");
    form.form("submit", {
        success: function (data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                form.form("clear");
                $("#revokeDlg").dialog("close");
                userGrid.datagrid("reload");
            } else {
                alert(data.errorMsg);
            }
        }
    });
}

function viewCsr(idx) {
    var userGrid = $("#tt");
    var rows = userGrid.datagrid("getRows");
    $("#csr").empty().append(rows[idx].reqBuf);
    $("#viewCsrDlg").dialog("open");
}

function showExportJksDlg() {
    var userGrid = $("#tt");
    var rows = userGrid.datagrid("getSelections");
    if (rows.length == 0) {
        alert("请选择要导出的证书");
        return;
    }
    var certIds = "";
    for (var i = 0; i < rows.length; i++) {
        if (i != rows.length - 1) {
            certIds += rows[i].id + ",";
        } else {
            certIds += rows[i].id;
        }
    }

    var dlg = $("#exportJksDlg");
    dlg.dialog("open");
    dlg.find("input[name='certIds']").val(certIds);
}

function exportPrivate(certId) {
    var dlg = $("#downloadP12Dlg");
    dlg.find("input[name='certId']").val(certId);
    dlg.dialog("open");
}
