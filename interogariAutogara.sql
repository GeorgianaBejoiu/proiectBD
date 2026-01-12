--crearea bazei de date
CREATE DATABASE ProiectAutogara;

--PUNCTUL 3
--tabel Autogara
CREATE TABLE Autogara (
    ID_autogara INT PRIMARY KEY,
    Nume VARCHAR(100),
    Adresa VARCHAR(200),
    Oras VARCHAR(100)
);

--tabel RutaFixa
CREATE TABLE RutaFixa (
    ID_rutaFixa INT PRIMARY KEY,
    Oras_start VARCHAR(100),
    Oras_destinatie VARCHAR(100),
    Distanta_km INT
);

--tabel Sofer
CREATE TABLE Sofer (
    ID_sofer INT PRIMARY KEY,
    Nume VARCHAR(100),
    Prenume VARCHAR(100),
    Nr_permis VARCHAR(50),
    Experienta INT
);

--tabel Autobuz
CREATE TABLE Autobuz (
    ID_autobuz INT PRIMARY KEY,
    Numar_inmatriculare VARCHAR(50),
    Capacitate INT,
    ID_sofer INT,
    FOREIGN KEY (ID_sofer) REFERENCES Sofer(ID_sofer)
);

--tabel Pasager
CREATE TABLE Pasager (
    ID_pasager INT PRIMARY KEY,
    Nume VARCHAR(100),
    Prenume VARCHAR(100)
);

--tabel Bilet
CREATE TABLE Bilet (
    ID_bilet INT PRIMARY KEY,
    Pret DECIMAL(10,2),
    Loc INT,
    ID_pasager INT,
    FOREIGN KEY (ID_pasager) REFERENCES Pasager(ID_pasager)
);

--tabel Statie
CREATE TABLE Statie (
    ID_statie INT PRIMARY KEY,
    Nume VARCHAR(100),
    Timp_stationare INT
);

--tabel Cursa
CREATE TABLE Cursa (
    ID_cursa INT PRIMARY KEY,
    Data_plecare DATE,
    Ora_plecare TIME,

    ID_autogara INT,
    ID_rutaFixa INT,
    ID_autobuz INT,

    FOREIGN KEY (ID_autogara) REFERENCES Autogara(ID_autogara),
    FOREIGN KEY (ID_rutaFixa) REFERENCES RutaFixa(ID_rutaFixa),
    FOREIGN KEY (ID_autobuz) REFERENCES Autobuz(ID_autobuz)
);

DROP TABLE Bilet;

--corectarea tabelului Bilet
CREATE TABLE Bilet (
    ID_bilet INT PRIMARY KEY,
    Pret DECIMAL(10,2),
    Loc INT,
    ID_pasager INT,
    ID_cursa INT,
    FOREIGN KEY (ID_pasager) REFERENCES Pasager(ID_pasager),
    FOREIGN KEY (ID_cursa) REFERENCES Cursa(ID_cursa)
);

--Foloseste -> tabel de legatura intre Cursa si Autobuz
CREATE TABLE Foloseste (
    ID_are INT PRIMARY KEY,
    ID_cursa INT,
    ID_autobuz INT,
    FOREIGN KEY (ID_cursa) REFERENCES Cursa(ID_cursa),
    FOREIGN KEY (ID_autobuz) REFERENCES Autobuz(ID_autobuz)
);

--Opreste -> tabel de legatura intre Cursa si Statie
CREATE TABLE Opreste (
    ID_oprire INT PRIMARY KEY,
    ID_cursa INT,
    ID_statie INT,
    Ordine_oprire INT,
    Timp_stationare INT,
    FOREIGN KEY (ID_cursa) REFERENCES Cursa(ID_cursa),
    FOREIGN KEY (ID_statie) REFERENCES Statie(ID_statie)
);

