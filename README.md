<!--
SPDX-FileCopyrightText: 2026 Michael Dippel

SPDX-License-Identifier: MIT
-->

# Connect Four

A small terminal implementation of Connect Four in Java. Two players take
turns dropping discs into one of 7 columns; the first to connect four discs
in a row (horizontally, vertically or diagonally) wins.

## Project structure

```
connect4/
├── pom.xml                                   # Maven build file, declares JUnit 5 dependency
├── src/
│   ├── main/java/connect4/
│   │   ├── Main.java                         # Entry point, handles terminal I/O
│   │   ├── Connect4Game.java                 # Turn order and win/draw detection
│   │   ├── Board.java                        # Board state and rules
│   │   └── Player.java                       # The two players
│   └── test/java/connect4/
│       └── BoardTest.java                    # JUnit 5 tests for Board
└── README.md
```

## Requirements

- JDK 21+
- Maven 3.9+

## Build and run

```
mvn package
java -cp target/classes connect4.Main
```

## Run the tests

```
mvn test
```

## License

See the license headers in individual files (REUSE/SPDX annotated).
