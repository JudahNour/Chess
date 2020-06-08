# CHESS

This is a program version of the classic game of chess. 

## Installation

In order to install this project, you should put all the java files in the src folder into to IDE and run the main method in the GameRunner class

## Rules

The rules of the game are just the rules of regular chess which I will not go over as there is no major deviation from the standard rules besides those noted in the missing elements section. 

However, one rule that many people do not know in chess is the en passant rule which states that if a pawn jumps up 2 moves during the pawns first move and it skips past the square that another pawn is attacking, the attacking pawn can actually capture the pawn on the next move as if it had only went up 1.

## Playing

In order to play the game, you just need to follow the prompts of putting the first row value, the first column value, the first row value, and the second column value. This will simply be the values that you want the piece to move to and from. 

If the move is valid it will switch the players and now the other player will now input his move. Otherwise, it will ask again for these values. 

This continues until there is a stalemate or a checkmate

## Missing Elements

The only things that are missing from the game are the repetition rule, the 50 move rule, and a Timer. The reason for this is that the repetition rule and 50 move rule are generally invoked by players rather than automatically by the computer so I chose not to force this feature like many chess programs. The timer was not added as we must stay within the AP subset for this project.

## Looking Towards The Future

Future versions of this project may include a GUI in order to simplify the inputting process of moves as if we are to add a timer, the speed at which moves are made is essential.

I would also like to look into being able to implement Chess960 as that would be an interesting project that requires only a slight modification of a Board constructor. 