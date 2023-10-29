def f():
    s=0
    n=int(input())
    while n!=0:
        d=int(input())
        if d!=0 and n<d:
            s+=1
        n=d
    print(s)
f()
