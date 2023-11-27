with open('СапрыкинаЕкатерина_У-233_vvod.txt', 'r') as file:
    n = int(file.readline())
    c = file.readline().split()

    matrix = [[] for _ in range(n)]
    for i in range(n):
        a = []
        for j in range(n):
            if j >= i:
                a.append(int(c[(i * n - (i - 1) * i // 2) + (j - i)]))
            else:
                a.append(matrix[j][i])
        matrix[i] = a

with open('СапрыкинаЕкатерина_У-233_vivod.txt', 'w') as file:
    for row in matrix:
        file.write(' '.join(map(str, row)) + '\n')

    diagonal = [matrix[i][i] for i in range(len(matrix))]
    file.write(' '.join(map(str, diagonal)) + '\n')

    s = sum(diagonal)
    file.write(str(s) + '\n')

    for i in range(n):
        if i % 2 == 0:
            m = s
        else:
            m = 1
        for j in range(n):
            matrix[i][j] /= m

    for row in matrix:
        file.write(' '.join(map(str, row)) + '\n')