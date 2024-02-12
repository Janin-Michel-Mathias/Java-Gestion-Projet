INSERT INTO 'level_skills' ('id', 'level') VALUES
(1, 'junior.e'),
(2, 'expérimenté.e'),
(3, 'expert.e');

INSERT INTO 'skills' (skill_name) VALUES
('HTML'),
('CSS'),
('JavaScript'),
('PHP'),
('SQL'),
('Python'),
('Ruby'),
('Java'),
('C++'),
('C#'),
('Swift'),
('Objective-C'),
('React'),
('Angular'),
('Vue.js'),
('Node.js'),
('Express.js'),
('Symfony'),
('Laravel'),
('Django'),
('Ruby on Rails'),
('Spring');

INSERT INTO 'developers' (name, email) VALUES
('John Doe', 'johndoe@gmail.com'),
('Jane Doe', 'janedoe@gmail.com'),
('John Smith', 'johnsmith@gmail.com'),
('Vanande Kach', 'vava@gmail.com');

INSERT INTO 'developer_skills' (developer_id, skill_id, level_id) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
(2, 6, 2),
(2, 7, 2),
(2, 8, 2),
(2, 9, 2),
(2, 10, 2),
(3, 11, 3),
(3, 12, 3),
(3, 13, 3),
(3, 14, 3),
(3, 15, 3),
(4, 16, 1),
(4, 17, 1),
(4, 18, 1),
(4, 19, 1),
(4, 20, 1),
(4, 21, 1);

UPDATE 'skills' SET skill_name = 'HTML' WHERE id = 1;