/**
 * Created by ligson on 2015/4/17 0017.
 * 参考文档http://www.jq22.com/yanshi522
 */
$(function () {
    $("#register_form").bootstrapValidator({
        message: '输入格式不正确！',
        feedbackIcons: {
            //valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '该项不能为空!'
                    }
                }
            },
            password: {
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
            password2: {
                validators: {
                    /*different: {
                     field: "password",
                     message: "两次输入密码不一致！"
                     },*/
                    notEmpty: {
                        message: '确认密码禁止为空！'
                    },
                    identical: {
                        field: 'password',
                        message: '两次输入密码不一致！'
                    }
                }
            }

        }
    });
});