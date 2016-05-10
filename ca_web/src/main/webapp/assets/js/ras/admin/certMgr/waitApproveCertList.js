$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40},
            {
                field: 'subjectDn', title: '颁发给', width: 50
            },
            {
                field: 'reqDate', title: '申请日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            }
        ]],
        nowrap: false,
        toolbar: [{
            id: 'btnadd',
            text: '批准',
            iconCls: 'icon-ok',
            handler: function () {
            }
        }, {
            id: 'btndelete',
            text: '拒绝',
            iconCls: 'icon-cut',
            handler: function () {
            }
        }]
    });
});