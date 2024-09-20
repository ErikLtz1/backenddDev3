# 'Destroy' Game Server

## Overview

This repository contains the server-side implementation for the **Destroy** game (https://github.com/ErikLtz1/DevsGrp3Frontend). The server is responsible for managing the game state, handling player interactions, and communicating with clients using STOMP (Simple Text Oriented Messaging Protocol). This project was developed as part of a school assignment.

## Developers

- D-Hankin
- brycom
- ErikLtz1
- Samback92

## Features

- Player registration and movement management.\
- Bullet generation and collision detection.\
- Round-based gameplay with automatic score updates.\
- Multi-round game with 4 players.\
- Final score calculation and game reset functionality.\
- Server-client communication using WebSockets (STOMP).

## Technologies Used

- **Java 17**\
- **Spring Boot** for the backend server.\
- **STOMP** for WebSocket messaging.\
- **Maven** for dependency management.

## Endpoints and Messaging

The server uses WebSocket STOMP messaging to communicate with clients. Below are some of the key destinations:

- `/app/new-player`\
  Registers a new player and adds them to the game.

- `/app/update-player-movement`\
  Updates the position of the player on the grid.

- `/app/update-player-score`\
  Updates the score for a player.

- `/app/new-round`\
  Resets the game state for a new round.

- `/app/new-game`\
  Resets the game for a new game session.

## Game Flow

1\. **Player Registration**: Players are registered and assigned unique roles and positions on the grid.\
2\. **Round Gameplay**: The game consists of 4 rounds. In each round, players take turns shooting at the other three, and their positions are updated on the grid.\
3\. **Bullet Handling**: Players shoot bullets that can hit other players. The server detects collisions and updates the active status of players.\
4\. **Score Calculation**: Scores are updated after each hit, and at the end of the round. One point for a hit and 1 poin for surviving the round.\
5\. **Game Reset**: After all rounds are completed, the final scores are confirmed, and the game is reset for a new session.

## Running the Server

1\. Clone the repository.\
   ```bash\
   git clone <repository-url>\
   cd shooting-gallery-server\
   ```

2\. Ensure that **Java 17** and **Maven** are installed on your system.

3\. Build and run the server:\
   ```bash\
   mvn clean install\
   mvn spring-boot:run\
   ```

4\. The server will start on the default port (e.g., `http://localhost:8080`), and clients can connect via WebSocket for game communication.

This is a school project developed to showcase real-time WebSocket-based communication and game state management in a multiplayer environment.
