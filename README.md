# Household Grocery Spending Predictor
*Yash Shah*

*Master of Applied Computing - Wilfrid Laurier University*

*CP630: Enterprise Computing*

## Running this Project:
### Start Spark master node using following command:
    spark-class.cmd org.apache.spark.deploy.master.Master

### Start Spark worker node using following command:
    spark-class.cmd org.apache.spark.deploy.worker.Worker spark://10.211.55.3:7077

### Uri to start the application:
    http://127.0.0.1/household

# Architecture

<pre>
index.html              /rest/prediction                   household EJB           titanic.py
+-----------------+        +---------------------+       +---------------+      +---------------+
|                 |        |                     |       |               |      |               |
|                 +-------->                     +------->               +------>               |
|   Web UI        |        |      RESTful        |       |  Stateless    |      |  PySpark ML   |
| JSP Bootstrap   <--------+  Web application    <-------+    Bean       <------+               |
|  SPRING MVC     |        |                     |       |               |      |               |
|                 |        |                     |       |               |      |               |
+-----------------+        +---------------------+       +---------------+      +---------------+

    Inputs: Number of Adults at home, Family Income, How Many Vehicles, Distance To Store,
            Vegetarian ?, Number of Children
+------------------------------------------------------------------------------------------------------>
<------------------------------------------------------------------------------------------------------+
                                                                        Output: Your Grocery Prediction
</pre>

# Main Components
## Logistic Regression Algorithm : Predict.py
*  Written in Python, and executed on Spark using  the PySpark connector, This file uses 1000 rows of data from the household Dataset.
It trains and predicts a Linear Regression Model to determine the average grocery bill for a houshold in Toronto, ON Canada.


## EJB Stateless Bean : titanicEJB
* This EJB stateless bean receives the query string parameters from the web service
    and creates a CSV file in the '/pythonprojects/new.csv'. It then executes a command to submit the predict.py or load.py job to Spark using spark-submit.

## REST Web Service : titanicWS
* This RESTful web service recieves requests from the Spring MVC based Web application and calls the EJB stateless bean. 
Alternatively, A Spring MVC based web application can be used to directly save values to the CSV and submit jobs to Spark without
the need for EJBs and any RESTful Api at all.


## EAR Deployment : titanicEAR
* Description: This enterprise application project packages the java projects
  together in a single package to be deployed to the Wildfly 10.x server.

