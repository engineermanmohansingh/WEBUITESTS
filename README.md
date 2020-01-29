#  Automating Top of the Pyramid
The project uses *Selenium* and *TestNG*  for test automation of web scenarios. The combination of *TestNG* and *Rest-assured* is used for test automation of web scenarios. Page object model is used for organizing test classes and page functions.

There are 3 test classes for web test cases and 1 test class for API test cases. The testsuite file consumed by the framework : **testng.xml** must be located at project root location.

The project can be built/executed on a locally hosted CI server like Jenkins/CircleCI/BuildKite. The build automation is controlled via *Maven* . To run the test suite from CLI, use this command from project root location. 
`mvn test`

To run a specific test class of this project, use this command.
`mvn test -Dtest=<testClassName>`

By default, all web tests are triggered on chrome browser. To switch to other browser, change the browser name in the **Config.properties** file. The browser support for various operating system is listed below. 


|     Browsers      	|    Windows     |   Unix  	|   Mac   |
|:-----------------:|:--------------:|:--------:|:-------:|		
| Google Chrome    	| 	✅		 	 |   ✅ 	|	✅	  |
| Mozilla Firefox   | 	✅      	 |   ✅     |	✅	  |	
| MS Edge     		| 	✅      	 |    -     |	 -	  |
| Internet Explorer | 	✅       	 |    -     |	 -	  |


The project test report is generated in two formats : a testNG report and an Extent report. 

For more queries, please reach out to the [author](https://stackoverflow.com/story/engineermanmohansingh) at Stackoverflow.