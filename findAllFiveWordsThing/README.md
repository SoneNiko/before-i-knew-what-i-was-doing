# findAllFiveWordsThing

I saw a youtube thumbnail once that had a text on it. I forgot what video it was i am sorry.
But the text on the thumbnail was something along the lines of: "Having all 25 characters of the alphabet in just five words of five characters"
or somehting like that. I do recognise that the english alphabet is 26 characters long. But I was bored and thats why i made this
stupid little script that takes all the words in a txt file (which i have gitignored cus it was something stupid like 5MB big)
called words.txt. this text gets all 5 character long words with no special characters and duplicate chars, converts them to lower-case
and then bruteforces all possible combinations of those words and if the combination contains 25 different characters spread across the words
it adds them to a list of results and prints them at the end.
Its a terrible inefficient solution that produces duplicate results, spams your terminal with words from your input file.
It is this way because i just wanted to see whether it was possible to find such a combination of words. Apparently it is very possible
due to the high amount of weird words in the english language, but then again i used a very generous dictionary that includes "words"
like "klutz" and "zwick" which are propably not in use by any sane english speaker
So in case you need soomething like this, here you go. 

If you feel like making this more usable feel free to PR on the off chance i wont ignore the PR
