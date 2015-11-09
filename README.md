
# **What is ArduinoTestingTool?**

The idea for this project comes from my need to have the opportunity to testing Arduino shield
which was composed of different sensors without having to handle each components. For convenience, you can imagine testing a new car. You just  have to Insert the cable and perform diagnosis of a car condition.

# **Android and Java usage**

You can run Java SE Embedded or Java ME on a Raspberry Pi, ARM Cortex- A57 and others, but the Arduino is a bit too constrained to run Java directly. However, with the help of serial port communication software, you can communicate with and control an Arduino from Java running on another computer. Although the code to do so has been published on the Arduino site.

We have said above, that  You can not execute Java code on the Arduino, so our task is to realize communication between the Arduino board and testing platform. In other words, we need pre-written and compiled C / C ++ test program that will load in our Arduino. All communication to keep the current program in Arduino, if any, charging the test program and the work itself testing is done with the help of a special protocol, which provides the opportunity for communication between the two platforms.
