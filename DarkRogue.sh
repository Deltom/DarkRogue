#!/bin/bash

# Определение текущей директории, где находится скрипт
CURRENT_DIR=$(pwd)

# Указание полного пути к Java файлу и классу
JAVA_FILE_PATH="$CURRENT_DIR/Rogue/src/main/java/org/example/Main.java"
CLASS_NAME="org.example.Main"

# Компиляция Java файла
echo "Компиляция Java файла..."
javac "$JAVA_FILE_PATH"
if [ $? -ne 0 ]; then
    echo "Ошибка компиляции."
    exit 1
fi

# Запуск Java программы
echo "Запуск Java программы..."
java -cp "$CURRENT_DIR/Rogue/src/main/java" "$CLASS_NAME" &
JAVA_PID=$!

# Ожидание нажатия клавиши для завершения программы
read -p "Нажмите любую клавишу для завершения программы..."

# Завершение Java программы
echo "Завершение работы Java программы..."
kill $JAVA_PID

echo "Программа завершена."
