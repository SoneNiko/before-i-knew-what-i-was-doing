import re

chars = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
         "w", "x", "y", "z"]


def add_to_result(letters: list[str], result: list[str], word: str):
    for char in word:
        letters.remove(char)
    result.append(word)
    #print(result)


def check_viable(letters: list[str], result: list[str], word: str):
    if word in result:
        return False

    viable = True
    for c in word:
        if c not in letters:
            viable = False

    return viable


def main():
    f = open("words.txt", "r")
    five_letter_words = []
    for c in f:
        word = c.replace("\n", "").lower()
        pattern = re.compile("[A-z]{5}")
        if pattern.fullmatch(word):

            contains_dupe = False
            for char in chars:
                if word.count(char) > 1:
                    contains_dupe = True
                    break

            if not contains_dupe:
                print(word)
                five_letter_words.append(word)

    print(f"Collected {len(five_letter_words)} unique five-letter words without spechail chars and duplicate chars")
    f.close()

    results = []

    for word in five_letter_words:
        letters = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                   "u", "v", "w", "x", "y", "z"]
        result = []

        add_to_result(letters=letters, result=result, word=word)

        for second_word in five_letter_words:
            if check_viable(letters=letters, result=result, word=second_word):
                add_to_result(letters=letters, result=result, word=second_word)

                for third_word in five_letter_words:
                    if check_viable(letters=letters, result=result, word=third_word):
                        add_to_result(letters=letters, result=result, word=third_word)

                        for fourth_word in five_letter_words:
                            if check_viable(letters=letters, result=result, word=fourth_word):
                                add_to_result(letters=letters, result=result, word=fourth_word)

        if len(result) == 4:
            results.append(result)

    for r in results:
        print(r)


if __name__ == '__main__':
    main()
