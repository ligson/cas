$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'aliase', title: 'aliase', width: 80},
            {
                field: 'keyType', title: '类型', width: 70, formatter: function (value) {
                if (value == 1) {
                    return "RSA";
                } else {
                    return "SM2";
                }
            }
            },
            {
                field: 'keySize', title: '长度', width: 100
            }
        ]],
        nowrap: false,
        toolbar: [{
            id: 'revokeBtn',
            text: '生成',
            iconCls: 'icon-ok',
            handler: function () {
                $("#showGenKeyDlg").dialog("open");
            }
        }]
    });
});
function genKey() {
    var form = $("#genKeyForm");
    if (form.form("validate")) {
        form.form('submit', {
            success: function (data) {
                var data = eval('(' + data + ')');  // change the JSON string to javascript object
                if (data.success) {
                    form.form("clear");
                    $("#showGenKeyDlg").dialog("close");
                } else {
                    alert(data.errorMsg);
                }
            }
        });
    }
//required:true
}