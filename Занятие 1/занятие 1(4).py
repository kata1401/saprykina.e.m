#4
second=int(input("Введите количество секунд:"))
a=second/(60*60*24)  #60*60*24 - количество секунд в сутках
b=second/(60*60*12)  #60*60*12 - количество секунд в дне
s=second/(60*60)     #60*60 - количество секунд в часе
c=second/60          #60 - количество секунд в минуте
print(a)
print(b)    
print(s)
print(c)

