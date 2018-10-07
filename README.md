# tournament-scoring-system
This project takes care of various scoring scenario in a typical tennis game 
A tennis game is a match plasyed between two players (support of multiplayers could be added by extending Game object)
A match consist of a set and a set consist of multiple games(atMost 7) between two players
==============================================================================================================================
Initially the Algorithm of scoring system relied heavily on the rulesEngines . This made classes tightly coupled
In last commit the Algorithm is moved to FSM pattern . 
Both Set and Game Objects maintain their states 
================================================================================================================================
To run this programm 
1) Execute the TennisRunner (Main) class. This will randomize the complete match and the output of each scored point is logged on the console
2) Run MatchProcessorIntegrationTest .This test takes care of few scenarios in an ordinary tennis match
==================================================================================================================================================

Test cases do not cover the consoleLogger and TangibleStatus that is used to convert the GameState into readable messages