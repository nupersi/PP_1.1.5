>Описание задачи:

В прошлой задаче мы познакомились с Maven и JDBC, доработали приложение, взаимодействующее с базой данных.

На этот раз обратим внимание на класс UserHibernateDaoImpl, который реализует тот же интерефейс, что и UserDaoJdbcImpl.

В рамках этой задачи необходимо реализовать взаимодействие с базой данных с помощью Hibernate и дописать методы в классе UserHibernateDaoImpl, проверить свои методы заранее написанными JUnit тестами из заготовки.



>Требования к классам приложения:

UserHibernateDaoImpl должен реализовывать интерефейс UserDao
В класс Util должна быть добавлена конфигурация для Hibernate ( рядом с JDBC), без использования xml.
Service на этот раз использует реализацию dao через Hibernate
Методы создания и удаления таблицы пользователей в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.


Алгоритм приложения и операции не меняются в сравнении с предыдущим заданием.