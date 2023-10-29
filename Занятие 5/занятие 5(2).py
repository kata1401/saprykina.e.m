#2
n=int(input("n>2:"))
def f(c):
    i=1
    while i<=n:
        i+=1
        if n%i==0:
            print(i)
            break

f(n)
    
