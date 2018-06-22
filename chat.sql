UPDATE USER SET NAME='bbb',age=19,email='111',phoneNumber='12345678901',PASSWORD=MD5('')
SELECT * FROM USER WHERE NAME='蔡荣镔'
SELECT role_id roleID FROM user_role WHERE user_name='蔡荣镔'
INSERT INTO user_role VALUES('蔡荣镔',3)
UPDATE user_role SET role_id=4 WHERE user_name='蔡荣镔'
DELETE FROM user_role WHERE user_name='蔡荣镔' AND role_id=3