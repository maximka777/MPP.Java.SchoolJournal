use school_journal_db;
INSERT INTO `role` (`name`,`level`)
Value ('test',1);

INSERT INTO `user` (`role_id`,`username`,`pass_hash`,`locked`,`email`)
VALUES 	(1,'v','v',false,'v'),
		(1,'a','a',false,'a');
INSERT INTO `class` (`number`,`letter_mark`)
VALUES 	(1,'v'),
		(2,'a');
INSERT INTO `class` (`number`,`letter_mark`)
VALUES 	(1,'v'),
		(2,'a');
INSERT INTO `pupil` (`pupil_id`,`class_id`,`first_name`,`pathronymic`,`last_name`)
VALUES 	(3,1,'v','v','v'),
		(4,2,'a','a','a');

insert into `subject` (`name`,`description`)
values ('math','mathematics'),('supermath','high math');

insert into `mark` (`pupil_id`,`type`,`date`,`subject_id`)
values 	(3,'simple',str_to_date('01-01-2017','%d-%m-%Y'),1),
		(3,'simple',str_to_date('01-01-2017','%d-%m-%Y'),2),
        (4,'simple',str_to_date('01-01-2017','%d-%m-%Y'),1),
        (4,'simple',str_to_date('01-01-2017','%d-%m-%Y'),2);
        