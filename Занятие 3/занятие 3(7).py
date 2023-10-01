

#7
a=int(input("Введите год:"))
def f(n):
    if n%4==0 and (n%100!=0 or n%400==0):
        print("Да")
    else:
        print("Нет")
f(a)
