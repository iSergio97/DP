QUERY C.1

 - Description:
The average, the minimum, the maximum, and the standard deviation of the number of fix-up tasks per user.

 - Query:
select min(c.fixUpTasks.size),max(c.fixUpTasks.size),avg(c.fixUpTasks.size),stddev(c.fixUpTasks.size) from Customer c;

 - Explanation:
The min function returns the minimum value of a parameter for all the rows in a table, max returns the maximum, avg returns the average and stddev returns the standard deviation.
By performing the four functions separated by commas, we obtain an array containing the results of each one.
We perform the functions on the size parameter of the list of fix-up tasks of each customer to obtain the result of those functions applied to the number of fix-up tasks per customer.

 - Result:
[0, 2, 0.8333, 0.6872]



QUERY C.2

 - Description:
The average, the minimum, the maximum, and the standard deviation of the number of applications per fix-up task.

 - Query:
select min(f.applications.size),max(f.applications.size),avg(f.applications.size),stddev(f.applications.size) from FixUpTask f;

 - Explanation:
The min function returns the minimum value of a parameter for all the rows in a table, max returns the maximum, avg returns the average and stddev returns the standard deviation.
By performing the four functions separated by commas, we obtain an array containing the results of each one.
We perform the functions on the size parameter of the list of applications of each fix-up task to obtain the result of those functions applied to the number of applications per fix-up task.

 - Result:
[0, 3, 2.0, 1.0954]



QUERY C.3

 - Description:
The average, the minimum, the maximum, and the standard deviation of the maximum price of the fix-up tasks.

 - Query:
select avg(f.maximumPrice), min(f.maximumPrice), max(f.maximumPrice), stddev(f.maximumPrice) from FixUpTask f;

 - Explanation:
The min function returns the minimum value of a parameter for all the rows in a table, max returns the maximum, avg returns the average and stddev returns the standard deviation.
By performing the four functions separated by commas, we obtain an array containing the results of each one.
We perform the functions on the maximum price parameter of each fix-up task to obtain the result of those functions applied to the maximum price per fix-up task.

 - Result:
[880.0, 100, 2000, 738.6474]



QUERY C.4

 - Description:
The average, the minimum, the maximum, and the standard deviation of the price offered in the applications.

 - Query:
select avg(a.offeredPrice), min(a.offeredPrice), max(a.offeredPrice), stddev(a.offeredPrice) from Application a;

 - Explanation:
The min function returns the minimum value of a parameter for all the rows in a table, max returns the maximum, avg returns the average and stddev returns the standard deviation.
By performing the four functions separated by commas, we obtain an array containing the results of each one.
We perform the functions on the offered price parameter of each application to obtain the result of those functions applied to the offered price per application.

 - Result:
[97.0, 30, 450, 120.0042]



QUERY C.5

 - Description:
The ratio of pending applications.

 - Query:
select (count(a)/(select count(b) from Application b))*1.0 from Application a where a.status='PENDING';

 - Explanation:
Performs a query to count all the applications and multiplies it by 1.0 to get the count as a floating point number so as to get an integer from the count function.
It counts all the applications meeting the condition of having their status parameter equal pending and divides it by the count to obtain the ratio as a floating point number.

 - Result:
0.4



QUERY C.6

 - Description:
The ratio of accepted applications.

 - Query:
select (count(a)/(select count(b) from Application b))*1.0 from Application a where a.status='ACCEPTED';

 - Explanation:
Performs a query to count all the applications and multiplies it by 1.0 to get the count as a floating point number so as to get an integer from the count function.
It counts all the applications meeting the condition of having their status parameter equal accepted and divides it by the count to obtain the ratio as a floating point number.

 - Result:
0.2



QUERY C.7

 - Description:
The ratio of rejected applications.

 - Query:
select count(a)/(select count(b) from Application b)*1.0 from Application a where a.status='REJECTED';

 - Explanation:
