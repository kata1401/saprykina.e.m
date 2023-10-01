n=int(input("Введите число n :" ))
a1=1
a2=1
k=0
s=2
while k<n-2:
    a3=a1+a2
    s+=a3
    a1=a2
    a2=a3
    k+=1
print(s)