--3A
--inserari in tabelul Autogara
INSERT INTO Autogara VALUES (1, 'Autogara Nord', 'Str. Libertatii 10', 'Bucuresti');
INSERT INTO Autogara VALUES (2, 'Autogara Sud', 'Str. Unirii 5', 'Bucuresti');
INSERT INTO Autogara VALUES (3, 'Autogara Vest', 'Str. Vestului 20', 'Timisoara');
INSERT INTO Autogara VALUES (4, 'Autogara Est', 'Str. Est 12', 'Iasi');
INSERT INTO Autogara VALUES (5, 'Autogara Central', 'Str. Centrala 7', 'Cluj');
INSERT INTO Autogara VALUES (6, 'Autogara Militari', 'Bd. Militari 15', 'Bucuresti');
INSERT INTO Autogara VALUES (7, 'Autogara Gara de Nord', 'Calea Gara de Nord 1', 'Bucuresti');
INSERT INTO Autogara VALUES (8, 'Autogara Pipera', 'Str. Pipera 33', 'Bucuresti');
INSERT INTO Autogara VALUES (9, 'Autogara Bucurestii Noi', 'Str. Bucurestii Noi 5', 'Bucuresti');
INSERT INTO Autogara VALUES (10, 'Autogara Pantelimon', 'Str. Pantelimon 9', 'Bucuresti');

--inserari in tabelul Sofer
INSERT INTO Sofer VALUES (1, 'Popescu', 'Ion', 'B123456', 10);
INSERT INTO Sofer VALUES (2, 'Ionescu', 'Maria', 'C654321', 7);
INSERT INTO Sofer VALUES (3, 'Georgescu', 'Andrei', 'D987654', 15);
INSERT INTO Sofer VALUES (4, 'Dumitru', 'Elena', 'E345678', 5);
INSERT INTO Sofer VALUES (5, 'Radu', 'Alexandru', 'F123987', 12);
INSERT INTO Sofer VALUES (6, 'Nedelcu', 'Vlad', 'G456789', 8);
INSERT INTO Sofer VALUES (7, 'Marin', 'Cristina', 'H987123', 6);
INSERT INTO Sofer VALUES (8, 'Constantin', 'Mihai', 'I234567', 9);
INSERT INTO Sofer VALUES (9, 'Florea', 'Ana', 'J876543', 4);
INSERT INTO Sofer VALUES (10, 'Matei', 'Bogdan', 'K123987', 11);

--inserari in tabelul Autobuz
INSERT INTO Autobuz VALUES (1, 'B123ABC', 50, 1);
INSERT INTO Autobuz VALUES (2, 'CJ456DE', 40, 2);
INSERT INTO Autobuz VALUES (3, 'TM789FG', 60, 3);
INSERT INTO Autobuz VALUES (4, 'IS101JK', 45, 4);
INSERT INTO Autobuz VALUES (5, 'BV202LM', 55, 5);
INSERT INTO Autobuz VALUES (6, 'B789XYZ', 52, 6);
INSERT INTO Autobuz VALUES (7, 'CJ999AA', 48, 7);
INSERT INTO Autobuz VALUES (8, 'TM555GH', 50, 8);
INSERT INTO Autobuz VALUES (9, 'IS888XY', 46, 9);
INSERT INTO Autobuz VALUES (10, 'BV777ZZ', 54, 10);
INSERT INTO Autobuz VALUES (11, 'B000TEST', 45);


--inserari in tabelul Pasager
INSERT INTO Pasager VALUES (1, 'Vasilescu', 'Ana');
INSERT INTO Pasager VALUES (2, 'Marinescu', 'Bogdan');
INSERT INTO Pasager VALUES (3, 'Stoica', 'Elena');
INSERT INTO Pasager VALUES (4, 'Popa', 'Gabriel');
INSERT INTO Pasager VALUES (5, 'Dobre', 'Iulia');
INSERT INTO Pasager VALUES (6, 'Tudor', 'Alina');
INSERT INTO Pasager VALUES (7, 'Barbu', 'Claudiu');
INSERT INTO Pasager VALUES (8, 'Neagu', 'Raluca');
INSERT INTO Pasager VALUES (9, 'Sandu', 'Cristian');
INSERT INTO Pasager VALUES (10, 'Lungu', 'Gabriela');

