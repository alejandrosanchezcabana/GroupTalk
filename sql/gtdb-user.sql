drop user 'gt'@'localhost';
create user 'gt'@'localhost' identified by 'gt';
grant all privileges on gtdb.* to 'gt'@'localhost';
flush privileges;