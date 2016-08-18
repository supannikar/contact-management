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

        #ReindexById .att_col2 label.error, #addNewDetail .att_col2 label.error {
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
        <h2>Add new group detail</h2>
    </div>
    <fieldset>
        <legend></legend>
        <div class="tab_gradient"></div>
        <div class="info_group">
            <form id="addNewDetail" action="/">
                <div>
                    <div class="body_row">
                        <div class="att_col1">
                            <label class="strong">Id: </label>
                        </div>
                        <div class="att_col2">
                            <input type="text" name="detailid" value="${id}" />
                        </div>
                    </div>
                    <div class="body_row">
                        <div class="att_col1">
                            <label class="strong">Name: </label>
                        </div>
                        <div class="att_col2">
                            <input type="text" name="detailname" value="${name}"/>
                        </div>
                    </div>
                    <div class="body_row">
                        <div class="att_col1">
                            <label class="strong">Email: </label>
                        </div>
                        <div class="att_col2">
                            <input type="text" name="detailemail" value="${email}"/>
                        </div>
                    </div>
                    <div class="body_row">
                        <div class="att_col1">
                            <label class="strong">Phone: </label>
                        </div>
                        <div class="att_col2">
                            <input type="text" name="detailphone" value="${phone}"/>
                        </div>
                    </div>
                    <div class="body_row">
                        <div class="att_col1">
                            <label class="strong">Group: </label>
                        </div>
                        <div class="att_col2">
                            <input type="text" name="groupid" value="${groupid}"/>
                        </div>
                    </div>
                    <div class="body_row">
                        <div class="att_col1">
                        </div>
                        <div class="att_col2">
                            <input type="submit" value="Save detail" class="btn_gray"/>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </fieldset>
</div>


<script type="text/javascript">

$( "#addNewDetail" ).submit(function( event ) {

    // Stop form from submitting normally
    event.preventDefault();

    // Get some values from elements on the page:
    var $form = $( this ),
            detailid = $form.find( "input[name='detailid']" ).val(),
            detailname = $form.find( "input[name='detailname']" ).val(),
            detailemail = $form.find( "input[name='detailemail']" ).val(),
            detailphone = $form.find( "input[name='detailphone']" ).val(),
            groupid = $form.find( "input[name='groupid']" ).val();
    var url = "/api/cis/v1/cisdetails/" + detailid;
    var jsonData = { id: detailid, name: detailname, email: detailemail, phone: detailphone, group_id: groupid};

    $.ajax({
        type : "PUT",
        url : url,
        data: JSON.stringify(jsonData),
        dataType: "json",
        contentType: "application/json; charset=UTF-8",
        success: function (data) {
            alert("Save success !!!");
            window.location = "http://localhost:8091/api/cis/details";
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