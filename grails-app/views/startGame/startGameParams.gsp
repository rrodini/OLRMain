<html>
<head>
    <%-- meta name="layout" content="main" /--%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" >
    <title>Start Game</title>
    <asset:stylesheet src="jquery.bootgrid.css"/>
    <asset:stylesheet src="jquery.steps.css"/>
    <asset:stylesheet src="bootstrap.css"/>

    <!-- get the needed javascript -->
    <asset:javascript type="text/javascript" src="jquery-2.2.0.min.js"/>
    <asset:javascript type="text/javascript" src="bootstrap.js"/>
    <asset:javascript type="text/javascript" src="jquery.bootgrid.min.js"/>
    <asset:javascript type="text/javascript" src="jquery.bootgrid.fa.min.js"/>
    <asset:javascript type="text/javascript" src="jquery.steps.min.js"/>

</head>
<body>
<div id="steps">
    <!-- Step 1: section Game Type -->
    <h1>Select Game Type:</h1>
    <section>
        <div style="display:inline-block">
            <span style="float: left">
            <input type="radio" id="prGame" name="gameType" value="0" style="display:inline-block" checked="checked">
            <label for="prGame">Presentation Round</label>
            </span>
            <span>&nbsp; No players. Emcee projects questions to the audience for review.
        </div>
        <div style="display:inline-block">
            <span style="float: left">
            <input type="radio" id="olrGame" name="gameType" value="1" style="display:inline-block">
            <label for="olrGame">Open Lightning Round </label>
            </span>
            <span>&nbsp; Open to players (or teams) with a device. Emcee marks answers "corrent" or  "incorrent".</span>
        </div>
        <div style="display:inline-block">
            <span style="float: left">
            <input type="radio" id="cdrGame" name="gameType" value="2" style="display:inline-block">
            <label for="cdrGame">Countdown Round </label>
            </span>
            <span>&nbsp; Must use a player file.  Formal Countdown Rules rules apply.</span>
        </div>
   </section>
    <!-- Step 2: section Question File -->
    <h1>Select Question File</h1>
    <section>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-7">
                    <div class="table-responsive">
    <table id="questionFileGrid"
           class="table table-condensed table-hover table-striped"
           data-selection="true" data-row-select="true"
           data-keep-selection="true">
     <thead>
        <tr>
            <th data-column-id="id" data-identifier="true"
                data-type="numeric" data-align="right"
                data-header-align="right" data-width="10%">Id</th>
            <th data-column-id="description" data-align="left"
                data-header-align="center" data-width="80%">Description</th>
        </tr>
    </thead>
    <tbody>
       <g:each in="${questionFileList}"  >
       <tr>
           <td>${it.id}</td>
           <td>${it.questionsJson}</td>
       </tr>
       </g:each>
    </tbody>
    </table>
                    </div>
                </div>
            </div>
        </div>

    </section>
<!-- Step 3: section Player File -->
    <h1>Select Player File</h1>
    <section>
        <div id="playerFileSection" class="container-fluid">
            <div class="row">
                <div class="col-md-7">

                    <div class="table-responsive">

    <table id="playerFileGrid"
           class="table table-condensed table-hover table-striped"
           data-selection="true" data-row-select="true"
           data-keep-selection="true">
        <thead>
        <tr>
            <th data-column-id="id" data-identifier="true"
                data-type="numeric" data-align="right"
                data-header-align="right" data-width="10%">Id</th>
            <th data-column-id="description" data-align="left"
                data-header-align="center" data-width="80%">Description</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${playerFileList}"  >
            <tr>
                <td>${it.id}</td>
                <td>${it.playersJson}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
<g:javascript>
var emceeId = ${emceeId};
var urlStartGame = "${urlStartGame.encodeAsRaw()}";
var urlGameEmcee = "${urlGameEmcee.encodeAsRaw()}";
</g:javascript>
<script>
    var gameType = 0; // valid => Presentation Round
    var questionFileId = 0;  // invalid
    var playerFileId = 0;  // invalid
    var tab2Seen = false;
    var tab2Content;

    function initGrid(which) {
        $(which).bootgrid({
            navigation : 3, // 0=>none,1=>header(search),2=>footer(pagination),3=>both
            rowCount : [ 5] // first # is count of rows displayed, others are irrelevant.
                                // WARNING: if too many rows are displayed, the pagination widget disappears!
        });

    }
    // Save the content for display later on.
    tab2Content = $("#playerFileSection").html();
    $("#steps").steps({
        headerTag : "h1",
        bodyTag : "section",
        cssClass : "wizard",
        onStepChanging : function(event, currIndex, newIndex) {
            // always allow a return to previous step
            if (currIndex > newIndex) {
                return true;
            }
            if (newIndex === 1) {
                // display question file grid
                return true;
            } else if (newIndex === 2) {
                // display player file grid
                return true;
            }
        },
        onFinishing : function(event, currIndex) {
            // validate that a questionFile was chosen
            if (questionFileId === 0) {
                alert("You must chose a question file.");
                return false;
            }
            // validate that a playerFile was chosen
            if (gameType === 2 && playerFileId === 0) {
                alert("You must chose a player file.")
                return false;
            }
            return true;
        },
        onFinished : function(event, currIndex) {

            //alert('game type: ' + gameType +' , question id: ' + questionFileId + ', player id: ' + playerFileId);
            startGame();
            return true;
        }
    });
    initGrid("#questionFileGrid");
    $("#questionFileGrid").bootgrid().on("selected.rs.jquery.bootgrid",
        function(e, rows) {
            questionFileId = rows[0].id;
        });
    // Don't show Player file selection at start.
    // Wait until Countdown Round is selected.
    $("#steps").steps("remove", 2);
    $("#prGame,#olrGame,#cdrGame").change(function() {
        gameType = $('input[name=gameType]:checked').val();
        gameType = parseInt(gameType);
        if (gameType === 2) {
            $("#steps").steps("add", {
                title : "Select Player File",
                content : tab2Content
            });
            initGrid("#playerFileGrid");
            $("#playerFileGrid").bootgrid().on("selected.rs.jquery.bootgrid",
                function(e, rows) {
                    playerFileId = rows[0].id;
                });
            tab2Seen = true;
        } else if (tab2Seen) {
            $("#steps").steps("remove", 2);
            tab2Seen = false;
        }
    });
</script>
<asset:javascript type="text/javascript" src="startGame.js"/>
</body>
</html>