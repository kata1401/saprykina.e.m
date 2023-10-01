#5
a=int(input("Введите число 1 :"))
b=int(input("Введите число 2 :"))
c=int(input("Введите число 3 :"))
def f(a,b,c):
    if a<=b and a<=c:
        return a
    if b<=a and b<=c:
        return b
    else:
        return c
print(f(a,b,c))
