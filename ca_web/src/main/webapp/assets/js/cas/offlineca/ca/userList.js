$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 80},
            {
                field: 'name', title: '登录名', width: 70
            },
            {
                field: 'status', title: '状态', width: 100
            },
            {
                field: 'createDate', title: '注册日期', width: 60, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'modifyPwdOpt', title: '修改密码', width: 60, sortable: true, formatter: function (value, row, idx) {
                return "<a onclick='showModifyPwdDlg(" + idx + ")'>修改</a>";
            }
            }
        ]],
        nowrap: false
        /* toolbar: [{
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
         }]*/
    });
});

function showModifyPwdDlg(idx) {
    var userGrid = $("#tt");
    var rows = userGrid.datagrid("getRows");
    var id = rows[idx].id;
    var dlg = $("#modifyPwdDlg");
    dlg.find("input[name='id']").val(id);
    dlg.dialog("open");
}
function modifyPwd() {
    var dlg = $("#modifyPwdDlg");
    $("#modifyPwdForm").form("submit", {
        success: function (data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                dlg.dialog("close");
                alert("密码修改成功!");
            } else {
                alert(data.errorMsg);
            }
        }
    });
}