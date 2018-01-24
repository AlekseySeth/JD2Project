CREATE DATABASE sport_nutrition;
USE sport_nutrition;

CREATE TABLE roles (
  id   INT,
  name VARCHAR(15),
  PRIMARY KEY (id)
);

INSERT INTO roles (id, name) VALUES (0, 'Гость');
INSERT INTO roles (id, name) VALUES (1, 'Администратор');
INSERT INTO roles (id, name) VALUES (2, 'Маркетолог');
INSERT INTO roles (id, name) VALUES (3, 'Покупатель');

CREATE TABLE users (
  id                INT AUTO_INCREMENT,
  first_name        VARCHAR(50)         NOT NULL,
  last_name         VARCHAR(50),
  email             VARCHAR(100) UNIQUE NOT NULL,
  password          VARCHAR(50)         NOT NULL,
  phone             VARCHAR(20)         NOT NULL,
  address           VARCHAR(100)        NOT NULL,
  registration_date TIMESTAMP,
  role_id           INT,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
)
  AUTO_INCREMENT = 1000;

INSERT INTO users (id, first_name, email, password, phone, address, registration_date, role_id)
VALUES (1, 'Admin', 'Admin', 'JaS3MecSfP8f23L0DfTeuBV+AvtCpVcC8ybqb9XVjME=', 'admin@sportpit.by', 'admin@sportpit.by',
        '2017-12-01', 1);
INSERT INTO users (id, first_name, email, password, phone, address, registration_date, role_id)
VALUES (2, 'Marketer', 'Marketer', 'SOEXTRf5PxsZi4fm4QD5pxiTuMpWF5YUZ4+Ll1YUMes=', 'marketer@sportpit.by',
        'marketer@sportpit.by', '2017-12-01', 2);

CREATE TABLE categories (
  id          INT AUTO_INCREMENT,
  name        VARCHAR(30) NOT NULL,
  description TEXT,
  parent_id   INT,
  PRIMARY KEY (id),
  FOREIGN KEY (parent_id) REFERENCES categories (id)
);

CREATE TABLE products (
  id          INT          AUTO_INCREMENT,
  name        VARCHAR(100) UNICODE NOT NULL,
  description TEXT,
  price       DOUBLE               NOT NULL,
  qty         INT                  NOT NULL,
  category_id INT,
  image_url   VARCHAR(100) DEFAULT '/images/default.png',
  PRIMARY KEY (id),
  FOREIGN KEY (category_id) REFERENCES categories (id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE deliveries (
  id   INT AUTO_INCREMENT,
  name VARCHAR(30) UNIQUE NOT NULL,
  cost DOUBLE             NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO deliveries (name, cost) VALUES ('Самовывоз', 0.0);
INSERT INTO deliveries (name, cost) VALUES ('Стандартная доставка', 3.50);
INSERT INTO deliveries (name, cost) VALUES ('Экспресс доставка', 5.00);

CREATE TABLE statuses (
  id   INT AUTO_INCREMENT,
  name VARCHAR(15) UNIQUE NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO statuses (name) VALUES ('Открыт');
INSERT INTO statuses (name) VALUES ('В обработке');
INSERT INTO statuses (name) VALUES ('Доставлен');
INSERT INTO statuses (name) VALUES ('Отменен');

CREATE TABLE orders (
  id          INT AUTO_INCREMENT,
  status_id   INT    NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                DEFAULT 1,
  total_price DOUBLE NOT NULL,
  delivery_id INT    NOT NULL,
  open_date   TIMESTAMP,
  close_date  TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (delivery_id) REFERENCES deliveries (id),
  FOREIGN KEY (status_id) REFERENCES statuses (id)
)
  AUTO_INCREMENT = 1000;

CREATE TABLE orders_products (
  order_id    INT,
  product_id  INT,
  product_qty INT NOT NULL,
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES orders (id),
  FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE users_orders (
  user_id  INT,
  order_id INT,
  PRIMARY KEY (user_id, order_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (order_id) REFERENCES orders (id)
);

CREATE TABLE pages (
  id  INT AUTO_INCREMENT,
  url VARCHAR(50),
  PRIMARY KEY (id)
);

CREATE TABLE roles_pages (
  role_id INT,
  page_id INT,
  FOREIGN KEY (role_id) REFERENCES roles (id),
  FOREIGN KEY (page_id) REFERENCES pages (id)
);

##Permissions##
INSERT INTO pages (url) VALUES ('/admin');
INSERT INTO pages (url) VALUES ('/cart');
INSERT INTO pages (url) VALUES ('/download-order');
INSERT INTO pages (url) VALUES ('/login');
INSERT INTO pages (url) VALUES ('/log-out');
INSERT INTO pages (url) VALUES ('/marketer');
INSERT INTO pages (url) VALUES ('/my-account');
INSERT INTO pages (url) VALUES ('/order');
INSERT INTO pages (url) VALUES ('/order-placed');
INSERT INTO pages (url) VALUES ('/orders-list');
INSERT INTO pages (url) VALUES ('/products-list');
INSERT INTO pages (url) VALUES ('/registration');
INSERT INTO pages (url) VALUES ('/update-order');
INSERT INTO pages (url) VALUES ('/update-password');
INSERT INTO pages (url) VALUES ('/update-product');
INSERT INTO pages (url) VALUES ('/update-profile');
INSERT INTO pages (url) VALUES ('/update-user');
INSERT INTO pages (url) VALUES ('/user');
INSERT INTO pages (url) VALUES ('/users-list');
INSERT INTO pages (url) VALUES ('/add-product');
INSERT INTO pages (url) VALUES ('/remove-from-cart');

INSERT INTO roles_pages (role_id, page_id) VALUES (0, 1);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 2);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 3);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 5);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 6);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 7);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 8);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 9);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 10);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 11);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 13);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 14);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 15);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 16);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 17);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 18);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 19);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 20);
INSERT INTO roles_pages (role_id, page_id) VALUES (0, 21);