Performs a query to count all the applications and multiplies it by 1.0 to get the count as a floating point number so as to get an integer from the count function.
It counts all the applications meeting the condition of having their status parameter equal rejected and divides it by the count to obtain the ratio as a floating point number.

 - Result:
0.4



QUERY C.8

 - Description:
The ratio of pending applications that cannot change its status because their time period's elapsed.

 - Query:
select count(a)/(select count(b) from Application b)*1.0 from Application a where a.fixUpTask.timeLimit < CURRENT_DATE and a.status = 'PENDING');

 - Explanation:
Performs a query to count all the applications and multiplies it by 1.0 to get the count as a floating point number as the count function returns an integer.
It counts all the applications meeting the condition of having their status parameter equal pending and their time limit parameter being less than the current date and divides it by the count to obtain the ratio as a floating point number.

 - Result:
0.2



QUERY C.9

 - Description:
The listing of customers who have published at least 10% more fix-up tasks than the average, ordered by number of applications.

 - Query:
select f.customer from FixUpTask f where f.customer.fixUpTasks.size >= (select avg(c.fixUpTasks.size) from Customer c) * 1.1 group by f.customer order by sum(f.applications.size);

 - Explanation:
The query groups up fix-up tasks by the customer related to them so we can perform the sum of the number of applications of the fix-up tasks of each customer, which is necessary in order to sort the customers by that criterion.
The statement uses a query to obtain the average of number of fix-up tasks of each customer and multiplies it by 1.1 so as to obtain the average plus 10%.
Then, it selects the customers that have a number of fix-up tasks equal or bigger than the average plus 10% and orders it by the sum of applications of the fix-up tasks of those customers.

 - Result:
domain.Customer{id=2384, version=0}
	domain.DomainEntity::id: int = 2384
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Carlos"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Ruiz"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/bc9a07f806b4b8222308e38e48c6ff04"
	domain.Actor::email: java.lang.String = "carlosrb96@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 687245678"
	domain.Actor::address: java.lang.String = "Avenida Reina Mercedes s/n"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2373, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2432, version=0}, domain.MessageBox{id=2433, version=0}, domain.MessageBox{id=2434, version=0}, domain.MessageBox{id=2435, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2470, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2501, version=0}]
domain.Customer{id=2387, version=0}
	domain.DomainEntity::id: int = 2387
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Geromin"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Sanchez"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/bc9a07f806b4b8222308e38e48c6ff04"
	domain.Actor::email: java.lang.String = "gerominSanchez@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 688885678"
	domain.Actor::address: java.lang.String = "Calle Ave del Paraíso 7"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2376, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2444, version=0}, domain.MessageBox{id=2445, version=0}, domain.MessageBox{id=2446, version=0}, domain.MessageBox{id=2447, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2473, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2503, version=0}]
domain.Customer{id=2383, version=0}
	domain.DomainEntity::id: int = 2383
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Jose"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Valverde"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/41ff5f200e72b6f168037f62138ebb43"
	domain.Actor::email: java.lang.String = "jositovalverde@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 787291678"
	domain.Actor::address: java.lang.String = "Calle Bonita 12"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2372, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2428, version=0}, domain.MessageBox{id=2429, version=0}, domain.MessageBox{id=2430, version=0}, domain.MessageBox{id=2431, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2469, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2500, version=0}]
domain.Customer{id=2382, version=0}
	domain.DomainEntity::id: int = 2382
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Carlos"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Ruiz"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/bc9a07f806b4b8222308e38e48c6ff04"
	domain.Actor::email: java.lang.String = "carlosrb96@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 687245678"
	domain.Actor::address: java.lang.String = "Avenida Reina Mercedes s/n"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2371, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2424, version=0}, domain.MessageBox{id=2425, version=0}, domain.MessageBox{id=2426, version=0}, domain.MessageBox{id=2427, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2468, version=0}]
	domain.Actor::messagesSent: java.util.Collection = [domain.Message{id=2391, version=0}]
	domain.Actor::messagesReceived: java.util.Collection = [domain.Message{id=2392, version=0}]
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2499, version=0}, domain.FixUpTask{id=2502, version=0}]



