#8.1
x=int(input("x: "))
y=int(input("y: "))
z=int(input("z: "))
t=int(input("t: "))
d=(x**2+y**2)**(1/2) #гипотенуза
p=(z+t+d)/2 # полупериметр треугольника
def f(a,b,c,):
    return (p*(p-a)*(p-b)*(p-c))**(1/2) #площадь одного треугольника

def n(a,b):
    return (a*b)/2 #площадь другого треугольника
print(f(z,t,d))
print(n(x,y))
print("S=", f(z,t,d)+n(x,y))

#8.2
b=int(input())
def f(a):
    m=''
    if a!=0:
        m+=str(a%8)
        a//=8
        return "{0:0>10}".format(m)
print(f(b))
