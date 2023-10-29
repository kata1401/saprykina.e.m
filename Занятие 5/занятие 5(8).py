def f():
    n=int(input())
    a=1
    b=1
    while n!=0:
        c=int(input())
        if c==n:
            a+=1
        else:
            if a>b:
                b=a
            if c==0:
                break
            a=1
        n=c
    print(b)
f()
