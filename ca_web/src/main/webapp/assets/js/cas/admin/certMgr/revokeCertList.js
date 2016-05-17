/***
 *  private String certId;
 private String id;
 private String certSerialNumber;
 private Integer revokeReason;
 private Date revokeDate;
 private String certSubjectDn;
 private String certIssueDn;
 private String adminId;
 private String adminName;
 */
$(function () {
    var userGrid = $("#tt");
    userGrid.datagrid({
        fit: true,
        columns: [[
            {field: 'ck', title: 'ckID', width: 40, checkbox: 'true'},
            {field: 'id', title: 'ID', width: 40},
            {
                field: 'certSerialNumber', title: '证书序列号', width: 50
            },
            {
                field: 'certIssueDn', title: '颁发者', width: 50
            },
            {
                field: 'certSubjectDn', title: '颁发给', width: 50
            },
            {
                field: 'revokeDate', title: '吊销日期', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'revokeReason', title: '吊销原因', width: 100, sortable: true, formatter: function (value) {
                if (value != null) {
                    return new Date(value).format("yyyy-MM-dd HH:mm:ss");
                }
            }
            },
            {
                field: 'adminId', title: '吊销人', width: 100, sortable: true, formatter: function (value, row) {
                if (value != null) {
                    return row.adminName;
                } else {
                    return "系统";
                }
            }
            }
        ]],
        nowrap: false,
        toolbar: [{
            id: 'genCrlBtn',
            text: '生成CRL',
            iconCls: 'icon-ok',
            handler: function () {
                //showRevokeDlg();
                $.post(baseUrl + "ca/admin/certMgr/genCrl.json", {}, function (data) {
                    if (data.success) {
                        alert("生成成功");
                    } else {
                        alert("生成失败," + data.errorMsg);
                    }
                }, "json");
            }
        },
            {
                id: 'downloadCrl',
                text: "下载CRL",
                iconCls: 'icon-save',
                handler: function () {
                    window.location.href = baseUrl + "ca/certMgr/downloadCrl.do";
                }
            }
        ]
    });
});