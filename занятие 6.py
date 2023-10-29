c=(input("Введите строку:"))

def f():
    s=''
    for i in range(int(len(c)/2)):
        if c[i]=="п":
            s+='*'
        else:
            s+=c[i]
    print(s+c[int(len(c)/2)::])
f()          
