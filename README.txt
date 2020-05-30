
# Events System, version 0.0.1

  WEB application for creating internal events or after work meetings for employees. 
  Examples of internal events: trainings / presentations / workshops. It is designated to running in inside network (intranet).

  Used technologies:
  - back-end: Java EE 8 / Java 11
  - data base: MySQL 8.0 / SQL
  - front-end: HTML / CSS / Bootstrap 3.4

## Installation

  Configuration used during development:
  - Java 11
  - application server: Tomcat 9.0
  - data base server: MySQL 8.0 (time zone: UTC, configured by data base address in context.xml file)
  - data base workbench: MySQL 8.0
  - IDE: Eclipse 06.2019

## Start-up

  Eclipse: Project Explorer > left click on the project > right click on the project > Run as > Run on server > Chose an existing server > Tomcat > Finish

## Usage

  Users can create information about events or meetings as well as register for events created by other users.
  Application shows future events with availability on main page and past events in archives. Search is available for future events.
  Organiser can check how many users have registered for event. Events creation and registration for events require user registration. 

## To do

  - time zones support
  - logs
  - tests
  - documentation

## Author / Rights

  Remigiusz Skalski, all rights reserved
