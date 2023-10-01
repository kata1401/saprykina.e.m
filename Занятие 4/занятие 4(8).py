n = int(input("Введите число n<=9:"))
for i in range(n+1):
    for c in range(1, i + 1):
        print(c,end="")
    print()
