# Top 5 Web

Your assignment is to create a web application to render 'Top 5 Websites Ranking' report. 
Web functionality:
The report should clearly show the top 5 websites based on the selected date with their respective total visits column (see below for source data).
User should be able to change the date and the report will be updated based on the selected date.
Hostnames matching the exclusion list (see below) for a selected date must be excluded from the results
User must be authenticated to use the system. 
Data sourcing:
Application must watch the pre-configured folder in the system it’s running on. Once a csv file appears (see example attached), it should load the contents and delete the file. The contents of this file will replace all data from previous files, and used to drive the report. 
Exclusion list  should be regularly updated from the api:  http://private-16b96-mamtrialrankingadjustments3.apiary-mock.com/exclusions  (doc: http://docs.mamtrialrankingadjustments3.apiary.io/ )
Document how we would deploy your application, and how we would access your application if hosted online. 
Please push your source code to BitBucket or GitHub using GIT repository and give repository access to dpoznyak
Some of the requirements have been intentionally left open-ended. In real life you’re expected to clarify those, but for this assignment please make (and document) your best assumptions.
 Preferred technologies are:
.NET + ASP.NET MVC/WebAPI + Angular + MSSQL + Windows Server [preferred]
Java + Sprint + MySQL are also OK 

Your application will be judged based on the following considerations (all equally important):
Have you chosen an appropriate design and architecture?
Have all features been implemented?
How well you managed the requirements and assumptions
Are all features covered by tests?
Is the code easy to read and understand?
Will the code be relatively easy to maintain and extend?
Bonus points:
Come up with additional functionality (reports, parameters etc)
Make your application available online. (Highly preferred).
List down other functional/nonfunctional improvements you wish to add on top your existing application.