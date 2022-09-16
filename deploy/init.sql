create database IF NOT EXISTS `trendit` collate utf8mb4_general_ci;
create user 'trendit'@'%' identified by 'trendit';
grant all privileges on *.* to trendit@'%';
flush privileges;
