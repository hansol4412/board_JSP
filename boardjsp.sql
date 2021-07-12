use kopo41;

drop table boardJSP;
create table boardJSP(
    id int not null primary key,
    b_name varchar(70)
)default charset=utf8;


drop table boardItemJSP;
create table boardItemJSP(
	id int not null primary key auto_increment,
    title varchar(70),
    date date,
    content text,
    viewCnt int,
    boardId int,
    foreign key (boardId) references boardJSP(id)
)default charset=utf8;


create table replyJSP(
		boardItemId int not null,
		replyId int not null,
		date date,
		content text,
		foreign key (boardItemId) references boardItemJSP(id)
)default charset=utf8;

insert into boardJSP values (1, '과제게시판');
insert into boardJSP values (2, '공지게시판');
insert into boardJSP values (3, '자유게시판');
select * from boardJSP;

drop procedure if exists insert_board;
delimiter $$
create procedure insert_board(_limit integer)
BEGIN
	declare _cnt integer;
    declare _title varchar(20);
	declare _content varchar(20);
    set _cnt=0;
    _loop: LOOP
		set _cnt = _cnt +1;
        set _title = concat("자유", cast(_cnt as char(5))) ;
        set _content = concat("자유내용", cast(_cnt as char(5))) ;
        insert into boardItemJSP(title, date, content, viewCnt, boardId) values(_title, date(now()), _content, 0 , 3);
        if _cnt = _limit then
			leave _loop;
		end if;
	END loop _loop;
END $$
call insert_board(100);

select * from boardItemJSP

ALTER TABLE replyJSP DROP FOREIGN KEY replyJSP_ibfk_1;
ALTER TABLE replyJSP ADD CONSTRAINT FOREIGN KEY (boardItemId) REFERENCES boardItemJSP(id) ON DELETE CASCADE;



