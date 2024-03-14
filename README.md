# Security_Task

## Для корректной работы сначала нужно:

1) Запустить проект, чтоб создались все нужные таблицы в базе данных.

2) Добавить роли в базу данных через SQL запрос:
```SQL
INSERT INTO role (name_roles) VALUES 
('ROLE_ADMIN'),
('ROLE_USER');
```

3) Добавить Юзера в таблицу юзеров, не забудь вставить свои значения:
```SQL
INSERT INTO users (name, password, last_name, department, salary) 
VALUES ('Your_name', 'Your_pass', 'Your_last_name', 'Your_department', Your_salary);

```

4) Прописать связи между ролями и юзером:
Для роли ADMIN. 
```SQL
INSERT INTO user_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE name = 'Your_name'),
        (SELECT id FROM role WHERE name_roles = 'ROLE_ADMIN'));
```
Для роли USER аналогично.

6) Перезапустить проект и войти под Your_name, Your_password.
