<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Household Income Predictor</title>
	<style>
	/* Space out content a bit */
body {
  padding-top: 1.5rem;
  padding-bottom: 1.5rem;
}

/* Everything but the jumbotron gets side spacing for mobile first views */
.header,
.marketing,
.footer {
  padding-right: 1rem;
  padding-left: 1rem;
}

/* Custom page header */
.header {
  padding-bottom: 1rem;
  border-bottom: .05rem solid #e5e5e5;
}
/* Make the masthead heading the same height as the navigation */
.header h3 {
  margin-top: 0;
  margin-bottom: 0;
  line-height: 3rem;
}

/* Custom page footer */
.footer {
  padding-top: 1.5rem;
  color: #777;
  border-top: .05rem solid #e5e5e5;
}

/* Customize container */
@media (min-width: 48em) {
  .container {
    max-width: 46rem;
  }
}
.container-narrow > hr {
  margin: 2rem 0;
}

/* Main marketing message and sign up button */
.jumbotron {
  text-align: center;
  border-bottom: .05rem solid #e5e5e5;
}
.jumbotron .btn {
  padding: .75rem 1.5rem;
  font-size: 1.5rem;
}

/* Supporting marketing content */
.marketing {
  margin: 3rem 0;
}
.marketing p + h4 {
  margin-top: 1.5rem;
}

/* Responsive: Portrait tablets and up */
@media screen and (min-width: 48em) {
  /* Remove the padding we set earlier */
  .header,
  .marketing,
  .footer {
    padding-right: 0;
    padding-left: 0;
  }
  /* Space out the masthead */
  .header {
    margin-bottom: 2rem;
  }
  /* Remove the bottom border on the jumbotron for visual effect */
  .jumbotron {
    border-bottom: 0;
  }
}
	</style>
  </head>
  <body>
  <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills float-right">
            <li class="nav-item">
              <a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${contextPath}/load">Load and Save</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${contextPath}/predict">Get Prediction</a>
            </li>
          </ul>
        </nav>
        <h4 class="text-muted">Household Spending Predictor</h4>
      </div>

      <div class="jumbotron">
        <h1 class="display-4">COVID19 : Predict your Household Income</h1>
        <p class="lead">Amidst the Novel Coronavirus (COVID19) pandemic outbreak, average Canadians have been buying loads of groceries especially toilet paper. Popular opinion is that people are unnecessarily stocking household usable times more than they would actually need, while some believe that it might be the right thing to do. Although, we cannot exactly predict how many people would actually be affected by COVID19, we can predict a high accuracy the amount of household items a family would need based on its demographics and produce the pretty accurate amount of food, the family would actually need so it becomes easier for us to know the percentage of overspending a family would be doing during the outbreak.</p>
        <p><a class="btn btn-lg btn-success" href="${contextPath}/predict" role="button">Try Now!</a></p>
      </div>

      <div class="row marketing">
        <div class="col-lg-6">
          <h4>Spring Framework</h4>
          <p>Spring is a powerful, lightweight framework used for application development. In broader terms, you can say that the Spring framework is a well-defined tool that supports several web applications using Java as a programming language.</p>

          <h4>Apache Spark</h4>
          <p>Apache Spark is an open source, general-purpose distributed computing engine used for processing and analyzing a large amount of data. Just like Hadoop MapReduce, it also works with the system to distribute data across the cluster and process the data in parallel.</p>

          <h4>Java EJB</h4>
          <p>Enterprise JavaBeans (EJB) is one of several Java APIs for modular construction of enterprise software. EJB is a server-side software component that encapsulates business logic of an application.</p>
        
        <h4>Log4J</h4>
		<p>Log4j helps to log files to a file or console or even to a database.</p> 
        </div>

        <div class="col-lg-6">
          <h4>RESTful Web Service</h4>
          <p>Java API for RESTful Web Services (JAX-RS), is a set if APIs to developer REST service. JAX-RS is part of the Java EE6, and make developers to develop REST web application easily.</p>

          <h4>JSP-EL</h4>
          <p>JSP Expression Language (EL) makes it possible to easily access application data stored in JavaBeans components. JSP EL allows you to create expressions both (a) arithmetic and (b) logical. Within a JSP EL expression, you can use integers, floating point numbers, strings, the built-in constants true and false for boolean values, and null</p>

          <h4>PySpark</h4>
          <p>PySpark is the Python API written in python to support Apache Spark. Apache Spark is a distributed framework that can handle Big Data analysis. ... Spark is basically a computational engine, that works with huge sets of data by processing them in parallel and batch systems.</p>
        
		    
       <h4>Docxygen</h4>
       <p>Doxygen is the de facto standard tool for generating documentation from annotated C++ sources, but it also supports other popular programming languages such as C, Objective-C, C#, PHP, Java, Python, IDL (Corba, Microsoft, and UNO/OpenOffice flavors), Fortran, VHDL and to some extent D</p>
       
        
        </div>
      </div>

      <footer class="footer">
        <p>&copy; 2020 Yash Shah WLU CP630 : The time on the server is ${serverTime}.</p>
      </footer>

    </div> <!-- /container -->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>