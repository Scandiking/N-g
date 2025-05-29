--  PERSON TABLE --
CREATE TABLE person(
	phone_no VARCHAR(15) PRIMARY KEY,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	mail VARCHAR(50),
	profile_picture BYTEA,
	date_of_birth DATE
);

-- ROOM TABLE --
CREATE TABLE room(
	room_id SMALLINT PRIMARY KEY,
	room_name VARCHAR(30) NOT NULL,
	room_descr VARCHAR(70),
	room_admin VARCHAR(15) NOT NULL,
	room_picture BYTEA,

	CONSTRAINT room_room_admin_fk FOREIGN KEY (room_admin) REFERENCES person(phone_no)
);

-- NOTIFICATION FREQUENCY TABLE --
CREATE TABLE noti_freq(
	noti_freq_id SMALLINT PRIMARY KEY,
	noti_freq_title TEXT NOT NULL,
	base_interval INTERVAL NOT NULL,
	growth_factor REAL NOT NULL,
	max_repeats SMALLINT NOT NULL
);

-- TASK TABLE --
CREATE TABLE task(
	task_id INT PRIMARY KEY,
	task_title VARCHAR(50) NOT NULL,
	task_descr TEXT,
	due_date_time TIMESTAMP NOT NULL,
	noti_freq_id SMALLINT NOT NULL,
	creator VARCHAR(15) NOT NULL,
	completed BOOLEAN NOT NULL,

	CONSTRAINT task_noti_freq_id_fk FOREIGN KEY(noti_freq_id) REFERENCES noti_freq(noti_freq_id), 
	CONSTRAINT task_creator_fk FOREIGN KEY(creator) REFERENCES person(phone_no)
);

-- ROOM FOR PERSON JOIN TABLE --
CREATE TABLE room_for_person(
	room_id SMALLINT NOT NULL,
	person_id VARCHAR(15) NOT NULL,
	score SMALLINT NOT NULL,

	PRIMARY KEY(room_id, person_id),

	CONSTRAINT room_for_person_room_id_fk FOREIGN KEY(room_id) REFERENCES room(room_id),
	CONSTRAINT room_for_person_person_id_fk FOREIGN KEY(person_id) REFERENCES person(phone_no)
);

-- TASK FOR PERSON JOIN TABLE --
CREATE TABLE task_for_person(
	task_id INT NOT NULL,
	person_id VARCHAR(15) NOT NULL,

	PRIMARY KEY(task_id, person_id),

	CONSTRAINT task_for_person_task_id FOREIGN KEY(task_id) REFERENCES task(task_id),
	CONSTRAINT task_for_person_person_id FOREIGN KEY(person_id) REFERENCES person(phone_no)
);

-- NOTIFICATION FREQUENCY FOR TASK JOIN TABLE --
CREATE TABLE noti_freq_for_task(
	task_id INT NOT NULL,
	noti_freq_id SMALLINT NOT NULL,

	PRIMARY KEY(task_id, noti_freq_id),

	CONSTRAINT noti_freq_for_task_task_id_fk FOREIGN KEY(task_id) REFERENCES task(task_id),
	CONSTRAINT noti_freq_for_task_noti_freq_id_fk FOREIGN KEY(noti_freq_id) REFERENCES noti_freq(noti_freq_id)
);


/*
INSERT NOTIFICATION FREQUENCIES FIRST.
*/
INSERT INTO noti_freq (noti_freq_id, noti_freq_title, base_interval, growth_factor, max_repeats)
VALUES
(1, 'Daily', '1 day', 1.0, 10),
(2, 'Weekly', '1 week', 1.0, 4),
(3, 'Monthly', '1 month', 1.0, 12);

/*
INSERT PEOPLE.
*/
INSERT INTO person (phone_no, first_name, last_name, mail, date_of_birth)
VALUES 
('1234567890', 'Hermann', 'Klovnesen', 'hermann@klovnesen.no', '1990-05-15'),
('9876543210', 'Suzanne', 'Sjimpanse', 'suzanne@chimpmail.net', '1985-11-22'),
('5551234567', 'Michael', 'Johnson', 'michael.j@example.com', '1992-03-08');

/*
INSERT ROOMS.
*/
INSERT INTO room (room_id, room_name, room_descr, room_admin)
VALUES 
(1, 'Sirkus', 'Scenen hvor jeg jobber', '1234567890'),
(2, 'Hybelen', 'Der jeg bor', '9876543210');

/*
INSERT TASKS.
*/
INSERT INTO task (task_id, task_title, task_descr, due_date_time, noti_freq_id, creator, completed)
VALUES 
(1, 'Sikkerhetssjekk på akrobatenes liner', 'Inspisere og rengjøre utstyret til akrobatene', '2025-03-15 17:00:00', 2, '1234567890', false),
(2, 'Ta oppvasken', 'Sørge for at det ser ut på kjøkkenet', '2025-03-05 10:00:00', 1, '9876543210', false);

/*
INSERT ROOM_FOR_PERSON RELATIONSHIPS.
*/
INSERT INTO room_for_person (room_id, person_id, score)
VALUES 
(1, '1234567890', 100),
(1, '9876543210', 75),
(2, '9876543210', 100),
(2, '5551234567', 80);


/*
INSERT TASK_FOR_PERSON RELATIONSHIPS.
*/
INSERT INTO task_for_person (task_id, person_id)
VALUES 
(1, '1234567890'),
(1, '9876543210'),
(2, '9876543210'),
(2, '5551234567');

/*
INSERT NOTI_FREQ_FOR_TASK RELATIONSHIPS.
*/
INSERT INTO noti_freq_for_task (task_id, noti_freq_id)
VALUES 
(1, 2),
(2, 1);

-- QUERYING DATA --
/* Here are some useful queries to retrieve data */
-- Get all people
SELECT * FROM PERSON;

-- Get all rooms with their admin's name
SELECT r.room_id, r.room_name, r.room_descr, p.first_name || ' ' || p.last_name AS admin_name
FROM room r
JOIN person p ON r.room_admin = p.phone_no;

-- Get all tasks with creator information
SELECT t.task_id, t.task_title, t.due_date_time, t.completed, p.first_name || ' ' || p.last_name AS creator_name, n.noti_freq_title
FROM task t
JOIN person p ON t.creator = p.phone_no
JOIN noti_freq n ON t.noti_freq_id = n.noti_freq_id;

-- Get all people assigned to a specific task
SELECT t.task_id, t.task_title,
	p.first_name || ' ' || p.last_name AS assignee_name
FROM TASK t
JOIN task_for_person tfp ON t.task_id = tfp.task_id
JOIN person p ON tfp.person_id = p.phone_no
WHERE t.task_id = 1;

-- Get all members of a specific room with their scores
SELECT r.room_name,
	p.first_name || ' ' || p.last_name AS member_name,
	rfp.score
FROM room r
JOIN room_for_person rfp ON r.room_id = rfp.room_id
JOIN person p ON rfp.person_id = p.phone_no
WHERE r.room_id = 1
ORDER BY rfp.score DESC;