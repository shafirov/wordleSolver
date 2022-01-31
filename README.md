# wordleSolver
Proposes a word from a dictionary that matches the knowns in a popular worlde game

To use it place your bets and the system outcome to the `input.txt` file each guess on a separate file like in the following example
```
water bbybb
could bbbyb
vinyl bgbby
```

means there were 3 bets: water, could, and vinul and the system gave answers coded with:
b - respective letter not present at all (black)
y - letter is present in the word but in a different position
g - letter is correctly guessed in correct position

then run `./gradlew run` from the command line