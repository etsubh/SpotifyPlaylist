# SpotifyPlaylist

This project builds a simple version of Spotify style daily mixes. Songs are sorted into playlists based on genre percentages, and each playlist only accepts songs that meet its requirements.
The project focuses on building everything from scratch, including a custom queue, instead of relying on built in Java structures.

## What it does

- Stores songs with pop, rock, and country values  
- Creates playlists with specific genre ranges  
- Checks if a song fits a playlist before adding it  
- Organizes songs using a queue structure  
- Reads data from input files  

## Main Classes

- `Song` handles song data  
- `GenreSet` stores genre values  
- `ArrayQueue` is a custom queue implementation  
- `Playlist` manages songs and requirements  
- `PlaylistReader` reads data from files  
- `ProjectRunner` runs the program  

## How to run

1. Open the project in Eclipse  
2. Add the required CS2 data structures library  
3. Make sure the input text files are in the project folder  
4. Run `ProjectRunner.java`  

## Why I made this

This was built for a Virginia Tech CS project to practice data structures, object oriented design, and testing. 
It helped me get more comfortable working with queues, file input, and structuring larger programs.

## Author

Etsub Habtewold
