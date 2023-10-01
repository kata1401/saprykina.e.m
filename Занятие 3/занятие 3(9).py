#9
n=int(input("Введите число:"))
m=int(input("Введите число:"))
k=int(input("Введите число:"))
def f(a,b,c):
    if (a*b-c)%n==0 or (a*b-c)%m==0:
        return "YES"
    else:
        return "NO"
print(f(n,m,k))
