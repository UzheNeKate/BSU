/*
  Лабораторная работа №2 «Проектирование баз данных. Основы работы в Oracle Data Modeler»
  Вариант 8
  Студентка группы 13
  Врублевская Е.А.
*/

DROP TABLE Инвентарь CASCADE CONSTRAINTS;
DROP TABLE Жилое_помещение CASCADE CONSTRAINTS;
DROP TABLE Участок CASCADE CONSTRAINTS;
DROP TABLE Гость;
DROP TABLE Культура;
DROP TABLE Сектор;
DROP SEQUENCE guest_seq;

CREATE TABLE Сектор (
    id        INTEGER NOT NULL,
    затраты   NUMBER,
    тип       VARCHAR2(25 CHAR) NOT NULL
);
--добавление первичного ключа
ALTER TABLE Сектор ADD CONSTRAINT сектор_pk PRIMARY KEY ( id );

CREATE TABLE Инвентарь (
    id               INTEGER GENERATED by default on null as IDENTITY,
    название         VARCHAR2(30 CHAR) NOT NULL,
    количество       INTEGER NOT NULL CHECK (количество >= 0),
    стоимость        NUMBER NOT NULL CHECK (стоимость >= 0),
    сектор_id        INTEGER NOT NULL
);
--добавление первичного ключа
ALTER TABLE Инвентарь ADD CONSTRAINT инвентарь_pk PRIMARY KEY ( id );

CREATE TABLE Жилое_помещение (
    id               INTEGER GENERATED by default on null as IDENTITY,
    площадь          NUMBER NOT NULL CHECK (площадь >= 0),
    тип              VARCHAR2(50 CHAR) NOT NULL,
    спальных_мест    INTEGER NOT NULL CHECK (спальных_мест >= 0),
    сектор_id        INTEGER NOT NULL
);
--добавление первичного ключа
ALTER TABLE Жилое_помещение ADD CONSTRAINT жилое_пом_pk PRIMARY KEY (id);

CREATE TABLE Участок (
    id               INTEGER GENERATED by default on null as IDENTITY,
    площадь          NUMBER NOT NULL CHECK (площадь >= 0),
    сектор_id        INTEGER NOT NULL,
    культура_id      INTEGER
);
--добавление первичного ключа
ALTER TABLE Участок ADD CONSTRAINT участок_pk PRIMARY KEY (id);

CREATE TABLE Гость (
    id               INTEGER GENERATED by default on null as IDENTITY,
    имя              VARCHAR2(30 CHAR) NOT NULL,
    предпочтения     VARCHAR2(100 CHAR),
    помещение_id     INTEGER
);
--добавление первичного ключа
ALTER TABLE Гость ADD CONSTRAINT гость_pk PRIMARY KEY ( id );

CREATE TABLE Культура (
    id                      INTEGER GENERATED by default on null as IDENTITY,
    колво_удобрений         INTEGER NOT NULL CHECK (колво_удобрений >= 0),
    норма_посева            NUMBER NOT NULL CHECK (норма_посева >= 0),
    режим_полива            VARCHAR2(100 CHAR) NOT NULL,
    вегет_период            INTEGER NOT NULL CHECK (вегет_период >= 0),
    срок_уборки             DATE NOT NULL,
    стоимость               NUMBER NOT NULL CHECK (стоимость >= 0),
    вода_полив              NUMBER NOT NULL CHECK (вода_полив >= 0)
);
--добавление первичного ключа
ALTER TABLE Культура ADD CONSTRAINT культура_pk PRIMARY KEY ( id );

-- добавление внешнего ключа (связь с таблицей Сектор)
ALTER TABLE Инвентарь
    ADD CONSTRAINT рекр_сектор_fk FOREIGN KEY ( сектор_id )
        REFERENCES Сектор ( id )
        ON DELETE CASCADE;
     
-- добавление внешнего ключа (связь с таблицей Сектор)
ALTER TABLE Жилое_помещение
    ADD CONSTRAINT жил_сектор_fk FOREIGN KEY ( сектор_id )
        REFERENCES Сектор ( id )
        ON DELETE CASCADE;

-- добавление внешнего ключа (связь с таблицей Жилое_помещение)
ALTER TABLE Гость
    ADD CONSTRAINT помещение_fk FOREIGN KEY ( помещение_id )
        REFERENCES Жилое_помещение ( id )
        ON DELETE SET NULL;

-- добавление внешнего ключа (связь с таблицей Сектор)
 ALTER TABLE Участок
    ADD CONSTRAINT хоз_сектор_fk FOREIGN KEY ( сектор_id )
        REFERENCES Сектор ( id )
        ON DELETE CASCADE;
        
-- добавление внешнего ключа (связь с таблицей Культура)        
ALTER TABLE Участок
    ADD CONSTRAINT уч_культура_fk FOREIGN KEY ( культура_id )
        REFERENCES Культура ( id )
        ON DELETE SET NULL;
        
--создание последовательности для заполнения таблицы Гость        
CREATE SEQUENCE guest_seq
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 20;

--заполнение таблицы Гость
INSERT INTO Гость VALUES(guest_seq.NEXTVAL, 'Анастасия', 'шашки', NULL);
INSERT INTO Гость VALUES(guest_seq.NEXTVAL, 'Егор', 'шахматы', NULL);
INSERT INTO Гость VALUES(guest_seq.NEXTVAL, 'Марина', 'бадминтон', NULL);
INSERT INTO Гость VALUES(guest_seq.NEXTVAL, 'Алексей', 'книги', NULL);

--создание индексов
CREATE UNIQUE INDEX инв_сектор on Инвентарь(сектор_id);
CREATE UNIQUE INDEX пом_сектор on Жилое_помещение(сектор_id);
CREATE UNIQUE INDEX участок_сектор on Участок(сектор_id);
CREATE UNIQUE INDEX гость_помещение on Гость(помещение_id);

--создание ограничений

-- добавление ограничения на значения столбца "срок_уборки"
ALTER TABLE Культура
  ADD CONSTRAINT chk_time 
  CHECK (to_date(срок_уборки,'dd-mon-yyyy') BETWEEN 
         to_date('02-APR-2022','dd-mon-yyyy') AND to_date('31-SEP-2022','dd-mon-yyyy'));

-- добавление ограничения на значения столбца "площадь"
ALTER TABLE Участок
  ADD CONSTRAINT chk_square CHECK (площадь >= 0.3);
ALTER TABLE Участок 
  MODIFY (площадь default 0.4);
  
--добавление ограничения на значения столбца "норма_посева"
ALTER TABLE Культура
  ADD CONSTRAINT chk_weight CHECK (норма_посева >= 5);