# NewsAgency Java Desktop Application #
## Overview ##
This is a desktop application made with JavaFX as front-end and Java as back-end with MySQL Database. 

[JavaFX](https://openjfx.io/ "JavaFX")

[mySQL](https://en.wikipedia.org/wiki/MySQL "MySQL")
### Languages Used ###
JAVA
## Prerequisites ##
* JDK(any version may suffice, preferably 16)
* Xampp and Run MySQL
* Any IDE
## What it does? ##
It helps any newspaper agency manage it's everyday operations efficiently through a desktop application that is easy to use. Now some of those everyday operations may include -
* Managing Newspapers available and their prices
* Managing hawkers Delivering NewsPapers
* Managing Hawker Details
* Managing Customer Details
* Managing Bills
## Install And Run Guide ##
### Step 1 ###
After getting this project into your machine, import the project into eclipse or any other ide that you have.
### Step 2 ###
Open the Dashboard package and run the main.java file in it.

![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/workspace.png )


## How It Works? ##

![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/DashBoard.PNG)


**NOTE** In the newest version, Before opening the app, you are also presented with a Login Page which was added for additional security of the application. So to access the app you need to enter username : admin and password as : admin

After running the Main.java file, this is what you are presented with. In this you will find different buttons leading to different dialog boxes each performing a unique task. We will now disuss each of these in detail i.e. what they do and how they do it.

## 1. Paper Master ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/PaperMaster.PNG)

This was specifically built for ease of management of papers available and their rates. From this tab, One can easily manage all the papers available for sale and their rates. All the data that is feeded into this goes to a table in mySQL from which the data can then be updated, removed and fetched. New papers can also be added by just a click of a button 'SAVE' . Fetching of data will occur automatically and the rates will also appear once a paper is selected from the comboBox. The comboBox was made editable so that new papers can be added by typing in their name.

## 2. Hawker Manager ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/HawkerManager.PNG)

Managing Hawkers has never been easier . From this tab, One can easily manage the hawkers working for them. By simply typing in or selecting the available names from the comboBox , all the details of that particular hawker can be fetched from the table , and any detail can also be changed easily. Also as we know one Hawker may not serve only one area but possibly several based on the requirement , therefore multiple areas can be selected from the area ComboBox (this is auto-updated and fetches values that were filled in paperMaster tab) and they will start appearing with , seperating them in textfield SelectedAreas. Name has been set as primary key in the database table so that it is easier for the admin to identify any hawker. An image of hawker can also be uploaded from local machine and during fetch operation, this image gets autoSet as the path of image gets saved in the database.

## 3. Customer Panel ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/CustomerPanel.PNG)

This tab was built was specific use of customers cum admin. Any new Customer can easily navigate through this tab and fill in his own details. The area comboBox is again pre-Filled from the database table and customer can simply select from the available choices. After selecting area , all the hawkers serving that area will get automatically filled into the Hawker ComboBox. In this Contact is set as primary key as some customers may have the same name but their numbers will surely be different. After that from the listView multiple papers can be selected at once or one at a time, which then appear in the Selected Paper listView , Selected Paper's prices will automatically reflect in the prices listview. For searching , Customer's mobile number is required as it has been set as primary key. Also the Selected papers as well as their prices will be stored as String seperated by , in the table which when required will be fetched , seperated using split method and displayed .

## 4. Bill Generator ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/BillGenerator.PNG)

This tab generates the bill that is to be paid by the customer. After typing in the customer Mobile number ( which was set as primary key) , Their corresponding Date of Start will be fetched from the table and after selecting the UptoDate, per day Price will appear in total Price textField. Clicking on generate Total bill will produce the amount payable by the customer. Note : this tab is only for generating total bill and saving that bill in table with status 0.
Status 0 : Means bill is not yet Paid.
Status 1 : Bill payed .

## 5. Bill Collector ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/BillCollector.PNG)

This tab will set the Status of the unpaid bill to 1 thus showing the bill has been paid. Bill will be fetched in the same way by typing in the Mobile number.

## 6. HawkerView Panel ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/HawkerViewPanel.PNG)

This tab was built so that all the details of all the hawkers can be viewed from one place . Results can be filtered using  area comboBox which will then make only those hawkers appear in TableView that are serving in the selected area.

## 7. Bill Table ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/BillStatus.PNG)

This tab wiill show all the bill details - Bills that have been paid(Status 1), Bills that are still due(Status 0).

## 8. Customer Table ##
![picture alt](https://github.com/PreshJindal/JavaProject/blob/master/snaps/CustomerViewer.PNG)

This tab shows details of all the cutomers in one place which can also be filtered using paper ComboBox and area ComboBox.

# To Do Things #
* ~~Fix minor Bugs~~
* ~~include a login page that appears before the DashBoard~~
* Include a .exe file for easy running of the application
