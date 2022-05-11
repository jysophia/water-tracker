# My Personal Project

## Sophia Kim CPSC 210

This is a water tracker, a centralized location where you can track how much
water you are drinking throughout the day. This tracker is suitable for
*anyone* who struggle to drink enough water in a day. Water helps maintain
body fluid balance, energize the body, manage calories, keep our kidneys
healthy, improve skin health, and more. As someone who habitually does not
drink enough water, I find this project meaningful and something I know
that many others will find useful in their daily lifestyle.

The water tracker will have the following **features**:

- Enter how much water you drank at any time of the day (date, amount drank)
- See how much water you drank at the end of the day in comparison to the recommended amount
- Compare the amount of water you drank on multiple days
- See all the entries you made on the application

User Stories:

- As a user, I want to be able to add the amount of water I drank on a certain day, multiple times a day (in mL)
- As a user, I want to be able to store multiple of these entries into a list of all entries
- As a user, I want to be able to see how much water I drank that day
- As a user, I want to be able to retroactively input an amount on a previous day
- As a user, I want to be able to compare how much I drank on different days
- As a user, I want to be prompted with the option to save my progress before I quit
- As a user, I want to be able to save my progress at any time
- As a user, I want to be able to reload my progress next time I return to the app

Phase 4 Task 3:
If I had more time, I would refactor to ensure that there was a class that all the JPanels extended because there
are some similarities between all the JPanels. This includes the enterButton, backButton, and returnToMainPage methods.
I would probably create a MyPanel class that included all the JPanel design and setup, and include those methods there.
This would allow for less separation between the classes and more consistency adn connection.