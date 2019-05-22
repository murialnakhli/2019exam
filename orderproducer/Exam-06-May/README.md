# Exam - 2

## Rules:

### Conduct

* You are not allowed to use any sort of chatting tool and any other electronic device than the University computer, failing to follow this rule fails the student from the course and may be subject of sanctions in the form given by University rules.
  * Acts that put the student behavior under suspicious will cancel the exam and the student will be subject to a new exam under fully-controlled environment.

* The only allowed websites are Google (for searching *DOCUMENTATION*), the official Oracle documentation and Github (including previous classes).

* Equal or similar exams cancel both exams.

* You are not allowed to leave the exam room without the examiner's authorization.

* In case of misconduct, the examiner will not, at any time, take arguments in consideration during the exam. The student exam is cancelled, the student is notified and, in case the student does not agree, the decision must be appealed after the exam.


### Exam Rules
* You can use methods that you find convenient/better (e.g. different Serialization) as far as you fulfill the requirements (e.g. send a full object, create an RMI server etc)

* In case of retake, the greatest grade is the valid one

* The exam must be finished 10 minutes prior the lab reservation is over (i. e. 15:50)

* With the exam grades there will be the time and place to re-check the exam with the examiner.

* When finishing the exam, you must send this assignment to ferrari@caesar.elte.hu with copy to svu938@inf.elte.hu. **for performing it, you shall call the examiner**.
  * The usage of e-mail tools without the examiner supervision is forbidden.

* The examiner will not give any support that is not regarding the exam flow (understanding of this description, computer setup etc). Students are expected to well know errors like *Connection reset* and *Serialization error* to pass this course.


## RMI Bakery System

You are the new developer of the Raspberry Muffin with Icing(RMI). Your work is to develop a system that will connect your clients and the bakers.

Since the Bakery is part of a big chain, you cannot simply offer your products to the clients, but, you need to develop a system that can produce an **Order** through RMI call (being order an object).

You shall develop a Client that connects to the Server giving the option of Muffin toppings (e.g. chocolate, vanilla), the server replies with the object. The object is opened and the information is shown to the user and sent to the local shop (which will be localhost port 12343)


How it works step-by-step:
* The **Client** connects to a server that provides Remote Access to **OrderProducer** object;
* The **Client** using an RMI Call asks for the list of toppings and print it in the user's screen;
* The **Client** requests an **Order** object to the remote object **OrderProducer** and prints it;
* The **Client** then send it to the other **Server** that will process it;
* The **Server** marks the Order as received and send it back to the **Client** that will print it out.


## 2 Points

* Adapt the class **Order** so that its instances can be sent over the network
* Create a **Client** class that connects to one **RMIServer**;
* Create a **RMIServer** class registering a service called *GenerateOrders* putting available the methods of **OrderProducer**;
* Adapt the **OrderProducer** object so that its methods can be called using remote method invocation;
* Create an Interface implemented by OrderProducer for the RMI methods that will be used by the **Client**;
* Using the *GenerateOrders* service, request a new Order from the OrderProducer method of a muffin with "*Strawberry*" topping;

## 3 Points
* Make the RMI methods multithread, in case you think it is not necessary, justify your answer on a comment in the first lines of the **RMIServer** code (3 lines max);

## 4 Points
* Send the **Order** object using **ObjectInputStream** and **ObjectOutputStream** from the **Client** to a **Server**
* Call the **order.receiveOrder()** method in the Server
* Send the **Order** back to the **Client** and print it in your Client.

## 5 Points
* If your Server was multithreaded, where and how would you place a lock? Answer this question explaining why in a comment in **Server.java** (max 5 lines).
