RESTfulWS
=========

REST Web Service to fetch and insert into a MySQL Database

It fetches current weather for a particular city after every interval of 3 seconds and stores the details in a database.

The table looks like as below:-

+-------------+--------------+------+-----+---------+----------------+

| Field       | Type         | Null | Key | Default | Extra          |

+-------------+--------------+------+-----+---------+----------------+

| rowNo       | int(11)      | NO   | PRI | NULL    | auto_increment |

| city        | varchar(40)  | YES  |     | NULL    |                |

| state       | varchar(40)  | YES  |     | NULL    |                |

| time        | datetime     | YES  |     | NULL    |                |

| description | varchar(100) | YES  |     | NULL    |                |

+-------------+--------------+------+-----+---------+----------------+