QUERY C.10

 - Description:
The listing of handy workers who have got accepted at least 10% more applications than the average, ordered by number of applications.

 - Query:
select h1 from HandyWorker h1 where h1.applications.size >= (select avg(h2.applications.size) from HandyWorker h2) * 1.1 order by h1.applications.size;

 - Explanation:
This statement uses a query to obtain the average of number of applications of each handy worker and multiplies it by 1.1 in order to obtain the average plus 10%.
Then, it selects the handy workers that have a number of applications equal or bigger than the average plus 10% and orders it by the number of applications.

 - Result:
domain.HandyWorker{id=2390, version=0}
	domain.DomainEntity::id: int = 2390
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Dolores"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Umbridge"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/b4434cfcf2cec2ffd1aab7e47ffce846"
	domain.Actor::email: java.lang.String = "lolitalamaga@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 657777725"
	domain.Actor::address: java.lang.String = "Paseo del Gnomo 13"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2379, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2456, version=0}, domain.MessageBox{id=2457, version=0}, domain.MessageBox{id=2458, version=0}, domain.MessageBox{id=2459, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2476, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.HandyWorker::make: java.lang.String = "Dolores Fuertes de Cabeza Umbridge"
	domain.HandyWorker::applications: java.util.Collection = [domain.Application{id=2404, version=1}, domain.Application{id=2406, version=1}, domain.Application{id=2407, version=1}, domain.Application{id=2408, version=1}]
	domain.HandyWorker::finder: domain.Finder = domain.Finder{id=2498, version=0}
	domain.HandyWorker::curriculum: domain.Curriculum = domain.Curriculum{id=2490, version=0}
	domain.HandyWorker::tutorials: java.util.Collection = []



QUERY B.1

 - Description:
The minimum, the maximum, the average, and the standard deviation of the number of complaints per fix-up task.

 - Query:
select min(f.complaints.size), max(f.complaints.size), avg(f.complaints.size), stddev(f.complaints.size) from FixUpTask f;

 - Explanation:
The min function returns the minimum value of a parameter for all the rows in a table, max returns the maximum, avg returns the average and stddev returns the standard deviation.
By performing the four functions separated by commas, we obtain an array containing the results of each one.
We perform the functions on the size parameter of the list of complaints of each fix-up task to obtain the result of those functions applied to the number of complaints per fix-up task.

 - Result:
[0, 2, 0.4, 0.8]



QUERY B.2

 - Description:
The minimum, the maximum, the average, and the standard deviation of the number of notes per referee report.

 - Query:
select min(r.notes.size), max(r.notes.size), avg(r.notes.size), stddev(r.notes.size) from Report r;

 - Description:
The min function returns the minimum value of a parameter for all the rows in a table, max returns the maximum, avg returns the average and stddev returns the standard deviation.
By performing the four functions separated by commas, we obtain an array containing the results of each one.
We perform the functions on the size parameter of the list of notes of each report to obtain the result of those functions applied to the number of notes per report.

 - Result:
[1, 1, 1.0, 0.0]



QUERY B.3

 - Description:
The ratio of fix-up tasks with a complaint.

 - Description:
select count(f1)/(select count(f2) from FixUpTask f2)*1.0 from FixUpTask f1 where f1.complaints.size > 0;

 - Explanation:
Performs a query to count all the fix-up tasks and multiplies it by 1.0 to get the count as a floating point number so as to get an integer from the count function.
It counts all the applications meeting the condition of having their status parameter equal pending and divides it by the count to obtain the ratio as a floating point number.

 - Result:
0.2



QUERY B.4

 - Description:
The top 3 customers in terms of complaints.

 - Query:
select f.customer from FixUpTask f group by f.customer order by sum(f.complaints.size);

 - Explanation:
The query groups up fix-up tasks by the customer related to them so we can perform the sum of the number of complaints of the fix-up tasks of each customer, which is necessary in order to sort the customers by that criterion.
It performs the sum of all the fix-up tasks of each customer and returns the customers of each group of fix-up tasks, ordered by their sum.

 - Result:
domain.Customer{id=2383, version=0}
	domain.DomainEntity::id: int = 2383
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Jose"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Valverde"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/41ff5f200e72b6f168037f62138ebb43"
	domain.Actor::email: java.lang.String = "jositovalverde@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 787291678"
	domain.Actor::address: java.lang.String = "Calle Bonita 12"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2372, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2428, version=0}, domain.MessageBox{id=2429, version=0}, domain.MessageBox{id=2430, version=0}, domain.MessageBox{id=2431, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2469, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2500, version=0}]
