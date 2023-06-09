# Комментарии к выполнению [самостоятельной работы](https://github.com/AllIWantIsNotAvailable/GeekBrains_OOP/tree/main/seminars/Sem01_Encapsulation_inheritance_polymorphism/HomeWork):
> ## Предисловие:
> В ходе выполнения работы самим собой были поставлены более глобальные цели, чем того требовали условия.
> Представленный вариант работы – это результат 4 или 5 попытки начала с 0... Искренне надеюсь, что не упустил ничего 
> важного из поставленных задач.
> 
> Причина такой кропотливой работы заключалась в том, что мне очень не нравилась реализация некоторых классов, их логики
> и тп. Переписывал до достижения более или менее сносного на мой взгляд варианта в рамках задачи.
> 

## Подход к выполнению работы:
Перед тем как преступить к выполнению самостоятельной части, мной была переписана логика сущностей и интерфейсов 
сформированных на "семинарском занятии". Особенно это коснулось сущностей имплементирующих интерфейс Vending. В работе 
представлено 2:
1. VendingMachine – подобие было реализовано в ходе семинарского занятия;
2. CoffeeMachine – реализация домашнего задания.
Объем и Температура реализованы за счет перечислений. Перегрузка метода getProduct() с параметром "Температура" упущена
сознательно, так как нарушила бы выстроенную логику работы "кофейного автомата".


## Что не нравится:
1. Очень много дублирования кода при инициализации возвращаемого экземпляра класса-наследника CoffeeDrink, из метода
getProduct(). Я точно понимаю, что в случае Python количество кода можно сократить до 1-2, вместо 5-6 методов за счет 
переменных "указателей" на метод или конструктор класса.
2. Реализован интерфейс Products для абстракции Product. Реализовано 2 дополнительных интерфейса, которые бы должны были
разделить Product на "Готовую продукцию" и "Материалы", например: кофе – готовый продукт, а его составляющие зерно, вода,
молоко – материалы. В итоге 2 дополнительных интерфейса спасли логическую структуру, но оказались весьма бесполезны 
функционально... 


## Что не было реализовано, но было задумано:
1. Механизм продаж (учет доходов/расходов);
2. Конвертация ед. изм. для разных фасовок;