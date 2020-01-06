INSERT INTO blogger (name, age) VALUES ('Akác Norton', 102);
INSERT INTO blogger (name, age) VALUES ('Jamie Lennister', 40);
INSERT INTO blogger (name, age) VALUES ('Coffee Machine', 3);
INSERT INTO blogger (name, age) VALUES ('Nice Try Billie', 18);
INSERT INTO blogger (name, age) VALUES ('Sittes', 28);

INSERT INTO STORIES (blogger_id, title, content, posted) VALUES ((SELECT id FROM blogger WHERE name = 'Akác Norton' LIMIT 1), 'Izgalmas kalandozások a forgácsolónál', '<p>''Milyen kanyargós faforgácsok'' - gondolta Norton a forgácsoló boltjában szemlélődve.</p>', '2019-12-16 17:11');
INSERT INTO STORIES (blogger_id, title, content, posted) VALUES ((SELECT id FROM blogger WHERE name = 'Nice Try Billie' LIMIT 1), 'Izgalmas kalandozások a tankerhajón', '<p>''Ilyen gecinagy szél bizony nem gyakran van a szárazföldön.'' - gondolta Norton akinek közben fütyülve szelte át a szél kopasz feje és deformált fülei közét, a tankerhajó fedélzetén.</p>', CURRENT_TIME()); -- CURRENT_TIME() helyett NOW()

INSERT INTO STORIES (blogger_id, title, content, posted) VALUES ((SELECT id FROM blogger WHERE name = 'Coffee Machine' LIMIT 1), 'bang', '<p>Here I am.</p>', NOW());
INSERT INTO STORIES (blogger_id, title, content, posted) VALUES ((SELECT id FROM blogger WHERE name = 'Sittes' LIMIT 1), 'outlaw', '<p>Prison sucks.</p>', '2013-03-15 02:25');
INSERT INTO STORIES (blogger_id, title, content, posted) VALUES ((SELECT id FROM blogger WHERE name = 'Sittes' LIMIT 1), 'day231', '<p>Prison sucks again.</p>', '2013-08-05 01:45');
INSERT INTO STORIES (blogger_id, title, content, posted) VALUES ((SELECT id FROM blogger WHERE name = 'Sittes' LIMIT 1), 'day433', '<p>Shrek is love.</p>', '2015-01-12 06:27');

-- SELECT s.id, s.title, s.content, b.name AS AUTHOR FROM STORIES AS s LEFT JOIN BLOGGER AS b WHERE b.id = s.blogger_id;
