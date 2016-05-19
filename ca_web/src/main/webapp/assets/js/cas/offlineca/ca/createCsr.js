/**
 * Created by ligson on 2016/5/9.
 */
function genCsr() {
    var subjects = $(".subjectItem");
    var subject = "";
    $.each(subjects, function (idx, item) {
        if (idx != subjects.length - 1) {
            subject += $(item).attr("textboxname") + "=" + $(item).val() + ",";
        } else {
            subject += $(item).attr("textboxname") + "=" + $(item).val();
        }
    });
    $("input[name='subjectDn']").val(subject);

    var form = $("#enrollForm");
    form.form("submit", {
        success: function (data) {
            var data = eval('(' + data + ')');  // change the JSON string to javascript object
            if (data.success) {
                $("#csr").empty().append(data.csr);
            } else {
                alert(data.errorMsg);
            }
        }
    });
}
