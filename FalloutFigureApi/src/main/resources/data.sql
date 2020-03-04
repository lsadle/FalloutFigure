DROP TABLE IF EXISTS public.figures;
DROP TABLE IF EXISTS public.addresses;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.orders;
DROP TABLE IF EXISTS public.order_details;

CREATE TABLE public.figures (
    figure_id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(MAX),
    price DOUBLE NOT NULL,
    image_url VARCHAR(MAX));

CREATE TABLE public.addresses (
    address_id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    street VARCHAR(250) NOT NULL,
    city VARCHAR(250) NOT NULL,
    state VARCHAR(250) NOT NULL);

CREATE TABLE public.users (
    user_id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(MAX) NOT NULL,
    shipping_address UUID NOT NULL,
    billing_address UUID NOT NULL,
    FOREIGN KEY (shipping_address) REFERENCES addresses(address_id),
    FOREIGN KEY (billing_address)  REFERENCES addresses(address_id));

CREATE TABLE public.orders (
    order_id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    user_id UUID NOT NULL,
    order_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(USER_ID));

CREATE TABLE public.order_details (
    order_detail_id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    order_id UUID NOT NULL,
    figure_id UUID NOT NULL,
    item_count INT,
    FOREIGN KEY (order_id) REFERENCES orders(ORDER_ID),
    FOREIGN KEY (figure_id) REFERENCES figures(FIGURE_ID));

INSERT INTO public.figures(FIGURE_ID, name, description, price, IMAGE_URL)
VALUES
    ('da186035-9bb3-4665-a043-f4a3889ddfd7',
    'VAULT BOY STRENGTH BOBBLEHEAD',
    'Having proper strength is important out in the wasteland! Add the new Fallout 76 Strength Bobblehead to your collection, it’s one of the 7 perks that makes you S.P.E.C.I.A.L.',
    15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Strength_600x900.jpg?v=1545166216'),
    ('a34b5172-c74d-4709-97a6-dd1c8072df77',
    'VAULT BOY PERCEPTION BOBBLEHEAD',
     'When your out in the wasteland, it’s important to have all of your senses heightened. Boost your perception and grab the new Fallout 76 Perception Bobblehead, one of the 7 that makes you S.P.E.C.I.A.L',
     15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Perception_600x900.jpg?v=1545166157'),
    ('356ea5e8-079b-4600-b4c0-dc34dfa553d7',
    'VAULT BOY ENDURANCE BOBBLEHEAD',
     'Wandering the wasteland is difficult and tiring, be sure to keep up your endurance! Add the new Fallout 76 Endurance Bobblehead to your collection, it’s one of the 7 perks that makes you S.P.E.C.I.A.L',
     15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Endurance_600x900.jpg?v=1545165957'),
    ('6aad2afa-5ee3-41af-871c-1c0008b424d8',
    'VAULT BOY CHARISMA BOBBLEHEAD',
     'Talking your way out of a sticky situation may serve you better than shooting your way out. Keep your charisma high and you’ll be as smooth as grease lightning! Add the new Fallout 76 Charisma Bobblehead to your collection, it’s one of the 7 perks that makes you S.P.E.C.I.A.L.',
     15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Charisma_600x900.jpg?v=1545165870'),
    ('541a3c36-179f-413b-8ab8-28717101a818',
    'VAULT BOY INTELLIGENCE BOBBLEHEAD',
     'Sometimes it takes more than braun to solve a problem when reclaiming the land. Boost your intelligence and make sure you’ve got this crucial perk. Add the new Fallout 76 Intelligence Bobblehead to your collection, it’s one of the 7 that makes you S.P.E.C.I.A.L.',
     15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Intelligence_600x900.jpg?v=1545166036'),
    ('63dafabd-3439-4e14-ba1f-45cd19ff7a14',
    'VAULT BOY AGILITY BOBBLEHEAD',
     'Stay on your toes and keep your head on a swivel! Boost your agility with the new Fallout 76 Agility Bobblehead, it’s one of the 7 perks that makes you S.P.E.C.I.A.L.',
     15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Agility_600x900.jpg?v=1545165793'),
    ('076007ea-b30c-4008-939f-9b85a29dfb61',
    'VAULT BOY LUCK BOBBLEHEAD',
     'Even if you''re the smartest, strongest, most charismatic and most agile wanderer out in the wasteland, it still doesn’t hurt to have a little bit of luck on your side. Boost your luck! Add the new Fallout 76 Luck Bobblehead to your collection, it’s one of the 7 perks that makes you S.P.E.C.I.A.L.',
     15.00,
    'https://cdn.shopify.com/s/files/1/3045/2256/products/Luck_600x900.jpg?v=1545166094');

INSERT INTO public.addresses (address_id, street, city, state)
    VALUES
        ('2f0ea24f-61d5-4768-ab4d-d1b63edecf8e', '123 Town Street', 'Omaha', 'Ne'),
        ('1085faad-a0ca-45df-b2d5-6009912ba2b8', '978 Burlington Road', 'Omaha', 'Ne');

INSERT INTO public.users(USER_ID, username, password, SHIPPING_ADDRESS, BILLING_ADDRESS)
    VALUES ('46c3bf11-7f9a-4c31-b327-a637247928e9', 'lucas', '$2a$12$yBh.cLkwa90WkXv3OjRkiOdMOI5eEaO6SsOFGWt7FUCm1.gCzqo9y', '2f0ea24f-61d5-4768-ab4d-d1b63edecf8e', '1085faad-a0ca-45df-b2d5-6009912ba2b8');

INSERT INTO public.orders (ORDER_ID, USER_ID, ORDER_DATE)
    VALUES
        ('e420a277-3625-4461-b2fe-7662f9be3c2e', '46c3bf11-7f9a-4c31-b327-a637247928e9', PARSEDATETIME('26 Jul 2019, 05:15:58 PM','dd MMM yyyy, hh:mm:ss a','en')),
        ('65592b17-c36b-4e02-9a77-2a4d66da702b', '46c3bf11-7f9a-4c31-b327-a637247928e9', PARSEDATETIME('7 Jan 2020, 03:36:24 PM','dd MMM yyyy, hh:mm:ss a','en'));

INSERT INTO public.order_details (ORDER_DETAIL_ID, ORDER_ID, FIGURE_ID, ITEM_COUNT)
    VALUES
        ('8b5a0876-dc36-411a-a250-2ba814f853e1', 'e420a277-3625-4461-b2fe-7662f9be3c2e', 'da186035-9bb3-4665-a043-f4a3889ddfd7', 1),
        ('ec555365-5f81-41ba-b735-6b8e7aab7be4', 'e420a277-3625-4461-b2fe-7662f9be3c2e', 'a34b5172-c74d-4709-97a6-dd1c8072df77', 2),
        ('9a618e2d-96da-49c3-a279-0f9f1d188403', '65592b17-c36b-4e02-9a77-2a4d66da702b', '356ea5e8-079b-4600-b4c0-dc34dfa553d7', 1),
        ('09958cbe-5c47-455a-a4fb-93f3c8738b25', '65592b17-c36b-4e02-9a77-2a4d66da702b', '6aad2afa-5ee3-41af-871c-1c0008b424d8', 3),
        ('995e9d4b-c3ac-4ae2-86e7-569924d3f80d', '65592b17-c36b-4e02-9a77-2a4d66da702b', '541a3c36-179f-413b-8ab8-28717101a818', 1);
