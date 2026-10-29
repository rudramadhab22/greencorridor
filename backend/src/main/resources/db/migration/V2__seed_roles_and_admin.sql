INSERT INTO roles (name, description) VALUES
    ('ADMIN', 'System administrator'),
    ('OPERATOR', 'Traffic operator'),
    ('ANALYST', 'Analytics user'),
    ('DRIVER', 'Ambulance driver');

-- Default local admin: username=admin password=Admin@123 (change after first login)
INSERT INTO users (username, email, password_hash, full_name, active)
VALUES (
    'admin',
    'admin@greencorridor.local',
    '$2b$10$b1AJgxvP40Fcey4a.XdvDuddrJOTdEDXETGsXgC13dqXPeFDNPbcK',
    'System Administrator',
    TRUE
);

INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
CROSS JOIN roles r
WHERE u.username = 'admin'
  AND r.name = 'ADMIN';