--inserari in tabelul RutaFixa
INSERT INTO RutaFixa VALUES (1, 'Bucuresti', 'Cluj', 450);
INSERT INTO RutaFixa VALUES (2, 'Timisoara', 'Iasi', 600);
INSERT INTO RutaFixa VALUES (3, 'Cluj', 'Timisoara', 300);
INSERT INTO RutaFixa VALUES (4, 'Bucuresti', 'Iasi', 400);
INSERT INTO RutaFixa VALUES (5, 'Cluj', 'Bucuresti', 450);
INSERT INTO RutaFixa VALUES (6, 'Bucuresti', 'Timisoara', 550);
INSERT INTO RutaFixa VALUES (7, 'Iasi', 'Cluj', 620);
INSERT INTO RutaFixa VALUES (8, 'Bucuresti', 'Brasov', 170);
INSERT INTO RutaFixa VALUES (9, 'Brasov', 'Cluj', 300);
INSERT INTO RutaFixa VALUES (10, 'Iasi', 'Bucuresti', 400);

--inserari in tabelul Statie
INSERT INTO Statie VALUES (1, 'Statia Nord', 5);
INSERT INTO Statie VALUES (2, 'Statia Sud', 4);
INSERT INTO Statie VALUES (3, 'Statia Vest', 6);
INSERT INTO Statie VALUES (4, 'Statia Est', 3);
INSERT INTO Statie VALUES (5, 'Statia Central', 7);
INSERT INTO Statie VALUES (6, 'Statia Militari', 5);
INSERT INTO Statie VALUES (7, 'Statia Gara de Nord', 4);
INSERT INTO Statie VALUES (8, 'Statia Pipera', 3);
INSERT INTO Statie VALUES (9, 'Statia Bucurestii Noi', 5);
INSERT INTO Statie VALUES (10, 'Statia Pantelimon', 4);

--inserari in tabelul Cursa
INSERT INTO Cursa VALUES (1, '2025-12-01', '08:00:00', 1, 1, 1);
INSERT INTO Cursa VALUES (2, '2025-12-02', '09:30:00', 2, 2, 2);
INSERT INTO Cursa VALUES (3, '2025-12-03', '07:45:00', 3, 3, 3);
INSERT INTO Cursa VALUES (4, '2025-12-04', '10:00:00', 4, 4, 4);
INSERT INTO Cursa VALUES (5, '2025-12-05', '11:15:00', 5, 5, 5);
INSERT INTO Cursa VALUES (6, '2025-12-06', '12:00:00', 6, 6, 6);
INSERT INTO Cursa VALUES (7, '2025-12-07', '13:30:00', 7, 7, 7);
INSERT INTO Cursa VALUES (8, '2025-12-08', '14:00:00', 8, 8, 8);
INSERT INTO Cursa VALUES (9, '2025-12-09', '15:00:00', 9, 9, 9);
INSERT INTO Cursa VALUES (10, '2025-12-10', '16:00:00', 10, 10, 10);
INSERT INTO Cursa VALUES (11, '2025-12-15', '18:30:00', 1, 1, NULL);


-- Bilet (10 înregistrări)
INSERT INTO Bilet VALUES (1, 50.00, 10, 1, 1);
INSERT INTO Bilet VALUES (2, 45.00, 15, 2, 2);
INSERT INTO Bilet VALUES (3, 60.00, 20, 3, 3);
INSERT INTO Bilet VALUES (4, 55.00, 25, 4, 4);
INSERT INTO Bilet VALUES (5, 40.00, 30, 5, 5);
INSERT INTO Bilet VALUES (6, 48.00, 12, 6, 6);
INSERT INTO Bilet VALUES (7, 42.00, 22, 7, 7);
INSERT INTO Bilet VALUES (8, 38.00, 18, 8, 8);
INSERT INTO Bilet VALUES (9, 50.00, 16, 9, 9);
INSERT INTO Bilet VALUES (10, 52.00, 14, 10, 10);