INSERT INTO roles_pages (role_id, page_id) VALUES (1, 2);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 3);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 4);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 6);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 9);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 12);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 16);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 20);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 21);

INSERT INTO roles_pages (role_id, page_id) VALUES (2, 1);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 2);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 3);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 4);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 9);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 12);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 16);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 17);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 18);
INSERT INTO roles_pages (role_id, page_id) VALUES (2, 19);
INSERT INTO roles_pages (role_id, page_id) VALUES (1, 21);

INSERT INTO roles_pages (role_id, page_id) VALUES (3, 1);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 4);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 6);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 10);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 11);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 12);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 13);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 15);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 17);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 18);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 19);
INSERT INTO roles_pages (role_id, page_id) VALUES (3, 20);

##Products##

INSERT INTO categories (id, name, description) VALUES (1, 'Протеин', 'Протеин — это концентрированный белок, который является
основным строительным элементом для нашего тела. Без протеинов невозможно продвижение в спорте, он содержит аминокислоты,
необходимые для развития организма человека');

INSERT INTO categories (id, name, description) VALUES (2, 'Гейнеры', 'Гейнер — вид продукта для питания спортсменов, который
представляет собой белково-углеводную смесь. Также может содержаться небольшое количество жиров. Гейнеры предназначены
для набора массы тела, а также для восполнения энергетических запасов организма');

INSERT INTO categories (id, name, description) VALUES (3, 'Аминокислоты', 'Аминокислоты — это элементы, из которых состоят белки.
Белки, поступающие с пищей в организм,  расщепляются на аминокислоты, которые выполняют многочисленные жизненно
необходимые функции, в том числе и восстановление мышц после тренировки и образование новых мышечных тканей');

INSERT INTO categories (id, name, description) VALUES (4, 'BCAA', 'ВСАА — это комплекс из трёх аминокислот с разветвлённой
цепью – валин, лейцин и изолейцин. Они являются важными компонентами белков. В отличие от других аминокислот они не
синтезируются организмом, а потому их необходимо получать с пищей. BCAA - основной материал для построения новых мышц,
эти незаменимые аминокислоты составляют 35% всех аминокислот в мышцах и принимают важное участие в процессах анаболизма
и восстановления, обладают антикатаболическим действием');

INSERT INTO categories (id, name, description) VALUES (5, 'Креатин', 'Креатин — азотсодержащая карбоновая кислота, которая
участвует в энергетическом обмене в мышечных и нервных клетках. В мире бодибилдинга, креатин широко используется как
спортивная добавка, для увеличения силы, мышечной массы и кратковременной аэробной выносливости с доказанной безопасностью');

INSERT INTO categories (id, name, description) VALUES (6, 'Витамины', 'Витамины — это биологически активные вещества, участвующие
в процессах жизнедеятельности организма и поступающие с пищей. Не все витамины синтезируются организмом, а количество тех,
которые поступают с пищей, бывает недостаточным. Вследствие этого витамины для спортсменов выпускаются в виде витаминных комплексов.
Минеральные вещества — микро и макроэлементы, также участвуют в биосинтезе белка, регулируют процессы обмена веществ
внутри клеток и в организме в целом, мышечной деятельности и др. Так же, как и витамины, минеральные вещества поступают
с пищей, но чтобы обеспечить организм достаточным их количеством, необходимо принимать минеральные комплексы');

INSERT INTO categories (id, name, description) VALUES (7, 'Жиросжигатели', 'Жиросжигатели — это специальные препараты или смеси,
которые способствуют эффективному снижению массы тела за счет редукции жировых отложений спортсмена. Кроме того с их
помощью легче концентрироваться на выполнении упражнений и облегчаются тренировки.');

INSERT INTO categories (id, name, parent_id) VALUES (11, 'Biotech', 1);
INSERT INTO categories (id, name, parent_id) VALUES (12, 'QNT', 1);
INSERT INTO categories (id, name, parent_id) VALUES (13, 'Olimp', 1);
INSERT INTO categories (id, name, parent_id) VALUES (14, 'ActivLab', 1);
INSERT INTO categories (id, name, parent_id) VALUES (15, 'IronMaxx', 1);

INSERT INTO categories (id, name, parent_id) VALUES (21, 'Biotech', 2);
INSERT INTO categories (id, name, parent_id) VALUES (22, 'QNT', 2);
INSERT INTO categories (id, name, parent_id) VALUES (23, 'Olimp', 2);

INSERT INTO categories (id, name, parent_id) VALUES (31, 'Biotech', 3);
INSERT INTO categories (id, name, parent_id) VALUES (32, 'QNT', 3);
INSERT INTO categories (id, name, parent_id) VALUES (33, 'Olimp', 3);
INSERT INTO categories (id, name, parent_id) VALUES (34, 'IronMaxx', 3);

INSERT INTO categories (id, name, parent_id) VALUES (41, 'Biotech', 4);
INSERT INTO categories (id, name, parent_id) VALUES (42, 'QNT', 4);
INSERT INTO categories (id, name, parent_id) VALUES (43, 'Olimp', 4);
INSERT INTO categories (id, name, parent_id) VALUES (44, 'IronMaxx', 4);

INSERT INTO categories (id, name, parent_id) VALUES (51, 'Biotech', 5);
INSERT INTO categories (id, name, parent_id) VALUES (52, 'QNT', 5);
INSERT INTO categories (id, name, parent_id) VALUES (53, 'Olimp', 5);
INSERT INTO categories (id, name, parent_id) VALUES (54, 'IronMaxx', 5);

INSERT INTO categories (id, name, parent_id) VALUES (61, 'Biotech', 6);
INSERT INTO categories (id, name, parent_id) VALUES (62, 'QNT', 6);
INSERT INTO categories (id, name, parent_id) VALUES (63, 'Olimp', 6);

INSERT INTO categories (id, name, parent_id) VALUES (71, 'Biotech', 7);
INSERT INTO categories (id, name, parent_id) VALUES (72, 'QNT', 7);
INSERT INTO categories (id, name, parent_id) VALUES (73, 'Olimp', 7);

#Protein
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Protein Power Biotech USA 1000',
        'Protein Power – превосходный протеиновый порошок для целеустремлённых атлетов, которым необходимы продукты с высоким содержанием белка.
        Как и вся продукция BioTech USA, Protein Power содержит только безопасные для здоровья и тщательно отобранные ингредиенты.
        Изготовленный с добавлением высококачественного сывороточного протеина, Protein Power снабжает организм ВСАА, L-глутамином и другими заменимыми и незаменимыми аминокислотами.
        Его белковая составляющая способствует наращиванию мышечной массы и поддержанию формы мышц.
        Protein Power пользуется популярностью среди продукции BioTech USA, выделяясь на рынке протеиновых добавок выгодным соотношением цены и качества.',
        47.70, 100, 11,
        '/images/Protein Power Biotech USA 1000.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Iso Whey Zero Biotech USA 908',
        'Iso Whey Zero  от  BioTechUSA  -  чистейший изолят сывороточного белка. Чрезвычайно высокая биологическая ценность,  высокая скорость  усваивания белков делает Iso Whey Zero  абсолютно уникальным.
        Продукт Не содержит лактозы, трансжиров и сахара. Iso Whey Zero изготовлен из безопасных для здоровья и тщательно отобранных нутриентов. Никаких лишних или скрытых нутриентов, которые могут откладываться в виде жира. Только  протеин в каждой порции!
        Он идеален для спортсменов, стремящихся нарастить сухую мышечную массу, ведь белки, безусловно, способствуют росту мышечной массы и поддержанию ее формы.
        Iso Whey Zero богат ВСАА и незаменимыми аминокислотами, а также L-глутамином – важнейшими аминокислотами, позволяющими наращивать сухую мышечную массу.',
        89.90, 100, 11,
        '/images/Iso Whey Zero Biotech USA 908.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Biotech Iso Whey Zero lact free 500g',
        'Iso Whey ZERO состоит из чистейшего изолята сывороточного протеина, полученного методом микрофильтрации в поперечном потоке. НЕ СОДЕРЖИТ лактозы, трансжиров и сахара. Iso Whey ZERO, как и вся продукция BioTech USA, изготовлен из безопасных для здоровья и тщательно отобранных нутриентов.
        Некоторым хочется быть стройнее
        Благодаря чрезвычайно высокой биологической ценности белков Iso Whey ZERO представляет собой совершенно уникальный продукт в линии продуктов BioTech USA. Он идеально подходит для спортсменов, стремящихся нарастить сухую мышечную массу,  т.к. белки, безусловно, способствуют росту мышечной массы и поддержанию ее формы. «НЕ СОДЕРЖИТ» значит «НЕ СОДЕРЖИТ». Действительно, Iso Whey ZERO НЕ СОДЕРЖИТ ни трансжиров, ни сахара. Никаких лишних или скрытых нутриентов, которые могут откладываться в виде жира. Только 22 г чистейшего протеина в каждой порции для наращивания мышечной массы.
        Высокое содержание ВСАА, L-глутамина и незаменимых аминокислот
        Поскольку Iso Whey ZERO изготовлен из тщательно отобранных источников белка, его аминокислотный профиль идеально подходит для эффективного наращивания мышц. Он богат ВСАА и незаменимыми аминокислотами, а также L-глутамином – важнейшими аминокислотами, позволяющими наращивать сухую мышечную массу.',
        49.90, 100, 11, '/images/Biotech Iso Whey Zero lact free 500g.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Delicious Whey Protein QNT',
        'Как всем известно, белок – это главный строительный материал, а протеин Delicious Whey Protein является его отличным источником. Компания QNT разработала продукт, который специально предназначен для обеспечения спортсмена питательными веществами для максимального роста мышечной массы.
        По мнению многих специалистов, протеин Delicious Whey Protein является оптимальным соотношением цена/качество. Данный продукт отлично усваивается организмом и имеет хорошую растворимость. С помощью разработки специалистов QNT вы сможете сделать ощутимый шаг на пути к своей идеальной форме.
        Достоинства Delicious Whey Protein от QNT:
        - превосходный источник белка в высокой концентрации;
        - способствует увеличению мышечной массы и силы;
        - оптимальное соотношение цена/качество;',
        62.90, 100, 12,
        '/images/Delicious Whey Protein QNT.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Whey Protein Complex 100% 700 g',
        'Сывороточный протеин является наиболее распространённым продуктом спортивного питания, т.к. обладает очень высокой биологической ценностью и скоростью усвоения. Olimp Whey Protein Complex 100% обеспечивает быструю подпитку ваших мышц аминокислотами, тем самым обеспечивая быстрый рост мышц и восстановление.
        Сывороточный протеин Whey Protein Complex 100% Olimp чрезвычайно богат разветвленными аминокислотами BCAA, глютамином, а также другими аминокислотами, обеспечивающими запуск анаболических процессов в организме. Каждый прием этого протеина сопровождается быстрым высвобождением аминокислот в кровь и их транспортировкой к мышечным клеткам. Именно поэтому сывороточный протеин отлично подходит для приема после тренировки и утром, после сна – когда в организме наблюдается белковое окно.
        Протеин совершенно необходим спортсмену, он способствует ускоренному росту мышц и их активному восстановлению. Попадая в организм, протеин расщепляется на аминокислоты. Эти аминокислоты попадают в кровь и транспортируются к мышцам. Т.е. наш организм строит мышцы из аминокислот, полученных из протеина. Спортсменам требуется повышенное количество протеина – 1,5-2,5 гр. Белка на каждый килограмм веса тела.',
        48.30, 100, 13,
        '/images/Whey Protein Complex 100 700 g.png');

#ActivLab
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('MUSCLE UP Protein ActivLab 2000',
        'MUSCLE UP Protein ActivLab – это чистый сывороточный протеин, который ускоряет рост мышечной массы и повышает качество восстановительных процессов. Данная добавка может приниматься как во время набора массы, так и во время сушки. Muscle UP помогает спортсменам улучшать рельефность тела и делать свои тренировки более эффективными. Протеин можно принимать как мужчинам, так и женщинам.
        Преимущество MUSCLE UP Protein ActivLab в том, что данный препарат помимо сывороточного белка содержит также креатин моногидрат и таурин, которые помогают увеличивать силовые показатели и защищают мышцы от катаболизма.
        В целом, MUSCLE UP Protein ActivLab обладает следующими эффектами:
        - Рост сухой мышечной массы и повышение силового потенциала;
        - Ускорение выносливости и заживления травм;
        - Подавление катаболизма и быстрое возобновление энергетических запасов;
        - Повышение анаэробной выносливости и производительности на тренировках.',
        79.00, 100, 14,
        '/images/MUSCLE UP Protein ActivLab 2000.png');

#IronMaxx
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('100% Whey Protein 900',
        'Когда обычные протеиновые коктейли смешиваются с водой, часто страдает вкус. Задачи перед разработчиками IronMaxx ® были поставлено чётко: Новый протеин должен быть потрясающе вкусным даже при смешивании с водой. И новый 100% сывороточный протеин 100% Whey Protein, разработаный для смешивания с водой, во много раз превзошёл все ожидания. В Вашем распоряжении 14 потрясающих и неповторимых вкусов нового 100% сывороточного протеина от IronMaxx: печенье-взбитые сливки, вишня-йогурт, лимон-йогурт, арбуз, апельсин-маракуйя, клубника, малина, латте-макиато, белый шоколад, флорида-грейпфрут, фисташки-кокос, ананас, яблоко-корица,черная смородина-йогурт, банан-йогурт, тёмный шоколад, киви-йогурт, фундук.
        Новый сывороточный протеин марки IronMaxx ® не только черезвычайно вкусен, он также обладает отличной питательной ценностью и потрясающим соотношением цены и качества. Каждая порция 100% Whey Protein IronMaxx ® обеспечит Вас 38,5 г чистого, бытроусваиваемого сывороточного белка с очень низким содержанием жира, холестерина и лактозы. Это идеальный белок после тренировки, помогающий Вам эффективно наращивать мышцы и восстанавливаться.',
        77.50, 100, 15,
        '/images/100 Whey Protein 900.png');

#Gainer
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Biotech CARBOX 1000',
        'В 1 ПОРЦИИ:
        50 г смеси Multi-Interval Carbox™
        Обогащённый смесью Multi-Interval Carbox™, данный ультрасовременный комплекс углеводов разработан для приёма до и во время тренировки. Carbox™, как и все продукты BioTech USA, состоит из безопасных для здоровья и тщательно отобранных компонентов.
        Carbox™ состоит из 5 форм углеводов разной степени сложности. Carbox™ также снабжает организм палатинозой™: она содержит изоматулозу, которая по сравнению с другими сахарами в меньшей степени повышает уровень глюкозы в крови.',
        18.40, 100, 21,
        '/images/Biotech CARBOX 1000.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Hyper Mass 5000 Biotech USA',
        'Гейнеры Biotech Hyper Mass 5000 отлично подойдут тем спортсменам-бодибилдерам, которые активно тренируются на протяжении долгого времени. В состав продукта включены протеины и углеводы – все это потребуется вам для успешных тренировок, и для скорейшего достижения поставленной цели. Креатин, который содержится в продукте, позволяет увеличить силу и выносливость организма, а также – ускорит восстановление.',
        87.90, 100, 21,
        '/images/Hyper Mass 5000 Biotech USA.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('QNT 3000 MuscleMass',
        'Высококалорийный Muscle Mass 3000 является идеально сбалансированной углеводно-протеиновой смесью, содержащей необходимые аминокислоты, микроэлементы, а также комплекс витаминов для лучшего усвоения основных питательных веществ.
        Присутствующие в составе гейнера Muscle Mass 3000 быстрые и медленные углеводы играют важную роль в обеспечении организма спортсмена энергией и восстановлении ресурсов гликогена, истраченного в процессе тренировки. Благодаря углеводной «подпитке» организм получает энергию извне и активизирует внутренние источники энергии, которая дает прилив физических сил и тонус для тренировок, а также обеспечивает более короткий и эффективный период восстановления.
        Гейнер Muscle Mass 3000 от QNT работает на увеличение мышечных объемов и улучшение физических показателей. Кроме того, благодаря гейнеру у Вас не возникает синдром усталости от тренировок, тормозятся катаболические явления, более качественно проходит процесс восстановления между тренировками.',
        112.90, 100, 22,
        '/images/QNT 3000 MuscleMass.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Olimp Gain Bolic 6000',
        'GainBolic 6000 – белково-углеводная витаминизированная смесь, предназначенная для людей, которые занимаются , ведут активный образ жизни и которые хотят увеличить общий вес тела. В состав углеводной матрицы входят сахара с высоким и низким гликемическим индексом, благодаря чему гейнер становится разнонаправленным - организм повышает свой энергетический запас и повышается масса тела. В состав белка входит смесь протеинов: сывороточный, казеиновый и яичный. В гейнер добавлены также креатин, таурин и витаминный комплекс. Gain Bolic 6000 обеспечивает организм равномерным и постоянным притоком калорий , тем самым предотвращая катаболизм.',
        97.90, 100, 23,
        '/images/Olimp Gain Bolic 6000.png');

#Amino
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Mega Amino 3200 Biotech USA - 300',
        'Mega Amino 3200 аминокислоты высшего качества, помогают в поддержании положительного азотистого баланса, способствуют сжиганию жировой прослойки, увеличивают работоспособность организма в период интенсивных тренировок.
        Каждая таблетка Mega Amino 3200 поставляет аминокислоты, которые могут быть сразу абсорбированы кровяным потоком, для поддержания позитивного азотного баланса, увеличивать анаболический рост мышц и уменьшать жир в организме.
        В человеческом организме аминокислоты не только формируют блоки нашей свободной, или скелетной, мышечной ткани, такие как бицепсы, квадрицепсы и т.п., но они также формируют блоки непроизвольных мышц, таких как сердце. Исследования показали, что нехватка развитой разветвленной цепи аминокислот вместе с повышенными физическими требованиями к телу, могут привести к использованию здоровых мышечных тканей для обеспечения организма азотом. В функции цепи аминокислот, наряду с другими, входят транспортирование диетического жира в клетки для выработки энергии стимуляция слизистой выделять увеличенное количество гормонов, отвечающих за развитие слабой мышечной ткани, а также за мобилизацию жирных кислот из жировой ткани (т.е. сброс жира организма); обеспечения тела азотом. Mega Amino 3200 всасываются очень быстро, они легко попадают в кровяной поток и помогают гармонизировать и ферментизировать продукцию.',
        52.90, 100, 31, '/images/Mega Amino 3200 Biotech USA - 300.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Biotech AAKG Shock 1000 ml',
        'В 1 ПОРЦИИ:
        6000 мг аргинина альфа-кетоглутарата
        80 мг магния
        Каждая порция AAKG Shock поставляет в организм 6000 мг чистого аргинина альфа-кетоглутарата. Кроме того, AAKG Shock обогащен магнием, который помогает бороться с усталостью. Как и все продукты BioTech USA, AAKG Shock состоит из безопасных для здоровья и тщательно отобранных ингредиентов.',
        68.30, 100, 31,
        '/images/Biotech AAKG Shock 1000 ml.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('AAKG4000 QNT',
        'ХАРАКТЕРИСТИКИ:
        • поддержка мышц
        • улучшает приток крови в мышцы во время тренировки
        • помогает в строительстве мышц
        • оптимизирует восстановление и силу',
        38.90, 100, 32,
        '/images/AAKG4000 QNT.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('1250 Extreme Mega Caps',
        'Аргинин альфа-кетоглутарат (ААКГ) — это L-аргнинин в легкоусваиваемой форме и в большой дозировке. AAKG Extreme 1250 Mega Caps от Olimp — идеальный продукт для увеличения силы, роста мышечной массы, формирования рельефа, а также для поддержки сексуальной функции.
        Действие AAKG 1250 Extreme Mega Caps Olimp
        Анаболизм и гипертрофия мышечной ткани
        Памп-эффект во время и после тренировки
        Сжигание подкожного жира
        Увеличение интенсивности и продолжительности занятий
        Донатор, предшественник и переносчик NO (оксида азота).
        Клинически доказано, что употребление 2г аргинина в день на протяжении пяти недель способствует увеличению силы мышц, росту мышечной массы, а также снижению процента жира и выводу продуктов белкового распада.
        Olimp AAKG Extreme 1250 Mega Caps может использоваться как профессиональными атлетами, так и теми, кто занимается спортом время от времени.',
        49.90, 100, 33,
        '/images/1250 Extreme Mega Caps.png');

#IronMaxx
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Green Amino',
        'Green Amino в капсулах снабжает Вашу мускулатуру расщеплёнными под воздействием ферментов компонентами белка и способствует росту мышечной массы с помощью быстро усваиваемых аминокислот. Отличие этого продукта от белковых порошков легко объяснить: прежде чем наше тело сможет использовать белок, он должен перевариться в желудочно-кишечном тракте и расщепиться на отдельные компоненты. В отличие от этого аминокислоты Green Amino уже расщеплены под воздействием ферментов, то есть уже разобраны на отдельные составляющие. В результате чего аминокислоты Green Amino гораздо быстрее попадают в кровь и моментально начинают участвовать во всех процессах организма. Поэтому поддержите Ваши мышцы аминокислотами Green Amino, чтобы достичь решающих успехов в наращивании мышечной массы!',
        56.40, 100, 34,
        '/images/Green Amino.png');

#BCAA
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('BCAA B6 Biotech USA - 100',
        'Препарат BCAA + B6 в таблетках содержит аминокислоты лейцин, изолейцин и валин в соотношении 2:1:1. Кроме того, он дополнен витамином B6, который способствует нормальному обмену белка и гликогенов, а также снижает утомляемость. Как и все продукты BioTech USA, BCAA + B6 340 tabs состоит из безопасных для здоровья и тщательно отобранных компонентов.',
        28.90, 100, 41,
        '/images/BCAA B6 Biotech USA - 100.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Amino Liquid BCAA Biotech USA',
        'BioTechLiquid BCAA - это самая важная группа незаменимых аминокислот в жидкой форме для атлета. BCAA помогают поддерживать положительный баланс азота, сохраняют мышечную ткань даже в условиях жесткой диеты, обеспечивают мышцы дополнительной энергией во время тренировок. Исследования свидетельствуют, что недостаток BCAA наряду с интенсивными физическими нагрузками приводит к разрушению мышечной ткани. Среди функций BCAA можно отметить транспортировку жиров внутрь клетки для последующего получения из них энергии, усиление секреции гормона роста, мобилизацию жирных кислот из адипозной ткани (подкожного жира), снабжение организма азотом.',
        46.90, 100, 41,
        '/images/Amino Liquid BCAA Biotech USA.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Matrix QNT',
        'BCAA аминокислоты очень быстро усваиваются организмом и при этом попадают непосредственно в мышечную ткань. В пиковую нагрузку на тренировке они могут выступать в качестве дополнительного источника энергии и способствовать достижению максимального результата. Комплекс BCAA от QNT служит надежным помощником и эффективным восстановителем для спортсменов любого уровня.',
        50.90, 100, 42,
        '/images/Matrix QNT.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('BCAA Xplode 280 g',
        'Olimp BCAA XPLODE обогащен также глютамином, который крайне важен для активации иммунных клеток крови. Кроме того, данная аминокислота наряду с глюкозой является источником энергии, вызывает подъем уровня гормона роста, подавляет секрецию кортизола, ускоряет восстановление и предотвращает развитие перетренированности. В общем и целом, глютамин усиливает и поддерживает воздействие BCAA на организм.
        Имеется в составе данного продукта и витамин B6 (пиридоксин), который поддерживает обмен аминокислот, являющихся строительным материалом для тканей тела, поэтому если вы строите свой рацион из белковой пищи, то вам следует увеличивать потребление данного витамина. Кроме того, пиридоксин уменьшает ночные спазмы и судороги мышц и поддерживает оптимальное функционирование нервной системы.',
        47.80, 100, 43,
        '/images/BCAA Xplode 280 g.png');

#IronMaxx
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('BCAAs Glutamin 800',
        'БЦААс+Глутамин 1200 - аминокислоты с разветвлённой цепочкой и глютамин в свободной несвязанной форме!
        Для создания протеина и/или тканей нашему телу необходимы BCАА (аминокислоты с разветвлённой цепочкой) и L-глютамин. Эти аминокислоты являются незаменимыми, абсолютно необходимыми для человека органическими соединениями. В то время как другие незаменимые аминокислоты прежде всего служат для создания биологически-активных молекул, например, гормонов, BCAA являются в первую очередь строительным материалом для органов и мышц.',
        33.00, 100, 44,
        '/images/BCAAs Glutamin 800.png');

#Creatine
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Biotech 100 Creatine Monohydrate 500 bank',
        'BioTech 100% Creatine Monohydrate - увеличивает задержку воды в клетках мускул, тем самым обеспечивает все необходимые условия для синтеза белков после физических нагрузок.
        Из научных трудов ученых Лемона, Боска и Бредля в 1995 году нам стала известна способность моногидрата креатина к увеличению силы. С тех пор моногидрат креатина - самая популярная и часто используемая в мире спортивная пищевая добавка.
        Снижение цен на моногидрат креатина, а также огромный спрос на него привели к тому, что моногидрата креатина стало очень много в продаже, отличающейся к сожалению лишь качеством.
        Аттестация креатиновой продукции семейства BioTech проводилась в лабораториях Калифорнийского университета в Лос Анжелесе (University of California Los Angeles, UCLA), результаты которой показали, что этот препарат 100%-ой чистоты.
        Постоянный контроль  за препаратом гарантирует Вам надежное формирование мускулатуры! В производстве креатиновых препаратов семейства BioTech используется исключительно 100%-но чистый, молотый до микронных размеров моногидрат креатина.',
        31.90, 100, 51, '/images/Biotech 100 Creatine Monohydrate 500 bank.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Creatine pHX Biotech USA 90',
        'Креатин Creatine pHX - не требует никакого цикла загрузки, это идеальный продукт для увеличения работоспособности и силы. Употребляя 1.5 грамма Creatine pHX, ежедневно Вы можете многократно увеличить запас креатина в организме.
        После добавления воды или жидкости к обычному креатину, его существенная часть быстро превращается в креатинин. Только небольшой процент того, что потребляется, - все еще креатин, который может использовать организм. В течение тренировочного процесса молекулы креатина синтезируются с веществами резервного запаса, увеличивая pH фактор выше 12.
        В результате Креатин pHX приходит неповрежденным к кровотоку и достигает ваших мышечных клеток в полном объеме. Creatine pHX в десять раз эффективнее, чем любой другой моногидрат креатина из-за высокого pH фактора (pH12-14).
        Creatine pHX не проходит фактически никакого преобразования, что позволяет вашим мышечным клеткам усваивать почти 100 % рекомендованной дозы препарата.Употребление Creatine pHX с простыми углеводами повышает усвоение креатина. За счёт этого достигается эффект выброса инсулина.
        Креатин pHX обладает уникальной способностью не задерживать воду в организме. Creatine pHX предлагает креатин, который на 100% стабилен, который не разрушится и не превратится в креатинин, который эффективно абсорбируется в кровь. На самом деле, каждый грамм Creatine pHX эквивалентен 10 граммам усвоенного обыкновенного креатинового порошка, что позволяет атлетам принимать намного меньше продукта и получать более существенный результат. Creatine pHX сделает Вас намного сильнее, добавит выносливости и увеличит объем мышц.',
        20.50, 100, 51,
        '/images/Creatine pHX Biotech USA 90.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('CREATINE X3',
        'Сверхэффектвная креатиновая матрица Креатин X3 обеспечивает тройную силу для неограниченного увеличения прочности и производительности. Испытания Креатин Х3 показали увеличение максимальной мощности и силы до 15% и увеличение жима лежа до 20%. Креатин X3  работает быстро, сочетая количество активных ингредиентов и скоростной выброс их непосредственно в мышцу. Каждая порция содержит точное количество углеводов и повышено производительные соединения для максимального всасывания питательных веществ для приведения вашего тела в состояние постоянного мышечного роста. Креатин X3 поможет Вам активировать анаболические процессы и сделает возможным значительное увеличение размера и силы мускулов, который вы заметите, всего за 4 недели.',
        48.90, 100, 52,
        '/images/CREATINE X3.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Olimp Xplode powder 500 g',
        'В составе Creatine Xplode Powder используется смесь 6 самых популярных и самых эффективных форм креатина, используемых в настоящее время во всем мире, а именно: креатин малат, этиловый эфир, альфа-КГ, пируват, цитрат и креатин хелат магния (креатин Magna Power). Использование этих форм креатина обеспечивает высокие достижения в спорте.  Очень хорошо помогает в том числе обогащение продукта Creatine Xplode Powder таурином, который помогает эффективно тренироваться и способствует увеличению спортивных показателей, уменьшая при этом время восстановления после тренировки.',
        75.60, 100, 53,
        '/images/Olimp Xplode powder 500 g.png');

#IronMaxx
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Krea7 Superalkaline 180',
        'Новая формула креатина- технология для увеличения силы и быстрого роста мышц!
        Благодаря слиянию использованных инновационных форм креатина и специального буфера фаза загрузки уходит в прошлое. Буферизованный креатин Superalkaline Krea7 достигает полной концентрации в мышечных клетках и обеспечивает значительную и длительную мышечную нагрузку. Уже через несколько минут после приёма в распоряжение мускулатуры попадает значительный заряд креатина, который повышает интенсивность тренировки до максимального уровня. Таким образом, вы можете достичь чрезвычайной силы и огромную выносливость!',
        103.80, 100, 54,
        '/images/Krea7 Superalkaline 180.png');

#Vitamin
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Mens ArginMax Biotech USA',
        'Mens Arginmax - научно разработанный стимулирующий продукт c эксклюзивной формулой, которая обладает свойствами растений и ключевых аминокислот, которые увеличивают приток крови к гениталиям, половое влечение и сексуальную эффективность.
        В состав входит 3 грамма аминокислоты L-аргинина, один из ключевых компонентов в превосходной формуле Arginmax. L-аргинин является строительным материалом для окиси азота, который помогает поддерживать тонус кровеносных сосудов.
        Кроме того, эффективность препарата повышается благодаря добавленной в него эксклюзивной смеси растений афродизиаков. Ginkgo является одним из старейших растений в мире и поддерживает расширение периферического кровотока в конечностях.',
        30.70, 100, 61,
        '/images/Mens ArginMax Biotech USA.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Multivitamin for Women Biotech USA',
        'Не секрет, что мужской и женский организмы друг от друга отличаются. Поэтому, и витаминно-минеральные комплексы могут давать больший эффект, если  рассчитаны именно для женщин, или именно для мужчин.
        Женские витаминные комплексы имеют ряд отличий относительно других.
        Это обусловлено, как минимум, тем, что существуют различия между женским и мужским организмами.
        Основные из них:
        1. отличие гормональных систем;
        2. мышечная масса;
        3. физическая активность.
        Женскому организму особенно необходимы железо, фолиевая кислота, а также витамины и минералы для хорошего состояния кожи, волос и ногтей.
        Если Вам необходимо купить витамины для женщин, то советуем обратить внимание на Multivitamin for Women Biotech USA. Комплекс содержит оптимальное сочетание витаминов и минералов именно для женского организма.
        Кроме того, вещества в составе Multivitamin for Men Biotech USA скомбинированы так, чтобы постепенно пропорционально высвобождать питательные вещества.
        Это приводит к еще большей эффективности.',
        26.90, 100, 61, '/images/Multivitamin for Women Biotech USA.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Vitabolic Biotech USA',
        'Vitabolic представляет собой комплекс поливитаминов и минеральных формул, разработанных для атлетов. Витамины улучшают обмен веществ, регулируют процесс выделения энергии, стимулируют рост клеток, сдерживают накопление жира и снижение мышечной массы, укрепляют нервную и иммунную системы, улучшают состояние кожи, волос, ногтей.
        Vitabolic - высококачественный, чистый, сбалансированный и натуральный продукт без химических и других добавок. Содержит максимум витаминов, минералов и антиоксидантов, чтобы в полной мере обеспечить Вас питательными веществами.
        Каждая таблетка Vitabolic состоит из жирорастворимых и водорастворимых витаминов длительного действия и быстро высвобождаемых минералов и пищеварительных веществ.
        Данный препарат является обязательным для каждого спортсмена, желающего поддерживать хорошую физическую форму в течение продолжительного периода времени. Этот препарат рекомендуется принимать абсолютно всем.',
        14.90, 100, 61,
        '/images/Vitabolic Biotech USA.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('QNT Omega 3',
        'Омега 3 - группа ненасыщенных жирных кислот,при недостатке которых могут  возникнуть биохимические и физиологические нарушения.
        Омега-3 имеют широкий спектр действия, не только для спортсменов-бодибилдеров, но и для людей, не имеющих отношения к спорту.
        Применение Omega предупреждает раннее старение организма и помогает бороться с лишним весом. Улучшает работу головного мозга, памяти и сокращает риск ишемической болезни сердца. Помогает мышцам быстрее восстанавливаться после стрессовых нагрузок. Повышает иммунитет, снижает риск развития рака и ускоряет восстановление после воспалительных процессов. И это лишь краткий перечень их полезных свойств.',
        19.70, 100, 62,
        '/images/QNT Omega 3.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Olimp ZMA 120',
        'ZMA от Olimp способствует повышению уровня и активности тестостерона и увеличивает уровень IGF-I и лептина, который отвечает за более эффективное использования энергии, повышает силовые показатели и увеличивает выносливость организма.
        Препарат уменьшает катаболизм белка, поддерживает построение мышечной массы, предотвращает спазмы мышц, облегчает болевые ощущения в мышцах, вызванные физической нагрузкой.
        Olimp ZMA нейтрализует побочные эффекты перетренированности, способствует восстановлению организма и предотвращает возникновение окислительного стресса. Кроме того, улучшает концентрацию и способствует глубокой релаксации во время сна.
        Потребность в микроэлементах и в витамине В6, входящих в состав Olimp ZMA, с повышением физической активности возрастает. Olimp ZMA предназначены для спортсменов и для людей, ведущих активный образ жизни.',
        69.30, 100, 63,
        '/images/Olimp ZMA 120.png');

