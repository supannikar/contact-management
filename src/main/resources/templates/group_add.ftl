<!DOCTYPE html>
<html lang="en">
<head>
    <!--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>-->
    <script type='text/javascript' src='/api/cis/js/knockout-min.js'></script>
    <script type='text/javascript' src="/api/cis/js/jquery-1.11.1.min.js"></script>
    <script type='text/javascript' src="/api/cis/js/jquery.validate.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/api/cis/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/api/cis/css/styles.css"/>
    <link rel="stylesheet" type="text/css" href="/api/cis/css/tinybox.css"/>


    <style>

        #ReindexById .att_col2 label.error, #addNewGroup .att_col2 label.error {
            color: #FB3A3A;
            display: inline-block;
            padding: 0;
            text-align: left;
            width: 220px;
        }

    </style>

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
        <h2>Add new group</h2>
    </div>
    <fieldset>
        <legend></legend>
        <div class="tab_gradient"></div>
        <div class="info_group">
            <form id="addNewGroup" action="/">
                <div>
                    <div class="body_row">
                        <div class="att_col1">
                            <label class="strong">Group : </label>
                        </div>
                        <div class="att_col2">
                            <input type="text" name="id" value="${id}">
                            <input type="text" name="groupname" value="${name}"/>
                        </div>
                    </div>
                    <div class="body_row">
                        <div class="att_col1">
                        </div>
                        <div class="att_col2">
                            <input type="submit" value="Save group" class="btn_gray"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>


<script type="text/javascript">

$( "#addNewGroup" ).submit(function( event ) {

    // Stop form from submitting normally
    event.preventDefault();

    // Get some values from elements on the page:
    var $form = $( this ),
            groupname = $form.find( "input[name='groupname']" ).val(),
            id = $form.find("input[name='id']").val();
    var url = "/api/cis/v1/cisgroups/" + id;
    var jsonData = { id: id, name: groupname, click_count: 0};

    $.ajax({
        type : "PUT",
        url : url,
        data: JSON.stringify(jsonData),
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert("Save success !!!");
            window.location = "http://localhost:8091/api/cis/groups";
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

</script>
</div>

<footer></footer>

</body>
</html>