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
            <h2>Group</h2>
        </div>
        <fieldset>
            <legend></legend>
            <div class="tab_gradient"></div>
            <div class="info_group">
                <form id="frmAdd" name="frmAdd">
                    <div>
                            <div class="body_row">
                                <div class="att_col1">
                                    <label class="strong">Group name: </label>
                                </div>
                                <div class="att_col2">
                                    <input type="text" name="groupname" id="groupname_id" />
                                </div>
                            </div>
                            <div class="body_row">
                                <div class="att_col1">
                                </div>
                                <div class="att_col2">
                                    <input type="submit" value="Add new group" class="btn_gray"/>
                                </div>
                            </div>
                    </div>
                </form>
                <form id="frmDel" name="frmDel">
                    <div>
                        <div class="body_row">
                            <div class="att_col1">
                                <label class="strong">Group List:</label>
                            </div>
                            <div class="att_col2" id="ItemGroupList">
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

        function goAdd(){
//            $("btnEdit").click(function(){
            var items = [];
            $("input:checkbox[name='itemId']:checked").each(function(){
                items.push($(this).val());
            });

            $(document).ready( function () {

                $.getJSON("http://localhost:8091/api/cis/v1/cisgroups/" + items, {format: "json"})
                    .done(function (data) {
//                        $("#groupname_id").val(data.name);
                        var name = data.name;

                        if (name != undefined && name != null) {
                            window.location = 'http://localhost:8091/api/cis/groupadd?id=' + items + '&name=' + name;
                        }
                    });
            });
        }

        function gotoDetail(id){
            $(document).ready( function () {
//
                $.getJSON("http://localhost:8091/api/cis/v1/cisgroups/" + id, {format: "json"})
                    .done(function (data) {
                        var name = data.name;
                        var count = data.click_count + 1;
                        var url = "/api/cis/v1/cisgroups/" + id;
                        var redirectUrl = "/api/cis/groups/" + id + "/detail";
                        var jsonData = { id: id, name: name, click_count: count};

                        $.ajax({
                            type : "PUT",
                            url : url,
                            data: JSON.stringify(jsonData),
                            dataType: "json",
                            contentType: "application/json; charset=UTF-8",
                            success: function (data) {
                                alert("Save success !!!");
                                window.location = redirectUrl;
                            },
                            statusCode: {
                                400: function() {
                                    // if your server return 400 status code then only it comes in this block. :-)
                                    alert("Something wrong!");
                                },
                                500: function(){
                                    alert("Datetime format incorrect!");
                                },
                                401: function(){
                                    alert("Unauthorization!");
                                }
                            }

                        });

                    });
            });
        }


        $(document).ready( function () {
            $.getJSON("http://localhost:8091/api/cis/v1/cisgroups", {format: "json"})
                    .done(function(data) {
                        var trHTML = '';
                        var tableHTML = '<table id = "tbResult"> <thead> <tr>' +
                                '<th><input type="checkbox" id="selectall" onclick="checkAll(this);"/></th>' +
                                '<th>Group Name</th>' + '<th>Click Count</th>' + '<th><img src="images/icon-edit.png" alt="Edit Icon"/></th> </tr>'
                                + '</thead> <tbody> </tbody>' +
                                '</table> <input type="submit" value="Delete" class="btn_gray"/> ';
                        $.each(data.result, function(i, item) {
                            trHTML += '<tr><td class="col8 align_center">' +
                                    '<input type="checkbox" class="check" id="itm' + item.id
                                    +'" value="'+ item.id +'"name = "itemId"/></td>' +
                                    '<td onclick= "gotoDetail(' + item.id + ');">' + item.name + '</td><td>'
                                    + item.click_count + '</td><td>'
                                    + '<input class="icon_edit_btn" id="btnEdit" type="button" onclick="goAdd();" /></td></tr>';
                        });
                        if(data.count != 0){
                            document.getElementById("ItemGroupList").innerHTML = tableHTML;
                            $('#tbResult').append(trHTML);
                        <#--<#assign status = "unstable">-->
                        }else{
                            $("#ItemGroupList").text("No result to display");
                        }
                    });

//            document.getElementById("btnEdit").onclick = function () {
//                location.href = "http://localhost:8091/api/cis/groupadd";
//            };

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
                        url: "http://localhost:8091/api/cis/v1/cisgroups/" + items[i],
                        type: "DELETE",
                        success: function(result) {
                            location.reload();
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
                        groupname = $form.find( "input[name='groupname']" ).val()
                var jsonData = { name: groupname, click_count: 0};
                $.ajax({
                    url: "http://localhost:8091/api/cis/v1/cisgroups",
                    type: "POST",
                    contentType: 'application/json',
                    data: JSON.stringify(jsonData),
                    success: function(result) {
                        location.reload();
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