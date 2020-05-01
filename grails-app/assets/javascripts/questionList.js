// below is the html for top of popup window
const popupHtml = `
<!DOCTYPE html>
<html lang="en">
<head>
<!-- <meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/> -->
<title id='title'>Question File description</title>
<link rel="stylesheet" href="/assets/main.css" type="text/css" />
</head>
<body id="container" class="fieldcontain">
<label>Description:</label>
<textarea id="desc" cols="60" rows="10" class="property-value"></textarea>
<fieldset class="buttons">
 <input type="submit" name="save" class="save" id="btnSave" value="Save">
 </fieldset>
<script>
function saveClicked() {
    const descId = document.getElementById("desc");
    const desc = descId.value;
    console.log('desc: ' + desc);
    window.opener.setDescriptionAndSubmit(desc);
    window.close();
}

window.onload = function() { 
    document.getElementById("btnSave").onclick = saveClicked;
};
</script>
</body>
</html>
`
let popup;
function openPopup(e) {
    "use strict";
    // Must do this otherwise the form is submitted prematurely.
    e.preventDefault();
    // ATTENTION: width and height values are result of experimentation.
    const width = 600;
    const height = 235;
    const top = (screen.width - width) / 2;
    const left = (screen.height - height) / 2
//    popup = window.open("about:blank", "popup", "width=500, height=250, top=-1000, left=-1000");
    popup = window.open("about:blank", "popup",
        "width=" + width  +
                 ", height=" + height +
                 ", top=" + top +
                 ", left=" + left );
    popup.document.open();
    popup.document.write(popupHtml);
    popup.document.close();
}

function setDescriptionAndSubmit(desc) {
    $("#description").val(desc);
    // Now do the real submit of form.
    $("#questionListForm").submit();
}

// set up the event listeners
$(document).ready(function () {
    "use strict"
    $("#btnSave").attr("disabled", $("#questionCount").val() === 0);
    $("#btnSave").click(openPopup);
})


