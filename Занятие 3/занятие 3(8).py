#8

a=int(input("Введите число 1 :"))
b=int(input("Введите число 2 :"))
c=int(input("Введите число 3 :"))

def f(a,b,c):
    if a==b==c:
        print('3')
    if a==b!=c or a==c!=b or c==b!=a:
        print('2')
    if a!=b and b!=c and a!=c:
        print('1')
f(a,b,c)
