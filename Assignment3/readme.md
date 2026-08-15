# Assignment 3 - Educational App

# Explanation
- I made this app having skimmed the task sheet and prioritising the rubric over all else, leading to me not exactly fulfilling the requirement of an *interactive* learning experience. The app is intended to be a mirror of applications like [op.gg](https://op.gg) and [League of Graphs](https://www.leagueofgraphs.com), where people *play* League of Legends, and check this application to see how they did. These apps do include advanced educational content in some ways, but I intended for this to be an 'intro to league' so to speak.

- I still believe that this application I've made fulfills a majority of the intended requirements put forth, particularly in interesting API usage/features, navigation and UI. As such, I hope you can see past some of my faults in my mishandling of the task and grade it for what it is intended to be; a tool that accompanies another activity - League of Legends. A lot of time was spent on this app, and I'm sure that's obvious by the scale considering I essentially coded the whole thing in a week.

- Enough of me justifying my inability to read! (though the actual requirements never specify that I MUST include some sort of quiz, puzzle, simulation - they merely heavily suggest it!)

# Using the App
- Having downloaded the app, you'll need to get an API key from Riot. You may have to create an account [here](https://developer.riotgames.com).

- This key goes into 'assignment3/api/ApiKey'

- These development keys expire every 24 hours! So the one in the current GitHub repo won't work, and if you're not willing to create an account you will have to contact me to get one!

- Once running the app, you'll be prompted to enter a user in settings. Admittedly - entering nothing, or an invalid user will just crash the app - so feel free to use my user: Aniki#Aniki. Entering 'Aniki' into each textbox and hitting the go button will run the app as intended.

## Features

## Homepage / Landing Screen
- Once you have a user in, this screen displays some basic info on champions - the user's levels on each champion in descending order, and the champion's name with an icon next to it.

- This is quite a simple screen and serves to simply be where you arrive when you open the app, so I thought best not to overdo it.

## User Screen (Right Hand Side on the Nav Bar)
- This screen displays match history and user rank. Ranked is the premier gamemode in League, and the mode most people care about, so displaying the rank is quite important. The match history LazyColumn displays matches played from most recent downwards, and shows useful information (from left to right): Win/Loss result, champion icon, damage dealt/damage per minute, creepscore/creepscore per minute, vision score/vision score per minute, KDA stats, and items purchased.

- This information is the baseline to see how you did in a League game, hence my choice. Were I given more time, I would have made each clickable to bring up more in-depth metrics, but unfortunately, this would require even more scanning of the Riot-API docs.

## Learn Page (Left Hand Side on the Nav Bar)
- This is the learning page - and gives some basic info on the game. This includes pop-up cards that give extra info on certain things, including the very basics of League, and a few simple champions to pick up if you're new.

- Intended to be for someone with fresh eyes for the game, this page and it's pop up's are the bare minimum to understanding League, and miss or simply elaborate on other information that would otherwise be in the in-game tutorial - so it appears extremely barebones.
