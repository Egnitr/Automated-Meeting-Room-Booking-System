# Automated-Meeting-Room-Booking-System
Repository for CodeFury 2020
Startingup Project

1) From the application folder, import or clone the project and open it in IDE (SpringToolSuite here).

2) In database folder in repository there are two files namely:
	a) DDLSCRIPT.txt
	b) InsertScript.txt

3) Startup the derbyDb in system by going to bin folder in installed derby. Open terminal there and run "StartNetworkServer;". So, derby would be running at post 1527. (Note keep it running. Further no use of this terminal to run command)

4) Now open another cmd at same bin location and run "ij.bat;" command. So, this makes terminal ready to connect to derbyDb.

5) From DDLSCRIPT.txt run entire content(copy/paste) in this ij connected terminal. This would create Database named "RoomBooking2". You can see tables created using "show tables;" command.

6) Now same way to insert base data run entire content(copy/paste) of InsertScript.txt in that ij ternimal. So raw data is inserted in terminal.

7) This data contains following roles in Users table.

	a) Member data 
		userid: 2019101
		email: karan@gmail.com
	
	b) Manager data
		userid: 2019104
		email: poki@gmail.com

	c) Admin data
		usreid: 2019102
		email: kush@gmail.com

8) Using this userid and email you can login in system and run the functionalities defined according to their role.


9) By logging as Admin, you have right to insert users using json file which is kept in database folder having titled "sample.json"


10) The base url of our application is "http://localhost:8080/AutomatedBookingSystem/" 	   
