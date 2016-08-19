<!DOCTYPE html>
<html>
<head>
    <script type='text/javascript' src="/api/cis/js/jquery-1.11.1.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/api/cis/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/api/cis/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/api/cis/css/tinybox.css"/>

</head>
<body>
<div id="page">
    <header>
        <div id="admin_head">
            <div id="top_menu_logo"></div>
            <h1>Group Management</h1>
        </div>
    </header>
    <div class="info"></div>

    <div class="info">
        <div>
            <h2>Group Detail</h2>
        </div>
        <fieldset>
            <legend></legend>
            <div class="tab_gradient"></div>
            <div class="info_group">
                <form id="frmAdd" name="frmAdd">
                    <div>
                        <div class="body_row">
                            <div class="att_col1">
                                <label class="strong">Name: </label>
                            </div>
                            <div class="att_col2">
                                <input type="text" name="detailname" />
                            </div>
                        </div>
                        <div class="body_row">
                            <div class="att_col1">
                                <label class="strong">Email: </label>
                            </div>
                            <div class="att_col2">
                                <input type="text" name="detailemail" />
                            </div>
                        </div>
                        <div class="body_row">
                            <div class="att_col1">
                                <label class="strong">Phone: </label>
                            </div>
                            <div class="att_col2">
                                <input type="text" name="detailphone" />
                            </div>
                        </div>
                        <div class="body_row">
                            <div class="att_col1">
                                <label class="strong">Group: </label>
                            </div>
                            <div class="att_col2">
                                <input type="text" name="groupid" value="${id}"/>
                            </div>
                        </div>
                        <div class="body_row">
                            <div class="att_col1">
                            </div>
                            <div class="att_col2">
                                <input type="submit" value="Add new detail" class="btn_gray"/>
                            </div>
                        </div>
                    </div>
                </form>
                <form id="frmDel" name="frmDel">
                    <div>
                        <div class="body_row">
                            <div class="att_col1">
                                <label class="strong">Group Detail:</label>
                            </div>
                            <div class="att_col2" id="ItemGroupDetailList">
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </fieldset>
    </div>

    <script type="text/javascript">

        function checkAll(c){
//                    $('.checkItem:checked').attr('checked', false);
            if(c.checked == true){
                $('.check:not(:checked)').attr('checked', true);
            }else {
                $('.check:checked').attr('checked', false);
            }
        }

        function goEdit(id){
//            $("btnEdit").click(function(){
            var items = [];
            $("input:checkbox[name='itemId']:checked").each(function(){
                items.push($(this).val());
            });

            $(document).ready( function () {

                $.getJSON("http://localhost:8091/api/cis/v1/cisdetails/" + items, {format: "json"})
                        .done(function (data) {
//                        $("#groupname_id").val(data.name);
                            var name = data.name;
                            var email = data.email;
                            var phone = data.phone;
                            var groupid = data.group_id;

                            if (name != undefined && name != null) {
                                window.location = 'http://localhost:8091/api/cis/detailadd?id=' + items
                                        + '&name=' + name + '&email=' + email + '&phone=' + phone + '&groupid=' + groupid;
                            }
                        });
            });
        }

        $(document).ready( function () {
            var $form = $( this ),
                    id = $form.find( "input[name='groupid']" ).val();
            $.getJSON("http://localhost:8091/api/cis/v1/cisgroups/" + id + "/detail", {format: "json"})
                    .done(function(data) {
                        var trHTML = '';
                        var tableHTML = '<table id = "tbResult"> <thead> <tr>' +
                                '<th><input type="checkbox" id="selectall" onclick="checkAll(this);"/></th>' +
                                '<th>Name</th>' + '<th>Email</th>' + '<th>Phone</th>' + '<th></th> </tr>'
                                + '</thead> <tbody> </tbody>' +
                                '</table> <input type="submit" value="Delete" class="btn_gray"/> ';
                        $.each(data.result, function(i, item) {
                            trHTML += '<tr><td class="col8 align_center">' +
                                    '<input type="checkbox" class="check" id="itm' + item.id
                                    +'" value="'+ item.id +'"name = "itemId"/></td>' +
                                    '<td>' + item.name + '</td><td>'
                                    + item.email + '</td><td>'
                                    + item.phone + '</td><td>'
                                    + '<input class="icon_edit_btn" id="btnEdit" type="button" onclick="goEdit();" /></td></tr>';
                        });
                        if(data.count != 0){
                            document.getElementById("ItemGroupDetailList").innerHTML = tableHTML;
                            $('#tbResult').append(trHTML);
                        <#--<#assign status = "unstable">-->
                        }else{
                            $("#ItemGroupDetailList").text("No result to display");
                        }
                    });

            $("#frmDel").submit(function(event){
                event.preventDefault();
//                        var $form = $( this ),
//                                id = $form.find( "input:checkbox[name='itemId']:checked" ).val()
                var items = [];
                $("input:checkbox[name='itemId']:checked").each(function(){
                    items.push($(this).val());
                });
                for(i=0; i<items.length; i++){
                    $.ajax({
                        url: "http://localhost:8091/api/cis/v1/cisdetails/" + items[i],
                        type: "DELETE",
                        success: function(result) {
                            location.reload();
//                            window.location = "http://localhost:8091/api/cis/groups/" + groupid + "/detail"
                        },
                        statusCode: {
                            400: function() {
                                alert("Something wrong!");
                            },
                            404: function() {
                                alert("Something wrong!");
                            },
                            500: function(){
                                alert("Something wrong!");
                            }
                        }
                    });
                }

            });

            $("#frmAdd").submit(function(event){
                event.preventDefault();
                var $form = $( this ),
                        detailname = $form.find( "input[name='detailname']" ).val(),
                        detailemail = $form.find( "input[name='detailemail']" ).val(),
                        detailphone = $form.find( "input[name='detailphone']" ).val(),
                        groupid = $form.find( "input[name='groupid']" ).val();
                var jsonData = { name: detailname, email: detailemail, phone: detailphone, group_id: groupid};
                $.ajax({
                    url: "http://localhost:8091/api/cis/v1/cisdetails",
                    type: "POST",
                    contentType: 'application/json',
                    data: JSON.stringify(jsonData),
                    success: function(result) {
//                        location.reload();
                        window.location = "http://localhost:8091/api/cis/groups/" + groupid + "/detail"
                    },
                    statusCode: {
                        400: function() {
                            // if your server return 400 status code then only it comes in this block. :-)
                            alert("Something wrong!");
                        },
                        500: function(){
                            alert("Something wrong!");
                        }
                    }
                });
            });
        });


    </script>

    <footer>
        <span>&copy; 2016 by Supannikar Nontarak. (Thailand). All rights reserved.<br/></span>
    </footer>
</div>
</body>
</html>