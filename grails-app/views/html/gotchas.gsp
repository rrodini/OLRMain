<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Gotchas</title>
</head>

<body>
<div class="panel-body">
<p>To avoid frustration with all this technology please follow the advice given here.</p>
<p>Gotchas:</p>
<ol>
   <li>
       <p>
       Play a game on a computer by yourself to get familiar with the steps.  Use one tab within the browser for
       the emcee screen, another tab for the projector screen, and one or two other tabs for players.  Start a game
       and then activate the other tabs one by one.  Ask a couple of questions and answer as one of the players.
       Click "End Game" and return to the application.
       <p>Finally, bookmark the emcee screen and the projector screen.  This will make it much easier to start the next game.</p>
       </p>
   </li>
   <li>
       <p>
       The application it extremely sensitive to the emcee screen losing its connection to the server.  This will happen
       if the screen "times out."  The best way to avoid this is to change the device's time-out setting to "never"
       before starting a game.  This is <b>highly recommended</b>.
       </p>
   </li>
   <li>
       <p>
       The player devices must be able to connect to the game url which is <b>different</b> from the emcee url.  If the device
       is school property, the connection probably goes through a proxy server which may disallow the player url.
       You should contact the network administrator and get the player url white-listed so the connection is allowed.
           <b>A tell-tale indicator of this problem is that the player is never asked to enter the room #.</b>
       </p>
   </li>
   <li>
       <p>The current urls are:</p>
       <table>
           <thead>
             <td>Role</td><td>Url</td>
           </thead>
           <tr>
             <td>emcee</td>
             <td>https://olrmain.herokuapp.com</td>
           </tr>
           <tr>
               <td>projector</td>
               <td>https://olr.herokuapp.com/projector.html</td>
           </tr>
           <tr>
               <td>player</td>
               <td>https://olr.herokuapp.com</td>
           </tr>
       </table>
       <p style="font-weight: bold;">Note: the projector and player urls are different from the emcee url.</p>
   </li>
</ol>
</div>
</body>
</html>