--foloseste
INSERT INTO Foloseste VALUES (1, 1, 1);
INSERT INTO Foloseste VALUES (2, 2, 2);
INSERT INTO Foloseste VALUES (3, 3, 3);
INSERT INTO Foloseste VALUES (4, 4, 4);
INSERT INTO Foloseste VALUES (5, 5, 5);
INSERT INTO Foloseste VALUES (6, 6, 6);
INSERT INTO Foloseste VALUES (7, 7, 7);
INSERT INTO Foloseste VALUES (8, 8, 8);
INSERT INTO Foloseste VALUES (9, 9, 9);
INSERT INTO Foloseste VALUES (10, 10, 10);

--opreste
INSERT INTO Opreste VALUES (1, 1, 1, 1, 5);
INSERT INTO Opreste VALUES (2, 1, 5, 2, 7);
INSERT INTO Opreste VALUES (3, 2, 2, 1, 4);
INSERT INTO Opreste VALUES (4, 2, 6, 2, 5);
INSERT INTO Opreste VALUES (5, 3, 3, 1, 6);
INSERT INTO Opreste VALUES (6, 3, 7, 2, 4);
INSERT INTO Opreste VALUES (7, 4, 4, 1, 3);
INSERT INTO Opreste VALUES (8, 4, 8, 2, 3);
INSERT INTO Opreste VALUES (9, 5, 5, 1, 7);
INSERT INTO Opreste VALUES (10, 5, 9, 2, 5);

--modificarea atributului nume din Statie in nume_statie
ALTER TABLE Statie
RENAME COLUMN Nume TO Nume_Statie;

--am modificat relatia Sofer-Autobuz=M:N, trebuie sters FK-ul ID_sofer pus initial in Autobuz
ALTER TABLE Autobuz
DROP COLUMN ID_sofer;

--Conduce -> tabel de legatura intre Sofer si Autobuz
CREATE TABLE Conduce(
	ID_conduce INT PRIMARY KEY,
	ID_sofer INT NOT NULL,
	ID_autobuz INT NOT NULL,
	Data_inceput DATE NOT NULL,
	Data_sfarsit DATE,
	FOREIGN KEY (ID_sofer) REFERENCES Sofer(ID_sofer),
	FOREIGN KEY (ID_autobuz) REFERENCES Autobuz(ID_autobuz)
);

INSERT INTO Conduce (ID_conduce, ID_sofer, ID_autobuz, Data_inceput, Data_sfarsit) VALUES
(1, 1, 1, '2024-01-01', '2024-12-31'),
(2, 2, 2, '2024-03-15', NULL),
(3, 3, 3, '2024-05-10', '2024-08-30'),
(4, 4, 4, '2024-07-01', NULL),
(5, 5, 5, '2024-09-01', NULL),
(6, 6, 6, '2024-10-01', '2024-11-30'),
(7, 7, 7, '2024-01-20', NULL),
(8, 8, 8, '2024-02-10', '2024-05-15'),
(9, 9, 9, '2024-04-01', NULL),
(10, 10, 10, '2024-06-01', NULL);

--3B
--update simplu, mareste experienta cu 1
UPDATE Sofer
SET Experienta = Experienta + 1
WHERE ID_sofer = 1;

--delete simplu
DELETE FROM Bilet
WHERE ID_pasager = 10;

DELETE FROM Pasager
WHERE ID_pasager = 10;

--3C
--UPDATE capacitatea cu 5
UPDATE Autobuz
SET Capacitate = Capacitate + 5
WHERE ID_autobuz IN (
    SELECT ID_autobuz
    FROM Conduce
    WHERE Data_sfarsit IS NULL
);

--UPDATE pentru cursele din ziua respectiva mareste pretul cu 10%
UPDATE Bilet
SET Pret = Pret * 1.10
WHERE ID_cursa IN (
    SELECT ID_cursa
    FROM Cursa
    WHERE Data_plecare > '2025-12-05'
);

