# Pico & Placa Predictor
This project allows predicting whether a car can be on the road based on the "Pico y Placa" policy that restricts cars to be on the road depending on the last number of the plate. 
The policy is active during peak hours on weekdays based on a schedule.

The current configuration restricts vehicles based on the following schedule:

Restriction | Schedule
-----------|---------
Morning | 07:00-9:30
Afternoon-evening | 16:00-19:30



And the following last numbers of the plates based on the day:

Day  | Restricted Last Numbers
---- | -------------
Monday | 1,2
Tuesday | 3,4
Wednesday | 5,6
Thursday | 7,8
Friday | 9,0

## How to run

In order to run the project, you need the JDK 8 and Maven installed on your computer.
If you have the minimum requirements then you need to download or clone the repository.
Open a command prompt and then navigate to the project folder. 
Next, execute the following command to compile the code and run the web application:

```cmd
mvn package
```

This command will invoke maven to test and build the project.

Once the project has been successfully build, you need to execute the following command to lunch the project on a local web server:

```cmd
mvn spring-boot:run
```

Then you need to navigate on a web browser to **localhost:8080**


## Configuration

The configuration of the restricted times, days and plates is based on an XML configuration file named "PicoPlacaConfig.xml".

If you want to modify the restricted hours you should modify the content of the "restrictedTime" nodes.
Also, you can add more restricted times. Currently there are just two "restrictedTime" nodes but you can add more nodes to indicate a new start-end restriction time using the following standard:
HH:mm-HH:mm 

You should not overlap the times in different restricted time ranges.

Moreover, you can modify the banned last numbers of the plates per day. For this, you need to change the values in the "numbers" nodes of each day. You can also add new days with other restricted plate numbers.

To add a new restricted day and plate numbers you need to add a node "restriction" with a property "dayNumber" which indicates the number of the day to apply the restriction. Each day is mapped to a number as follows:

Day | Day Number
----|-----
Monday | 1 
Tuesday | 2
Wednesday | 3
Thursday | 4
Friday | 5
Saturday | 6
Sunday | 7   

Also, the node "restriction" needs a nested node "numbers" which content indicates the restricted last numbers of the plates separated by commas.

Example for adding a new day restriction on Saturday for plates ending in 1,3,5

```xml
<restriction dayNumber="6">
    <numbers>1,3,5</numbers>
</restriction>
```

Finally, if you modify the configuration file, you should also update the test case(s) affected by the change to avoid build failure due to test failures.

## Technical Information

The project was developed using Java 8, Junit 4 for the unit tests and Maven for build automation.

Frameworks: 
* Backend: Spring boot
* Frontend: Thymeleaf, Bootstrap

## Extras
You can find the flow chart of the program on the following url: **https://bit.ly/2wZqMTb**

