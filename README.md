To Run The Program

1. Unzip the zip file
   unzip ConferenceTrackManagement.zip
2. Go to unzip folder
   cd ConferenceTrackManagement/
3. Perform:   
   mvn clean install  (To run test cases as well)
4. Create another folder "Test"
   mkdir ~/Test
5. Copy ConferenceTrackManagement-0.0.1-jar-with-dependencies.jar to Test/
   cp target/ConferenceTrackManagement-0.0.1-jar-with-dependencies.jar ~/Test/
6. Copy conf folder to Test/
   cp -r conf ~/Test/
7. Copy IO folder to Test
   cp -r IO ~/Test/
8. To run the program
   cd ~/Test
   java -jar ConferenceTrackManagement-0.0.1-jar-with-dependencies.jar

-----------------------------------------------------------------------------------------------------------------------------
To Run Program Through Eclipse

1. Unzip the zip file
   unzip ConferenceTrackManagement.zip
2. Go to unzip folder
   cd ConferenceTrackManagement/
3. Perform:   
   mvn clean install
   mvn ecipse:eclipse
4. Open Eclipse and File -> Import -> Existing Maven Project -> ConferenceTrackManagement
5. Add conf and IO folder in build path
6. run com.amruta.entry.ConferenceTrackManagement java application

-----------------------------------------------------------------------------------------------------------------------------
To configure input

1. Go to ConferenceTrackManagement/IO folder and add talk details in "input" file

-----------------------------------------------------------------------------------------------------------------------------

To configure session 

1. Go to ConferenceTrackManagement/conf folder and add session details in "session.properties" in following format only
   
   e.g. 

   1_Morning=9:00 AM#12:00 PM
   2_Lunch=12:00 PM#1:00 PM
   3_Afternoon=1:00 PM#5:00 PM
   4_Networking=4:00 PM-5:00 PM#6:00 PM

   Assumptions:
   a. add sessions in sequential order and add sequence number before session name
   b. seperate start time and end time with "#"
   c. if start time is configurable like netowrking. seperate start times with "-"
   d. time format should be h:mm AM/PM 
   e. session timings should not change day. e.g 11:00 PM#1:30 AM where 11:00PM is of today's timing and 1:30AM is next days timing

------------------------------------------------------------------------------------------------------------------------------
To check the output

1. Go to ConferenceTrackManagement/IO folder and check "output" file

------------------------------------------------------------------------------------------------------------------------------ 
To check logs

1. Go to ConferenceTrackManagement/log folder and check "conference-track-mgmt.log" file

-----------------------------------------------------------------------------------------------------------------------------

