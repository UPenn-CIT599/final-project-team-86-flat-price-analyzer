# final-project-team-86-flat-price-analyzer

## Team mates: 

Jiamin Lua - jmlua@seas.upenn.edu

Jia Xun Paw - pawjx93@seas.upenn.edu 

Yixin Miao - yixinm@seas.upenn.edu 

## Project Proposal:
The aim of this app is to analyse how property prices have changed in Singapore over the past 15 years so users looking to purchase a flat in Singapore can be better informed when making their decision.

We have 3 main sections:

Firstly, the Property Index tab. Here, you can see how housing prices have changed since 2005. 

You can further break down the data into the different flat types - 3 room, 4 room, or 5 room.

We have also crunched the data to provide information about the average and median prices of each flat type over the years in the sidebar.

Secondly, we also break down the numbers and show how flat prices differ across different areas in Singapore. In particular, flat prices in the coveted Central District can be up to twice that of a flat elsewhere.

Users can use the dropdown list to access the list of towns in Singapore and examine the changes in prices in each town across the years.

Lastly, we also have a mortgage calculator so users can find out the estimated monthly installments they’d have to pay on their flat. 


## Set up

1) Go to Releases
2) Download MyProperty.jar into your main user folder
3) Go into command line to run the jar file
```sh
java -jar MyProperty.jar 
```
 
## Class designs and Interactions:

**PropertyReader**:
  - Responsibilities
    - Read CSV files from data.gov
    - Generates PropertyData arraylist that stores all information about individual flat listings
  - Collaborators
    - Property
    - PropertyData 

**Property**:
  - Responsibilities
    - Contains specific information about each property sale - eg year/ month of sale, number of rooms, location of flat, square feet, lease remaining and price of flat during resale
  - Collaborators
    - PropertyData 
    - PropertyReader
 
**PropertyData:**
  - Responsibilities
    - Stores all Property instances in an arrayList
    - Consists of a function to add individual Property instance
  - Collaborator
    - Property

**MathsAnalysis**:

  - Responsibilities
    - Import Math packages 
    - Compute answers after receiving queries from user input in widget
  - Collaborator
    - PropertyData
    
**ChartData**:
  - Responsibilities 
    - Output data points for plotting in the Widget class
  - Collaborators
    - PropertyData
    - MathAnalysis 

**LoanCalculator**:
  - Responsibilities 
    - Calculate monthly installment needed for a given loan amount
  - Collaborators
    

**CalculatorController**:
  - Responsibilities
    - Main controller for the GUI/widget class
    - Coordinates and listens for ActionEvents in the widget
  - Collaborators
    - ChartData
    - MathAnalysis
    - PropertyReader
    - PropertyData
    - Property
    - LoanCalculator

**Widget**:
  - Responsibilities
    - Displays the entire property price analyzer into a user-friendly GUI
  - Collaborators
    - CalculatorController

## Suggested workflow:
 
CSV files (Jan 2005 - current) → PropertyReader → Property + PropertyData (stores all listings in an arraylist) → User Input Parameters (under widget/ GUI) → MathAnalysis → ChartData → DataAnalyzer → CalculatorController → Widget


## Widget Screenshots:

![Landing](screengrabs/1_Landing.png?raw=true "Landing Page")
![Landing](screengrabs/2_PropertyPriceIndices.png?raw=true "Property Price Indices")
![Landing](screengrabs/3_LocationInsights.png?raw=true "Location Insights")
![Landing](screengrabs/4_Calculators.png?raw=true "Calculators")
![Landing](screengrabs/5_About.png?raw=true "About")
![Landing](screengrabs/6_Help.png?raw=true "Help")
