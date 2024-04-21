# Checkers Game in Java

## Overview
This is a simple implementation of the classic board game Checkers in Java. This game was made as a final project for COMP 352 - Computer Networks. 
This game utilizes Java socket programming to allow for a two player experience through server-client architecture.

## Features
- Two player support via server-client architecture
- Command Line GUI
- User-inputted movement across a two-dimensional array representation of a Checkers board
- Piece differentiation (Kings, normal pieces)
- Win condition implementations

## Installation & Instructions
1. Make sure Java is downloaded on your machine.
2. Clone or download the repository.
3. Open the project in your preferred Java IDE (such as VSCode, Eclipse).
4. Edit the 'CheckersClient.java' and 'CheckersServer.java' file to ensure they have matching ports and that the client is connecting to the proper IP address of the server.
6. Compile the Java files and run the `CheckersServer.java` file to start the server.
7. Run the `CheckersClient.java` class to launch the client application.
8. Have another instance of 'CheckersClient.java' be run to have two players connected to the server.
9. Start playing!

## Usage
- Upon each player's turn, they will receive an updated game state of the board in the command line, and will be prompted for a move.
- Enter a move in the format 'move/jump sourceRow sourceColumn destinationRow destinationColumn'.
- The move will be processed by the server and the new game state will be sent to the other player.

## Credits
- Developed by Sophia Elguera & Ethan Hart
****