--UPDATE mareste pretul pentru cursele care au ca si oras Bucuresti
UPDATE Bilet 
SET Pret = Pret + 10
WHERE ID_cursa IN(
	SELECT C.ID_cursa
	FROM Cursa as C
	JOIN Autogara A ON C.ID_autogara = A.ID_autogara
	WHERE  A.Oras = 'Bucuresti'
);

--mareste experienta pentru soferii care conduc autobuze cu capacitatea > 50
UPDATE Sofer
Set Experienta =Experienta + 1
WHERE ID_sofer IN(
	SELECT ID_sofer
	FROM Conduce
	WHERE ID_autobuz IN (
		SELECT ID_autobuz
		FROM Autobuz
		WHERE Capacitate > 50
	)
);

--UPDATE creste capacitatea autobuzelor care au ca si oras Bucuresti
UPDATE Autobuz
Set Capacitate = Capacitate + 5
WHERE ID_autobuz IN(
	 SELECT ID_autobuz
    FROM Cursa
    WHERE ID_cursa IN (
        SELECT C.ID_cursa
        FROM Cursa C
        JOIN Autogara A ON C.ID_autogara = A.ID_autogara
        WHERE A.Oras = 'Bucuresti'
    )
);

--UPDATE timpul de stationare pentru cursa cu id-ul 1
UPDATE Opreste
SET Timp_stationare = 3
WHERE ID_cursa IN (
    SELECT ID_cursa
    FROM Cursa
    WHERE ID_cursa = 1
);

--DELETE sterge relatiile de conducere pentru autobuzele cu capacitatea = 56
DELETE FROM Conduce
WHERE ID_autobuz IN (
    SELECT ID_autobuz
    FROM Autobuz
    WHERE Capacitate = 56
);

--DELETE biletele pentru cursele din data respectiva
DELETE FROM Bilet
WHERE ID_cursa IN(
	SELECT ID_cursa
	FROM Cursa
	WHERE Data_plecare = '2025-12-05'
);

--DELETE elimina opririle curselor care pleaca din autogari aflate în orasul Bucuresti
DELETE FROM Opreste
WHERE ID_cursa IN(
	SELECT C.ID_cursa
	FROM CURSA C
	JOIN Autogara A ON C.ID_autogara = A.ID_autogara
	WHERE A.Oras = 'Bucuresti'
);

--DELETE autobuzele care nu sunt folosite la nicio cursa
DELETE FROM Autobuz
WHERE ID_Autobuz NOT IN(
	SELECT DISTINCT ID_autobuz
    FROM Foloseste
);

-- PUNCTUL 4: NORMALIZAREA RELATIILOR 
--in structura anterioara locul din autobuz nu are nicio si ar putea sa apara pe mai multe bilete acelasi loc
--constrangere de unicitate care sa impiedice rezervarea aceluiasi loc de mai multe ori pe aceeasi cursa
ALTER TABLE Bilet
ADD CONSTRAINT UQ_Bilet_Cursa_Loc UNIQUE (ID_cursa, Loc);
--1NF (valorile sunt atomice)
--Pentru a evita anomalii de inserare(ex:locuri duplicate)

--de tipul 2NF nu exista deoarece exista tabele de legatura
--de tipul 3NF era initial, in momentul in care facusem relatia sofer-autobuz 1 la N
--aceasta s-a normalizat prin refacerea legaturi M:N, prin stergere FK-ului ID_sofer din Autobuz si prin tabelul de legatura Conduce
--Relatia Cursa nu respecta initial forma normala a treia
--atributul ID_autobuz introducea o dependenta tranzitiva intre cheia primara si atributele autobuzului
--de tipul 3NF era initial, in momentul in care facusem relatia cursa-autobuz 1 la N
--aceasta s-a normalizat prin refacerea legaturi M:N, prin stergere FK-ului ID_autobuz din Cursa si prin tabelul de legatura Foloseste
ALTER TABLE Cursa
DROP COLUMN ID_autobuz;


