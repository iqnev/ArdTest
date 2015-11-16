
# **What is ArduinoTestingTool?**

The idea for this project comes from my need to have the opportunity to testing Arduino shield
which was composed of different sensors without having to handle each components. For convenience, you can imagine testing a new car. You just  have to Insert the cable and perform diagnosis of a car condition.

# **Arduino and Java usage**

You can run Java SE Embedded or Java ME on a Raspberry Pi, ARM Cortex- A57 and others, but the Arduino is a bit too constrained to run Java directly. However, with the help of serial port communication software, you can communicate with and control an Arduino from Java running on another computer. Although the code to do so has been published on the Arduino site.

We have said above, that  You can not execute Java code on the Arduino, so our task is to realize communication between the Arduino board and testing platform. In other words, we need pre-written and compiled C / C ++ test program that will load in our Arduino. All communication to keep the current program in Arduino, if any, charging the test program and the work itself testing is done with the help of a special protocol, which provides the opportunity for communication between the two platforms.


# The Architecture of the ArduinoTestingTool

The ArduinoTestingTool consists of a set of modules. Each module performs a clear and independent function. Modules can be instantiated by specific needs for working with them. According to the above mentioned considerations, our suggestion is to provide two design architectures and guidelines for work.

![Architecture model](https://github.com/iqnev/ArduinoTestingTool/blob/master/Wiki/AWS%20Design.png)

**1. Consumer Access Layer:**
The main functionality of the Consumer Access Layer is to provide an abstract representation of the mechanisms for working with ArduinoTestingTool. Each of them translates the commands from the consumer into specific application commands. In this adapter the programmer can implement any kind of mechanism for working:web page, mobile app or desktop app.

   **1.1. RESTfull:**
A functionality of the RESTfull is to provide a mechanism for most WEB and mobile apps. The amazing thing about the RESTfull is the fact that clients(browsers) or mobile apps can interact in complex ways without the client knowing anything about the Driver. The RESTfull works across HTTP and receives data in Json type.

 **1.2. Desktop GUI:** 
This is a basic mechanism for work with ArduinoTestingTool, which can run of the desktop PC or Laptop.

***

**2. Arduino management & control:** 
It is functional group, which implements the core functionalities of the testing stack and mechanism for handling all communication requests.

**2.1. Consumer API:** 
This interface is the link between Consumer Access Layer and Arduino Core. This API provides mechanism, which has functionality for backup of the testing history by all Arduino boards.

**2.2. Arduino Core:**
The main function of Arduino Core is to manage the connectivity between the boatd and the consumer and must accept the events and responses by boards. Also the Arduino Core accepts commands by Consumer Access Layer. Whenever a Consumer tries to access a resource via Consumer API, it forwards the requested command, received from Consumer API. Then Arduino Testing Stack in order to check if a testing program exists or doesn't exist. If a program doesn’t exist, the Arduino Core will arrange a message response with error code for the Consumer API. Otherwise the program Core will forward request to a board. 

**2.3. Arduino Testing Stack:**
In this component are stored all existed testing programs. Each user can upload their testing program and use it on the current Arduino board.

***

**3. Board Communication Layer:** 
Board Communication Layer is communication mechanism between the abstraction of Arduino Core
model and boards. This layer consists of 2 layers.
The first layer is formed of custom communication protocol. After having converted to the appropriate format, the resulting frame "gets up" on the appropriate transport protocol(second layer). Here may be included different transport protocols but all of them must be built on the abstraction of the OSI model.

**3.1. Board access API:**
This interface is an entry point for all commands by consumer. There are multiple potential data format for communication between the boards and Arduino Core. 
