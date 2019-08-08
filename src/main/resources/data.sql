set schema 'api';

INSERT INTO produto (id, nome, valor) VALUES
(1, 'Memória HyperX Fury, 8GB, 2400MHz, DDR4, CL15, Preto - HX424C15FB2/8', 270.90),
(2, 'Processador Intel Core i7-9700K', 2454.00),
(3, 'Headset Gamer HyperX Cloud Stinger - HX-HSCS-BK/NA ', 189.37),
(4, 'Teclado Mecânico Gamer HyperX Mars, RGB, Switch Outemu Bluem, US - HX-KB3BL3-US/R4 ', 284.11),
(5, 'Mouse Logitech M90 Preto 1000DPI ', 26.90),
(6, 'Gabinete C3Tech Gamer ATX sem Fonte Preto MT-G50BK', 119.60),
(7, 'Headphone Edifier Bluetooth W800BT Preto', 250),
(8, 'Kindle Novo Paperwhite, 8GB, Wi-Fi, Preto - AO0705 ', 418.99),
(9, 'SSD Kingston A400, 240GB, SATA, Leitura 500MB/s, Gravação 350MB/s - SA400S37/240G ', 166),
(10, 'HD Seagate BarraCuda, 1TB, 3.5´, SATA - ST1000DM010', 290),
(11, 'Cadeira Gamer DT3sports GT, Black - 10293-5', 552.41),
(12, 'Kaspersky Antivírus 2019 1 PC - Digital para Download', 39.90),
(13, 'Console Sony PlayStation Hits Bundle 5.1 1TB - Days Gone + Detroit Become Human + Call of Duty Black Ops 4 - CUH-2214B', 2209.91),
(14, 'Câmera de Segurança Xiaomi Wi-Fi 360° 1080P Branca - XM314BRA', 369.89),
(15, 'Patinete Elétrico Xiaomi M365, até 25km/h, Rodas 8.5´, Suporta 100kg ,Branco - PX251BRA', 3989.90);

SELECT setval('produto_seq', 15);