#1
n=int(input())
c=0
k=1
d=[i for i in range(n+1)]
for i in range(n+1):
    if d[i]%2==0:
        c+=d[i]
    else:
        k*=d[i]
print(d)
print(c,k)
max1, min1 = d.index(max(d)), d.index(min(d))
d[max1], d[min1] =d[min1], d[max1]
print(d)
