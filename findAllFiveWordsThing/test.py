from main import check_viable

letters = ["f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                   "u", "v", "w", "x", "y", "z"]

result = []

print(check_viable(letters=letters, result=result, word="lksdj"))
print(check_viable(letters=letters, result=result, word="uiopt"))


