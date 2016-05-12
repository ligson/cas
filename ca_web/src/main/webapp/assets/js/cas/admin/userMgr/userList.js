$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40},
            {
                field: 'status', title: '状态', width: 50, formatter: function (value, row, index) {
                if (value == 1) {
                    return "有效";
                } else {
                    return "禁用";
                }
            }
            },
            {
                field: 'mobile', title: '手机号', width: 50
            },
            {
                field: 'email', title: '邮箱', width: 50
            },
            {
                field: 'role', title: '角色', width: 50, formatter: function (value) {
                // USER(1, "普通用户"), RA_ADMIN(2, "RA管理员"), CA_ADMIN(3, "CA管理员"), SUPER(100, "超级管理员");
                if (value == 1) {
                    return "普通用户";
                } else if (value == 2) {
                    return "RA管理员";
                } else if (value == 3) {
                    return "CA管理员";
                } else if (value == 100) {
                    return "超级管理员";
                } else {
                    return value;
                }

            }
            },
            {
                field: 'registerDate', title: '注册日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            }
        ]],
        nowrap: false,
        toolbar: [{
            id: 'btnadd',
            text: '创建用户',
            iconCls: 'icon-add',
            handler: function () {
                showAddUserDlg();
            }
        }]
    });
});

function showAddUserDlg() {
    $("#addUserDlg").dialog("open");
}
function addUser() {
    var form = $("#addUserForm");
    if (form.form("validate")) {
        form.form('submit', {
            success: function (data) {
                var data = eval('(' + data + ')');  // change the JSON string to javascript object
                if (data.success) {
                    form.form("clear");
                    $("#addUserDlg").dialog("close");
                    $("#tt").datagrid("reload");
                } else {
                    alert(data.errorMsg);
                }
            }
        });
    }
}