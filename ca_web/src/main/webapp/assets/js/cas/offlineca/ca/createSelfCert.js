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
            }
        }
    }).on('success.form.bv', function (e) {
        //alert("-----");
        var subjects = $(".subjectItem");
        var subject = "";
        $.each(subjects, function (idx, item) {
            if (idx != subjects.length - 1) {
                subject += $(item).attr("name") + "=" + $(item).val() + ",";
            } else {
                subject += $(item).attr("name") + "=" + $(item).val();
            }
        });
        $("input[name='subjectDn']").val(subject);
    });
});
