A=int(input("Введите число:"))
B=int(input("Введите число:"))
if A>B:
    for i in range(A,B-1,-1):
        if i%2!=0:
            print(i)
else:
    print("Ваши числа не соответствуют условию A>B")
