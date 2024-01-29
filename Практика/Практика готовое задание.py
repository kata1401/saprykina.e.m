import tkinter as tk
from tkinter import messagebox
import random
import string
import hashlib
import pyperclip


class PasswordManager:
    def __init__(self, win):
        self.win = win
        self.win.title("Менеджер")
        self.win.geometry('300x500+100+100')
        self.win.config(bg='#D235D2')

        self.passwords = {}

        # Создание и настройка виджетов интерфейса
        self.label_name = tk.Label(win, text="Имя/Метка:",
                                   bg='red',
                                   font=('Arial', 10, 'bold'),
                                   padx=10,pady=10,
                                   relief=tk.RAISED)
        self.label_name.grid(row=0, column=0)

        self.entry_name = tk.Entry(win)
        self.entry_name.grid(row=0, column=1,  stick='w')

        self.label_password = tk.Label(win, text="Пароль:",
                                       font=('Arial', 10, 'bold'),
                                       bg='red',
                                       padx=20,pady=10,
                                       relief=tk.RAISED)
        self.label_password.grid(row=1, column=0)

        self.entry_password = tk.Entry(win)
        self.entry_password.grid(row=1, column=1, stick='w')

        self.generate_button = tk.Button(win, text="Генерировать пароль", command=self.generate_password)
        self.generate_button.grid(row=2, column=0, columnspan=2, pady=10)

        self.add_button = tk.Button(win, text="Сохранить пароль", command=self.add_password)
        self.add_button.grid(row=3, column=0, columnspan=2, pady=10)

        self.copy_button = tk.Button(win, text="Копировать пароль", command=self.copy_password)
        self.copy_button.grid(row=4, column=0, columnspan=2, pady=10)

        self.search_label = tk.Label(win, text="Поиск:",
                                     font=('Arial', 10, 'bold'),
                                     bg='red',
                                     padx=25,pady=10,
                                     relief=tk.RAISED)
        self.search_label.grid(row=5, column=0)

        self.entry_search = tk.Entry(win)
        self.entry_search.grid(row=5, column=1, stick='w')

        self.search_button = tk.Button(win, text="Найти пароль", command=self.search_password)
        self.search_button.grid(row=6, column=0, columnspan=2, pady=10)



    def generate_password(self):
        length = 12  # Задаем длину пароля
        chars = string.ascii_letters + string.digits + string.punctuation
        generated_password = ''.join(random.choice(chars) for _ in range(length))
        self.entry_password.delete(0, tk.END)
        self.entry_password.insert(0, generated_password)

    def add_password(self):
        name = self.entry_name.get()
        password = self.entry_password.get()
        if name and password:
            hashed_password = hashlib.sha256(password.encode()).hexdigest()
            self.passwords[name] = hashed_password
            messagebox.showinfo("Успешно", "Пароль сохранен успешно.")
            self.clear_entries()
        else:
            messagebox.showerror("Ошибка", "Введите имя и пароль.")

    def search_password(self):
        keyword = self.entry_search.get()
        if keyword:
            matching_passwords = [(label, password) for label, password in self.passwords.items() if keyword in label]
            if matching_passwords:
                results = "\n".join([f"{label}: {password}" for label, password in matching_passwords])
                messagebox.showinfo("Результаты поиска", f"Найденные пароли:\n{results}")
            else:
                messagebox.showinfo("Результаты поиска", "Пароли не найдены.")
        else:
            messagebox.showerror("Ошибка", "Введите ключевое слово для поиска.")

    def copy_password(self):
        name = self.entry_name.get()
        if name in self.passwords:
            pyperclip.copy(self.passwords[name])
            messagebox.showinfo("Успешно", "Пароль скопирован в буфер обмена.")
        else:
            messagebox.showerror("Ошибка", "Не правильно ввели метку пароля для копирования.")

    def clear_entries(self):
        self.entry_name.delete(0, tk.END)
        self.entry_password.delete(0, tk.END)
        self.entry_search.delete(0, tk.END)

# Создание и запуск графического интерфейса
win= tk.Tk()
app = PasswordManager(win)
win.mainloop()
