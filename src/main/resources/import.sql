INSERT INTO Entry(id, title, slug, content, parent_id) VALUES (1, 'Root', 'root', '', null);
INSERT INTO Entry(id, title, slug, content, parent_id) VALUES (2, 'JavaScript utilities', 'js', '...', 1);
INSERT INTO Entry(id, title, slug, content, parent_id) VALUES (3, 'Project Euler', 'euler', '...', 1);
ALTER SEQUENCE entry_seq RESTART WITH 4;
