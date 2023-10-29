#3
n=int(input())
def f(c):
    a=2
    i=1
    while a<=n:
        a*=2
        i+=1
    print((i-1), a//2)
f(n)
