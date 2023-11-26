import requests
import tkinter as tk
import json


def get_json(name_reposit):
    url = f"https://api.github.com/users/{name_reposit}"
    response = requests.get(url)

    if response.status_code == 200:
        return response.json()
    else:
        return None
def get_repoz():
    name_reposit = entry.get()
    data=get_json(name_reposit)
    if data:
        repository_info = {
            'company': None,
            'created_at': data['created_at'],
            'email': None,
            'id': data['id'],
            'name': data['name'],
            'url': data['url']
            }

        with open('repository.json', "w") as f:
            json.dump(repository_info, f,indent=4)
            print("Информация успешно сохранена в файл 'repository.json'")
    else:
        print("Ошибка при выполнении запроса")


win = tk.Tk()
win.title("Получение информации о репозитории")
win.geometry("400x100")
win.config(bg="#2DFF00")
tk.Label(win, text="Имя репозитория:",
                   bg="red",
                   fg="black",
                   font=("Arial",15,"bold")
                   ).grid()


entry = tk.Entry(win)
entry.grid(row=0,column=1)

tk.Button(win, text="OK", command=get_repoz).grid(row=0,column=2)
win.mainloop()