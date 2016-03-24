# jBowling
Test Case description
@author: Dmytro Horpynchenko ( horpynchenko.d@gmail.com )

Simple Bowling Java console application ( javaFX in future :) ). 
To see results, run src\main\java\simple.Main.java 

1. Set Players.
By default exists 5 Players: 
- 2 created by name ( Timo and Anton ) 
- 3 by default ( Player-1,  Player-2, Player-3 ).  
  
1.a. For create players by names, in src\main\java\simple.MainExecutor.java, 
in method startInteractiveGame() need sign players as String from 2-nd paremeter. 
To example, to create 2 named players Timo and Anton:
CreatePlayerInterface cpi = new CreateNamedPlayer(mainGame, "Timo", "Anton");

To create no named users, sign empty string as 2-nd paramerer: 
CreatePlayerInterface cpi = new CreateNamedPlayer(mainGame, "");

1.b. To create players with default names, sign quantity of default players as 2-nd parameter.
For example, to create 3 default players:
cpi = new CreateDefaultPlayers(mainGame, 3);

To create no default users, sign 0 (zero) as 2-nd parameter:

2. Set score type. 
By default filling Score type for Players signed Rundomly, in src\main\java\simple.MainExecutor.java: 
mainGame.startGame( new GameTypeRandom() );

To customize different filling Score type for Players: 
- input from console:     mainGame.startGame( new GameTypeConsoleInput() );
- randomly:               mainGame.startGame( new GameTypeRandom() );
- static for all players: mainGame.startGame( new GameTypeStatic() );