--PUNCTUL 5
--A
--returneaza doar cursele care au o autogara asociata
SELECT 
    C.ID_cursa,
    C.Data_plecare,
    C.Ora_plecare,
    A.Nume AS Autogara,
    A.Oras
FROM Cursa C
INNER JOIN Autogara A 
    ON C.ID_autogara = A.ID_autogara;

--se obtin biletele vandute, impreuna cu pasagerul si data cursei
SELECT 
	B.ID_bilet,
	P.nume,
	P.prenume,
	C.data_plecare,
	B.pret
FROM Bilet B
INNER JOIN Pasager P ON B.ID_pasager = P.ID_pasager
INNER JOIN Cursa C ON B.ID_cursa = C.ID_cursa;

--returneaza cursele care au asociata atat o autogara, cat si o ruta
SELECT
	C.ID_cursa,
	C.data_plecare,
	C.ora_plecare,
	A.oras,
	R.oras_destinatie,
	R.distanta_km
FROM Cursa C
INNER JOIN Autogara A ON C.ID_autogara = A.ID_autogara
INNER JOIN RutaFixa R ON C.ID_rutafixa = R.ID_rutafixa;

--afiseaza toate autogarile, impreuna cu curselele care pleaca din ele, daca exista
SELECT
    A.ID_autogara,
    A.Nume,
    A.Oras,
    C.ID_cursa,
    C.Data_plecare,
    C.Ora_plecare
FROM Autogara A
LEFT JOIN Cursa C ON A.ID_autogara = C.ID_autogara;

--afiseaza toate cursele, chiar si pe cele care nu au un autobuz asociat
SELECT 
    C.ID_cursa,
    C.Data_plecare,
    C.Ora_plecare,
    A.Numar_inmatriculare
FROM Cursa C
LEFT JOIN Foloseste F ON C.ID_cursa = F.ID_cursa
LEFT JOIN Autobuz A ON F.ID_autobuz = A.ID_autobuz;

--afiseaza toate autobuzele, chiar si pe cele care nu sunt folosite in nicio cursa
SELECT
    A.Numar_inmatriculare,
    A.Capacitate,
    C.Data_plecare
FROM Cursa C
RIGHT JOIN Foloseste F ON C.ID_cursa = F.ID_cursa
RIGHT JOIN Autobuz A ON F.ID_autobuz = A.ID_autobuz;


--5B

--Numarul de curse pentru fiecare autogara
SELECT
    A.Oras,
    COUNT(C.ID_cursa) AS Numar_curse
FROM Autogara A
JOIN Cursa C ON A.ID_autogara = C.ID_autogara
GROUP BY A.Oras;

--Pretul mediu al biletelor pentru fiecare cursa
SELECT
    ID_cursa,
    AVG(Pret) AS Pret_mediu
FROM Bilet
GROUP BY ID_cursa;

--Capacitatea totala a autobuzelor pentru fiecare sofer
SELECT
	S.nume,
	S.prenume,
	SUM(A.Capacitate) AS Capacitate_totala
FROM Sofer S
JOIN Conduce C ON S.ID_sofer = C.ID_sofer
JOIN Autobuz A ON C.ID_autobuz = A.ID_autobuz
GROUP BY S.Nume, S.Prenume;

--Numarul de opriri pentru fiecare cursa
SELECT
    ID_cursa,
    COUNT(ID_statie) AS Numar_opriri
FROM Opreste
GROUP BY ID_cursa;

--autogarile care au cel putin 1 cursa
SELECT 
    A.Nume AS Nume_Autogara,
    A.Oras,
    COUNT(C.ID_cursa) AS Numar_Curse
FROM Autogara A
JOIN Cursa C ON A.ID_autogara = C.ID_autogara
GROUP BY A.ID_autogara, A.Nume, A.Oras
HAVING COUNT(C.ID_cursa) >= 1;




