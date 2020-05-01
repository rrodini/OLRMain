/*
 Projector.js displays the question under construction in a popup window.
 The html and css for the popup window should MATCH the html and css of the
 projector.html page within the OLR projector window.
 */
// below is the html for top of popup window
const popupHtmlTop = `
<html lang="en">
<head>
<meta content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
<title id='title'>Projector</title>
<link rel="stylesheet" href="/assets/projector.css" type="text/css" />
<!--
<script type="text/x-mathjax-config">
 MathJax.Hub.Config( {
   jax: ["input/AsciiMath","output/CommonHTML"]
 });
</script>
-->
<!-- somehow this script automatically generates ASCII Math formulas. -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.2/latest.js?config=AM_CHTML"></script> 
</head>
<body id="container">
`
// the question text goes here in a <div>
// a nested <div> for an optional img with data URL goes here
// next a declaration like: let format = 3;
// below is the html for bottom of popup window
const popupHtmlBottom = `
<div class="align-radio-center">
<fieldset>
<input type="radio" name="format" id="f1" value="1">
<input type="radio" name="format" id="f2" value="2">
<input type="radio" name="format" id="f3" value="3">
<input type="radio" name="format" id="f4" value="4">
<input type="radio" name="format" id="f5" value="5">
</fieldset>
</div>
<script>
// set up event listener on the radio buttons.
function changeFormat() {
    console.log("changeFormat() called.")
    var formatButtons = document.getElementsByName("format");
    for (let i=0; i < formatButtons.length; i++) {
        formatButtons[i].onclick = function() {
            const centralDiv = document.getElementById("central_div");
            console.log("format change: " + i);
            let klass = "";
            switch (i) {
        case 0: klass = "very-short"; break;     
        case 1: klass = "short"; break;     
        case 2: klass = "medium"; break;     
        case 3: klass = "long"; break;     
        case 4: klass = "very-long"; break;     
            }
            format = i + 1;
            centralDiv.className = klass;
        };
    }
    // set the button to reflect format on gsp screen.
    formatButtons[format-1].click();
}
window.onload = changeFormat;
window.onunload = function () { window.opener.setFormat(format)};
</script>
</body>
</html>
`
// this is the script for the question window itself.
let popup;
let figureBase64;
// open the popup window with the raw queston and the optional figure image.
function openPopup(newText) {
    "use strict";
    // ATTENTION: 800x600 was chosen to favor devices with 16:9 aspect ratios.
    popup = window.open("about:blank", "popup", "width=800, height=480");
    popup.document.open();
    popup.document.write(popupHtmlTop);
    //console.log(popupHtmlTop);
    popup.document.write("<div id='central_div' class='xyz' >");
    //console.log("<div id='central_div' class='xyz' >");
    popup.document.write(newText);
    //console.log(newText);
    if (figureBase64) {
        const img = "<div class='figure'><img src=\'data:image/png;base64," + figureBase64 + "\' ></div>"
        popup.document.write(img);
        //console.log(img);
    }
    popup.document.write("</div>");
    //console.log("</div>");
    popup.document.write(("<script>let format = ") + $("#format").val() + ";</script>");
    //console.log("<script>let format = ") + $("#format").val() + ";</script>");
    popup.document.write(popupHtmlBottom);
    //console.log(popupHtmlBottom);
    popup.document.close();
}
// display the popup window with the question.
function openPopupWithQuestionText(e) {
//    e.stopPropagation();
    e.preventDefault();
    openPopup($("#qText").val());
}
// not used
function openPopupWithAnswerText(e) {
    openPopup($("#aText").val());
}
// handle the change event on the figure file.
function loadFile(e) {
    let reader = new FileReader();
    let fileCount = e.target.files.length;
    //console.log("# files: " + e.target.files.length);
    if (fileCount == 0) {
        // no file selected => interpret as "remove file"
        figureBase64 = ""; // falsy value for strings
        //console.log("file name: [deleted]");
        $("#figureName").text("[deleted]"); // don't use "" as new value!
    } else {
        let file = e.target.files[0];
        let fileName = file.name;
        //console.log("file name: " + fileName);
        $("#figureName").text(fileName);
        reader.readAsDataURL(file);
        reader.onload = function() {
            figureBase64 = reader.result.replace(/^data:.+;base64,/, '');
        }
    }
}
// now set the format from the popup window
function setFormat(f) {
    $("#format").val(f);
}
// get the format for the popup window.
function getFormatClass() {
    let f = $("#format").val();
    let klass = "";
    if (f === undefined) {
        f = 3;  // medium
    }
    f = f-1;
    switch (f) {
        case 0: klass = "very-short"; break;
        case 1: klass = "short"; break;
        case 2: klass = "medium"; break;
        case 3: klass = "long"; break;
        case 4: klass = "very-long"; break;
    }
    return klass;
}
// set up the event listeners.
$(document).ready(function() {
    "use strict";
    $("#btnQuestion").click(openPopupWithQuestionText);
    figureBase64 = $("#figureBase64").val();
    $("#imgFile").change(loadFile);
});


