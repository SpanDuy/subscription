INSERT INTO subscriptions (name, description, price, duration_days) VALUES
    ('Базовый', 'Стандартный план с базовыми функциями', 199.00, 30),
    ('Премиум', 'Расширенный план со всеми функциями', 499.00, 30),
    ('Годовой', 'Годовая подписка со скидкой', 1999.00, 365),
    ('Пробный', 'Бесплатный пробный период', 0.00, 14),
    ('Семейный', 'План для всей семьи с возможностью добавления 5 пользователей', 799.00, 30);

INSERT INTO users (username, email) VALUES
    ('user1', 'user1@example.com'),
    ('user2', 'user2@example.com'),
    ('user3', 'user3@example.com'),
    ('admin', 'admin@example.com'),
    ('test', 'test@example.com');

INSERT INTO user_subscriptions (user_id, subscription_id, start_date, end_date, status) VALUES
    (1, 1, CURRENT_TIMESTAMP - INTERVAL '15 days', CURRENT_TIMESTAMP + INTERVAL '15 days', 'ACTIVE'),
    (1, 3, CURRENT_TIMESTAMP - INTERVAL '30 days', CURRENT_TIMESTAMP + INTERVAL '335 days', 'ACTIVE'),
    (2, 2, CURRENT_TIMESTAMP - INTERVAL '5 days', CURRENT_TIMESTAMP + INTERVAL '25 days', 'ACTIVE'),
    (3, 4, CURRENT_TIMESTAMP - INTERVAL '10 days', CURRENT_TIMESTAMP + INTERVAL '4 days', 'ACTIVE'),
    (4, 5, CURRENT_TIMESTAMP - INTERVAL '60 days', CURRENT_TIMESTAMP - INTERVAL '30 days', 'EXPIRED'),
    (5, 1, CURRENT_TIMESTAMP - INTERVAL '45 days', CURRENT_TIMESTAMP - INTERVAL '15 days', 'EXPIRED'),
    (5, 2, CURRENT_TIMESTAMP - INTERVAL '10 days', CURRENT_TIMESTAMP + INTERVAL '20 days', 'CANCELLED'); 