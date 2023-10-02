#10

n=int(input("Введите число n :"))
k=int(input("Введите число k :"))
fib=[0,1]
for i in range(2, k+n):
    fib.append(fib[i-1]+ fib[i-2])
s=sum(fib[k:k+n])
print(s)
