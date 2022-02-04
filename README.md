# wordleSolver
Proposes a word from a dictionary that matches the knowns in a popular worlde game

To use it place your bets and the system outcome to the `input.txt` file each guess on a separate file like in the following example
```
water bbybb
could bbbyb
vinyl bgbby
```

means there were 3 bets: water, could, and vinul and the system gave answers coded with:<br/>
b - respective letter not present at all (black)<br/>
y - letter is present in the word but in a different position (yellow)<br/>
g - letter is correctly guessed in correct position (green)<br/>


then run `./gradlew run` from the command line

Supported dictionaries are English and Russian with automatic detection based on actual input.