--5C
--subinterogari necorelate
--IN
--returneaza numele si prenumele soferilor care conduc un autobuz cu capacitatea > 50
SELECT DISTINCT S.Nume, S.Prenume
FROM Sofer S
JOIN Conduce C ON S.ID_sofer = C.ID_sofer
JOIN Autobuz A ON C.ID_autobuz = A.ID_autobuz
WHERE A.Capacitate > 50;

--EXISTS
--returneaza cursele care au cel putin o oprire in statii cu timp de stationare mai mare de 5 minute
SELECT ID_cursa, data_plecare, ora_plecare
FROM Cursa
WHERE EXISTS(
	 SELECT 1
    FROM Opreste O
    JOIN Statie S ON O.ID_statie = S.ID_statie
    WHERE O.ID_cursa = Cursa.ID_cursa
    AND S.Timp_stationare > 5
);

--ANY
--returneaza toate autobuzele cu capacitate mai mare decat capacitatea oricaruia dintre autobuzele conduse de soferul cu ID_sofer = 1
SELECT *
FROM Autobuz
WHERE Capacitate > ANY (
	SELECT A.Capacitate
	FROM Autobuz A
	JOIN Conduce C ON A.ID_autobuz = C.ID_autobuz
	WHERE C.ID_sofer = 1
);

--NOT IN
--returneaza pasagerii care nu au niciun bilet pentru cursele ce pleaca din autogari din orasul Bucuresti
SELECT ID_pasager, nume, prenume
FROM Pasager
WHERE ID_pasager NOT IN(
	SELECT B.ID_pasager
	FROM BILET B
	JOIN Cursa C ON B.ID_cursa = C.ID_cursa
	JOIN Autogara A ON C.ID_autogara = A.ID_autogara
    WHERE A.Oras = 'Bucuresti'
);

--subinterogari corelate
--obtine toti soferii care conduc autobuze ce au fost folosite in curse care pleaca din autogari din Bucuresti
SELECT S.Nume, S.Prenume
FROM Sofer S
WHERE EXISTS(
	SELECT 1
	FROM Conduce C
	JOIN Foloseste F ON C.ID_autobuz = F.ID_autobuz
	JOIN Cursa Cu ON F.ID_cursa = Cu.ID_cursa
	JOIN Autogara A ON Cu.ID_autogara = A.ID_autogara
	WHERE C.ID_sofer = S.ID_sofer
    AND A.Oras = 'Bucuresti'
);

--afiseaza toate cursele care au bilete cumparate de pasageri care au si bilete la curse cu data de plecare dupa 2025-12-05
SELECT DISTINCT C.ID_cursa, C.Data_plecare, C.Ora_plecare
FROM Cursa C
WHERE EXISTS(
	 SELECT 1
	 FROM Bilet B
	 JOIN Cursa C2 ON B.ID_cursa = C2.ID_cursa
	 WHERE B.ID_pasager IN(
		SELECT B2.ID_pasager
        FROM Bilet B2
		JOIN Cursa C3 ON B2.ID_cursa = C3.ID_cursa
        WHERE C3.Data_plecare > '2025-12-05'
	 )
	 AND B.ID_cursa = C.ID_cursa
);

--afiseaza autogarile care au curse cu autobuze conduse de soferi cu experienta mai mica de 10 ani
SELECT DISTINCT A.nume, A.oras
FROM Autogara A
WHERE EXISTS(
	SELECT 1
	FROM Cursa C
	JOIN Foloseste F ON C.ID_cursa = F.ID_cursa
	JOIN Conduce Co ON F.ID_autobuz = Co.ID_autobuz
	JOIN Sofer S ON Co.ID_sofer = S.ID_sofer
	WHERE C.ID_autogara = A.ID_autogara
	AND S.experienta < 10
);

