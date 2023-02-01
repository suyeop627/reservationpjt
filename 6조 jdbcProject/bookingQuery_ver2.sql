USE bookingdb;

DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS roomtype;


CREATE TABLE member (
	m_id	VARCHAR(20)	PRIMARY KEY NOT NULL,
	m_pwd	VARCHAR(10)	NOT NULL,
	m_name	VARCHAR(10)	NOT NULL,
	m_birth	DATE	NOT NULL,
	m_phone	VARCHAR(20)	NOT NULL,
	m_email	VARCHAR(50)	NOT NULL
);

CREATE TABLE room (
	r_no	INT	PRIMARY KEY NOT NULL,
	t_name	VARCHAR(10)	NOT NULL,
	r_status	VARCHAR(10)	NOT NULL	DEFAULT '예약가능'
);

CREATE TABLE booking (
	b_no	INT	PRIMARY KEY AUTO_INCREMENT NOT NULL,
	m_id	VARCHAR(20)	NOT NULL,
	r_no	INT	NOT NULL,
	t_name	VARCHAR(10)	NOT NULL,
	b_start	DATE	NOT NULL,
	b_end	DATE	NOT NULL
);

CREATE TABLE RoomType (
	t_name	VARCHAR(10) PRIMARY KEY NOT NULL,
	t_bed	INT	NOT NULL,
	t_size	VARCHAR(20)	NOT NULL,
	t_tub	VARCHAR(10)	NOT NULL	DEFAULT '유',
	t_price	INT	NOT NULL,
	t_parking	VARCHAR(10)	NOT NULL,
	t_maxnum	INT	NOT NULL,
	t_specialnote	VARCHAR(255)	NULL
);

CREATE TABLE admin (
	a_id	VARCHAR(20) PRIMARY KEY NOT NULL,
	a_pwd	VARCHAR(20)	NOT NULL,
	a_name	VARCHAR(20)	NOT NULL
);


ALTER TABLE room ADD CONSTRAINT FK_RoomType_TO_room_1 FOREIGN KEY (t_name)
REFERENCES RoomType (t_name) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE booking ADD CONSTRAINT FK_member_TO_booking_1 FOREIGN KEY (m_id)
REFERENCES member (m_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE booking ADD CONSTRAINT FK_room_TO_booking_1 FOREIGN KEY (r_no)
REFERENCES room (r_no) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE booking ADD CONSTRAINT FK_room_TO_booking_2 FOREIGN KEY (t_name)
REFERENCES room (t_name) ON UPDATE CASCADE ON DELETE CASCADE;


-- 임시 데이터 

-- member 
select * from member;
INSERT INTO member VALUES ('test01', '0000', '김은영','1991-03-03', '000-0000-0000','456@email.com'); 
INSERT INTO member VALUES ('test02', '0000', '김선민','1995-12-11', '010-2222-2222','3571@email.com'); 
INSERT INTO member VALUES ('test03', '0000', '유지영','1997-05-11', '010-1111-1111','dfsg2@email.com'); 
INSERT INTO member VALUES ('test04', '0000', '김승현','1999-01-11', '010-4444-4444','sadfu@email.com'); 
INSERT INTO member VALUES ('test05', '0000', '박수진','1990-01-01', '010-5555-5555','xzv11@email.com'); 
INSERT INTO member VALUES ('test06', '0000', '강헤인','1990-06-06', '010-6666-6666','ashj@email.com'); 
INSERT INTO member VALUES ('sameName1', '0000', '동명이인','1990-07-07', '010-7777-7777','asf1hk@email.com'); 
INSERT INTO member VALUES ('sameName2', '0000', '동명이인','1990-08-08', '010-8888-8888','fgvki@email.com'); 
INSERT INTO member VALUES ('under18', '0000', '미성년자','2004-09-09', '010-9999-9999','234nhkj@email.com'); 
select * from member;


-- roomType
select * from roomType;

INSERT INTO roomtype VALUE ('일반', 1, '5평', DEFAULT, 25000, '가능', 1, null);
INSERT INTO roomtype VALUE ('더블', 2, '8평', DEFAULT, 45000, '가능', 2, null);
INSERT INTO roomtype VALUE ('트리플', 2, '10평', DEFAULT, 65000, '가능', 3, null);
INSERT INTO roomtype VALUE ('특실', 4, '15평', DEFAULT, 125000, '가능', 5, null);



-- room
select * from room;

INSERT INTO room VALUES ( 101, '일반', '예약중');
INSERT INTO room VALUES ( 102, '일반', '예약중');
INSERT INTO room VALUES ( 103, '일반', default);
INSERT INTO room VALUES ( 104, '더블', default);
INSERT INTO room VALUES ( 105, '트리플', default);
INSERT INTO room VALUES ( 106, '특실', '예약중');

INSERT INTO room VALUES ( 201, '더블', '예약중');
INSERT INTO room VALUES ( 202, '더블', '예약중');
INSERT INTO room VALUES ( 203, '더블', default);
INSERT INTO room VALUES ( 204, '트리플', default);
INSERT INTO room VALUES ( 205, '트리플', default);
INSERT INTO room VALUES ( 206, '트리플', default);

INSERT INTO room VALUES ( 301, '더블', '예약중');
INSERT INTO room VALUES ( 302, '더블', default);
INSERT INTO room VALUES ( 303, '트리플', '예약중');
INSERT INTO room VALUES ( 304, '트리플', default);
INSERT INTO room VALUES ( 305, '특실', default);
INSERT INTO room VALUES ( 306, '특실', default);

-- booking

INSERT INTO booking VALUE (null, 'test01', 101, '일반', '2022-10-13', '2022-10-17');
INSERT INTO booking VALUE (null, 'test02', 102, '일반', '2022-10-30', '2022-11-19');
INSERT INTO booking VALUE (null, 'test03', 106, '특실', '2022-10-15', '2022-10-16');
INSERT INTO booking VALUE (null, 'test04', 201, '더블', '2022-10-22', '2022-10-25');
INSERT INTO booking VALUE (null, 'test05', 202, '더블', '2022-11-01', '2022-11-23');
INSERT INTO booking VALUE (null, 'test06', 301, '더블', '2022-11-20', '2022-11-30');
INSERT INTO booking VALUE (null, 'samename1', 303, '트리플', '2022-10-20', '2022-10-30');

-- admin
INSERT INTO admin VALUE ('admin', '0000', '관리자');

commit;