domain.Customer{id=2384, version=0}
	domain.DomainEntity::id: int = 2384
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Carlos"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Ruiz"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/bc9a07f806b4b8222308e38e48c6ff04"
	domain.Actor::email: java.lang.String = "carlosrb96@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 687245678"
	domain.Actor::address: java.lang.String = "Avenida Reina Mercedes s/n"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2373, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2432, version=0}, domain.MessageBox{id=2433, version=0}, domain.MessageBox{id=2434, version=0}, domain.MessageBox{id=2435, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2470, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2501, version=0}]
domain.Customer{id=2387, version=0}
	domain.DomainEntity::id: int = 2387
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Geromin"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Sanchez"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/bc9a07f806b4b8222308e38e48c6ff04"
	domain.Actor::email: java.lang.String = "gerominSanchez@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 688885678"
	domain.Actor::address: java.lang.String = "Calle Ave del Paraíso 7"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2376, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2444, version=0}, domain.MessageBox{id=2445, version=0}, domain.MessageBox{id=2446, version=0}, domain.MessageBox{id=2447, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2473, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2503, version=0}]
domain.Customer{id=2382, version=0}
	domain.DomainEntity::id: int = 2382
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Carlos"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Ruiz"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/bc9a07f806b4b8222308e38e48c6ff04"
	domain.Actor::email: java.lang.String = "carlosrb96@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 687245678"
	domain.Actor::address: java.lang.String = "Avenida Reina Mercedes s/n"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2371, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2424, version=0}, domain.MessageBox{id=2425, version=0}, domain.MessageBox{id=2426, version=0}, domain.MessageBox{id=2427, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2468, version=0}]
	domain.Actor::messagesSent: java.util.Collection = [domain.Message{id=2391, version=0}]
	domain.Actor::messagesReceived: java.util.Collection = [domain.Message{id=2392, version=0}]
	domain.Actor::notes: java.util.Collection = []
	domain.Customer::fixUpTasks: java.util.Collection = [domain.FixUpTask{id=2499, version=0}, domain.FixUpTask{id=2502, version=0}]



QUERY B.5

 - Description:
The top-three handy workers in terms of complaints.

 - Query:
select a.handyWorker from Application a group by a.handyWorker order by sum(a.fixUpTask.complaints.size);

 - Explanation:
The query groups up applications by the handy worker related to them so we can perform the sum of the number of complaints of the fix-up tasks related to the applications of each handy worker, which is necessary in order to sort the handy workers by that criterion.
It performs the sum of all the applications of each handy worker and returns the handy workers of each group of applications, ordered by their sum.

 - Result:
