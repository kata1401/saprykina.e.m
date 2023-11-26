#Блок А(№7)
A=int(input())
B=int(input())

def f(a, b):
    if a<b:
        f(a, b - 1)
        print(b)
    elif a>b:
        print(a)
        f(a - 1, b)
    else:
        print(a)

f(A,B)

#Блок Б(2)
def f():
    n = int(input("Введите число: "))
    if n == 0:
        return 0, 0
    second_max, max_n = f()
    if n> max_n:
        return max_n, n
    elif n > second_max:
        return n, max_n
    else:
        return second_max, max_n

second, _ = f()
print("Второе по величине число:", second)
