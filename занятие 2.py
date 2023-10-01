#1
import math 
x=14.26
y=-1.22
z=3.5*10**-2
s=(2*math.cos(x-2/3))/(1/2+math.pow(math.sin(y),2))* (1+(z**2/(3-((z**2)/5))))
print(s)


#6
import math
x=16.55*10**-3
y=-2.75
z=0.15
s=(10*(x**(1/3)+x**(y+2)))**(1/2)*(math.pow(math.asin(z),2)-math.fabs(x-y))
print(s)


#12
import math
x=3.251
y=0.325
z=0.466*10**-4
s=2**(y**x)+3**(x*y)- y*(math.atan(z)-1/3)/(math.fabs(x)+1/(y**2+1))
print(s)

#13
import math
x=17.421
y=10.365*10**-3
z=0.828*10**5
s= (y+(x-1)**(1/3))**(1/4)/(math.fabs(x-y)*(math.pow(math.sin(z),2)+math.tan(z)))
print(s)
