

def main():

    for i in range(1, 101):

        if i % 3 == 0 and i % 5 != 0:
            print(f"Fizz {i}")
        elif i % 5 == 0 and i % 3 != 0:
            print(f"Buzz {i}")
        elif i % 3 == 0 and i % 5 == 0:
            print(f"FizzBuzz {i}")
        else:
            print(i)


if __name__ == '__main__':
    main()