--afiseaza pasagerii care au cumparat bilete la curse ce opresc in statii cu timp de stationare mai mare de 5 minute
SELECT DISTINCT P.nume, P.prenume
FROM Pasager P
WHERE EXISTS(
	SELECT 1
	FROM Bilet B
	JOIN Opreste O ON B.ID_cursa = O.ID_cursa
	WHERE B.ID_pasager = P.ID_pasager
	AND O.Timp_stationare > 5
);

--5D
SELECT
    -- Functii pe siruri de caractere
    CONCAT(P.Nume, ' ', P.Prenume) AS Nume_Complet,  -- concatenate
    UPPER(SUBSTRING(A.Oras FROM 1 FOR 3)) AS Prefix_Oras,  -- primele 3 caractere majuscule

    -- Funcții pe date calendaristice
    EXTRACT(year FROM C.Data_plecare) AS An_Plecare,  -- extrage anul
    (CURRENT_DATE - C.Data_plecare) AS Zile_de_la_plecare, -- diferenta in zile (date - date)

    -- Expresie CASE
    CASE 
        WHEN S.Experienta >= 10 THEN 'Veteran'
        WHEN S.Experienta BETWEEN 5 AND 9 THEN 'Medie'
        ELSE 'Incepator'
    END AS Nivel_Experienta

FROM Pasager P
JOIN Bilet B ON P.ID_pasager = B.ID_pasager
JOIN Cursa C ON B.ID_cursa = C.ID_cursa
JOIN Autogara A ON C.ID_autogara = A.ID_autogara
JOIN Foloseste F ON C.ID_cursa = F.ID_cursa
JOIN Conduce Co ON F.ID_autobuz = Co.ID_autobuz
JOIN Sofer S ON Co.ID_sofer = S.ID_sofer
WHERE C.Data_plecare >= '2025-12-01';

--PUNCTUL 5E
-- Vedere simpla pe tabelul Sofer (vedere fara JOIN, permite modificari)
CREATE VIEW VedereSoferi AS
SELECT ID_sofer, Nume, Prenume, Experienta
FROM Sofer
WHERE Experienta > 5;

-- Vedere care combina curse si autogari (vedere cu JOIN)
CREATE VIEW VedereCurseAutogari AS
SELECT C.ID_cursa, C.Data_plecare, C.Ora_plecare, A.Nume AS Nume_Autogara, A.Oras
FROM Cursa C
JOIN Autogara A ON C.ID_autogara = A.ID_autogara;

-- Vedere cu agregare bilete per cursa (nu permite modificari)
CREATE VIEW VederePretMedBilete AS
SELECT ID_cursa, AVG(Pret) AS Pret_mediu
FROM Bilet
GROUP BY ID_cursa;

--Exemplu operatie LMD permisa pe vedere
-- Pe vederea simplă VedereSoferi putem face UPDATE
UPDATE VedereSoferi
SET Experienta = Experienta + 1
WHERE ID_sofer = 1;

--Exemplu operatie LMD nepermisa pe vedere
/*UPDATE VederePretMedBilete
SET Pret_mediu = 100
WHERE ID_cursa = 1;*/
-- Va genera eroare deoarece vederea contine GROUP BY si agregare

--PUNCTUL 6
--initial
SELECT * 
FROM Cursa 
WHERE Data_plecare = '2025-12-01' 
  AND ID_autogara = 1;

--optimizare
DROP INDEX IF EXISTS idx_cursa_data_autogara;
CREATE INDEX idx_cursa_data_autogara 
ON Cursa (Data_plecare, ID_autogara);


SELECT * FROM Autogara;

DELETE FROM Autogara
WHERE ID_autogara = 11;

INSERT INTO Autogara VALUES (11, 'Autogara Sud', 'Str. Popoveni 1', 'Bucuresti');

SELECT * FROM Cursa;

INSERT INTO RutaFixa VALUES (11, 'Craiova', 'Bucuresti', 230);

INSERT INTO Autobuz VALUES (12, 'DJ000MM', 54);

SELECT * FROM Autogara WHERE Oras = 'Bucuresti';

SELECT * FROM Cursa WHERE Data_plecare = '2025-12-01';
