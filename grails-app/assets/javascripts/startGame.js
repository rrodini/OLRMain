// The variables below must be passed to the gsp startGameParams!
// var urlStartGame
// var urlGameEmcee

function startGame() {
    var gameId;
    console.log(urlStartGame);
    console.log(urlGameEmcee);
    $.ajax({
//        url: 'http://localhost:3001/startGame',
        url: urlStartGame,
        method: 'POST',
        data: {
            game_type: gameType, // values: 0, 1, 2
            question_file_id: questionFileId,
            player_file_id: playerFileId,
            emcee_id: emceeId,
        },
        success: function(data) {
            gameId = data.gameId;
//            console.log('http://localhost:3001/game' + '?gamedId=' + gameId);
            console.log(urlGameEmcee + '?gamedId=' + gameId);
//            location.href = 'http://localhost:3001/game' + '?gameId=' + gameId;
            location.href = urlGameEmcee + '?gameId=' + gameId;
        },
        failure: function(jqXHR, errMsg, errorThrown) {
            console.log(errMsg);
        }
    })
    .done (function() {
        console.log('post to node app successful');
    })
    .fail(function(){
        console.log("post to node app failed");
    });


}