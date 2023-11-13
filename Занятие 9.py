
n=int(input("Введите размер квадратной матрицы:"))
c= input("Введите элементы списка, разделенные пробелами: ").split()
matrix=[[] for _ in range(n)]
for i in range (n):
    a=[]
    for j in range(n):
        if j>=i:
            a.append(int(c[(i*n-(i-1)*i//2)+(j-i)]))
        else:
            a.append(matrix[j][i])
    matrix[i]=a
for i in matrix:
    print(i)


diagonal=[matrix[i][i] for i in range(len(matrix))]
print(diagonal)

s=sum(diagonal)
print(s)



for i in range(n):
    if i%2==0:
        m=s
    else:
        m=1
    for j in range(n):
        matrix[i][j]/=m
        
for i in matrix:
    print(i)