domain.HandyWorker{id=2389, version=0}
	domain.DomainEntity::id: int = 2389
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Benito"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Camela"
	domain.Actor::photo: java.lang.String = "https://imgur.com/a/GJ0wQ1f"
	domain.Actor::email: java.lang.String = "benitocamela@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 654789125"
	domain.Actor::address: java.lang.String = "Calle Sobrealta 19"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2378, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2452, version=0}, domain.MessageBox{id=2453, version=0}, domain.MessageBox{id=2454, version=0}, domain.MessageBox{id=2455, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2475, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.HandyWorker::make: java.lang.String = "Benito Camela"
	domain.HandyWorker::applications: java.util.Collection = [domain.Application{id=2402, version=1}, domain.Application{id=2403, version=1}, domain.Application{id=2409, version=1}]
	domain.HandyWorker::finder: domain.Finder = domain.Finder{id=2497, version=0}
	domain.HandyWorker::curriculum: domain.Curriculum = domain.Curriculum{id=2484, version=0}
	domain.HandyWorker::tutorials: java.util.Collection = []
domain.HandyWorker{id=2388, version=0}
	domain.DomainEntity::id: int = 2388
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Sara"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Fernandez"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/673df2f21880d6c7e568ec54cb8dc53b"
	domain.Actor::email: java.lang.String = "sarafdez@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 630681378"
	domain.Actor::address: java.lang.String = "Calle Alta 8"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2377, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2448, version=0}, domain.MessageBox{id=2449, version=0}, domain.MessageBox{id=2450, version=0}, domain.MessageBox{id=2451, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2474, version=0}]
	domain.Actor::messagesSent: java.util.Collection = [domain.Message{id=2392, version=0}]
	domain.Actor::messagesReceived: java.util.Collection = [domain.Message{id=2391, version=0}]
	domain.Actor::notes: java.util.Collection = [domain.Note{id=2506, version=0}]
	domain.HandyWorker::make: java.lang.String = "Sara Fernandez"
	domain.HandyWorker::applications: java.util.Collection = [domain.Application{id=2400, version=1}, domain.Application{id=2401, version=1}, domain.Application{id=2405, version=1}]
	domain.HandyWorker::finder: domain.Finder = domain.Finder{id=2496, version=0}
	domain.HandyWorker::curriculum: domain.Curriculum = domain.Curriculum{id=2479, version=0}
	domain.HandyWorker::tutorials: java.util.Collection = [domain.Tutorial{id=2422, version=1}]
domain.HandyWorker{id=2390, version=0}
	domain.DomainEntity::id: int = 2390
	domain.DomainEntity::version: int = 0
	domain.Actor::name: java.lang.String = "Dolores"
	domain.Actor::middleName: java.lang.String = ""
	domain.Actor::surname: java.lang.String = "Umbridge"
	domain.Actor::photo: java.lang.String = "https://gyazo.com/b4434cfcf2cec2ffd1aab7e47ffce846"
	domain.Actor::email: java.lang.String = "lolitalamaga@gmail.com"
	domain.Actor::phoneNumber: java.lang.String = "+34 657777725"
	domain.Actor::address: java.lang.String = "Paseo del Gnomo 13"
	domain.Actor::userAccount: security.UserAccount = security.UserAccount{id=2379, version=0}
	domain.Actor::messageBoxes: java.util.Collection = [domain.MessageBox{id=2456, version=0}, domain.MessageBox{id=2457, version=0}, domain.MessageBox{id=2458, version=0}, domain.MessageBox{id=2459, version=0}]
	domain.Actor::socialProfiles: java.util.Collection = [domain.SocialProfile{id=2476, version=0}]
	domain.Actor::messagesSent: java.util.Collection = []
	domain.Actor::messagesReceived: java.util.Collection = []
	domain.Actor::notes: java.util.Collection = []
	domain.HandyWorker::make: java.lang.String = "Dolores Fuertes de Cabeza Umbridge"
	domain.HandyWorker::applications: java.util.Collection = [domain.Application{id=2404, version=1}, domain.Application{id=2406, version=1}, domain.Application{id=2407, version=1}, domain.Application{id=2408, version=1}]
	domain.HandyWorker::finder: domain.Finder = domain.Finder{id=2498, version=0}
	domain.HandyWorker::curriculum: domain.Curriculum = domain.Curriculum{id=2490, version=0}
	domain.HandyWorker::tutorials: java.util.Collection = []