#FatBurn
#Biotech
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Super Fat Burner Biotech USA',
        'Super Fat Burner - формула, которая поможет вам понизить уровень жира и воды в организме.
        SuperFatBurner разработан, чтобы помочь оптимизации метаболизма жировых клеток, сжигания подкожного жира и повышения рельефности тела. Он содержит активные ключевые ингредиенты, такие как L-карнитин, потенцирующее средство инсулина, естественные энерджайзеры и эмульгирующие жир липотропики.
        Используйте SuperFatBurner во время ваших тренировок и в повседневной жизни и вы получите великолепные результаты!',
        39.90, 100, 71,
        '/images/Super Fat Burner Biotech USA.png');

#QNT
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Stack Force QNT',
        'Каждая быстродействующая доза укомплектована научно разработанными ингредиентами, чтобы помочь достичь максимальной прорисовки мышц с его мощными многофазными активаторами жиросжигания.
        Фаза 1 запускает усиление термогенеза. Это повышение температуры тела сожжет давно накопившиеся жировые массы.
        Фаза 2 стимулирует обмен веществ, увеличивая сжигание калорий с помощью ключевого жиросжигателя гормона норадреналина. По доказанным фактам эффективности данного препарата было установлено, улучшение сжигание жира до 35% больше в испытуемой группе, чем в группе плацебо, всего за 24 часа.
        И наконец, фаза 3 - использование адаптогенных и повышающих энергию питательных веществ, чтобы помочь телу адаптироваться к физической нагрузке и получить мощный заряд энергии, для увеличения интенсивности тренировок. ',
        54.20, 100, 72,
        '/images/Stack Force QNT.png');

INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Thermo Booster QNT',
        'Напиток (Thermo Booster) Термо Бустер идеален перед любой физической активностью. Он не содержит калорий и сахара! Этот напиток - активатор потери веса, придающий  мощную энергию и силы во время тренировок.',
        2.90, 100, 72,
        '/images/Thermo Booster QNT.png');

#Olimp
INSERT INTO products (name, description, price, qty, category_id, image_url)
VALUES ('Olimp Chitosan chromium',
        'Благодаря свойству хитозана связываться с молекулами жира, можно ограничить усвоение жиров, потребляемых с пищей. Хром же действует как регулятор уровня сахара в крови, а также помогает избавить от чувства чрезмерного аппетита.
        Olimp Chitosan + Chromium:
        Хитозан + хром;
        Помогает контролировать вес;
        Не дает жиру усваиваться;
        Нормализует уровень глюкозы в крови.
        Хитозан и хром для помощи в контроле веса.
        Основным компонентом Chitosan + Chromium от Olimp является хитозан. Его получают из хитиновой оболочки ракообразных. Особенность данного соединения состоит в том, что оно связывает жиры и выводит их из организма. Другими словами, хитозан не дает излишкам жира накапливаться в вашем теле.
        Хром усиливает эффект от приема хитозана. Данный микроэлемент ценен тем, что помогает организму регулировать уровень сахара в крови, поддерживая тем самым правильное усвоение глюкозы и уменьшая приступы голода.',
        12.20, 100, 73,
        '/images/Olimp Chitosan chromium.png');

#DROP DATABASE nutrition_store;