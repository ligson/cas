$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40},
            {
                field: '状态', title: '类型', width: 50, formatter: function (value, row, index) {
                //ENROLL(0, "申请"), VALID(1, "有效"), REVOKE(2, "吊销"), SUSPEND(3, "挂起");
                if (value == 0) {
                    return "待审批";
                } else if (value == 1) {
                    return "有效";
                } else if (value == 2) {
                    return "已吊销";
                } else if (value == 3) {
                    return "已挂起";
                }
            }
            },
            {
                field: 'serialNumber', title: '证书序列号', width: 50
            },
            {
                field: 'issuerDn', title: '颁发者', width: 50
            },
            {
                field: 'subjectDn', title: '颁发给', width: 50
            },
            {
                field: 'notAfter', title: '开始日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'notBefore', title: '结束日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'reqBufType', title: 'CSR类型', width: 50
            },
            {
                field: 'reqBuf', title: 'CSR', width: 100, formatter: function (value) {
                if (value != null) {
                    return "已生成";
                } else {
                    return "未生成";
                }
            }
            },
            {
                field: 'reqDate', title: '申请日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'approveDate', title: '证书批准日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: "rejectDate", title: "证书批准拒绝日期", sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            }
        ]],
        nowrap: false
        /*toolbar: [{
         id: 'btnadd',
         text: '生成密钥对',
         iconCls: 'icon-ok',
         handler: function () {
         showGenKeyDlg();
         }
         }, {
         id: 'btndelete',
         text: '批量删除',
         iconCls: 'icon-cut',
         handler: function () {
         deleteArticle();
         }
         }]*/
    });
});