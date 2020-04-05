# final-project-team-86-flat-price-analyzer

## Team mates: 

Jiamin Lua - jmlua@seas.upenn.edu

Jia Xun Paw - pawjx93@seas.upenn.edu 

Yixin Miao - yixinm@seas.upenn.edu 

## Project Proposal:

Build a tool for appraising value of public residential properties using a data-driven approach. 

We hope that the tool will answer questions that users will have about a property that they are hoping to buy - for example, how much the median price of a property in a specific area differs from their asking price.

**Components of the project include**:

- To collect geospatial data of properties across Singapore, historical transactions and listing information.
- Informational: 
  - Display information about changing property value over the years in specific locations that can be selected by the users
- Property valuation
- Give users information about the Singapore real estate market, taking into consideration factors like: 
  - Location
  - Size of flat
  - Years of lease left -  Most housing in Singapore are available only on 99-year lease. A small proportion are freehold - can be owned in perpetuity
  - Number of rooms
 
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
    - Chart
    
**Chart**:
  - Responsibilities 
    - Plots data from PropertyData into easily accessible charts and graphs
    - Output will be used by widget
  - Collaborators
    - PropertyData
    - MathAnalysis 

**Widget**:
  - Responsibilities
    - Displays the entire property price analyzer into a user-friendly GUI
  - Collaborators
    - Chart
    - MathAnalysis
    - Property Reader

## Suggested workflow:
 
CSV files (Jan 2015 - current) → PropertyReader → Property + PropertyData (stores all listings in an arraylist) → User Input Parameters (under widget/ GUI) → MathAnalysis → Chart → Widget
