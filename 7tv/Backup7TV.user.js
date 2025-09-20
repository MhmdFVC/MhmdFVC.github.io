// ==UserScript==
// @name         Backup Uploaded 7TV Emotes
// @namespace    http://tampermonkey.net/
// @version      2025-09-19
// @description  Back up all emotes uploaded by one 7TV user (in 4x size and AVIF format only)
// @author       Mhmd_FVC
// @match        https://7tv.app/users/*/uploaded
// @grant        none

// @require https://cdn.jsdelivr.net/npm/jszip@3.10.1/dist/jszip.js

// ==/UserScript==
(function() {
    let functionInterval = setInterval(theThing, 5000);
    setTimeout(() => {
        clearInterval(functionInterval);
        console.log("Interval stopped.");
    }, 5001);
})();

async function theThing() {
    'use strict';

    //userUrl = "https://7tv.app/users/" + userId + "/uploaded";
	//userName = ; // auto-grabbing the username is not a priority

	// As a userscript, I can make it so that you have to be on the page itself anyway.
	//if !("/^(https:\/\/)?7tv.app\/users\/[A-Z0-9]*\/uploaded$/".test(window.location.href))
	//	return;
    //console.log("7TV Emote Backup - Triggering load event listener...");

    console.log("7TV Emote Backup - Loaded");

    const emoteData = document.getElementsByClassName("emote");
    console.log("7TV Emote Backup - Got Emote Data");
    console.log("As proof, the first emote name is: " + emoteData[0].title.split(" ")[0]);
    const zip = new JSZip();

    for (let i = 0; i < emoteData.length; i++) { // Getting the images and adding them to a zip file
        let emote = emoteData[i]
        let emoteId = emote.href.split("/")[4];
        let emoteUrl = "https://cdn.7tv.app/emote/" + emoteId + "/4x.avif";

        let emoteBlob = await (fetch(emoteUrl).then(response => response.blob()));

        let emoteName = emote.title.split(" ")[0];
        zip.file(emoteName + ".avif", emoteBlob);
        console.log("7TV Emote Backup - Added " + emoteName);
    }

    const zipData = await (zip.generateAsync) ({
        type: "blob",
        streamFiles: true
    })

    const link = document.createElement('a');
    link.href = window.URL.createObjectURL(zipData);
    link.download = "emotes.zip";
    link.click();
    //});
    //return;
}