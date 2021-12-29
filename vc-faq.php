<html>
<title>Vice City General Speedrun FAQ</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<?php include_once "navbar.html" ?>

<body>
<h1>Vice City General Speedrun FAQ</h1>

<b>Q: What's all this "onmission" stuff about?</b>
<p>A: For example, you can <b>start a rampage + Vigilante at the same time</b> ("rampage-starting") (using a saved replay; rampages can be activated when picked up in a replay), and then <b>cancel Vigilante so that the game thinks you are not on a "mission"</b>, when in fact the rampage is still running.<br><br>
In this example, you'd then be able to start a mission, and then fail or complete the rampage, at which point the game once again sets the mission state to 0, meaning you are not considered to be on a mission (the variable in code is <b>$ONMISSION</b>, abbreviated to "om". <b>om0 = "not on a mission"</b>, <b>om1 = "on a mission"</b>). From here, you can start other missions while still actually on a mission, producing highly glitchy (and sometimes fast) results. onmission state can also be manipulated using save markers and phone calls.</p>
<br>
<b>Q: Why is the game in Asian?</b>
<p>A: Full-game speedruns of Vice City are typically done on the Japanese version because it is one of the so-called <b>"Haitian-friendly" versions</b> (look it up if you don't know what that means). This means <b>the mission script has been slightly modified from the original game</b>, which in turn is relevant for different <b>instapasses</b> (see below if you don't know what that is).<br><br>
The only other Haitian-friendly PC versions are the German Green Pepper version (no rampages and two cut missions, severely altering the route in a bad way and its 100% isn't considered 100% for leaderboard purposes), the Australian version (game crashes on Dildo Dodo and upon entering the Ammu-Nation menu), and the Polish version (technically unofficial and same issues as Australian version).<br>
You can technically use the Australian version for No SSU since Ammu-Nation isn't used in the route, but there's no real advantage to being able to switch languages nowadays anyway.</p>
<br>
<b>Q: OK, but what's an instapass?</b>
<p>A: It's shorthand for instant pass (<b>further abbreviated to "IP"</b>), i.e. instantly completing a mission by starting it while on a certain part of another mission.<br><br>
  For example, if you start Distribution while on the first part of Hit the Courier <b>om0</b> (on the Japanese version), it will instantly pass Distribution.<br><br>
  It just so happens that the part of Hit the Courier's mission script before you obtain the plates has about the same offset into the mission code as the mission pass section for Distribution on the Japanese version, and for some reason starting another mission carries over the first mission's script offset. Thereby which it is "instapassed". (<a target="_blank" href="https://cdn.discordapp.com/attachments/908364377021886524/917919389813190666/unknown.png">Here's a visual example courtesy of Patrick</a>)<br><br>
 
There are quite a few different instapasses, many of which can be observed in All Missions and 100% runs.<br>
A non-exhaustive list of the instapasses you may see (many are 'Japanese-exclusive'):<br>
<ul>
<li>Paramedic (with the start of the G-Spotlight elevator cutscene)</li>
<li>Pizza Boy (with Love Juice → Spilling the Beans → suicide)</li>
<li>Demolition Man (with The Shootist, between rounds 2 and 3)</li>
<li>Friendly Rivalry (with Bombs Away!)</li>
<li>Bombs Away! (with Vigilante)</li>
<li>Spilling the Beans (with the "I had a beautiful woman" voice line from Trojan Voodoo, softlocked by cancelling a held phone call)</li>
<li>RC Raider (with the remaining instance of Spilling the Beans, after threatening the dude on the boat)</li>
<li>Distribution/Ice Cream Mission (with the very start of Hit the Courier)</li>
<li>Cabmaggedon (also with the very start of Hit the Courier)</li>
<li>Double IP (instapassing The Job with the final cutscene fade of Keep Your Friends Close, and then using the remaining instance of The Job to instapass Keep Your Friends Close using the "Hey Tommy, why've we stopped" voice line, softlocked by a phone call)</li>
</ul></p>
<br>
<b>Q: How did you teleport?</b>
<p>A: You can pick up objects within replays.
<ul>
  <li>If you save one over a property purchase marker, you can buy that property through the saved replay, and it'll place you at the property that you just purchased.
  <li>As for save markers, they only work when you're in the same zone as the save marker that you made a replay over (but they can be "buffered" to teleport you as soon as you enter the property's zone). For example, if you want to teleport to the Vercetti Mansion via a save marker replay, you'd have to be considered by the game to be on Starfish Island, otherwise you would not warp. But if you play the replay and then enter Starfish Island afterwards while not on a mission, you'd teleport as soon as you enter Starfish.</li>
</ul></p>
<br>
<b>Q: What is that sliding thing?</b><br>
<p>A: More replay stuff. When you change movement animations and play a replay at the same time, Tommy will begin sliding, which works during phone calls and is very useful for heavy weapon rampages, since you go a lot faster than the forced slow walking speed with those weapons.</p>
</body>
</html>