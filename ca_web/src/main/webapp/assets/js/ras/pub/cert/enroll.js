/**
 * Created by ligson on 2016/5/9.
 */
$(function () {
    $("#enrollForm").bootstrapValidator({
        message: '输入格式不正确！',
        feedbackIcons: {
            //valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            o: {
                validators: {
                    notEmpty: {
                        message: '禁止为空!'
                    }
                }
            },
            ou: {
                validators: {
                    notEmpty: {
                        message: '禁止为空！'
                    }
                }
            },
            cn: {
                validators: {
                    notEmpty: {
                        message: '禁止为空！'
                    }
                }
            },
            certPin: {
                validators: {
                    notEmpty: {
                        message: '密码禁止为空！'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度是6-30位'
                    }
                }
            },
            certPinRetry: {
                validators: {
                    /*different: {
                     field: "password",
                     message: "两次输入密码不一致！"
                     },*/
                    notEmpty: {
                        message: '确认密码禁止为空！'
                    },
                    identical: {
                        field: 'certPin',
                        message: '两次输入密码不一致！'
                    }
                }
            }
        }
    });
});
