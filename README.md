# jBowling

*@author: Dmytro Horpynchenko ( horpynchenko.d@gmail.com )*

Simple Bowling Java console application ( javaFX in future :) ). 
The program counts the number of points each Player after each ball throw.

**To see results run _src\main\java\simple.Main.java_** 

## Set Players.

By default exists 5 Players: 
- *2 created by name ( Timo and Anton )*
- *3 by default ( Player-1,  Player-2, Player-3 )*  
  
*1.* For create players by names, in **_src\main\java\simple.MainExecutor.java_**, 
in method startInteractiveGame() need sign players as String start with 2-nd parameter,
any players number, separates by coma.
For example, to create 2 named players Timo and Anton:
```java 
CreatePlayerInterface cpi = new CreateNamedPlayer(mainGame, "Timo", "Anton");
```
To create no named users, sign empty String as 2-nd parameter (or remove interface implementation :-) ) :
```java 
CreatePlayerInterface cpi = new CreateNamedPlayer(mainGame, "");
```
*2.* To create players with default names, sign number of default players as 2-nd parameter.
For example, to create 3 default players:
```java 
cpi = new CreateDefaultPlayers(mainGame, 3);
```
To create no default users, sign 0 (zero) as 2-nd parameter (or remove interface implementation :-) ):
```java 
cpi = new CreateDefaultPlayers(mainGame, 0);
```

## Set points type.
 
By default Pints filing type for Players signed Rundomly, in **_src\main\java\simple.MainExecutor.java_**: 
```java 
mainGame.startGame( new GameTypeRandom() );
```

To customize different game-points filing type, you can also select one of the following interface realizations: 
- *input from console:*     
```java 
mainGame.startGame( new GameTypeConsoleInput() );
```
- *randomly:*               
```java 
mainGame.startGame( new GameTypeRandom() );
```
- *static for all players:* 
```java 
mainGame.startGame( new GameTypeStatic( <staticPoints in int> ) );
```

